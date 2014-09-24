package cn.superid.dao.impl;

import cn.superid.dao.IAllianceDao;
import cn.superid.entity.AllianceEntity;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peng on 2014/9/17.
 */
@Component
public class AllianceDao extends BaseDAOSupport<AllianceEntity> implements IAllianceDao {
}
