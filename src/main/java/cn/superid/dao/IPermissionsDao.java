package cn.superid.dao;

import cn.superid.entity.PermissionsEntity;

import java.security.Permission;

/**
 * Created by peng on 2014/9/17.
 */
public interface IPermissionsDao extends IBaseDAO<PermissionsEntity> {
    public String findPermissionById(String pid);
}
