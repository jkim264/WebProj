package jian_free_persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jian_free_domain.BoardVO;
import jian_free_domain.ServiceVO;;

public class BoardDAO {
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
	   
	   public static boolean add(BoardVO vo) {
			connect();
			String sql = "insert into board values (?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext());
				pstmt.setString(2, vo.getBtitle());
				pstmt.setString(3, vo.getBcontent());
				pstmt.setString(4, getDate());
				pstmt.setString(5, vo.getBwriter());
				pstmt.executeUpdate();
			}	catch(SQLException e) {
				e.printStackTrace();
				return false;
			}	finally {
				disconnect();
			}
			return true;
		}
	   public ArrayList<BoardVO> getBoardList(int pagenumber) {
			//글이 10개씩 표시되게 함
			connect();
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			String sql = "select * from board where bnum < ? order by bnum desc limit 20";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext() - (pagenumber -1) * 10);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardVO vo = new BoardVO();
					vo.setBnum(rs.getInt(1));
					vo.setBtitle(rs.getString(2));
					vo.setBcontent(rs.getString(3));
					vo.setBdate(rs.getString(4));
					vo.setBwriter(rs.getString(5));
					boardlist.add(vo);
				}
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return boardlist;
		}

	public static BoardVO read(String parameter) {
		// TODO Auto-generated method stub
		connect();
		String sql = "select * from board where bnum=?";
		BoardVO vo = new BoardVO();
		int number = Integer.parseInt(parameter);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setBnum(rs.getInt(1));
				vo.setBtitle(rs.getString(2));
				vo.setBcontent(rs.getString(3));
				vo.setBdate(rs.getString(4));
				vo.setBwriter(rs.getString(5));
			}
			
			rs.close();
		}	catch(SQLException e) {
			e.printStackTrace();
		}	finally {
			disconnect();
		}
		return vo;
	}

	public boolean update(BoardVO boardVO) {
		// TODO Auto-generated method stub
		connect();
		String sql = "update board set btitle=?, bcontent=?, bdate=? where bnum=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getBtitle());
			pstmt.setString(2, boardVO.getBcontent());
			pstmt.setString(3, BoardDAO.getDate());
			pstmt.setInt(4, boardVO.getBnum());
			pstmt.executeUpdate();
		}	catch(SQLException e) {
			e.printStackTrace();
			return false;
		}	finally {
			disconnect();
		}
		return true;
	}
	public boolean delete(String bnum) {
		// TODO Auto-generated method stub
		connect();
		String sql = "delete from board where bnum=? ";
		int number = Integer.parseInt(bnum);
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
	public static int getNext() {
		connect();
		String sql = "select bnum from board order by bnum desc";
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
	
	public boolean nextPage(int pageNumber) {
		String sql = "select * from board where bnum < ? order by bnum desc limit 20";
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
	
}

