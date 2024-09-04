package org.mybatis.example;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BlogDAO {
   private SqlSessionFactory sqlSessionFactory;
   
   public BlogDAO() {
      try {
         String resource = "org/mybatis/example/mybatis-config.xml";
         Reader reader = Resources.getResourceAsReader(resource);
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public BlogDAO(SqlSessionFactory sqlSessionFactory) {
      this.sqlSessionFactory = sqlSessionFactory;
   }

   public Blog selectBlog(int id) {
      try(SqlSession mybatis = sqlSessionFactory.openSession()) {
         BlogMapper blogMapper = mybatis.getMapper(BlogMapper.class);
         return blogMapper.selectBlog(id);
      }
   }
   
   public List<Blog> selectAllBlog() {
      try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
         BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
         return blogMapper.selectAllBlog();
      }
   }
   
   public void insertBlog(Blog blog) {
      System.out.println("insertBlog() - BlogDAO" + blog);
      try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
         BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
         blogMapper.insertBlog(blog);
         sqlSession.commit(); // update되었을때는 commit해야 적용 됨.
      }
   }
   
   public void updateBlog(Blog blog) {
      try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
         BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
         blogMapper.updateBlog(blog);
         sqlSession.commit();
      }
   }
   
   public void deleteBlog(Blog blog) {
      try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
         BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
         blogMapper.deleteBlog(blog);
         sqlSession.commit();
      }
   }
}
