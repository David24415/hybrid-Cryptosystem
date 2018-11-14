import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;


public class TripleDesDecryption {
	
	private
		SecretKey theSecretKey;
		byte [] theOriginalElGamalEncryptedText;
		byte [] theElGamal3DesEncryptedText;
		
		void decryptTheElGamal3DesEncryptedText() throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException
		{
			
			
			Cipher myCipher = Cipher.getInstance("DESede");
			

			//do the encryption and return as an array of bytes
			myCipher.init(Cipher.DECRYPT_MODE, theSecretKey);
			theOriginalElGamalEncryptedText = myCipher.doFinal(theElGamal3DesEncryptedText);
			
		}
		
		
		
		
		
		
		
	public
		
		//getters and setters
		byte[] getTheOriginalElGamalEncryptedText() {
			return theOriginalElGamalEncryptedText;
		}
	
		void setTheSecretKey(SecretKey aSecretKey) {
			theSecretKey = aSecretKey;
		}
		
		void setTheElGamal3DesEncryptedText(byte [] aElGamal3DesEncryptedText ) {
			theElGamal3DesEncryptedText = aElGamal3DesEncryptedText;
		}
		
		
		


}
