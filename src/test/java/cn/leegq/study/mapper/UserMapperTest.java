package cn.leegq.study.mapper;

import cn.leegq.study.model.SysRole;
import cn.leegq.study.model.SysUser;
import junit.framework.Assert;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

/**
 * @author Hale Li 2018/4/2
 */
public class UserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectById(1l);
            Assert.assertNotNull(user);
            Assert.assertEquals("admin",user.getUserName());
        }catch (Exception e){

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysUser> users=userMapper.selectAll();
            Assert.assertNotNull(users);
            Assert.assertTrue(users.size()>0);
        }finally {
            sqlSession.close();
        }
    }

    //测试多表管理但是只返回单表中数据的情况
    @Test
    public void testSelectUserRole(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles=userMapper.selectRolesByUserId(1l);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size()>0);
        }finally {
            sqlSession.close();
        }
    }


    //测试多表管理但是只返回多表数据
    @Test
    public void testSelectUserRole2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles=userMapper.selectRolesByUserId2(1l);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size()>0);
        }finally {
            sqlSession.close();
        }
    }

    //不返回主键
    @Test
    public void testInsert(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=new SysUser();
            user.setUserEmail("lgq@test.com");
            user.setUserName("zhangsan");
            user.setUserPassword("11111");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result=userMapper.insert(user);
            Assert.assertEquals(1,result);
            Assert.assertNull(user.getId());
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    //返回主键，根据数据自增取到
    @Test
    public void testInsert2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=new SysUser();
            user.setUserEmail("lgq@test.com");
            user.setUserName("zhangsan");
            user.setUserPassword("11111");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result=userMapper.insert2(user);
            Assert.assertEquals(1,result);
            Assert.assertNotNull(user.getId());
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    //返回主键，根据<selectKey>
    @Test
    public void testInsert3(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=new SysUser();
            user.setUserEmail("lgq@test.com");
            user.setUserName("zhangsan");
            user.setUserPassword("11111");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result=userMapper.insert3(user);
            Assert.assertEquals(1,result);
            Assert.assertNotNull(user.getId());
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectById(1l);
            user.setUserName("admin_test");
            int res=userMapper.updateById(user);
            Assert.assertEquals(1,res);
            user=userMapper.selectById(1l);
            Assert.assertEquals("admin_test",user.getUserName());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectById(1l);
            Assert.assertNotNull(user);
            Assert.assertEquals(1, userMapper.deleteById(1l));
            Assert.assertNull(userMapper.selectById(1l));
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //注意方法中多个参数时，mapper中sql变量参数的配置方法（非注解方式）
    @Test
    public void testSelectRoleByUserIdAndRoleEnabled(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles=userMapper.selectRoleByUserIdAndRoleEnabled(1l,1);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size()>0);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //注意方法中多个参数时，mapper中sql变量参数的配置方法（注解方式,参数为简单类型）
    @Test
    public void testSelectRoleByUserIdAndRoleEnabled2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles=userMapper.selectRoleByUserIdAndRoleEnabled2(1l, 1);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size()>0);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //注意方法中多个参数时，mapper中sql变量参数的配置方法（注解方式,参数为简单类型）
    @Test
    public void testSelectRoleByUserIdAndRoleEnabled3(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectById(1l);
            SysRole role=new SysRole();
            role.setEnabled(1);
            List<SysRole> roles=userMapper.selectRoleByUserIdAndRoleEnabled3(user, role);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size()>0);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //注意方法中多个参数时，mapper中sql变量参数的配置方法（注解方式,参数为Map）
    @Test
    public void testSelectRoleByUserIdAndRoleEnabled4(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectById(1l);
            SysRole role=new SysRole();
            role.setEnabled(1);
            Map map=new HashMap<String,Object>();
            map.put("id",1);
            map.put("enabled",1);
            List<SysRole> roles=userMapper.selectRoleByUserIdAndRoleEnabled4(map);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size()>0);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    //select中使用if
    @Test
    public void testSelectByUser(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser param=new SysUser();
            param.setUserName("test");
            param.setUserEmail("test@mybatis.tk");

            List<SysUser> users=userMapper.selectByUser(param);
            Assert.assertNotNull(users);
            Assert.assertTrue(users.size()>0);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    //update 中使用if
    @Test
    public void testUpdateBySelective(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser dbUser=userMapper.selectById(1l);

            dbUser.setUserName("test1");
            dbUser.setUserEmail("test1@mybatis.tk");

            int res=userMapper.updateByIdSelective(dbUser);

            Assert.assertEquals(1, res);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //update 中使用if 和 set 标签, 解决了最后一个属性设置的逗号问题，但是没有解决属性为空时的问题
    @Test
    public void testUpdateBySelective2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser dbUser=userMapper.selectById(1l);

            dbUser.setUserName("test1");
            dbUser.setUserEmail("test1@mybatis.tk");

            int res=userMapper.updateByIdSelective2(new SysUser());

            Assert.assertEquals(1, res);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //select 中使用choose
    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser param=userMapper.selectById(1l);

            SysUser user=userMapper.selectByIdOrUserName(param);

            Assert.assertEquals("admin",user.getUserName());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //select中使用 where 标签
    @Test
    public void testSelectByUser2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser param=new SysUser();
            param.setUserName("admin");
            List<SysUser> users=userMapper.selectByUser2(param);
            Assert.assertTrue(users.size()==1);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //select中使用 foreach 标签,参数为数组
    @Test
    public void testSelectByIdList(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysUser> users=userMapper.selectByIdList(new Long[]{1l,1001l});
            Assert.assertTrue(users.size()==2);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //select中使用 foreach 标签,参数为list
    @Test
    public void testSelectByIdList2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<Long> ids=new ArrayList<Long>();
            ids.add(1l);
            ids.add(1001l);
            List<SysUser> users=userMapper.selectByIdList2(ids);
            Assert.assertTrue(users.size()==2);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //foreach 标签,批量插入. mybatis 3.3.1 支持批量返回自增主键值
    @Test
    public void testInsertList(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList=new ArrayList<SysUser>();
            for(int i=0;i<2;i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("123455");
                user.setUserEmail("test@mybatis.com");
                user.setCreateTime(new Date());
                userList.add(user);
            }
            int res=userMapper.insertList(userList);
            for(SysUser user:userList){
                System.out.println(user.getId());
            }
            Assert.assertEquals(2, res);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    //foreach 标签,实现动态update
    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("id",1l);
            map.put("user_email","testemail");
            map.put("user_password","11111");
            userMapper.updateByMap(map);
            SysUser user=userMapper.selectById(1l);

            Assert.assertEquals("11111",user.getUserPassword());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
