package ma.projet.services;

import ma.projet.dao.IDao;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTacheId;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeTacheService implements IDao<EmployeTache, EmployeTacheId> {

    @Override
    public EmployeTache create(EmployeTache et) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(et);
        tx.commit();
        s.close();
        return et;
    }

    @Override
    public EmployeTache update(EmployeTache et) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.update(et);
        tx.commit();
        s.close();
        return et;
    }

    @Override
    public void delete(EmployeTacheId id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        EmployeTache et = s.get(EmployeTache.class, id);
        if (et != null) s.delete(et);
        tx.commit();
        s.close();
    }

    @Override
    public EmployeTache findById(EmployeTacheId id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        EmployeTache et = s.get(EmployeTache.class, id);
        s.close();
        return et;
    }

    @Override
    public List<EmployeTache> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<EmployeTache> list = s.createQuery("FROM EmployeTache", EmployeTache.class).list();
        s.close();
        return list;
    }
}
