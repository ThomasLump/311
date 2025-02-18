package web.dao;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import web.model.User;

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
    public User getUserByName(String name) {
        return entityManager.createQuery("from User where username = :name", User.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public void saveNewUser(User user) {
        System.out.println("persist " + user);
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        System.out.println("deleted ID " + id);
        entityManager.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
    }
}
