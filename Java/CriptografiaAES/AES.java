
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AES {
    
    private byte[] key;
    private SecretKeySpec secretKey;
    private String encryptedString;
    private String decryptedString;

    public void clear() {
        this.key = null;
        this.secretKey = null;
        this.encryptedString = null;
        this.decryptedString = null;
    }
    
    public boolean encrypt(String strToEncrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
            setEncryptedString(
                    Base64.getEncoder().encodeToString(
                            cipher.doFinal(
                                    strToEncrypt.getBytes("UTF-8")
                            )
                    )
            );
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            return false;
        }
        return true;
    }

    public boolean decrypt(String strToDecrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
            setDecryptedString(
                    new String(
                            cipher.doFinal(
                                    Base64.getDecoder().decode(
                                            strToDecrypt
                                    )
                            )
                    )
            );
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            return false;
        }
        return true;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            this.key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            this.key = sha.digest(this.key);
            this.key = Arrays.copyOf(this.key, 16); // use only first 128 bit
            this.secretKey = new SecretKeySpec(this.key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
    }

    public SecretKeySpec getSecretKey() {
        return secretKey;
    }

    protected void setSecretKey(SecretKeySpec secretKey) {
        this.secretKey = secretKey;
    }

    public String getEncryptedString() {
        return encryptedString;
    }

    protected void setEncryptedString(String encryptedString) {
        this.encryptedString = encryptedString;
    }

    public String getDecryptedString() {
        return decryptedString;
    }

    protected void setDecryptedString(String decryptedString) {
        this.decryptedString = decryptedString;
    }
    
    public static void main(String args[]) {
        AES aes = new AES();
        final String strToEncrypt = "Teste";
        final String strPssword = "2b7e151628aed2a6";
        aes.setKey(strPssword);

        aes.encrypt(strToEncrypt.trim());

        System.out.println("String to Encrypt: " + strToEncrypt);
        System.out.println("Encrypted: " + aes.getEncryptedString());

        final String strToDecrypt = aes.getEncryptedString();
        aes.decrypt(strToDecrypt.trim());

        System.out.println("String To Decrypt : " + strToDecrypt);
        System.out.println("Decrypted : " + aes.getDecryptedString());
    }

}
