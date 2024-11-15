package tpsit.battaglia;
import java.math.BigInteger;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main { //Uguale a RSA.java ma utilizzo input e output

    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RSA rsa = new RSA();
        int lunghezza = 1024;

        rsa.generaChiavi(lunghezza); //genero le chiavi
        logger.info("Chiavi RSA generate.");

        logger.info("Inserisci il messaggio da cifrare: ");
        String s = scanner.nextLine();

        BigInteger messaggio = new BigInteger(s.getBytes());

        // Cifra
        BigInteger criptato = rsa.cifra(messaggio);
        logger.info("Messaggio cifrato: " + criptato);

        // Decifratura
        BigInteger decriptato = rsa.decifra(criptato);
        String messaggioDecriptato = new String(decriptato.toByteArray());
        logger.info("Messaggio decifrato: " + messaggioDecriptato);



        // Confronto
        // System.out.println("\n\nConfronto:");
        // System.out.println("Messaggio originale: " + s);
        // System.out.println("Messaggio decifrato: " + messaggioDecriptato);

        logger.info("Premi il tasto invio per uscire >> ");
        scanner.nextLine();
        scanner.close();
    }
}