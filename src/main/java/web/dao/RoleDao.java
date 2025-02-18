package web.dao;

import web.model.Role;

import java.util.Optional;

public interface RoleDao {
    Optional<Role> findRoleByName(String name);
    void postRole(Role role);
    Iterable<Role> getAllRoles();

    Role geetRoleById(long id);
}
