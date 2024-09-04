package org.comstudy.myweb.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtil {
	// JDBC 대신 JNDI를 이용해서 DB Connection 객체를 얻는다. 
	// DataSource에는 데이터 커넥션 풀이 포함 되었다. 
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/MemoDB");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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