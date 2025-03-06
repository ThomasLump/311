package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {
    void addNew(Role addedRole);
    Role getRole(String roleName);
    Role getRoleById(long id);
    List<Role> getAll();
}
