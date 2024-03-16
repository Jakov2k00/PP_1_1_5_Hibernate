package jm.task.core.hibernate.dao;

import jm.task.core.hibernate.model.User;
import jm.task.core.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDaoHibernate {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    @SuppressWarnings("rawtypes")
    public void createUsersTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS `usertable` (" +
                " `id` int NOT NULL AUTO_INCREMENT," +
                " `name` varchar(32) NOT NULL," +
                " `lastName` varchar(32) NOT NULL," +
                " `age` tinyint NOT NULL," +
                " PRIMARY KEY (`id`)" +
                ") ENGINE = InnoDB DEFAULT CHARSET = utf8mb3";

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void dropUsersTable() {
        final String sql = "DROP TABLE IF EXISTS usertable";

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings({"deprecation", "unchecked"})
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createCriteria(User.class).list();
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void cleanUsersTable() {
        final String sql = "TRUNCATE TABLE usertable";

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
