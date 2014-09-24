package cn.superid.entity;

import javax.persistence.*;

/**
 * Created by peng on 2014/9/17.
 */
@Entity
@Table(name = "permissions_group", schema = "", catalog = "shiro")
public class PermissionsGroupEntity {
    private int id;
    private String name;
    private String permissionsGroup;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "permissionsGroup", nullable = false, insertable = true, updatable = true, length = 255)
    public String getPermissionsGroup() {
        return permissionsGroup;
    }

    public void setPermissionsGroup(String permissionsGroup) {
        this.permissionsGroup = permissionsGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionsGroupEntity that = (PermissionsGroupEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (permissionsGroup != null ? !permissionsGroup.equals(that.permissionsGroup) : that.permissionsGroup != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (permissionsGroup != null ? permissionsGroup.hashCode() : 0);
        return result;
    }
}
