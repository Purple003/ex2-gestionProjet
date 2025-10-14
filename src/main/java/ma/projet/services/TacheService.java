package ma.projet.services;

import ma.projet.dao.IDao;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class TacheService implements IDao<Tache, Integer> {

    @Override
    public Tache create(Tache t) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(t);
        tx.commit();
        s.close();
        return t;
    }

    @Override
    public Tache update(Tache t) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.update(t);
        tx.commit();
        s.close();
        return t;
    }

    @Override
    public void delete(Integer id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Tache t = s.get(Tache.class, id);
        if (t != null) s.delete(t);
        tx.commit();
        s.close();
    }

    @Override
    public Tache findById(Integer id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Tache t = s.get(Tache.class, id);
        s.close();
        return t;
    }

    @Override
    public List<Tache> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Tache> list = s.createQuery("FROM Tache", Tache.class).list();
        s.close();
        return list;
    }

    // 7) Méthode: tâches dont le prix > 1000 (requête nommée définie dans Tache)
    public List<Tache> trouverTachesPrixSuperieur(Double prix) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> q = s.createNamedQuery("Tache.findByPrixGreaterThan", Tache.class);
        q.setParameter("prix", prix);
        List<Tache> res = q.list();
        s.close();
        return res;
    }

    // 8) Méthode: tâches réalisées entre deux dates (basées sur dateFin réelle depuis EmployeTache)
    public List<?> trouverTachesRealiseesEntre(Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT et FROM EmployeTache et WHERE et.dateFinReelle BETWEEN :d1 AND :d2";
        Query<?> q = s.createQuery(hql);
        q.setParameter("d1", d1);
        q.setParameter("d2", d2);
        List<?> res = q.list();
        s.close();
        return res;
    }
}
