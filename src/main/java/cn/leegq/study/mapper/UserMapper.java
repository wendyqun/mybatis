package cn.leegq.study.mapper;

import cn.leegq.study.model.SysRole;
import cn.leegq.study.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Hale Li 2018/4/2
 */
public interface UserMapper {

    public SysUser selectById(Long idd);

    public List<SysUser> selectAll();

    //只包含role自己的属性
    public List<SysRole> selectRolesByUserId(Long userId);

    //包含role自己的属性和部分user的属性
    public List<SysRole> selectRolesByUserId2(Long userId);

    //不返回主键
    public int insert(SysUser sysUser);

    //通过配置getGeneratedKeys返回主键
    public int insert2(SysUser sysUser);

    //通过配置<selectKey>返回主键
    public int insert3(SysUser sysUser);

    public int updateById(SysUser sysUser);

    public int deleteById(Long id);

    public List<SysRole> selectRoleByUserIdAndRoleEnabled(Long userId,Integer enabled);

    public List<SysRole> selectRoleByUserIdAndRoleEnabled2(@Param("userId")Long userId,@Param("enabled")Integer enabled);

    //测试 param 注解
    public List<SysRole> selectRoleByUserIdAndRoleEnabled3(@Param("user")SysUser user,@Param("role")SysRole role);
    //测试 param 注解
    public List<SysRole> selectRoleByUserIdAndRoleEnabled4(Map param);
    //测试if标签
    public List<SysUser> selectByUser(SysUser user);

    //测试if标签
    public int updateByIdSelective(SysUser user);

    //测试if标签 和 set 标签
    public int updateByIdSelective2(SysUser user);

    //测试 choose 标签
    public SysUser selectByIdOrUserName(SysUser user);

    //测试where 标签，省了where 1=1 ，更简洁
    public List<SysUser> selectByUser2(SysUser user);

    public List<SysUser> selectByIdList(@Param("ids") Long[] idArray);
    public List<SysUser> selectByIdList2(@Param("ids") List<Long> list);
    //foreach 实现批量插入
    public int insertList(List<SysUser> users);

    //foreach 实现动态update
    public int updateByMap(Map<String,Object> map);

    //sql一对一映射
    public SysUser selectUserAndRoleById(Long id);

    //使用了配置文件中resultMap的继承方式 一对一映射
    public SysUser selectUserAndRoleById2(Long id);

    //使用了association标签 一对一映射
    public SysUser selectUserAndRoleById3(Long id);

    //使用了association标签,使用resultMap属性引用另一个配置文件中的roleMap 一对一映射
    public SysUser selectUserAndRoleById4(Long id);

    //嵌套查询 发出两次sql查询
    public SysUser selectUserAndRoleById5(Long id);

    //嵌套查询,使用associaction的fetchType=lazy 发出两次sql查询
    public SysUser selectUserAndRoleById6(Long id);

    //一对多查询
    public List<SysUser> selectAllUserAndRoles();

    //一对多查询，用户-角色-权限 全部关联出
    public List<SysUser> selectAllUserAndRoles2();
}
