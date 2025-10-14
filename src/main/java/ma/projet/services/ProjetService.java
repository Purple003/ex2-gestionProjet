package ma.projet.services;

import ma.projet.dao.IDao;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProjetService implements IDao<Projet, Integer> {

    @Override
    public Projet create(Projet p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(p);
        tx.commit();
        s.close();
        return p;
    }

    @Override
    public Projet update(Projet p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.update(p);
        tx.commit();
        s.close();
        return p;
    }

    @Override
    public void delete(Integer id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Projet p = s.get(Projet.class, id);
        if (p != null) s.delete(p);
        tx.commit();
        s.close();
    }

    @Override
    public Projet findById(Integer id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Projet p = s.get(Projet.class, id);
        s.close();
        return p;
    }

    @Override
    public List<Projet> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Projet> list = s.createQuery("FROM Projet", Projet.class).list();
        s.close();
        return list;
    }

    // 5) Méthode: lister les tâches planifiées pour un projet
    public List<Tache> listerTachesPlanifieesPourProjet(Integer projetId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> q = s.createQuery("FROM Tache t WHERE t.projet.id = :id", Tache.class);
        q.setParameter("id", projetId);
        List<Tache> res = q.list();
        s.close();
        return res;
    }

    // 6) Méthode: afficher les tâches réalisées dans un projet avec détails réels
    // On va récupérer EmployeTache pour le projet (via tache.projet.id)
    public List<?> listerTachesRealiseesAvecDetailsReels(Integer projetId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT p.id, p.nom, p.dateDebut, et.tache.id, et.tache.nom, et.dateDebutReelle, et.dateFinReelle "
                   + "FROM EmployeTache et JOIN et.tache t JOIN t.projet p WHERE p.id = :pid";
        Query<?> q = s.createQuery(hql);
        q.setParameter("pid", projetId);
        List<?> res = q.list();
        s.close();
        return res;
    }
}
