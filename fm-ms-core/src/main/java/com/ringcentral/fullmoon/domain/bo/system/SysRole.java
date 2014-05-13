package com.ringcentral.fullmoon.domain.bo.system;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the SYS_GM_ROLE database table.
 */
@Entity
@Table(name = "SYS_ROLE")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private Long roleId;

    private String description;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "ROLE_TYPE")
    private String roleType;

    @Column(name = "DISABLE")
    private String disable;

    public SysRole() {
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return this.roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }
}