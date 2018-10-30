/*
 * Author : Jammy Jean
 * 10//2018
 * CIS_5371
 * ElGamal Implementation
 */


/*
 * http://www.java2s.com/Tutorial/Java/0490__Security/ElGamalexamplewithrandomkeygeneration.htm
 * use the final lines on this example to implement the decryption
 */

/*
 * link to add jar file to eclipse
 * https://www.edureka.co/community/4028/how-to-import-a-jar-file-in-eclipse
 */
import javax.crypto.*;
import java.security.*;

public class ElGamalDecryption {
	

	private
	//member variables
	Key theSecretKey;
	String thePlainText;
	byte [] theEncryptedText,
	theCipherText;
	String decryptThePlainText() throws NoSuchAlgorithmException, 
									NoSuchPaddingException, 
									InvalidKeyException, 
									IllegalBlockSizeException, 
									BadPaddingException, 
									NoSuchProviderException {

		//adding BC provider to support ELGamal encryption
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());


		//specifying the scheme type
		Cipher cipher = Cipher.getInstance("ElGamal/None/NoPadding","BC");

		//retrieving public and private keys
		Key privateKey = theSecretKey;

		//performing the encryption
		cipher.init(Cipher.DECRYPT_MODE,privateKey);
		theCipherText = cipher.doFinal(theEncryptedText);
		thePlainText = new String(theCipherText);
		// returning the private key for when the decryption needs to be done
		return thePlainText;

	}
	
	

	//getters and setter
	void setThePrivateKey(Key aSecretKey) {
		theSecretKey = aSecretKey;
	}

	void setCipherText(byte [] CipherText)
	{
		theEncryptedText = CipherText;
	}

}