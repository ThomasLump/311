package web.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import web.data.User;

@Repository
public class UserDaoHibernateImpl implements UserDao {
    private final EntityManager entityManager;

    public UserDaoHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @SuppressWarnings("unchecked")
    public Iterable<User> getAllUsers() {
        return (Iterable<User>) entityManager.createQuery("from User").getResultList();
    }

    @Override
    public void updateUser(User user) {
        System.out.println(user);
        entityManager.merge(user);

    }

    @Override
    public void saveNewUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        User deletedUser = entityManager.createQuery("from User where id = :id", User.class).setParameter("id", id).getSingleResult();
        entityManager.remove(deletedUser);
    }
}
