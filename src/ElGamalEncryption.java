/*
 * Author : David Dorneau
 * 10/20/2018
 * CIS_5371
 * ElGamal Implementation
 */

import javax.crypto.*;
import java.security.*;


public class ElGamalEncryption {

	private
	//member variables
	String thePlainText;
	int theKeyLength;
	byte [] theCipherText,
	yourPlainTextInBytes;

	//member methods
	Key encryptThePlainText() throws NoSuchAlgorithmException, 
									NoSuchPaddingException, 
									InvalidKeyException, 
									IllegalBlockSizeException, 
									BadPaddingException, 
									NoSuchProviderException {
		
		//adding BC provider to support ELGamal encryption
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		yourPlainTextInBytes = thePlainText.getBytes();

		//specifying the scheme type
		Cipher cipher = Cipher.getInstance("ElGamal/None/NoPadding","BC");
		KeyPairGenerator myKeyGen = KeyPairGenerator.getInstance("ElGamal","BC");

		//generating random number
		SecureRandom myRandomNum = new SecureRandom();

		//initialize the keypair using the random number
		myKeyGen.initialize(theKeyLength, myRandomNum);
		KeyPair pair = myKeyGen.generateKeyPair();

		//retrieving public and private keys
		Key publicKey = pair.getPublic();
		Key privateKey = pair.getPrivate();

		//performing the encryption
		cipher.init(Cipher.ENCRYPT_MODE,publicKey,myRandomNum);
		theCipherText = cipher.doFinal(yourPlainTextInBytes);

		// returning the private key for when the decryption needs to be done
		return privateKey;

	}

	//getters and setter

	void setThePlainText(String aPlainText) {
		thePlainText = aPlainText;
	}

	void setTheKeyLength(int aKeylength) {
		theKeyLength = aKeylength;
	}

	byte [] getTheCipherText() {

		return theCipherText;
	}

}
