package server_side;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.plaf.FileChooserUI;

import client_side.ChatFrame;





// For every client's connection we call this class
public class clientThread extends Thread{
	public  String clientName = null;
	private DataInputStream is = null;
	private PrintStream os = null;
	private Socket clientSocket = null;
	private  clientThread[] threads;
	private int maxClientsCount; 
	String message="";
	String username="";
	String filename="";
	ArrayList<String> messages = new ArrayList<String>();


	public clientThread(String message,Socket clientSocket, clientThread[] threads,String username) {
		this.message=message;
		this.clientSocket = clientSocket;
		this.threads = threads;
		this.username=username;
	}


	public clientThread(Socket clientSocket, clientThread[] threads) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		maxClientsCount = threads.length;
	}




	public void run() {
		int maxClientsCount = this.maxClientsCount; 
		clientThread[] threads = this.threads;
		
		try {

			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());	

			String name = null;
			while (true) {
				os.println("1- Enter _connect to connect to server"); 
				String line = is.readLine();
				if (line.startsWith("_connect")) {
					os.println("Enter your name."); 
					name = is.readLine().trim();
					if (name.indexOf('@') == -1) {
						break;
					} else {
						os.println("The name should not contain '@' character.");
					}
				}						

			}

			/* Welcome the new the client. */
			os.println("Welcome " + name + " to our chat room.");
			os.println("--------------------------------------");
			os.println("1- Enter _quit to exist .");
			os.println("2- Enter _who to see users connected .");
			os.println("3- Chat peer-to-peer --> you must specific the name of the user before you start by @ . \n(exemple : @BOB hi how are you ?) .");
			os.println("4- Enter _download after you see the path given by any user to download the documents on your desktop");
			synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] != null && threads[i] == this) {
						clientName = "@" + name;
						break;
					}
				}
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] != null && threads[i] != this) {
						threads[i].os.println("*** A new user " + name + " entered the chat room !!! ***");
					}
				}
			}


			/* Start the conversation. */
			while (true) {				
				String line = is.readLine();

				if (line.startsWith("_quit")) {
					break;
				}


				if (line.startsWith("_download")) {
					synchronized (this) {						
						
					/*	Path FILE_TO_RECEIVED = null  ;
	    			   	FILE_TO_RECEIVED = Paths.get("url appear in console");
						String PATH = "c:/Users/User/Desktop/Sharing-Folder/";
						File directory = new File(PATH);
						if (! directory.exists()){
							directory.mkdir();
						}
						// receive file
						File file = new File(PATH + FILE_TO_RECEIVED.getFileName());
						try {
							FileReader fr = new FileReader(FILE_TO_RECEIVED.toString());
							BufferedReader br = new BufferedReader(fr);
							FileWriter fw = new FileWriter(file, true);
							String s;

							while ((s = br.readLine()) != null) { // read a line
								fw.write(s); // write to output file
								fw.flush();
							}
							br.close();
							fw.close();
							this.os.println("***** File downloaded!! *******");
						} catch (IOException e) {
							e.printStackTrace();
						}
						*/	
				}		    
				}			

				if (line.startsWith("_who")) {
					// i use -1 to know the count of clients thread . Thread.activeCount return all thread with server and client. 
					int count = Thread.activeCount() -1;
					os.println( "Total of clients connected is : " + count);
					synchronized (this) {
						for (int i = 0; i < maxClientsCount; i++) {
							if (threads[i] != null && threads[i].clientName != null) {
								this.os.println(threads[i].clientName);
							}	
						}
					}
				}

				/* If the message is private sent it to the given client. */
				if (line.startsWith("@")) {
					String[] words = line.split("\\s", 2);
					if (words.length > 1 && words[1] != null) {
						words[1] = words[1].trim();
						if (!words[1].isEmpty()) {
							synchronized (this) {
								for (int i = 0; i < maxClientsCount; i++) {
									if (threads[i] != null && threads[i] != this
											&& threads[i].clientName != null
											&& threads[i].clientName.equals(words[0])) {
										threads[i].os.println(name + " : " + words[1]);
										/*
										 * Echo this message to let the client know the private
										 * message was sent.
										 */
										this.os.println( name + " : " + words[1]);
										break;
									}
								}
							}
						}
					}
				} else {
					/* The message is public, broadcast it to all other clients. */
					synchronized (this) {

						for (int i = 0; i < maxClientsCount; i++) {
							if (threads[i] != null && threads[i].clientName != null && !line.equals("_who") ) {
								threads[i].os.println(name + " : " + line);
								messages.add(line);
							}
						}

					}
				}
			}
			synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] != null && threads[i] != this && threads[i].clientName != null  ) {
						threads[i].os.println("*** The user " + name + " is leaving the chat room !!! ***");
					}
				}
			}




			os.println("*** Bye " + name + " ***");
			/*
			 * Clean up. Set the current thread variable to null so that a new client
			 * could be accepted by the server.
			 */
			synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] == this) {
						threads[i] = null;
					}
				}
			}
			/*
			 * Close the output stream, close the input stream, close the socket.
			 */
			is.close();
			os.close();
			clientSocket.close();
		} catch (IOException e) {
		}
	}

	@SuppressWarnings("deprecation")
	public void exit(){
		clientThread[] threads = this.threads;
		try {

			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());
			if (message.equals("_quit")){
				for (int i = 0; i < threads.length; i++) {
					if (threads[i] != null  && !threads[i].clientName.equals(username)) {
						threads[i].os.println("*** The user " + username + " is leaving the chat room !!! ***");
					}
				}		

				for (int i = 0; i < threads.length; i++) {
					if (threads[i] != null  && threads[i].clientName.equals(username)) {
						threads[i].os.println("*** Bye " + username + " ***");	
						synchronized (this) {
							threads[i].is.close();
							threads[i].os.close();
							threads[i].clientSocket.close();
							threads[i].stop();
							threads[i] = null;
						}	

					}
				}

			}
		} catch (IOException e) {
		}
	}
}