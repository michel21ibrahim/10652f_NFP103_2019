package server_side;


import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;


// the Server class
public class MultiThread {
	// The server socket.
	private static ServerSocket serverSocket = null;
	// The client socket.
	private static Socket clientSocket = null;

	private static DataInputStream is = null;
	private static PrintStream os = null;


	// This chat server can accept up to maxClientsCount clients' connections.
	private static final int maxClientsCount = 20;
	private static final clientThread[] threads = new clientThread[maxClientsCount];

	@SuppressWarnings("deprecation")
	public static void main(String args[]) {

		// The default port number.
		int portNumber = 2222;
		if (args.length < 1) {
			System.out.println("Server start and  using port number=" + portNumber);
		} else { 
			portNumber = Integer.valueOf(args[0]).intValue();
		}


		// Open a server socket on the portNumber
		try {
			serverSocket = new ServerSocket(portNumber);

		} catch (IOException e) {
			System.out.println(e);
		}


		/*
		 * Create a client socket for each connection and pass it to a new client
		 * thread.
		 */
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				for (i = 0; i < maxClientsCount; i++) {

					if (threads[i] == null) {
						(threads[i] = new clientThread(clientSocket, threads)).start();
						break;
					}

				}

				if (threads[i] != null && i > 0) {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
					System.out.println("Press enter to continue or enter a command"); 
					String a = br.readLine(); 
					
					if (a.equals("_shutdown")){
						System.out.println("Server will be shutdown");
						System.exit(0);
					}
					if (a.equals("_kill")){
						int x = 0;
						int j =0;
						System.out.println("who you need to kill ?");
						for ( x=0;x <= i; x++) {
						System.out.println(threads[x].clientName);
					}
						System.out.println("==>"); 
						String b = br.readLine(); 		
						for ( j=0;j <= x; j++) {
						if (b.equals(threads[j].clientName.toString())){
						  clientThread client= new clientThread("_quit",clientSocket, threads,threads[j].clientName.toString());
						  client.exit();
							break;
						}
						}
					}
					
					if (a.equals("_who")){
						System.out.println("Server will be shutdown");
						System.exit(0);
					}					
					
					
					
					
				}




				if (i == maxClientsCount) {
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					os.println("Server too busy. Try later.");
					os.close();
					clientSocket.close();
				}

			} catch (IOException e) {
				System.out.println(e);
			}
		}





	}  
}
