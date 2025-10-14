package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.services.*;
import ma.projet.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // Garantir initialisation de Hibernate
        SessionFactory sf = HibernateUtil.getSessionFactory();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService etService = new EmployeTacheService();

        // 1) Créer quelques employés
        Employe e1 = new Employe("BEN", "Ali", "060000001");
        Employe e2 = new Employe("EL", "Hadi", "060000002");
        employeService.create(e1);
        employeService.create(e2);

        // 2) Créer projet et affecter chef de projet
        Projet p1 = new Projet("Gestion de stock", sdf.parse("14/01/2013"), sdf.parse("30/04/2013"));
        p1.setChefProjet(e1);
        projetService.create(p1);

        // 3) Créer taches rattachées au projet
        Tache t1 = new Tache("Analyse", sdf.parse("10/02/2013"), sdf.parse("20/02/2013"), 800.0);
        Tache t2 = new Tache("Conception", sdf.parse("10/03/2013"), sdf.parse("15/03/2013"), 1500.0);
        Tache t3 = new Tache("Développement", sdf.parse("10/04/2013"), sdf.parse("25/04/2013"), 2500.0);

        t1.setProjet(p1); t2.setProjet(p1); t3.setProjet(p1);
        tacheService.create(t1);
        tacheService.create(t2);
        tacheService.create(t3);

        // 4) Associer employés <-> tâches avec dates réelles
        // Important : après save, les ids sont générés, donc on récupère les entités avec leurs id
        t1 = tacheService.findById(t1.getId()); t2 = tacheService.findById(t2.getId()); t3 = tacheService.findById(t3.getId());
        e1 = employeService.findById(e1.getId()); e2 = employeService.findById(e2.getId());

        EmployeTache et1 = new EmployeTache();
        et1.setEmploye(e1); et1.setTache(t1); et1.setDateDebutReelle(sdf.parse("10/02/2013")); et1.setDateFinReelle(sdf.parse("20/02/2013"));
        etService.create(et1);

        EmployeTache et2 = new EmployeTache();
        et2.setEmploye(e1); et2.setTache(t2); et2.setDateDebutReelle(sdf.parse("10/03/2013")); et2.setDateFinReelle(sdf.parse("15/03/2013"));
        etService.create(et2);

        EmployeTache et3 = new EmployeTache();
        et3.setEmploye(e2); et3.setTache(t3); et3.setDateDebutReelle(sdf.parse("10/04/2013")); et3.setDateFinReelle(sdf.parse("25/04/2013"));
        etService.create(et3);

        // Tests demandés

        // - 3) Liste des tâches réalisées par un employé
        System.out.println("Taches réalisées par Employe e1:");
        List<EmployeTache> listEt = employeService.listerTachesRealiseesParEmploye(e1.getId());
        listEt.forEach(System.out::println);

        // - 4) Liste des projets gérés par un employé
        System.out.println("\nProjets gérés par e1:");
        projetService.findAll().forEach(System.out::println); // ou employeService.listerProjetsGerésParEmploye(e1.getId())

        // - 5) Liste taches planifiées pour un projet
        System.out.println("\nTaches planifiées pour projet p1:");
        tacheService.findAll().forEach(System.out::println);

        // - 6) Afficher tâches réalisées dans un projet avec détails réels
        System.out.println("\nTâches réalisées (détails réels) pour le projet:");
        List<?> rows = projetService.listerTachesRealiseesAvecDetailsReels(p1.getId());
        rows.forEach(row -> System.out.println(row));

        // - 7) Tâches prix > 1000
        System.out.println("\nTaches avec prix > 1000:");
        tacheService.trouverTachesPrixSuperieur(1000.0).forEach(System.out::println);

        // - 8) Tâches réalisées entre deux dates
        Date d1 = sdf.parse("01/03/2013");
        Date d2 = sdf.parse("30/04/2013");
        System.out.println("\nTâches réalisées entre " + d1 + " et " + d2);
        tacheService.trouverTachesRealiseesEntre(d1, d2).forEach(System.out::println);

        // Close SessionFactory on exit (optional)
        sf.close();
    }
}
