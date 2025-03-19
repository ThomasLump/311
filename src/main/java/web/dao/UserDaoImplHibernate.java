package web.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImplHibernate implements UserDao {
    private final EntityManager entityManager;

    public UserDaoImplHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User u join fetch u.role", User.class).getResultList();
    }

    @Override
    public Optional<User> findUserByName(String name) {
        List<User> attemt = entityManager.createQuery("from User u join fetch u.role where u.username = :name ", User.class).setParameter("name", name).getResultList();
        return Optional.ofNullable(attemt.isEmpty() ? null : attemt.getFirst());
    }

    @Override
    public void updateUser(User user) {
        User persistUser = entityManager.createQuery("from User u join fetch u.role where u.id= :id",User.class).setParameter("id",user.getId()).getSingleResult();
        persistUser.setUsername(user.getUsername());
        persistUser.setPhone_number(user.getPhone_number());
        if (user.getRole()!=null) {
            persistUser.setRole(user.getRole());
        }
    }

    @Override
    public void saveNewUser(User user) {
        System.out.println("persist " + user);
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        System.out.println("🚨deleted ID " + id);
        entityManager.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
    }
}
