package jian_free_persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jian_free_domain.ServiceVO;

public class ServiceDAO {
	static Connection conn = null;
	   static PreparedStatement pstmt = null;
	   static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	   static String jdbc_url = "jdbc:mysql://localhost/jspdb?useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	   static void connect() {
	      try {
	         Class.forName(jdbc_driver);
	         conn = DriverManager.getConnection(jdbc_url, "jspbook","passwd");
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	   }
	   
	   static void disconnect() {
	      if(pstmt != null) {
	         try {
	            pstmt.close();
	         } catch(SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      if(conn != null) {
	         try {
	            conn.close();
	         } catch(SQLException e) {
	            e.printStackTrace();
	         }
	      }
	   }
	
	
	public static int getNext() {
		String sql = "select snum from servicelist order by snum desc";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) return rs.getInt(1) + 1;
			return 1; //첫번째 게시물일 경우
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //Database error
	}
	public static String getDate() {
		connect();
		String sql = "select now()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //Database error
	}
	public static boolean add(ServiceVO vo) {
		connect();
		String sql = "insert into servicelist values (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, vo.getSname());
			pstmt.setString(3, vo.getSemail());
			pstmt.setString(4, vo.getScontent());
			pstmt.setString(5, getDate());
			pstmt.executeUpdate();
		}	catch(SQLException e) {
			e.printStackTrace();
			return false;
		}	finally {
			disconnect();
		}
		return true;
	}
	public ArrayList<ServiceVO> getServiceList(int pagenumber) {
		//글이 10개씩 표시되게 함
		connect();
		ArrayList<ServiceVO> servicelist = new ArrayList<ServiceVO>();
		String sql = "select * from servicelist where snum < ? order by snum desc limit 20";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pagenumber -1) * 10);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ServiceVO vo = new ServiceVO();
				vo.setSnum(rs.getInt(1));
				vo.setSname(rs.getString(2));
				vo.setSemail(rs.getString(3));
				vo.setScontent(rs.getString(4));
				vo.setSdate(rs.getString(5));
				servicelist.add(vo);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return servicelist;
	}
	public boolean nextPage(int pageNumber) {
		String sql = "select * from servicelist where snum < ? order by snum desc limit 20";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  getNext() - (pageNumber -1) * 10);;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return true; //다음 페이지로 넘어가게
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delete(String snum) {
		// TODO Auto-generated method stub
		connect();
		String sql = "delete from servicelist where snum=? ";
		int number = Integer.parseInt(snum);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.executeUpdate();
		}	catch(SQLException e) {
			e.printStackTrace();
			return false;
		}	finally {
			disconnect();
		}
		return true;
	}
}
