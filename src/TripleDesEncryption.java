import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/*
 * Author : David Dorneau
 * 11/3/2018
 * CIS_5371
 * 3DES Implementation
 */
public class TripleDesEncryption {
	private
	//member variables
	byte[] theOriginalElGamalEncryptedtxt;
	String yourSecretKey;
	
	byte[] theEncryptedElGamalEncryptedtxt;
		   

	public
	SecretKey encryptTheElGamalEncryptedtxt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		//specify encryption scheme, mode of operation & padding type
				/*
				 * we specify padding to ensure that we have the correct length of plaintext, 
				 * instead of checking for it during the time of user input. That way we fill each set of length 8 blocks.
				 */
		Cipher myCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		
		//convert the secret key string into Bytes
		byte[] yourSecretKeyInBytes = yourSecretKey.getBytes();
		
		//wrap the users key
		SecretKey secretKey = new SecretKeySpec(yourSecretKeyInBytes, "DESede");
		
		//do the encryption and return as an array of bytes
		myCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		theEncryptedElGamalEncryptedtxt = myCipher.doFinal(theOriginalElGamalEncryptedtxt);
		
		//return the secret key, so the decryption can be done afterwards
		return secretKey;


	}
	
	
	
	//getters and setters
	void setTheOriginalElgamalEncryptedtxt(byte [] aOriginalElGamalEncryptedtxt) {
		theOriginalElGamalEncryptedtxt = aOriginalElGamalEncryptedtxt;
	}
	
	void setTheSecretKey(String aSecretKey) {
		yourSecretKey = aSecretKey;
	}
	
	byte [] getTheEncryptedElGamalEncryptedtxt() {
		
		return theEncryptedElGamalEncryptedtxt;
	}

}
