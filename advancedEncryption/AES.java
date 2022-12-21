package advancedEncryption;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AES {
    private SecureRandom sr;
    private SecretKey sk;
    private Cipher eCipher;
    private Cipher dCipher;
    private IvParameterSpec params ;

    public AES() throws Exception{
        sk = KeyGenerator.getInstance("AES").generateKey();
        sr = new SecureRandom();
        eCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        dCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte []iv = new byte[eCipher.getBlockSize()];
        sr.nextBytes(iv);
        params = new IvParameterSpec(iv);
        eCipher.init(Cipher.ENCRYPT_MODE, sk, params);
        dCipher.init(Cipher.DECRYPT_MODE, sk, params);
    }

    public String encrypt(String pt) throws Exception{
        byte [] bpt = pt.getBytes();
        byte [] et = eCipher.doFinal(bpt);
        return Base64.getEncoder().encodeToString(et);
    }

    public String decrypt(String et) throws Exception{
        byte [] bet = et.getBytes();
        byte [] pt = dCipher.doFinal(Base64.getDecoder().decode(bet));
        return new String(pt , "UTF8");
    }
    public static void main(String[] args) throws Exception{
        AES aes = new AES();
        System.out.println(aes.encrypt("Manoj"));
        System.out.println(aes.decrypt(aes.encrypt("Manoj")));
    }
}
