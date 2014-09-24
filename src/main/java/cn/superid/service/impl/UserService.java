package cn.superid.service.impl;

import cn.superid.Constants;
import cn.superid.dao.Form.UserForm;
import cn.superid.dao.IAllianceMemberDao;
import cn.superid.dao.IPermissionsDao;
import cn.superid.dao.IPermissionsGroupDao;
import cn.superid.dao.IUserDao;;
import cn.superid.entity.AllianceMemberEntity;
import cn.superid.entity.PermissionsGroupEntity;
import cn.superid.entity.UserEntity;
import cn.superid.service.IUserService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by peng on 2014/9/17.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private PasswordHelper helper;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IAllianceMemberDao allianceMemberDao;
    @Autowired
    private IPermissionsGroupDao permissionsGroupDao;
    @Autowired
    private IPermissionsDao permissionsDao;
    @Override
    public Set<String> findPermissions(String username) {
        UserEntity user = findByUsername(username);
        AllianceMemberEntity am= allianceMemberDao.findByUserID(user.getId());
        PermissionsGroupEntity pg = permissionsGroupDao.findById(am.getGroupId());
        String permissionsUnion= new StringBuilder().append(pg.getPermissionsGroup()).append(Constants.SEPARATOR).append(am.getPrivileges()).toString();
        String[] permissions = permissionsUnion.split(Constants.SEPARATOR);
        List<String> pList = Arrays.asList(permissions);
        return  pList.stream()
                .map(permission -> permissionsDao.findPermissionById(permission))
                .map(permission->permission.replace(Constants.PERMISSION_ALLIANCE, String.valueOf(am.getAllianceId())))
                .collect(Collectors.toSet());
    }

    @Override
    public UserEntity createUser(UserForm uf) {
        UserEntity user = new UserEntity();
        user.setLocked(0);
        user.setPassword(uf.getPassword());
        user.setUsername(uf.getUsername());
        helper.encryptPassword(user);
        userDao.save(user);
        return  user;
    }

    @Override
    public UserEntity findByUsername(String username) {
       return  userDao.findByUsername(username);

    }
}
