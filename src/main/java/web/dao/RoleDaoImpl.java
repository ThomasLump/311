package web.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.thymeleaf.templateresolver.AbstractTemplateResolver;
import web.model.Role;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager, AbstractTemplateResolver abstractTemplateResolver) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        List<Role> attempt = entityManager.createQuery("from Role where name = :name",Role.class).setParameter("name", name).getResultList();
        return attempt.isEmpty() ? Optional.empty() : Optional.of(attempt.getFirst());
    }

    @Override
    public void postRole(Role role) {
        entityManager.merge(role);
    }

    /**
     * Получает список всех ролей из базы данных.
     * <p>
     * Метод выполняет SQL-запрос для выборки всех записей из таблицы "Role".
     * Если в базе данных отсутствуют роли, метод возвращает пустой список.
     *
     * @return Список всех ролей. Если роли отсутствуют, возвращается пустой список (но не null).
     */
    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role",Role.class).getResultList();
    }

    @Override
    public Optional<Role> findRoleById(long id) {
        List<Role> attempt = entityManager.createQuery("from Role where id = :id",Role.class).setParameter("id", id).getResultList();
        return Optional.ofNullable(attempt.isEmpty() ? null : attempt.getFirst());
    }
}
