import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class HybridCryptosystem {

	private JFrame frame;
	private JPasswordField passwordField;
	JButton btnNewButton = new JButton("256 BIT");
	JButton btnNewButton_1 = new JButton("512 BIT");

	String string;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HybridCryptosystem window = new HybridCryptosystem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HybridCryptosystem() {
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 758, 575);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//buttons
		btnNewButton.setBounds(208, 52, 115, 29);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new keySizeBtnListener());
		
		btnNewButton_1.setBounds(412, 52, 115, 29);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new keySizeBtnListener());

		
		JLabel lblNewLabel = new JLabel("Hybrid Cryptosystem");
		lblNewLabel.setBounds(281, 16, 189, 20);
		frame.getContentPane().add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(269, 117, 202, 26);
		frame.getContentPane().add(passwordField);
		
		JLabel keylbl = new JLabel("please enter your secret key");
		keylbl.setBounds(269, 86, 296, 20);
		frame.getContentPane().add(keylbl);
	}

	
	 private class keySizeBtnListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		    	//String text = passwordField.getPassword().toString();
				passwordField.setEditable(true);
				if(e.getSource() == btnNewButton) {
					System.out.println("left");
				}
				else {
					System.out.println("right");
				}
				
		        
		    }
		}
	
	
}
