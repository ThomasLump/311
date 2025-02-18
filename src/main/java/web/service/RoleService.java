package web.service;

import web.model.Role;

import java.util.Optional;

public interface RoleService {
    void addNew(Role addedRole);
    Optional<Role> getRole(String roleName);
    Iterable<Role> getAll();

    Role getRoleById(long id);
}
