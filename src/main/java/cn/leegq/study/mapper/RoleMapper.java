package cn.leegq.study.mapper;

import cn.leegq.study.model.SysRole;
import org.apache.ibatis.annotations.Select;

/**
 * @author Hale Li 2018/4/2
 */
public interface RoleMapper {

    @Select({"select id ,role_name roleName,enabled ,create_by createBy,create_time createTime" +
            " from sys_role where id=#{id}"})
    SysRole selectById(Long  id);


}
