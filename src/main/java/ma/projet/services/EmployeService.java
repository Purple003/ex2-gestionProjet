package ma.projet.services;

import ma.projet.dao.IDao;
import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeService implements IDao<Employe, Integer> {

    @Override
    public Employe create(Employe e) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(e);
        tx.commit();
        s.close();
        return e;
    }

    @Override
    public Employe update(Employe e) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.update(e);
        tx.commit();
        s.close();
        return e;
    }

    @Override
    public void delete(Integer id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Employe e = s.get(Employe.class, id);
        if (e != null) s.delete(e);
        tx.commit();
        s.close();
    }

    @Override
    public Employe findById(Integer id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Employe e = s.get(Employe.class, id);
        s.close();
        return e;
    }

    @Override
    public List<Employe> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Employe> list = s.createQuery("FROM Employe", Employe.class).list();
        s.close();
        return list;
    }

    // 3) Méthode: lister les tâches réalisées par un employé
    public List<EmployeTache> listerTachesRealiseesParEmploye(Integer employeId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<EmployeTache> q = s.createQuery("FROM EmployeTache et WHERE et.employe.id = :id", EmployeTache.class);
        q.setParameter("id", employeId);
        List<EmployeTache> res = q.list();
        s.close();
        return res;
    }

    // 4) Méthode: lister les projets gérés par un employé (chef de projet)
    public List<?> listerProjetsGerésParEmploye(Integer employeId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<?> q = s.createQuery("FROM Projet p WHERE p.chefProjet.id = :id");
        q.setParameter("id", employeId);
        List<?> res = q.list();
        s.close();
        return res;
    }
}
