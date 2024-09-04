package org.comstudy.myweb.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.comstudy.myweb.dbcp.JdbcUtil;

public class MemoDAO {
	static final String SELECT_ALL = "SELECT * FROM MEMO";
	static final String SELECT_ONE = "SELECT * FROM MEMO WHERE ID=?";
	static final String INSERT = "INSERT INTO MEMO(TITLE, CONTENT) VALUES (?,?)";
	static final String UPDATE = "UPDATE MEMO SET TITLE=?, CONTENT=? WHERE ID=?";
	static final String DELETE = "DELETE FROM  MEMO WHERE ID=?";
	
	// 공통으로 쓰는 참조변수는 필드로 선언
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;  // cursor
	
	// SELECT_ALL
	public List<Memo> getAllMemos() {
		System.out.println(">>>> getAllMemos() ...");
		List<Memo> memoList = new ArrayList();
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				memoList.add(new Memo(id, title, content));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return memoList;
	}

	// SELECT_ONE
	public Memo getMemoById(int id) {
		System.out.println(">>>> getMemoById() ...");
		Memo memo = null;
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(SELECT_ONE);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String title = rs.getString(2);
				String content = rs.getString(3);
				memo = new Memo(id, title, content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return memo;
	}

	// INSERT
	public void addMemo(Memo memo) {
		System.out.println(">>>> addMemo() ...");
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, memo.getTitle());
			pstmt.setString(2, memo.getContent());
			// select 문 외에는 모두 executeUpdate()사용.
			int cnt = pstmt.executeUpdate();
			if(cnt == 0 ) {
				System.out.println("입력 처리 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}

	// UPDATE
	public void updateMemo(Memo memo) {
		System.out.println(">>>> updateMemo() ...");
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, memo.getTitle());
			pstmt.setString(2, memo.getContent());
			pstmt.setInt(3, memo.getId());
			// select 문 외에는 모두 executeUpdate()사용.
			int cnt = pstmt.executeUpdate();
			if(cnt == 0 ) {
				System.out.println("수정 처리 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}

	// DELETE
	public void deleteMemo(int id) {
		System.out.println(">>>> deleteMemo() ...");
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			// select 문 외에는 모두 executeUpdate()사용.
			int cnt = pstmt.executeUpdate();
			if(cnt == 0 ) {
				System.out.println("삭제 처리 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	private static MemoDAO dao = new MemoDAO();
	
	public static void  getAllMemosTest() {
		// static으로 선언한 멤버는 클래스 차원의 멤버
		// static으로 없이 선언한 멤버는 인스턴스(객체) 멤버
		// static 멤버 안헤서는 this 사용 불가능.
		// 인스턴스 멤버는 객체를 통한 접근 가능.
		List<Memo> list = dao.getAllMemos();
		System.out.println(list);
	}
	
	public static void getMemoByIdTest() {
		Memo memo = dao.getMemoById(1);
		System.out.println(memo);
	}
	
	public static void addMemoTest() {
		Memo memo = new Memo("새 메모", "새 메모 내용");
		dao.addMemo(memo);
		getAllMemosTest();
	}
	
	public static void updateMemoTest() {
		Memo memo = new Memo(1, "수정 테스트", "수정 내용");
		dao.updateMemo(memo);
		getAllMemosTest();
	}
	
	public static void main(String[] args) {
		dao.deleteMemo(1);
		getAllMemosTest();
	}
}