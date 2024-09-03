package org.comstudy.myweb.dbcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	// 나중에 jdbc를 jndi 기술로 바꿈
	// 중요: h2 데이터베이스를 연동하기 위해서 먼저 DB 실행 해야 한다.
	// 접속 URL 확인 : jdbc:h2:tcp://localhost/~/test
	public static Connection getConnection() {
		String url = "jdbc:h2:tcp://localhost/~/test";
		String user = "sa";
		String password = "";
		Connection conn = null;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.err.println(">>> 드라이버 검색 실패!");
		} catch (SQLException e) {
			System.err.println(">>> DB 접속 실패!");
		}

		return conn;
	}

	public static void close(Connection obj) {
		try {
			if (obj != null)
				obj.close();
		} catch (SQLException e) {
		}
	}

	public static void close(ResultSet obj) {
		try {
			if (obj != null)
				obj.close();
		} catch (SQLException e) {
		}
	}

	public static void close(Statement obj) {
		try {
			if (obj != null)
				obj.close();
		} catch (SQLException e) {
		}
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		close(conn);
		close(rs);
		close(stmt);
	}
}
