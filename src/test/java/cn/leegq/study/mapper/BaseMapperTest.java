package cn.leegq.study.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.Reader;

/**
 * @author Hale Li 2018/4/2
 */
public class BaseMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init(){
        try{
            Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
