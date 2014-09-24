package cn.superid.dao.impl;

import cn.superid.dao.IUserDao;
import cn.superid.entity.UserEntity;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by peng on 2014/9/17.
 */
@Component
public class UserDao extends BaseDAOSupport<UserEntity> implements IUserDao {
    @Override
    public UserEntity findByUsername(String username) {
        List<UserEntity> userList = findByCriteria(Restrictions.eq("username",username));
        return userList.size()>0?userList.get(0):null;
    }
}
