package tpsit.battaglia;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RSA {
    private static final Logger logger = LogManager.getLogger(RSA.class);

    private BigInteger n, d, e;

    // Genera le chiavi
    public void generaChiavi(int lunghezza) {
        SecureRandom rand = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(lunghezza, rand);
        BigInteger q = BigInteger.probablePrime(lunghezza, rand);

        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); // Calcolo φ(n) = (p - 1) * (q - 1).

        e = BigInteger.valueOf(65537);
        d = e.modInverse(phi); // Calcolo l'inverso moltiplicativo di e modulo φ(n) cioè l'esponente privato d

        logger.info("Chiavi generate:");
        logger.info("Modulo n: " + n);
        logger.info("Esponente pubblico e: " + e);
        logger.info("Esponente privato d: " + d);
    }

    // Cifra
    public BigInteger cifra(BigInteger message) {
        return message.modPow(e, n); // Elevo il messaggio alla potenza e modulo n: c = m^e mod n.
    }

    // Decifra
    public BigInteger decifra(BigInteger ciphertext) {
        return ciphertext.modPow(d, n); // Elevo il messaggio cifrato alla potenza d modulo n: m = c^d mod n.
    }

    // public class Main { //TEST DEL PROGRAMMA
    //     public static void main(String[] args) {
    //         RSA rsa = new RSA();
    //         int lunghezza = 1024;
    
    //         rsa.generaChiavi(lunghezza); //genero le chiavi
    
    //         String s = "Testo in chiaro";
    //         BigInteger messaggio = new BigInteger(s.getBytes());
    
    //         // Cifra
    //         BigInteger criptato = rsa.cifra(messaggio);
    //         logger.info("Messaggio cifrato: " + criptato);
    
    //         // Decifratura
    //         BigInteger decriptato = rsa.decifra(criptato);
    //         String messaggioDecriptato = new String(decriptato.toByteArray());
    //         logger.info("Messaggio decifrato: " + messaggioDecriptato);
    //     }
    // }
    
}