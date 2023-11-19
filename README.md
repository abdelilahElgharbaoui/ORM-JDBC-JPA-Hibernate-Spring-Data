# ORM-JDBC-JPA-Hibernate-Spring-Data
## introduction
Ce projet utilise IntelliJ IDEA Ultimate comme environnement de développement et Spring Initializer pour créer une application Spring Boot. L'application est configurée avec les dépendances JPA, H2, Spring Web et Lombok.

L'entité principale du projet est "Patient", représentée par une classe JPA avec des attributs tels que l'identifiant (id), le nom, la date de naissance, le statut de maladie, et le score.

L'unité de persistance est configurée pour utiliser la base de données H2 en mémoire, ce qui permet de stocker les informations des patients. Une interface JPA Repository est créée pour gérer les opérations CRUD (Create, Read, Update, Delete) sur les entités Patient.

Le projet propose des fonctionnalités telles que l'ajout de patients, la consultation de tous les patients, la consultation d'un patient spécifique, la recherche de patients, la mise à jour des informations d'un patient, et la suppression d'un patient.

De plus, une migration de la base de données H2 vers MySQL est réalisée pour montrer comment changer le backend de la base de données.

Enfin, le projet peut être étendu en ajoutant d'autres entités telles que Médecin, Rendez-vous, Consultation, Utilisateurs et Rôles pour créer un système plus complet de gestion de patients, médecins et rendez-vous, avec une gestion d'authentification et d'autorisation basée sur les utilisateurs et les rôles.

## architecture JPA
<img width="328" alt="architecture" src="https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/ff922ed1-7ea0-4bb5-9aa9-d3d557a8aa09">

## TP
### Enonce 
1. Installer IntelliJ Ultimate
2. Créer un projet Spring Initializer avec les dépendances:

Spring Data JPA
H2 Database (base de données en mémoire)
Spring Web
Lombok (Generation du code automatique)
MySQL Driver
Thymeleaf

4. Créer l'entité JPA Patient ayant les attributs :
       - id de type Long
       - nom de type String
       - dateNaissanec de type Date
       - malade de type boolean
       - score de type int
   ![Screenshot 2023-11-19 140651](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/2926b9d1-3e54-42ac-965b-77f5004bff2c)


   
6. Configurer l'unité de persistance dans le ficher application.properties
   
Ces propriétés de configuration indiquent que votre application Spring Boot utilise une base de données H2 en mémoire (jdbc:h2:mem:patient-db). De plus, la console H2 est activée (spring.h2.console.enabled=true) et votre application s'exécute sur le port 8081 (server.port=8081).
![Screenshot 2023-11-19 140834](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/0abf3560-4ecf-4a3b-bd40-cf5f8eedb65f)

8. Créer l'interface JPA Repository basée sur Spring data
    L'interface PatientRepository étend JpaRepository et définit plusieurs méthodes de requête personnalisées pour interagir avec l'entité Patient. Voici une explication de chacune des méthodes :

findByMalade(boolean m) :

Cette méthode utilise la convention de nommage de Spring Data JPA pour générer automatiquement une requête qui récupère une liste de patients en fonction de la valeur du champ malade.
Page<Patient> findByMalade(boolean m, Pageable pageable) :

Cette méthode est similaire à la précédente, mais elle prend également un objet Pageable en paramètre pour prendre en charge la pagination.
List<Patient> findByMaladeAndScoreLessThan(boolean m, int score) :

Cette méthode récupère une liste de patients en fonction de la valeur du champ malade et du score inférieur à une valeur spécifiée.
List<Patient> findByMaladeIsTrueAndScoreLessThan(int score) :

Cette méthode est similaire à la précédente, mais elle utilise une autre syntaxe pour spécifier que malade doit être vrai.
List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1, Date d2, String mc) :

Cette méthode utilise la convention de nommage pour générer automatiquement une requête basée sur les conditions fournies. Elle récupère une liste de patients dont la date de naissance est entre d1 et d2, qui sont malades ou dont le nom est similaire à mc.
List<Patient> chercherPatients(@Param("x") String nom, @Param("y") int scoreMin) :

Cette méthode utilise une requête JPQL personnalisée pour récupérer une liste de patients en fonction du nom et d'un score minimal spécifié. Les paramètres sont annotés avec @Param pour spécifier les noms de paramètres dans la requête.
![Screenshot 2023-11-19 141041](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/c1162338-a6cb-442d-8242-9774de78efc9)


10. Tester quelques opérations de gestion de patients :
    - Ajouter des patients
      ![Screenshot 2023-11-19 141302](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/09a28f12-c3e1-488c-a679-250249a7c776)

    - Consulter tous les patients
      ![Screenshot 2023-11-19 141411](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/87173751-4f23-4e74-857a-6ff4d37ec628)

    - Consulter un patient
      ![Screenshot 2023-11-19 141438](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/abe0c24d-8c39-4d05-bfe5-a26f7ef58ef3)

    - Chercher des patients
      ![Screenshot 2023-11-19 141509](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/624e0831-7616-4a98-8b19-de4793bc8436)

    - Mettre à jour un patient
      ![Screenshot 2023-11-19 141447](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/aeae1277-4fff-48d4-8d26-b639ad471dc9)

    - supprimer un patient
      ![Screenshot 2023-11-19 141459](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/be7ebf84-35e1-4c49-b057-a15d72ee7d1c)

      
11. Migrer de H2 Database vers MySQL
![Screenshot 2023-11-19 142854](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/def5f837-19ed-4ed2-ae11-e8683655e0a9)

![Screenshot 2023-11-19 142928](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/1f7d8018-f2a4-4967-a894-39397c1da320)

![Screenshot 2023-11-19 142911](https://github.com/abdelilahElgharbaoui/ORM-JDBC-JPA-Hibernate-Spring-Data/assets/87317250/70ccb2b8-cf2c-4c18-8a6a-acf575932740)

### Conclusion

En conclusion, ce projet offre une base solide pour le développement d'une application de gestion de patients. Les technologies Spring Boot, JPA, et MySQL fournissent des outils puissants pour la création d'applications robustes et évolutives. Cependant, il est crucial de prendre en compte les spécificités de la migration de la base de données et de s'assurer que toutes les fonctionnalités sont testées avec succès après cette transition.



