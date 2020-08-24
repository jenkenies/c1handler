package com.utstar.c1handler.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public final class JDBCUtil {

	private static final String DRIVER;
	private static final String URL;
	private static final String USER;
	private static final String PASSWORD;
	
	private JDBCUtil() {
	}

	static {
		InputStream in = com.utstar.c1handler.util.JDBCUtil.class.getClassLoader().getResourceAsStream("application.yml");
		//System.out.println(in);
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e1) {
		}
		DRIVER = properties.getProperty("driver-class-name");
		URL = properties.getProperty("url");
		USER = properties.getProperty("username");
		PASSWORD = properties.getProperty("password");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 获取 Connetion
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
		
	}

	/**
	 * 释放资源
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void colseResource(Connection conn, Statement st, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(st);
		closeConnection(conn);
	}

	/**
	 * 释放连接 Connection
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 等待垃圾回收
		conn = null;
	}

	/**
	 * 释放语句执行者 Statement
	 * 
	 * @param st
	 */
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 等待垃圾回收
		st = null;
	}

	/**
	 * 释放结果集 ResultSet
	 * 
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 等待垃圾回收
		rs = null;
	}

	public static void closeRsStatement(Statement stmt,ResultSet rs)
	{
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null)
		{
			try
			{
				stmt.close();
			}
			catch (SQLException sqle)
			{
			}
		}
	}


	public static void closePreparedStatement(PreparedStatement pstmt)
	{
		if (pstmt != null)
		{
			try
			{
				pstmt.close();
			}
			catch (SQLException sqle)
			{
			}
		}
	}

	public static void closeCallableStatement(CallableStatement cstmt)
	{
		if (cstmt != null)
		{
			try
			{
				cstmt.close();
			}
			catch (SQLException sqle)
			{
			}
		}
	}

	/**
	 *
	 * @param rs
	 * @param index
	 * @return
	 */
	public static String getString(ResultSet rs, int index)
	{
		try {
			String s = rs.getString(index);
			if (s == null) {
				return "";
			}
			return s;
		} catch (Exception e) {
			return "";
		}
	}
}