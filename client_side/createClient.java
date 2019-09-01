package client_side;

import java.util.Scanner;

public class createClient {

	@SuppressWarnings({ "static-access", "resource" })
	public static void main(String[] args) {
	    Scanner myObj = new Scanner(System.in);      		
		String question = "Enter the Server ip adress ";

	    System.out.println(question);

	    String ipserver = myObj.nextLine(); 
		
		String[] arguments = new String[] {ipserver};
		
		
		
		 
        new ChatClient().main(arguments);

	}

}
