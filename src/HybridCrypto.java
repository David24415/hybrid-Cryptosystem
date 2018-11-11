
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
/*
 *  The algorithm for this cryptosystem is as follows
 *  	1) encrypt the users text using ElGamal encryption
 *  	2) encrypt the resulting ciphertext from the ElGaml encryption using 3DES
 *  	3) decrypt the resulting ciphertext from the 3DES encryption using 3DES
 *  	4) decrypt the resulting ciphertext from the 3DES decryption using ElGamal
 *  	   to  obtain the original plaintext from the user.
 */

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
		String yourSecretTripDesKey;
		
		Scanner myIn = new Scanner(System.in);
		// use this to store the cipher text created from the encryption
		byte [] yourElGamalCipherText;
		Key yourElGamalPrivateKey;
		SecretKey yourWrapped3DesSecretKey;
		byte [] yourElGamal3DesEncryptedText;

		// perform ElGamal Encryption, followed by 3DES encryption, then 
		// 3DES decryption followed by ElGamal decryption
		// create encryption objects
		ElGamalEncryption myElGamalEncryption = new ElGamalEncryption() ;
		TripleDesEncryption myTripleDesEncryption = new TripleDesEncryption();

		// create decryption objects
		ElGamalDecryption myElGamalDecryption = new ElGamalDecryption();
		TripleDesDecryption myTripleDesDecryption = new TripleDesDecryption();

		
		// get the key size from user for the ElGamal encryption
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
		myElGamalEncryption.setThePlainText(yourPlainText);
		myElGamalEncryption.setTheKeyLength(yourKeyLength);

		//perform the ElGamal encryption
		//returns the private key needed to do the decryption
		yourElGamalPrivateKey = myElGamalEncryption.encryptThePlainText();

		//the cipher text
		yourElGamalCipherText = myElGamalEncryption.getTheCipherText();

		System.out.println("the resulting El Gamal cipher text is:");
		System.out.println(new String(yourElGamalCipherText));

		
		
		myTripleDesEncryption.setTheOriginalElgamalEncryptedtxt(yourElGamalCipherText);
		
		//accept only a secret key that is 128 bits | 16 characters long
		do
		{
			System.out.println("please enter your secret key (it must be 16 characters long)");
			yourSecretTripDesKey = myIn.next();
			
		}while(yourSecretTripDesKey.length()!=16);
		
	
		myTripleDesEncryption.setTheSecretKey(yourSecretTripDesKey);
		yourWrapped3DesSecretKey = myTripleDesEncryption.encryptTheElGamalEncryptedtxt();
		
		yourElGamal3DesEncryptedText = myTripleDesEncryption.getTheEncryptedElGamal3DesEncryptedtxt();
		
		System.out.println("the 3DES and ElGamal ciphertext is " + 
				new String(yourElGamal3DesEncryptedText));
		
		myTripleDesDecryption.setTheSecretKey(yourWrapped3DesSecretKey);
		myTripleDesDecryption.setTheElGamal3DesEncryptedText(yourElGamal3DesEncryptedText);
		myTripleDesDecryption.decryptTheElGamal3DesEncryptedText();
		System.out.println("the El Gamal ciphertext is " + 
				new String (myTripleDesDecryption.getTheOriginalElGamalEncryptedText()));
		
		
		
		
		// Jammy: to do ElGamal decryption followed by 3DES decryption here
		
		
		
		//do decryption and output plain text here 
		// set the the private Key
		myElGamalDecryption.setThePrivateKey(yourElGamalPrivateKey);
		
		//set the cipher text
		myElGamalDecryption.setCipherText(yourElGamalCipherText);
		
		//get decrypted text
		OriginalText = myElGamalDecryption.decryptThePlainText();
		System.out.println("the Original plain text is:");
		System.out.println(OriginalText);
		myIn.close();

	}

}
