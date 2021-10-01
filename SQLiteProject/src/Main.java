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
		    
			System.out.println("\n*** 데이터 조회 ***");
			Statement stat1 = con.createStatement();	// dbFile에 들어갈 문장 생성
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);	// dbFile에서 쿼리 실행 결과를 ResultSet 객체값으로 가져옴
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id+" "+name);
			}
//			ResultSetMetaData rsmd = rs1.getMetaData();
//		    int numberOfColumns = rsmd.getColumnCount();
//		    System.out.println(numberOfColumns);			// field 명을 받아오는 방법은 없나요???
		    stat1.close();
		    
		    System.out.println("\n*** 데이타 추가 ***");
		    Statement stat2 = con.createStatement();
		    String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)" +
		    			" values ('winner', 'female', '2010', '2008', datetime('now', 'localtime'));";
		    int count2 = stat2.executeUpdate(sql2);			// insert, delete, update된 건수를 반환 | create, drop은 -1 반환
		    if (count2>0) System.out.println("데이타 추가가 완료되었습니다.");
		    else System.out.println("[Error] 데이타 추가가 실패하였습니다!");
		    stat2.close();

		    System.out.println("\n*** 데이타 변경 ***");
		    Statement stat3 = con.createStatement();
		    String sql3 = "update g_artists set a_type='male'" + " where name='winner';";
		    int count3 = stat3.executeUpdate(sql3);
		    if (count3>0) System.out.println("데이터 변경이 완료되었습니다!");
		    else System.out.println("[Error] 데이터 변경이 실패하였습니다!");
		    stat3.close();
		    
		    System.out.println("\n*** 데이타 삭제 ***");
		    Statement stat4 = con.createStatement();
		    String sql4 = "delete from g_artists"+" where a_type='male';";
		    int count4 = stat4.executeUpdate(sql4);
		    if (count4>0) System.out.println("데이터 삭제가 완료되었습니다!");
		    else System.out.println("[Error] 데이터 삭제가 실패하였습니다!");
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
			
			System.out.println("\n*** 데이터 조회 ***");
			Statement stat1 = con.createStatement();	// dbFile에 들어갈 문장 생성
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);	// dbFile에서 쿼리 실행 결과를 ResultSet 객체값으로 가져옴
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id+" "+name);
			}
//			ResultSetMetaData rsmd = rs1.getMetaData();
//		    int numberOfColumns = rsmd.getColumnCount();
//		    System.out.println(numberOfColumns);			// field 명을 받아오는 방법은 없나요???
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
//			System.out.println("\n*** 데이타 추가 ***");
//		    Statement stat2 = con.createStatement();
//		    String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate" +
//		    			" values ('winner', 'female', '2010', 2008', datetime('now', 'localtime'));";
//		    int count2 = stat2.executeUpdate(sql2);			// insert, delete, update된 건수를 반환 | create, drop은 -1 반환
//		    if (count2>0) System.out.println("데이타 추가가 완료되었습니다.");
//		    else System.out.println("[Error] 데이타 추가가 실패하였습니다!");
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
//			System.out.println("\n*** 데이타 변경 ***");
//		    Statement stat3 = con.createStatement();
//		    String sql3 = "update a_type='male'" + " where name='winner'";
//		    int count3 = stat3.executeUpdate(sql3);
//		    if (count3>0) System.out.println("데이터 변경이 완료되었습니다!");
//		    else System.out.println("[Error] 데이터 변경이 실패하였습니다!");
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
//			System.out.println("\n*** 데이타 삭제 ***");
//		    Statement stmt4 = con.createStatement();
//		    String sql4 = "delete from g_artists"+" where id='6'";
//		    int count4 = stmt4.executeUpdate(sql4);
//		    if (count4>0) System.out.println("데이터 삭제가 완료되었습니다!");
//		    else System.out.println("[Error] 데이터 삭제가 실패하였습니다!");
//		    stmt4.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}



