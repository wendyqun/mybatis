package cn.leegq.study.mapper;

import cn.leegq.study.model.SysRole;
import junit.framework.Assert;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author Hale Li 2018/4/2
 */
public class RoleMapperTest extends BaseMapperTest{

    @Test
    public void testSelectRoleByUserIdAndRoleEnabled3(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole role=roleMapper.selectById(1l);
            Assert.assertNotNull(role);
            Assert.assertTrue(role.getId()>0);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
