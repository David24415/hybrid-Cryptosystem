
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
		byte [] yourElGamalCipherText;
		Key yourPrivateKey;

		// perform ElGamal Encryption, followed by 3DES encryption, then 
		// 3DES decryption followed by ElGamal decryption
		// create encryption objects
		ElGamalEncryption myElGamalEncryption = new ElGamalEncryption() ;
		TripleDesEncryption myTripleDesEncryption = new TripleDesEncryption();

		// create decryption objects
		ElGamalDecryption myElGamalDecryption = new ElGamalDecryption();
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
		myElGamalEncryption.setThePlainText(yourPlainText);
		myElGamalEncryption.setTheKeyLength(yourKeyLength);

		//perform the encryption
		//returns the private key needed to do the decryption
		yourPrivateKey = myElGamalEncryption.encryptThePlainText();

		//the cipher text
		yourElGamalCipherText = myElGamalEncryption.getTheCipherText();

		System.out.println("the resulting El Gamal cipher text is:");
		System.out.println(new String(yourElGamalCipherText));

		
		
		myTripleDesEncryption.setTheOriginalElgamalEncryptedtxt(yourElGamalCipherText);
		
		//accept only a secret key that is 128 bits | 16 characters long
		do
		{
			System.out.println("please enter your secret key (it must be 16 characters long");
			yourSecretKey = myIn.next();
			
		}while(yourSecretKey.length()!=16);
		
	
		myTripleDesEncryption.setTheSecretKey(yourSecretKey);
		myTripleDesEncryption.encryptTheElGamalEncryptedtxt();

		System.out.println("the 3DES and ElGamal ciphertext is " + 
				new String(myTripleDesEncryption.theEncryptedElGamalEncryptedtxt));
		
		//do decryption and output plain text here 
		// set the the private Key
		myElGamalDecryption.setThePrivateKey(yourPrivateKey);
		
		//set the cipher text
		myElGamalDecryption.setCipherText(yourElGamalCipherText);
		
		//get decrypted text
		OriginalText = myElGamalDecryption.decryptThePlainText();
		System.out.println("the Original plain text is:");
		System.out.println(OriginalText);
		myIn.close();

	}

}
