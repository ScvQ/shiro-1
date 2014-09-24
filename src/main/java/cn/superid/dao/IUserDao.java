package cn.superid.dao;

import cn.superid.entity.UserEntity;

/**
 * Created by peng on 2014/9/17.
 */
public interface IUserDao extends IBaseDAO<UserEntity> {
   public UserEntity findByUsername(String username);
}
