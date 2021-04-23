package fr.exagone.spring.encryption;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class CryptoExemple {
	
    public static void main(String[] args) {
    	
    	// Password
        final String password = "opconn2021";  
        final String salt = "74e275c220bfe605";
        // final String salt = KeyGenerators.string().generateKey();
        
        // Définition d'un encryptor
        TextEncryptor encryptor = Encryptors.text(password, salt);      
        System.out.println("Salt: \"" + salt + "\"");
        
        // Text to encrypt
        String textToEncrypt = "*opconn*royal*secret*";
        System.out.println("Original text: \"" + textToEncrypt + "\"");
        
        String encryptedText = encryptor.encrypt(textToEncrypt);
        System.out.println("Encrypted text: \"" + encryptedText + "\"");
        
        // Could reuse encryptor but wanted to show reconstructing TextEncryptor
        TextEncryptor decryptor = Encryptors.text(password, salt);
        String decryptedText = decryptor.decrypt(encryptedText);
        System.out.println("Decrypted text: \"" + decryptedText + "\"");
        
        if(textToEncrypt.equals(decryptedText)) {
            System.out.println("Success: decrypted text matches");
        } else {
            System.out.println("Failed: decrypted text does not match");
        }       
    }
}
