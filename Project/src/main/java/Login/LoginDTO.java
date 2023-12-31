/**====================
* 작성자 : jeon
* 작성일 : 2023. 6. 13.
* 파일명 : LoginDTO.java
* 프로그램 설명 : 
*====================**/
package Login;

public class LoginDTO {
	private int id;
	private String name;
	private String studentNumber;
	private String password;
	private String email;
	private String grade;
	private String position;
	
	
	public LoginDTO( String studentNumber, String password) {
		this.studentNumber = studentNumber;
		this.password = password;
		
	}
	
	public LoginDTO(String name, String studentNumber, String password, String email, String grade, String position  ) {
		this.name = name;
		this.studentNumber = studentNumber;
		this.password = password;
		this.email = email;
		this.grade = grade;
		this.position = position;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
		
}
