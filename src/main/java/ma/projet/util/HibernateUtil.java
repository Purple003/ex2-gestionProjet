package ma.projet.util;

import java.io.InputStream;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            Properties props = new Properties();
            try (InputStream in = HibernateUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
                props.load(in);
            }
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .applySetting("hibernate.connection.url", props.getProperty("db.url"))
                .applySetting("hibernate.connection.username", props.getProperty("db.user"))
                .applySetting("hibernate.connection.password", props.getProperty("db.password"))
                .applySetting("hibernate.dialect", props.getProperty("hibernate.dialect"))
                .applySetting("hibernate.hbm2ddl.auto", props.getProperty("hibernate.hbm2ddl.auto"))
                .applySetting("hibernate.show_sql", props.getProperty("hibernate.show_sql"))
                .applySetting("hibernate.format_sql", props.getProperty("hibernate.format_sql"))
                .build();

            MetadataSources sources = new MetadataSources(registry)
                .addPackage("ma.projet.classes")
                .addAnnotatedClass(Class.forName("ma.projet.classes.Employe"))
                .addAnnotatedClass(Class.forName("ma.projet.classes.Projet"))
                .addAnnotatedClass(Class.forName("ma.projet.classes.Tache"))
                .addAnnotatedClass(Class.forName("ma.projet.classes.EmployeTache"))
                .addAnnotatedClass(Class.forName("ma.projet.classes.EmployeTacheId"));

            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
