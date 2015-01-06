package crazy.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日 上午10:18:24
 * @see
 */
public class Dbtool {
	// 获取当前项目路径
	private static String dburl = System.getProperty("user.dir") + "\\testData\\";
	// db连接字符串
	private static String strConnectAccess = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb, *.accdb);DBQ="
			+ dburl;
	

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private static String host;
	private static String port;
	private static String username;
	private static String password;
	private static DBType dbtype;
	private static String dbname;

	private Connection conn = null;

	public boolean getConnisClosed() {
		boolean bool = true;
		try {
			bool = this.conn.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 枚举浏览器类型firefox, ie, chrome;
	 * 
	 * @param browserstype
	 */
	public enum DBType {
		access, mysql;
	}

	/**
	 * 数据库创建连接
	 * 
	 * @return Connection
	 */
	public Dbtool getConnection() {
		// 创建与数据库的连接
		switch (dbtype) {
		case access:
			// 加载驱动

			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			} catch (ClassNotFoundException ex) {
				System.out.println("加载驱动失败：" + ex);
			}
			try {
				this.conn = DriverManager.getConnection(strConnectAccess + dbname + ".accdb");
				System.out.println("成功连接到数据库：" + conn);
			} catch (Exception ex) {
				System.out.println("连接失败：" + ex);
			}
			break;
		case mysql:
			// 加载驱动

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (ClassNotFoundException ex) {
				System.out.println("加载驱动失败：" + ex);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			String strConnectMysql = "jdbc:mysql://" + host + ":" + port + "/dbname?" + "user="
					+ username + "&password=" + password
					+ "&useUnicode=true&characterEncoding=UTF8";
			strConnectMysql = strConnectMysql.replace("dbname", dbname);
			try {

				this.conn = DriverManager.getConnection(strConnectMysql);
				System.out.println("成功连接到数据库：" + conn);
			} catch (Exception ex) {
				System.out.println("连接失败：" + ex + "\n" + strConnectMysql);
			}
			break;
		}

		return this;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public void close() {
		try {
			if (this.conn != null) {
				// 关闭连接，释放资源
				this.conn.close();
				System.out.println("数据库连接已经关闭");
			}
		} catch (SQLException ex) {
			//
			System.out.println("数据库连接关闭异常：" + ex);
		}
	}

	/**
	 * 将List转为字符串 aaa,bbb,ccc
	 * 
	 * @see
	 * @param valuename
	 * @return
	 */
	private String listTosqlstr(List<String> valuename) {
		// 创建data二维数组
		String value = "";
		for (String str : valuename) {
			value += str + ",";
		}
		return value.substring(0, value.length() - 1);
	}

	/**
	 * 将sql查询的结果集 存放到二维数组中
	 * 
	 * @see
	 * @param rs
	 * @return
	 */
	private String[][] sqlResultSet2Array(ResultSet rs) {
		// 创建data二维数组
		String[][] data = null;
		try {
			// 获取查询结果总条数
			rs.last();
			int rows = rs.getRow();
			rs.beforeFirst();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			// 实例化data二维数组
			data = new String[rows][columnCount];
			for (int j = 0; rs.next(); j++) {

				for (int i = 0; i < columnCount; i++) {
					data[j][i] = rs.getString(i + 1);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}

	/**
	 * 
	 * @param valuename
	 *            List查询对象列名集
	 * @param fromtablename
	 * 
	 * @return select List<String> valuename from fromtablename
	 * 
	 */
	public String[][] getData(List<String> valuename, String fromtablename) {
		return this.getValuesbyKeyORDERBY(valuename, fromtablename, "", "", "", 3);
	}

	/**
	 * 
	 * @param valuename
	 *            查询对象列名
	 * @param fromtablename
	 * 
	 * @return select valuename from fromtablename
	 * 
	 */
	public String[][] getData(String valuename, String fromtablename) {
		List<String> vlis = Arrays.asList(valuename);
		return this.getValuesbyKeyORDERBY(vlis, fromtablename, "", "", "", 3);
	}

	/**
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @return select valuename from fromtablename
	 * 
	 */
	public List<String> getValues(String valuename, String fromtablename) {
		List<String> list = new ArrayList<String>();
		List<String> vlis = new ArrayList<String>();
		vlis.add(valuename);
		String[][] rs = this.getData(vlis, fromtablename);
		for (String[] str : rs) {
			list.add(str[0]);
		}
		return list;
	}

	/**
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @return select valuename from fromtablename where key = keyvalue
	 * 
	 */
	public String[][] getValuesbyKey(List<String> valuename, String fromtablename, String key,
			String keyvalue) {
		return this.getValuesbyKeyORDERBY(valuename, fromtablename, key, keyvalue, "", 2);
	}

	/**
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @param orderby
	 * @return select valuename from fromtablename where key = keyvalue
	 *         如果查询结果中有多个值，查询结果集中orderby 升序最后一个
	 */
	public String getValuebyKeyOrderby(String valuename, String fromtablename, String key,
			String keyvalue, String orderby) {
		List<String> vlis = Arrays.asList(valuename);
		String[][] astr = this
				.getValuesbyKeyORDERBY(vlis, fromtablename, key, keyvalue, orderby, 1);
		int rows = astr.length;
		return astr[rows - 1][0];
	}

	/**
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @return select valuename from fromtablename where key = keyvalue
	 *         如果查询结果中有多个值，查询结果集中最后一个
	 */
	public String getValuebyKey(String valuename, String fromtablename, String key, String keyvalue) {
		List<String> vlis = Arrays.asList(valuename);
		String[][] astr = this.getValuesbyKeyORDERBY(vlis, fromtablename, key, keyvalue, "", 2);
		int rows = astr.length;
		return astr[rows - 1][0];
	}

	public void updateValueBykey(String updatetablenameindb, String key, String keyvalue,
			String valuename, String newvalue) {
		PreparedStatement state = null;

		try {
			String sql = "UPDATE " + updatetablenameindb + " SET " + valuename + " = ? WHERE "
					+ key + " = ?";
			state = this.conn.prepareStatement(sql);
			state.setString(1, newvalue);
			state.setString(2, keyvalue);
			state.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (state != null) {
					state.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void insertkeyValue(String totablenameindb, String key, String keyvalue) {
		PreparedStatement state = null;

		try {
			String sql = "insert into " + totablenameindb + "(" + key + ") values (?)";
			state = this.conn.prepareStatement(sql);
			state.setString(1, keyvalue);
			state.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (state != null) {
					state.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public List<String> getTableColumnName(String tablenameindb) {
		List<String> list = new ArrayList<String>();
		PreparedStatement state = null;
		ResultSet rs = null;

		try {
			String sql = "select *  from  " + tablenameindb;
			state = this.conn.prepareStatement(sql);

			rs = state.executeQuery();
			ResultSetMetaData data = rs.getMetaData();
			rs.next();
			// 显示查询结果
			for (int i = 1; i <= data.getColumnCount(); i++) {
				// System.out.println(data.getColumnName(1));
				list.add(data.getColumnName(i));
				// System.out.println(" rs.getString(1)="+str);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * 查询 OR BY KEY BY ORDERBY
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @param orderbyname
	 * @return select valuename from fromtablename where key = keyvalue order by
	 *         orderby
	 * 
	 */
	public String[][] getValuesbyKeyORDERBY(List<String> valuename, String fromtablename,
			String key, String keyvalue, String orderbyname) {
		return this.getValuesbyKeyORDERBY(valuename, fromtablename, key, keyvalue, orderbyname, 1);
	}

	/**
	 * 查询 OR BY KEY BY ORDERBY
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @param n
	 *            【 1：key and oderby】 【 2：key 】【3：null】
	 * @return select valuename from fromtablename where key = keyvalue
	 * 
	 */
	private String[][] getValuesbyKeyORDERBY(List<String> valuename, String fromtablename,
			String key, String keyvalue, String orderby, int n) {

		PreparedStatement state = null;
		ResultSet rs = null;

		// 创建data二维数组
		String[][] data = null;
		// 生成sql查询目标字符串
		String value = this.listTosqlstr(valuename);
		String sql = null;
		switch (n) {
		case 1:
			sql = "select " + value + " from " + fromtablename + " where " + key + " = ? ORDER BY "
					+ orderby;
			break;
		case 2:
			sql = "select " + value + " from " + fromtablename + " where " + key + " = ? ";
			break;
		case 3:
			sql = "select " + value + " from " + fromtablename;
			break;
		}
		try {

			// System.out.println(sql+"  "+ keyvalue);
			state = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			switch (n) {
			case 1:
			case 2:
				state.setString(1, keyvalue);
				break;
			default:
				break;
			}
			rs = state.executeQuery();

			data = this.sqlResultSet2Array(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return data;
	}

	public static void setDburl(String dburl) {
		Dbtool.dburl = dburl;
	}

	public static void setStrConnectAccess(String strConnectAccess) {
		Dbtool.strConnectAccess = strConnectAccess;
	}

	public void setDBconnPar(DBType dbtype,String dbname,String host, String port, String username, String password) {
		Dbtool.host = host;
		Dbtool.port = port;
		Dbtool.username = username;
		Dbtool.password = password;
		Dbtool.dbtype=dbtype;
		Dbtool.dbname=dbname;
	}
	
	public void setDBconnPar(DBType dbtype,String dbname) {
	
		Dbtool.dbtype=dbtype;
		Dbtool.dbname=dbname;
	}

	@Test
	public void testgetValuebyKey() throws SQLException {
		this.setDBconnPar(DBType.access, "YlyTestData");
		Dbtool dbtool = this.getConnection();
		// select UiSelector as t from StartPage where elementname = ?
		// start1Layout
		String str = dbtool.getValuebyKeyOrderby("密码", "userData", "用户名", "YYT", "序号");
		System.out.println(str);
		dbtool.close();

	}

	@Test
	public void testinsertkeyValue() throws SQLException {
		this.setDBconnPar(DBType.access, "testData");
		Dbtool dbtool = this.getConnection();
		dbtool.insertkeyValue("testAppUserDataInfo", "手机号", "13325465002");
		String str = dbtool.getValuebyKeyOrderby("手机号", "testAppUserDataInfo", "手机号",
				"13325465002", "序号");
		System.out.println(str);
		dbtool.close();

	}

	@Test
	public void testupdateValueBykey() throws SQLException {
		this.setDBconnPar(DBType.access, "testData");
		Dbtool dbtool = this.getConnection();
		dbtool.updateValueBykey("testAppUserDataInfo", "手机号", "13325465002", "司机姓名", "叶浩");
		String str = dbtool.getValuebyKeyOrderby("司机姓名", "testAppUserDataInfo", "手机号",
				"13325465002", "序号");
		System.out.println(str);
		dbtool.close();
	}

	@Test
	public void testgetTableColumnName() {
		this.setDBconnPar(DBType.access, "testData");
		Dbtool dbtool = this.getConnection();
		List<String> list = dbtool.getTableColumnName("testAppUserDataInfo");
		list.iterator();
		int i = 0;
		while (i < 6) {
			System.out.println(list.get(i + 2));
			i++;
		}
		dbtool.close();
	}

	@Test
	public void testconn() {

this.setDBconnPar(DBType.mysql, "TestData", "192.168.0.56", "3306", "root", "root");
		Dbtool dbtool = this.getConnection();

		String str = dbtool.getValuebyKey("用户名", "userdata", "序号", "1");
		System.out.println(str);

		Dbtool dbtool2 = this.getConnection();

		String str2 = dbtool2.getValuebyKey("密码", "userData", "序号", "1");
		System.out.println(str2);

		dbtool.close();
		dbtool2.close();

	}

	@Test
	public void testgetData() throws SQLException {
		this.setDBconnPar(DBType.mysql, "TestData", "192.168.0.56", "3306", "root", "root");
		Dbtool dbtool = this.getConnection();
		// select UiSelector as t from StartPage where elementname = ?
		// start1Layout
		List<String> valuename = Arrays.asList("区域范围", "序号");
		String[][] astr = dbtool.getData(valuename, "areadatapro");
		for (int j = 0; j < astr.length; j++) {
			for (int i = 0; i < astr[j].length; i++) {
				System.out.print(astr[j][i] + "  ");
			}
			System.out.println();
		}

		dbtool.close();

	}

}
