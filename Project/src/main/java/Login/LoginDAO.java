/**====================
* 작성자 : jeon
* 작성일 : 2023. 6. 13.
* 파일명 : LoginDAO.java
* 프로그램 설명 : 
*====================**/
package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class LoginDAO {
	
	private Connection getConnection() throws Exception {
		Context initCtx =  new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jkm");		
		Connection con = ds.getConnection();
		return con;
		
	}
	
		public void insertLogin(LoginDTO dto) throws Exception {
			
			String sql = "INSERT INTO students(name, studentNumber , password, email, grade, position) values(?, ?, ?, ?, ?, ?) ";
			try(Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);)
			{
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getStudentNumber());
				pstmt.setString(3, dto.getPassword());
				pstmt.setString(4, dto.getEmail());
				pstmt.setString(5, dto.getGrade());
				pstmt.setString(6, dto.getPosition());
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//approval_status가 승인인 회원만 로그인 가능하게함
		public boolean checkLogin(String studentNumber, String password) throws Exception {
		    String sql = "SELECT * FROM students WHERE studentNumber = ? AND password = ? AND approval_status = '승인'";
		    try (Connection con = getConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql)) {
		        pstmt.setString(1, studentNumber);
		        pstmt.setString(2, password);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            return rs.next(); // 로그인 정보가 일치하면 true, 그렇지 않으면 false 반환
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false; // 예외 발생 시 로그인 실패로 처리
		    }
		}
		
		//가입 승인 회원만 가져오는 메서드
		public List<LoginDTO> getApprovedMembers() throws Exception {
		    List<LoginDTO> joinRequests = new ArrayList<>();
		    String sql = "SELECT * FROM students WHERE approval_status = '승인'";
		    try (Connection con = getConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql);
		         ResultSet rs = pstmt.executeQuery()) {
		        while (rs.next()) {
		            String name = rs.getString("name");
		            String studentNumber = rs.getString("studentNumber");
		            String password = rs.getString("password");
		            String email = rs.getString("email");
		            String grade = rs.getString("grade");
		            String position = rs.getString("position");
		            LoginDTO request = new LoginDTO(name, studentNumber, password, email, grade, position);
		            joinRequests.add(request);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return joinRequests;
		}
		
		//미승인인 회원만 가져오는 메서드
		public List<LoginDTO> getJoinRequests() throws Exception {
		    List<LoginDTO> joinRequests = new ArrayList<>();
		    String sql = "SELECT * FROM students WHERE approval_status = '미승인'";
		    try (Connection con = getConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql);
		         ResultSet rs = pstmt.executeQuery()) {
		        while (rs.next()) {
		            String name = rs.getString("name");
		            String studentNumber = rs.getString("studentNumber");
		            String password = rs.getString("password");
		            String email = rs.getString("email");
		            String grade = rs.getString("grade");
		            String position = rs.getString("position");
		            LoginDTO request = new LoginDTO(name, studentNumber, password, email, grade, position);
		            joinRequests.add(request);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return joinRequests;
		}

		 // 회원가입 신청 승인 메서드
	    public void approveJoinRequest(String studentNumber) throws Exception {
	        String sql = "UPDATE students SET approval_status = '승인' WHERE studentNumber = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, studentNumber);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // 회원가입 신청 취소 메서드
	    public void cancelJoinRequest(String studentNumber) throws Exception {
	        String sql = "DELETE FROM students WHERE studentNumber = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, studentNumber);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}	


