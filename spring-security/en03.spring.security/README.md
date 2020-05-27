## Projet avec fonctionnalité REMEMBER-ME

### paramétrage en base de données
* REMEMBERME
* Authentificaiton

### REMEMBERME
* céation de la table  : persistent_logins
* création data-source

### AUTHENTIFICATION
user : admin
password: admin
password (sha-256): 8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
password (sha-256 / base64): jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=

user : guest
password: guest
password (sha-256): 84983c60f7daadc1cb8698621f802c0d9f9a3c3c295c810748fb048115c186ec
password (sha-256 / base64): hJg8YPfarcHLhphiH4AsDZ+aPDwpXIEHSPsEgRXBhuw=

### OBTENTION DES Encoding
import org.apache.shiro.crypto.hash.Sha256Hash;
return new Sha256Hash(password, salt, 1024).toBase64();
Maven
```
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-crypto-hash</artifactId>
            <version>1.4.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-core</artifactId>
            <version>1.4.0</version>
        </dependency>
```