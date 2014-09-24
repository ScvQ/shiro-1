package cn.superid.dao;

import cn.superid.entity.AllianceMemberEntity;

/**
 * Created by peng on 2014/9/17.
 */
public interface IAllianceMemberDao extends IBaseDAO<AllianceMemberEntity>{
    public AllianceMemberEntity findByUserID(int userId);
}
