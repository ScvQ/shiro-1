package cn.superid.dao.impl;

import cn.superid.dao.IPermissionsDao;
import cn.superid.entity.PermissionsEntity;
import cn.superid.entity.PermissionsGroupEntity;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by peng on 2014/9/17.
 */
@Component
public class PermissionsDao extends BaseDAOSupport<PermissionsEntity> implements IPermissionsDao {
    @Override
    public String findPermissionById(String p) {
        return  findById(Integer.parseInt(p)).getPermission();
    }
}
