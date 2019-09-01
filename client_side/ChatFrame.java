package client_side;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class ChatFrame extends JFrame implements Observer {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2637950760235968544L;
	private JTextArea textArea;
    private JTextField inputTextField;
    private JButton sendButton;
    public JButton ChooseFile;
    public static String FILE_TO_SEND;

	private Chatting Chatting;

    public ChatFrame(Chatting Chatting) {
        this.Chatting = Chatting;
        Chatting.addObserver(this);
        buildGUI();
    }
    
    public ChatFrame() {
    
    }

    /** Builds the user interface */
    private void buildGUI() {
           	
     	textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        Box box = Box.createHorizontalBox();
        add(box, BorderLayout.SOUTH);
        inputTextField = new JTextField();
        sendButton = new JButton("Send");
        ChooseFile= new JButton("Choose File");
        box.add(inputTextField);
        box.add(sendButton);
        box.add(ChooseFile);

        // Action for the inputTextField and the goButton
        ActionListener sendListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = inputTextField.getText();
                if (str != null && str.trim().length() > 0)
                Chatting.send(str);
                inputTextField.selectAll();
                inputTextField.requestFocus();
                inputTextField.setText("");
            }
        };
        
        // Action for the inputTextField and the goButton
        ActionListener ChooseListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {               
                    JFileChooser fileChooser = new JFileChooser();
                    int returnVal = fileChooser.showOpenDialog(ChatFrame.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        FILE_TO_SEND = file.getPath();
                    
                        Chatting.sendFile(FILE_TO_SEND);
                    } else {
                    	System.out.println("Open command cancelled by user." );
                    }
                 }
            
        };        
        
        inputTextField.addActionListener(sendListener);
        sendButton.addActionListener(sendListener);
        ChooseFile.addActionListener(ChooseListener);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Chatting.close();
            }
        });
    }
 
    /** Updates the UI depending on the Object argument */
    public void update(Observable o, Object arg) {
        final Object finalArg = arg;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea.append(finalArg.toString());
                textArea.append("\n");
            }
        });
    }


}
