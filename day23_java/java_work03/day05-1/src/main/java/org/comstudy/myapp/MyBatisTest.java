package org.comstudy.myapp;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.example.Blog;
import org.mybatis.example.BlogDAO;
import org.mybatis.example.BlogMapper;

public class MyBatisTest {
   private static BlogDAO blogDao;
   static {
      blogDao = new BlogDAO();
   }
   
   public static void main(String[] args) {
      Blog blog = new Blog(8, "새 글8","새 글 내용8");
      blogDao.updateBlog(blog);
      selectAllBlogTest();
   }
   
   public static void deleteBlogTest() {
      Blog blog = new Blog(10);
      blogDao.deleteBlog(blog);
      selectAllBlogTest();
   }
   
   public static void inserBlogTest() {
      Blog blog = new Blog("새 글3","새 글 내용3");
      blogDao.insertBlog(blog);
      selectAllBlogTest();
   }
   
   public static void selectAllBlogTest() {
      List<Blog> list = blogDao.selectAllBlog();
      for(Blog blog : list) {
         System.out.println(blog);
      }
   }
   
   public static void daoTest2(String[] args) throws IOException {
      
        Blog blog = blogDao.selectBlog(1);
        System.out.println(blog);
   }
   
   // dao에서 mybatis 사용
   public static void daoTest(String[] args) {
      String resource = "org/mybatis/example/mybatis-config.xml";
      try (Reader reader = Resources.getResourceAsReader(resource)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            BlogDAO blogDao = new BlogDAO(sqlSessionFactory);
            Blog blog = blogDao.selectBlog(1);
            System.out.println(blog);
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
   
   // 인터페이스 맵퍼 사용 방법
   public static void interfaceMapperTest(String[] args) {
      String resource = "org/mybatis/example/mybatis-config.xml";
      try (Reader reader = Resources.getResourceAsReader(resource)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
                Blog blog = blogMapper.selectBlog(1);
                System.out.println(blog);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

   // xml 매퍼 사용 방법
   public static void xmlMapperTest(String[] args) {
      // 연결 하고자 하는 DB의 드라이버와 mybatis 라이브러리 가 모두 준비 되어야 합니다.
      // app에서 실행 하기 위해서 라이브러리를 빌드패스로 추가
      
      String resource = "org/mybatis/example/mybatis-config.xml"; // MyBatis 설정 파일 경로
        try (Reader reader = Resources.getResourceAsReader(resource)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                 Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 1);
                 System.out.println(blog);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

}
