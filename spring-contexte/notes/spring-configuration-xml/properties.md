# [properties](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-xml/index.md)

## Configuration des fichiers XML par par avec un fichier de properties
- créez un fichier properties à la racine de votre répertoire spring


    # Format : clef=valeur
    # Information pour la premiere adresse
    addr1.codePostal=75000
    addr1.ville=Paris
    addr1.pays=France
    addr1.rue=14 rue de la gloire
    # Information pour la seconde adresse
    ...

## Configuration des fichiers XML par par avec un fichier de properties
- charger le fichier de properties via la classe Spring PropertyPlaceholderConfigurer
    
    <!-- PROPERTIES -->
    <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="location" value="spring/adresses.properties"/>
    </bean>
