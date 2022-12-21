package advancedEncryption;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger puk;
    private BigInteger prk;
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi;
    private SecureRandom sr;

    public RSA(int bitLength){
        sr = new SecureRandom();
        p = BigInteger.probablePrime(bitLength, sr);
        q = BigInteger.probablePrime(bitLength, sr);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        puk = new BigInteger(phi.bitLength() , sr);
        BigInteger e = new BigInteger(phi.bitLength() , sr);
        while( !e.gcd(phi).equals(BigInteger.ONE) ) e = new BigInteger(phi.bitLength() , sr);
        puk = e;
        prk = puk.modInverse(phi);
    }

    public BigInteger encrypt(String pt){
        byte [] bpt = pt.getBytes();
        BigInteger bipt = new BigInteger(bpt);
        return bipt.modPow(puk, n);
    }

    public String decrypt(BigInteger cpt){
        return new String(cpt.modPow(prk, n).toByteArray());
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(512);
        System.out.println(rsa.encrypt("Manoj").toString(16));
		System.out.println(rsa.decrypt(rsa.encrypt("Manoj")));
    }
}