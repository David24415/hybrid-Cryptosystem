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
		byte [] your3DesCipherText;
		Key yourElGamalPrivateKey;
		SecretKey yourWrapped3DesSecreyKey; 
		
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
			System.out.println("\nplease enter your secret key (it must be 16 characters long)");
			yourSecretTripDesKey = myIn.next();
			
		}while(yourSecretTripDesKey.length()!=16);
		
	
		myTripleDesEncryption.setTheSecretKey(yourSecretTripDesKey);
		yourWrapped3DesSecreyKey = myTripleDesEncryption.encryptTheElGamalEncryptedtxt();

		your3DesCipherText = myTripleDesEncryption.theEncryptedElGamalEncryptedtxt;
		System.out.println("the 3DES and ElGamal ciphertext is " + 
				new String(your3DesCipherText));
		
		
	 	
		
		
		
		//3DES decryption, set the key, text
		myTripleDesDecryption.setTheSecretKey(yourWrapped3DesSecreyKey);
		myTripleDesDecryption.setThe3DslElgamalDecryptedtxt(your3DesCipherText);
		myTripleDesDecryption.Decrypt3DesKey();
		
		//update the plain 3Des CipherText for ElGamal Decryption
		your3DesCipherText = myTripleDesDecryption.getTheDecrypted3Destxt();
		
		// set the the private Key
		myElGamalDecryption.setThePrivateKey(yourElGamalPrivateKey);
		
		//set the cipher text
		myElGamalDecryption.setCipherText(your3DesCipherText);
		
		//get decrypted text
		OriginalText = myElGamalDecryption.decryptThePlainText();
		System.out.println("\nthe Original plain text is:");
		System.out.println(OriginalText);
		myIn.close();

	}

}