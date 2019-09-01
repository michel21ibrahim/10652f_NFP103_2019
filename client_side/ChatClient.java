package client_side;

import javax.swing.*;
import java.io.IOException;

// Class to manage Client chat Box.
public class ChatClient {
		
    public static void main(String[] args) {
        String server = args[0];
        int port =2222;
        Chatting access = new Chatting();

        JFrame frame = new ChatFrame(access);
        frame.setTitle("Whatsup lite - connected to " + server + ":" + port);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        try {
            access.InitSocket(server,port);
        } catch (IOException ex) {
            System.out.println("Cannot connect to " + server + ":" + port);
            ex.printStackTrace();
            System.exit(0);
        }
    }
}