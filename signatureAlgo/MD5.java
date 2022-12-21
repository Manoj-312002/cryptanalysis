package signatureAlgo;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
    public static void main(String[] args) throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String name = "Manoj";
        byte[] ib = md5.digest(name.getBytes());
        BigInteger iInt = new BigInteger(ib);
        System.out.println(iInt.toString(16));
    }
}
