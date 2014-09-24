package cn.superid.service;

import cn.superid.dao.Form.UserForm;
import cn.superid.entity.UserEntity;
import java.util.Set;

/**
 * Created by peng on 2014/9/17.
 */
public interface IUserService {
    public UserEntity findByUsername(String username);
    public Set<String> findPermissions(String username );
    public UserEntity createUser(UserForm uf);
}
