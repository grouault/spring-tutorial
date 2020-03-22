# [properties](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-xml/index.md)

## Configuration des fichiers XML avec un fichier de properties
1- créez un fichier properties à la racine de votre répertoire spring

    ## Format : clef=valeur
    ## Information pour la premiere adresse
    addr1.codePostal=75000
    addr1.ville=Paris
    addr1.pays=France
    addr1.rue=14 rue de la gloire
    ## Information pour la seconde adresse
    ...
 
 2- charger le fichier de properties via la classe Spring PropertyPlaceholderConfigurer
 
 
    <!-- PROPERTIES -->
    <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="location" value="spring/adresses.properties"/>
    </bean>
    
 3- modifiez la déclaration de vos beans adresses afin qu'elle face usage du contenu du fichier de properties    


    <!-- CLIENT 1 -->
    <bean id="adresse1" class="fr.exagone.beans.Adresse">
        <property name="rue" value="${addr1.rue}" />
        <property name="ville" value="${addr1.ville}"/>
        <property name="codePostal" value="${addr1.codePostal}" />
        <property name="pays" value="${addr1.pays}" />
    </bean>
