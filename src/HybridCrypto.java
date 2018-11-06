
/*
 * Authors : David Dorneau & Jammy Jean
 * 10/21/2018
 * CIS_5371
 * El Gamal  Implementation
 */

import java.security.*;
import java.util.Scanner;
import javax.crypto.*;
public class HybridCrypto{


	public static void main(String[] args) throws InvalidKeyException, 
											NoSuchAlgorithmException, 
											NoSuchPaddingException, 
											IllegalBlockSizeException, 
											BadPaddingException, 
											NoSuchProviderException 
	{
		// TODO Auto-generated method stub

		int yourKeyLength;
		String yourPlainText;
		String OriginalText;
		String yourSecretKey;
		
		Scanner myIn = new Scanner(System.in);
		// use this to store the cipher text created from the encryption
		byte [] yourCipherText;
		Key yourPrivateKey;

		// perform ElGamal Encryption, followed by 3DES encryption, then 
		// 3DES decryption followed by ElGamal decryption
		// create encryption objects
		ElGamalEncryption myEncryption = new ElGamalEncryption() ;
		TripleDesEncryption myTripleDesEncryption = new TripleDesEncryption();

		// create decryption objects
		ElGamalDecryption myDecryption = new ElGamalDecryption();
		TripleDesDecryption myTripleDesDecryption = new TripleDesDecryption();

		
		// get the key size from user
		do {

			System.out.println("please enter your key size(integer value), "
					+ "256 for a 256-byte key or 512 for a 512-byte key ");

			yourKeyLength = myIn.nextInt();

		}while(yourKeyLength != 256 && yourKeyLength != 512);

		myIn.nextLine();
		//get the plaintext from user
		System.out.println("please enter your plaintext");
		yourPlainText = myIn.nextLine();

		//set the plaintext and the key length
		myEncryption.setThePlainText(yourPlainText);
		myEncryption.setTheKeyLength(yourKeyLength);

		//perform the encryption
		//returns the private key needed to do the decryption
		yourPrivateKey = myEncryption.encryptThePlainText();

		//the cipher text
		yourCipherText = myEncryption.getTheCipherText();

		System.out.println("the resulting cipher text is:");
		System.out.println(new String(yourCipherText));

		
		
		myTripleDesEncryption.setTheOriginalElgamalEncryptedtxt(yourCipherText);
		
		System.out.println("please enter your secret key");
		yourSecretKey = myIn.next();
		myTripleDesEncryption.setTheSecretKey(yourSecretKey);
		myTripleDesEncryption.encryptTheElGamalEncryptedtxt();

		//continue here comment for David
		//key must be 128 bits for 3DES
		
		//do decryption and output plain text here 
		// set the the private Key
		myDecryption.setThePrivateKey(yourPrivateKey);
		
		//set the cipher text
		myDecryption.setCipherText(yourCipherText);
		
		//get decrypted text
		OriginalText = myDecryption.decryptThePlainText();
		System.out.println("the Original plain text is:");
		System.out.println(OriginalText);
		myIn.close();

	}

}
