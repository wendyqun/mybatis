package cn.leegq.study.model;

/**
 * @author Hale Li 2018/4/2
 */
public class SysRolePrivilege {
    private Long roleId;
    private Long privilegeId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }
}
