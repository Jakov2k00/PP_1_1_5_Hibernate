package jm.task.core.hibernate.service;

import jm.task.core.hibernate.dao.UserDaoHibernate;
import jm.task.core.hibernate.dao.UserDaoHibernateImpl;
import jm.task.core.hibernate.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoHibernate userDao = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем Ч " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = userDao.getAllUsers();
        System.out.println(usersList);
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
