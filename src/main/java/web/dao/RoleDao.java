package web.dao;

import web.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    void postRole(Role role);
    List<Role> getAllRoles();
    Optional<Role> findRoleByName(String name);
    Optional<Role> findRoleById(long id);
}
