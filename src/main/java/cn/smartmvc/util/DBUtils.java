package cn.smartmvc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource dataSource;
	
	static {
		Properties prop = new Properties();
		//文件输入流
		InputStream ips = DBUtils.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
		try {
			prop.load(ips); 
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			String initSize = prop.getProperty("initSize");
			String maxSize = prop.getProperty("maxSize");
			//创建连接池数据源对象
		
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setInitialSize(Integer.parseInt(initSize));
			dataSource.setMaxActive(Integer.parseInt(maxSize));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() 
			throws SQLException {
		return dataSource.getConnection();
	}
	
	public static void close(ResultSet rs,
			Statement stat,Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
