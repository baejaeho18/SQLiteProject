import java.sql.*;
// import java.util.ArrayList; 

public class Main {

	public static void main(String[] args) {
		Connection con = null;
//		ArrayList<String> fields = new ArrayList<>();
		
		try { 
			Class.forName("org.sqlite.JDBC");	// ???
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
			
//			Main.ls(dbFile);
//			Main.insert(dbFile);
//			Main.update(dbFile);
//			Main.delete(dbFile);
		    
			System.out.println("\n*** ������ ��ȸ ***");
			Statement stat1 = con.createStatement();	// dbFile�� �� ���� ����
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);	// dbFile���� ���� ���� ����� ResultSet ��ü������ ������
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id+" "+name);
			}
//			ResultSetMetaData rsmd = rs1.getMetaData();
//		    int numberOfColumns = rsmd.getColumnCount();
//		    System.out.println(numberOfColumns);			// field ���� �޾ƿ��� ����� ������???
		    stat1.close();
		    
		    System.out.println("\n*** ����Ÿ �߰� ***");
		    Statement stat2 = con.createStatement();
		    String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)" +
		    			" values ('winner', 'female', '2010', '2008', datetime('now', 'localtime'));";
		    int count2 = stat2.executeUpdate(sql2);			// insert, delete, update�� �Ǽ��� ��ȯ | create, drop�� -1 ��ȯ
		    if (count2>0) System.out.println("����Ÿ �߰��� �Ϸ�Ǿ����ϴ�.");
		    else System.out.println("[Error] ����Ÿ �߰��� �����Ͽ����ϴ�!");
		    stat2.close();

		    System.out.println("\n*** ����Ÿ ���� ***");
		    Statement stat3 = con.createStatement();
		    String sql3 = "update g_artists set a_type='male'" + " where name='winner';";
		    int count3 = stat3.executeUpdate(sql3);
		    if (count3>0) System.out.println("������ ������ �Ϸ�Ǿ����ϴ�!");
		    else System.out.println("[Error] ������ ������ �����Ͽ����ϴ�!");
		    stat3.close();
		    
		    System.out.println("\n*** ����Ÿ ���� ***");
		    Statement stat4 = con.createStatement();
		    String sql4 = "delete from g_artists"+" where a_type='male';";
		    int count4 = stat4.executeUpdate(sql4);
		    if (count4>0) System.out.println("������ ������ �Ϸ�Ǿ����ϴ�!");
		    else System.out.println("[Error] ������ ������ �����Ͽ����ϴ�!");
		    stat4.close();
		    
		    Main.ls(dbFile);
		    
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void ls(String dbFile) {
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
			
			System.out.println("\n*** ������ ��ȸ ***");
			Statement stat1 = con.createStatement();	// dbFile�� �� ���� ����
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);	// dbFile���� ���� ���� ����� ResultSet ��ü������ ������
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id+" "+name);
			}
//			ResultSetMetaData rsmd = rs1.getMetaData();
//		    int numberOfColumns = rsmd.getColumnCount();
//		    System.out.println(numberOfColumns);			// field ���� �޾ƿ��� ����� ������???
		    stat1.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
//	public static void insert(String dbFile) {
//		Connection con;
//		try {
//			con = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
//			
//			System.out.println("\n*** ����Ÿ �߰� ***");
//		    Statement stat2 = con.createStatement();
//		    String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate" +
//		    			" values ('winner', 'female', '2010', 2008', datetime('now', 'localtime'));";
//		    int count2 = stat2.executeUpdate(sql2);			// insert, delete, update�� �Ǽ��� ��ȯ | create, drop�� -1 ��ȯ
//		    if (count2>0) System.out.println("����Ÿ �߰��� �Ϸ�Ǿ����ϴ�.");
//		    else System.out.println("[Error] ����Ÿ �߰��� �����Ͽ����ϴ�!");
//		    stat2.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	public static void update(String dbFile) {
//		Connection con;
//		try {
//			con = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
//			
//			System.out.println("\n*** ����Ÿ ���� ***");
//		    Statement stat3 = con.createStatement();
//		    String sql3 = "update a_type='male'" + " where name='winner'";
//		    int count3 = stat3.executeUpdate(sql3);
//		    if (count3>0) System.out.println("������ ������ �Ϸ�Ǿ����ϴ�!");
//		    else System.out.println("[Error] ������ ������ �����Ͽ����ϴ�!");
//		    stat3.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	public static void delete(String dbFile) {
//		Connection con;
//		try {
//			con = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
//			
//			System.out.println("\n*** ����Ÿ ���� ***");
//		    Statement stmt4 = con.createStatement();
//		    String sql4 = "delete from g_artists"+" where id='6'";
//		    int count4 = stmt4.executeUpdate(sql4);
//		    if (count4>0) System.out.println("������ ������ �Ϸ�Ǿ����ϴ�!");
//		    else System.out.println("[Error] ������ ������ �����Ͽ����ϴ�!");
//		    stmt4.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}



