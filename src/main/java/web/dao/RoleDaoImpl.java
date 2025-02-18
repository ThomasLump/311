package web.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.thymeleaf.templateresolver.AbstractTemplateResolver;
import web.model.Role;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {
    EntityManager entityManager;
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

    @Override
    public Iterable<Role> getAllRoles() {
        return entityManager.createQuery("from Role",Role.class).getResultList();
    }

    @Override
    public Role geetRoleById(long id) {
        return entityManager.createQuery("from Role where id = :id",Role.class).setParameter("id", id).getSingleResult();
    }
}
