package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void addNew(Role addedRole) {
        roleDao.postRole(addedRole);
    }

    @Transactional
    @Override
    public Optional<Role> getRole(String roleName) {
        return roleDao.findRoleByName(roleName);
    }

    @Transactional
    @Override
    public Iterable<Role> getAll() {
        return roleDao.getAllRoles();
    }

    @Transactional
    @Override
    public Role getRoleById(long id) {
        return roleDao.geetRoleById(id);
    }
}
