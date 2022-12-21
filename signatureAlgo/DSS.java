package signatureAlgo;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Scanner;

public class DSS{
    public Scanner sc;
    public KeyPair kp;
    public KeyPairGenerator kg;
    public SecureRandom sr;
    
    public DSS() throws Exception{
        sc = new Scanner(System.in);
        kg = KeyPairGenerator.getInstance("DSA");
        sr = new SecureRandom();
        kg.initialize(2048 , sr);
        kp = kg.genKeyPair();
    }

    public byte[] signInput(byte []inp) throws Exception{
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(kp.getPrivate());
        sign.update(inp);
        return sign.sign();
    }

    public boolean verif(byte []inp, byte []s) throws Exception{
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initVerify(kp.getPublic());
        sign.update(inp);
        return sign.verify(s);
    }

    public static void main(String[] args) throws Exception{
        DSS dss = new DSS();
        String name = "Manoj";
        byte[] sign = dss.signInput(name.getBytes());
        System.out.println(dss.verif(name.getBytes(), sign));
    }
}