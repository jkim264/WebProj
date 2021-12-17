package jian_free_persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jian_free_domain.UserVO;;

public class UserDAO {
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
	   
	public static boolean add(UserVO vo) {
		connect();
		String sql = "insert into users values (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getUserPasswd());
			pstmt.setString(3, vo.getUserName());
			pstmt.setString(4, vo.getUserMobile());
			pstmt.setString(5, vo.getUserEmail());
			pstmt.executeUpdate();
		}	catch(SQLException e) {
			e.printStackTrace();
			return false;
		}	finally {
			disconnect();
		}
		return true;
	}
	public ArrayList<UserVO> getUserList() {
		connect();
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
		String sql = "select * from users";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setUserId(rs.getString("userId"));
				vo.setUserPasswd(rs.getString("userPasswd"));
				vo.setUserName(rs.getString("userName"));
				vo.setUserMobile(rs.getString("mobile"));
				vo.setUserEmail(rs.getString("email"));
				userlist.add(vo);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return userlist;
	}

	public UserVO read(String parameter) {
		// TODO Auto-generated method stub
		connect();
		String sql = "select * from users where userId=?";
		UserVO vo = new UserVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, parameter);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setUserId(rs.getString("UserId"));
				vo.setUserPasswd(rs.getString("UserPasswd"));
				vo.setUserName(rs.getString("UserName"));
				vo.setUserMobile(rs.getString("Mobile"));
				vo.setUserEmail(rs.getString("Email"));
			}
			
			rs.close();
		}	catch(SQLException e) {
			e.printStackTrace();
		}	finally {
			disconnect();
		}
		return vo;
	}

	public boolean update(UserVO UserVO) {
		// TODO Auto-generated method stub
		connect();
		String sql = "update users set userPasswd=?, userName=?, mobile=?, email=? where userId=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, UserVO.getUserPasswd());
			pstmt.setString(2, UserVO.getUserName());
			pstmt.setString(3, UserVO.getUserMobile());
			pstmt.setString(4, UserVO.getUserEmail());
			pstmt.setString(5, UserVO.getUserId());
			pstmt.executeUpdate();
		}	catch(SQLException e) {
			e.printStackTrace();
			return false;
		}	finally {
			disconnect();
		}
		return true;
	}

	public int login(String userId, String userPassword) {
		connect();
		String sql = "select userPasswd from users where userId=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  userId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				} else
					return 0; //비밀번호 불일치
			}
			return -1; //아이디가 없을 경우
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2; //데이터 베이스 오류
	}
	public boolean delete(String userId) {
		// TODO Auto-generated method stub
		connect();
		String sql = "delete from users where userId=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			return true;
		}	catch(SQLException e) {
			e.printStackTrace();
			return false;
		}	finally {
			disconnect();
		}
	}
}


