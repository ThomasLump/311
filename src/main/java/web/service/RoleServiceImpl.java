package web.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void addNew(Role addedRole) {
        roleDao.postRole(addedRole);
    }

    @Override
    public Role getRole(String roleName) {
        Optional<Role> roleOptional = roleDao.findRoleByName(roleName);
        if(roleOptional.isPresent()) {
            return roleOptional.get();
        } else {
            throw new EntityNotFoundException("🙈 role with name: \'" + roleName + "\' is not found🥲");
        }
    }

    @Override
    public List<Role> getAll() {
        List<Role> roleList = roleDao.getAllRoles();
        if(roleList.isEmpty()) {
            throw new EntityNotFoundException("😱no roles at all");
        }
        return roleList;
    }

    @Override
    public Role getRoleById(long id) {
        Optional<Role> roleOptional = roleDao.findRoleById(id);
        if(roleOptional.isPresent()) {
            return roleOptional.get();
        } else {
            throw new EntityNotFoundException("🙈 role with id: \'" + id + "\' is not found🥲");
        }
    }
}
