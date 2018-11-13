import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;

public class HybridCryptosystem {

	private JFrame frmHybridCryptosystem;
	private JPasswordField passwordField;
	JButton btnNewButton = new JButton("256 BIT");
	JButton btnNewButton_1 = new JButton("512 BIT");
	JButton btnNewButton_2 = new JButton("Enter");
	JButton btnEncrypt = new JButton("Encrypt");
	JButton btnDecrypt = new JButton("Decrypt");
	JTextPane textPane = new JTextPane();
	
	int yourKeyLength;
	String yourPlainText;
	String Original3DesText;
	String yourSecretTripDesKey;
	// use this to store the cipher text created from the encryption
	byte [] yourElGamalCipherText;
	byte [] yourElGamal3DesEncryptedText;
	Key yourElGamalPrivateKey;
	SecretKey yourWrapped3DesSecretKey;

	
	
	// create encryption objects
	ElGamalEncryption myElGamalEncryption = new ElGamalEncryption() ;
	TripleDesEncryption myTripleDesEncryption = new TripleDesEncryption();

	// create decryption objects
	ElGamalDecryption myElGamalDecryption = new ElGamalDecryption();
	TripleDesDecryption myTripleDesDecryption = new TripleDesDecryption();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HybridCryptosystem window = new HybridCryptosystem();
					window.frmHybridCryptosystem.setVisible(true);
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
		frmHybridCryptosystem = new JFrame();
		frmHybridCryptosystem.setTitle("Hybrid Cryptosystem");
		frmHybridCryptosystem.setBounds(100, 100, 758, 575);
		frmHybridCryptosystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHybridCryptosystem.getContentPane().setLayout(null);
		
		//buttons
		btnNewButton.setBounds(229, 41, 115, 29);
		frmHybridCryptosystem.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new keySizeBtnListener());
		
		btnNewButton_1.setBounds(406, 41, 115, 29);
		frmHybridCryptosystem.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new keySizeBtnListener());
		
		btnNewButton_2.setBounds(429, 121, 69, 29);
		frmHybridCryptosystem.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new keySizeEnterBtnListener());
		
		btnEncrypt.setBounds(579, 242, 115, 29);
		frmHybridCryptosystem.getContentPane().add(btnEncrypt);
		btnEncrypt.addActionListener(new EncryptBtnListener());
		
		btnDecrypt.setEnabled(false);
		btnDecrypt.setBounds(579, 342, 115, 29);
		frmHybridCryptosystem.getContentPane().add(btnDecrypt);
		//buttons
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(229, 122, 202, 26);
		frmHybridCryptosystem.getContentPane().add(passwordField);
		
		
		JLabel keylbl = new JLabel("Enter your secret key (16 characters)");
		keylbl.setBounds(239, 85, 269, 20);
		frmHybridCryptosystem.getContentPane().add(keylbl);
		
		JLabel lblSelectYourKey = new JLabel("Please select your key size.");
		lblSelectYourKey.setBounds(293, 16, 202, 20);
		frmHybridCryptosystem.getContentPane().add(lblSelectYourKey);
		
		
		textPane.setBounds(184, 198, 363, 113);
		frmHybridCryptosystem.getContentPane().add(textPane);
		
		JLabel lblNewLabel = new JLabel("Enter the text to be encrypted");
		lblNewLabel.setBounds(256, 166, 216, 20);
		frmHybridCryptosystem.getContentPane().add(lblNewLabel);
		
		JLabel lblDecryptTheText = new JLabel("Decrypt the text ---------->");
		lblDecryptTheText.setBounds(266, 346, 210, 20);
		frmHybridCryptosystem.getContentPane().add(lblDecryptTheText);
		
		
	}

	
	 private class keySizeBtnListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == btnNewButton) {
					yourKeyLength = 256; //set 256 to int var
					
				}
				else {
					yourKeyLength = 512; //set 512 to int var
				}
				
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				passwordField.setEditable(true);


		        
		    }
		}
	 
	 private class keySizeEnterBtnListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
				
		    	yourSecretTripDesKey = new String(passwordField.getPassword());
		    	if(yourSecretTripDesKey.length() != 16) {
		    		JOptionPane.showMessageDialog(passwordField, 
                            "key must be 16 characters long", 
                            "Invalid key", 
                            JOptionPane.WARNING_MESSAGE);
		    	}
		    	else {
		    		
			    	passwordField.setEditable(false);
		    		
		    	}
		    	
		}
	 }
	 
	 private class EncryptBtnListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
				textPane.setEnabled(false);
				btnEncrypt.setEnabled(false);
				yourPlainText = new String (textPane.getText());
				
				//set the plaintext and the key length
				myElGamalEncryption.setThePlainText(yourPlainText);
				myElGamalEncryption.setTheKeyLength(yourKeyLength);

				//perform the ElGamal encryption
				//returns the private key needed to do the decryption
				try {
					yourElGamalPrivateKey = myElGamalEncryption.encryptThePlainText();
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchProviderException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//the el gamal cipher text
				yourElGamalCipherText = myElGamalEncryption.getTheCipherText();
				
				myTripleDesEncryption.setTheOriginalElgamalEncryptedtxt(yourElGamalCipherText);

				myTripleDesEncryption.setTheSecretKey(yourSecretTripDesKey);
				try {
					yourWrapped3DesSecretKey = myTripleDesEncryption.encryptTheElGamalEncryptedtxt();
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				yourElGamal3DesEncryptedText = myTripleDesEncryption.getTheEncryptedElGamal3DesEncryptedtxt();
				
				
				//print ciphertext new String(yourElGamal3DesEncryptedText)


		        
		    }
		}
	 
	 private class DecryptBtnListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		    	
				btnDecrypt.setEnabled(false);
				myTripleDesDecryption.setTheSecretKey(yourWrapped3DesSecretKey);
				myTripleDesDecryption.setTheElGamal3DesEncryptedText(yourElGamal3DesEncryptedText);
				try {
					myTripleDesDecryption.decryptTheElGamal3DesEncryptedText();
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//myTripleDesDecryption.getTheOriginalElGamalEncryptedText()
		    	
		}
	 }
	 
}
