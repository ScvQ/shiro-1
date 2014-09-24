package cn.superid.dao.impl;

import cn.superid.dao.IAllianceMemberDao;
import cn.superid.entity.AllianceMemberEntity;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by peng on 2014/9/17.
 */
@Component
public class AllianceMemberDao extends BaseDAOSupport<AllianceMemberEntity> implements IAllianceMemberDao {
    @Override
    public AllianceMemberEntity findByUserID(int userId) {
        List<AllianceMemberEntity> list = findByCriteria(Restrictions.eq("userId", userId));
        return list.size()>0?list.get(0):null;
    }
}
