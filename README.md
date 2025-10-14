<img width="385" height="290" alt="image" src="https://github.com/user-attachments/assets/3e55c2c2-9eff-4d1f-9b10-0e33a0582c62" />
<h4>1-Les quatre classes entités (Employe, Projet, Tache, EmployeTache) représentent les tables de la base de données.
Chacune contient les annotations JPA (@Entity, @Id, @ManyToOne, @OneToMany, etc.) pour la gestion automatique par Hibernate.</h4>
<p>2-Le fichier src/main/resources/application.properties:
Le fichier application.properties configure la connexion à la base MySQL et indique à Hibernate de créer automatiquement les tables au démarrage.</p>
<p>3-La classe HibernateUtil initialise la session Hibernate en chargeant la configuration depuis le fichier application.properties.</p>
<img width="273" height="156" alt="image" src="https://github.com/user-attachments/assets/88bec48e-5700-4870-b1b4-6325f4890036" />
<p>
  Ces classes implémentent l’interface IDao et contiennent des méthodes spécifiques pour interagir avec la base via Hibernate.
</p>
exécution et Résultats:
<p> Création automatique des tables</p>
<img width="747" height="451" alt="image" src="https://github.com/user-attachments/assets/1e674737-e759-43a7-93f7-7605833d1de5" />
<p> Insertion des données</p>
<img width="670" height="413" alt="image" src="https://github.com/user-attachments/assets/220ad286-0720-4c55-8c0e-c851af7703d6" />
<p>
Liste des tâches réalisées par un employé</p>
<img width="1160" height="303" alt="image" src="https://github.com/user-attachments/assets/a753a738-83f3-4340-80af-83ea6ace6c2a" />
<p>
Projets gérés par un employé</p>
<img width="802" height="365" alt="image" src="https://github.com/user-attachments/assets/9b3f1aeb-afd7-4297-a412-d4363ca8f57f" />
<p>
Tâches planifiées pour un projet</p>
<img width="599" height="258" alt="image" src="https://github.com/user-attachments/assets/fbe40930-b9fc-4a53-81f7-cfafc9fd6d4f" />
<p>
Tâches réalisées avec dates réelles</p>
<img width="704" height="150" alt="image" src="https://github.com/user-attachments/assets/22009d8e-eab2-452c-9129-677d621fd532" />
<p>
Tâches avec prix > 1000 DH</p>
<img width="562" height="98" alt="image" src="https://github.com/user-attachments/assets/3850ced4-6805-4974-92d1-b8c61beebf8f" />
<p>
Tâches réalisées entre deux dates</p>
<img width="1284" height="105" alt="image" src="https://github.com/user-attachments/assets/af0c9da9-2bdf-4aa3-874a-18743f3a6787" />

    
</p>
