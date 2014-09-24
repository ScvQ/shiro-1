package cn.superid.entity;

import javax.persistence.*;

/**
 * Created by peng on 2014/9/17.
 */
@Entity
@Table(name = "alliance_member", schema = "", catalog = "shiro")
public class AllianceMemberEntity {
    private int id;
    private int allianceId;
    private int userId;
    private int groupId;
    private String privileges;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "alliance_id", nullable = false, insertable = true, updatable = true)
    public int getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(int allianceId) {
        this.allianceId = allianceId;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "group_id", nullable = false, insertable = true, updatable = true)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "privileges", nullable = false, insertable = true, updatable = true, length = 255)
    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllianceMemberEntity that = (AllianceMemberEntity) o;

        if (allianceId != that.allianceId) return false;
        if (groupId != that.groupId) return false;
        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (privileges != null ? !privileges.equals(that.privileges) : that.privileges != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + allianceId;
        result = 31 * result + userId;
        result = 31 * result + groupId;
        result = 31 * result + (privileges != null ? privileges.hashCode() : 0);
        return result;
    }
}
