package com.junli.mybatis.demo.mybatis;

import com.junli.mybatis.beans.Blog;
import com.junli.mybatis.beans.Posts;
import com.junli.mybatis.beans.Test;
import com.junli.mybatis.mapper.BlogMapper;
import com.junli.mybatis.mapper.PostsMapper;
import com.junli.mybatis.mapper.TestMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class MyBatisDemo {
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() throws Exception {
        //配置文件
//        InputStream configFile = new FileInputStream(
//                "E:\\workspace\\code\\git\\gupaoedu-mybatis\\src\\main\\java\\com\\gupaoedu\\mybatis\\demo\\mybatis-config.xml");
        String resource = "G:\\learnworkspace\\learn-mybatis\\mybatis-demo\\src\\main\\java\\com\\junli\\mybatis\\demo\\mybatis\\mybatis-config.xml";
        InputStream configFile = new  FileInputStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        //加载配置文件得到SqlSessionFactory
        return sqlSessionFactory.openSession();
    }

    public static SqlSessionFactory getSqlSessionFactory() throws FileNotFoundException {
        InputStream configFile = new FileInputStream("G:\\learnworkspace\\learn-mybatis\\mybatis-demo\\src\\main\\java\\com\\junli\\mybatis\\demo\\mybatis\\mybatis-config.xml");
        if (null != sqlSessionFactory) {
            return sqlSessionFactory;
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        return sqlSessionFactory;
    }

    public static Test get(SqlSession sqlSession, int id) throws SQLException {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        long start = System.currentTimeMillis();
        Test test = testMapper.selectByPrimaryKey(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        test = testMapper.selectByPrimaryKey(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start) + "\n" + test);
//        test.setName(String.valueOf(new Random().nextInt(5)));
//        testMapper.updateByPrimaryKey(test);
        start = System.currentTimeMillis();
        test = testMapper.selectByPrimaryKey(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return test;
    }

    public static Test getOne(SqlSession sqlSession, int id) throws SQLException {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        long start = System.currentTimeMillis();
        Test test = testMapper.selectByPrimaryKey(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return test;
    }

    public static Posts getPosts(SqlSession sqlSession, int id) {
        PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
        long start = System.currentTimeMillis();
        Posts posts = postsMapper.selectByPrimaryKey(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        posts = postsMapper.selectByPrimaryKey(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start) + "\n" + posts);
//        test.setName(String.valueOf(new Random().nextInt(5)));
//        testMapper.updateByPrimaryKey(test);
        start = System.currentTimeMillis();
        posts = postsMapper.selectByPrimaryKey(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return posts;
    }

    //获取BLOG(关联查询)
    public static Blog getOneBlog(SqlSession sqlSession, Long id) throws SQLException {
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        long start = System.currentTimeMillis();
        Blog blog = blogMapper.selectById(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return blog;
    }


    //获取BLOG（嵌套结果）
    public static Blog getOneBlogById(SqlSession sqlSession, Long id) throws SQLException {
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        long start = System.currentTimeMillis();
        Blog blog = blogMapper.selectBlogById(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return blog;
    }

    //获取BLOG集合(关联查询)
    public static Blog selectPostsForBlog(SqlSession sqlSession, Long id) throws SQLException{
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        long start = System.currentTimeMillis();
        Blog blog = blogMapper.selectBlogAndPosts(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return blog;
    }
    //获取BLOG集合（嵌套结果）
    public static Blog selectBlogAndPostsResultQuery(SqlSession sqlSession, Long id) throws SQLException{
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        long start = System.currentTimeMillis();
        Blog blog = blogMapper.selectBlogAndPostsResultQuery(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return blog;
    }

    public static int insert(SqlSession sqlSession, Test test) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.insert(test);
    }

    public static void main(String[] args) throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {
//            diffSession();
           // System.out.println(getOne(sqlSession, 1));
            //Blog oneBlog = getOneBlog(sqlSession, 1L);
            //获取博客
            //System.out.println("关联查询博客："+oneBlog);

           // Blog oneBlog2 = getOneBlogById(sqlSession, 1L);
            //System.out.println("嵌套结果博客："+oneBlog);


            Blog blog = selectPostsForBlog(sqlSession, 1L);
            List<Posts> posts = blog.getPosts();
            System.out.println("博客和帖子关联查询："+blog);
            System.out.println("博客和帖子关联查询帖子："+posts);

            Blog blog1 = selectBlogAndPostsResultQuery(sqlSession,1L);
            System.out.println("博客和帖子嵌套结果："+blog);
            System.out.println("博客和帖子嵌套结果帖子："+posts);
//            System.out.println(getPosts(sqlSession, 1));
        //System.out.println(insert(sqlSession, new Test(null, 66, "test insert")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public static void diffSession() throws Exception {
        SqlSession sqlSession1 = getSqlSessionFactory().openSession();
        SqlSession sqlSession2 = getSqlSessionFactory().openSession();
        SqlSession sqlSession3 = getSqlSessionFactory().openSession();
        getOne(sqlSession1, 1);
        sqlSession1.commit();
        sqlSession1.close();
        getOne(sqlSession2, 1);
        sqlSession2.commit();
        sqlSession2.close();
        getOne(sqlSession3, 1);
        sqlSession3.commit();
        sqlSession3.close();
    }
}
