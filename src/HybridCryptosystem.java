/*
 * Author : David Dorneau
 * 11/11/2018
 * CIS_5371
 * Hybrid Crypto GUI
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class HybridCryptosystem {

	private JFrame frmHybridCryptosystem;
	private JPasswordField passwordField;
	JButton btnNewButton = new JButton("256 BIT");
	JButton btnNewButton_1 = new JButton("512 BIT");
	JButton btnNewButton_2 = new JButton("Enter");
	JButton btnEncrypt = new JButton("Encrypt");
	JButton btnDecrypt = new JButton("Decrypt");
	JTextPane textPane = new JTextPane();
	JTextArea textArea = new JTextArea();
	JTextArea textArea_1 = new JTextArea();
	JLabel lblTheOriginalText = new JLabel("The original text");
	
	int yourKeyLength;
	String yourPlainText;
	String Original3DesText;
	String yourSecretTripDesKey;
	String OriginalText;
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
		frmHybridCryptosystem.setBounds(100, 100, 812, 714);
		frmHybridCryptosystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHybridCryptosystem.getContentPane().setLayout(null);
		
		//buttons
		btnNewButton.setBounds(229, 41, 115, 29);
		frmHybridCryptosystem.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new keySizeBtnListener());
		
		btnNewButton_1.setBounds(406, 41, 115, 29);
		frmHybridCryptosystem.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new keySizeBtnListener());
		btnNewButton_2.setEnabled(false);
		
		btnNewButton_2.setBounds(429, 121, 69, 29);
		frmHybridCryptosystem.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new keySizeEnterBtnListener());
		btnEncrypt.setEnabled(false);
		
		btnEncrypt.setBounds(579, 242, 115, 29);
		frmHybridCryptosystem.getContentPane().add(btnEncrypt);
		btnEncrypt.addActionListener(new EncryptBtnListener());
		
		btnDecrypt.setEnabled(false);
		btnDecrypt.setBounds(579, 400, 115, 29);
		frmHybridCryptosystem.getContentPane().add(btnDecrypt);
		btnDecrypt.addActionListener(new DecryptBtnListener());
		
		JButton btnReset = new JButton("RESET");
		btnReset.setBounds(579, 613, 115, 29);
		frmHybridCryptosystem.getContentPane().add(btnReset);
		btnReset.addActionListener(new ResetBtnListener());
		
		
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
		
		
		textPane.setBounds(184, 198, 363, 79);
		frmHybridCryptosystem.getContentPane().add(textPane);
		
		JLabel lblNewLabel = new JLabel("Enter the text to be encrypted");
		lblNewLabel.setBounds(256, 166, 216, 20);
		frmHybridCryptosystem.getContentPane().add(lblNewLabel);
		
		JLabel lblDecryptTheText = new JLabel("Decrypt the text ---------->");
		lblDecryptTheText.setBounds(262, 400, 210, 20);
		frmHybridCryptosystem.getContentPane().add(lblDecryptTheText);
		
		textArea.setEditable(false);
		textArea.setBounds(184, 310, 363, 79);
		frmHybridCryptosystem.getContentPane().add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("The resulting ciphertext");
		lblNewLabel_1.setBounds(280, 287, 180, 20);
		frmHybridCryptosystem.getContentPane().add(lblNewLabel_1);
		
		textArea_1.setEditable(false);
		textArea_1.setBounds(184, 505, 363, 83);
		
		frmHybridCryptosystem.getContentPane().add(textArea_1);
		lblTheOriginalText.setBounds(299, 469, 132, 20);
		
		frmHybridCryptosystem.getContentPane().add(lblTheOriginalText);
		
		
		
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
				btnNewButton_2.setEnabled(true);


		        
		    }
		}
	 
	 private class ResetBtnListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
				
				main(null);
		        
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
			    	btnNewButton_2.setEnabled(false);
			    	btnEncrypt.setEnabled(true);
			    	
		    		
		    	}
		    	
		}
	 }
	 
	 private class EncryptBtnListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
				textPane.setEditable(false);
				btnEncrypt.setEnabled(false);
				btnDecrypt.setEnabled(true);
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
				
				textArea.setText(new String(yourElGamal3DesEncryptedText));
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
				
				myElGamalDecryption.setThePrivateKey(yourElGamalPrivateKey);
				
				//set the cipher text
				myElGamalDecryption.setCipherText(myTripleDesDecryption.getTheOriginalElGamalEncryptedText());
				
				//get decrypted text
				try {
					OriginalText = myElGamalDecryption.decryptThePlainText();
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
				
				textArea_1.setText(OriginalText);
				
		    	
		}
	 }
}
