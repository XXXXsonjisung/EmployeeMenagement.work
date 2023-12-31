package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.emp.common.JDBCTemplate.*;

import edu.kh.emp.model.vo.Employee;

public class EmployeeDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	
	private Properties prop;

	public EmployeeDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("query.xml") );
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 전체 사원 정보 조회 DAO
	 * @param conn
	 */
	public List<Employee> selectAll(Connection conn) throws Exception{
		
		// 결과 저장용 변수 선언
		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			// Statement 객체 생성
			
			stmt = conn.createStatement();
			
			// SQL을 수행 후 결과(ResultSet) 반환 받음
			rs = stmt.executeQuery(sql);
			
			// 조회 결과를 얻어와 한 행씩 접근하여
			// Employee 객체 생성 후 컬럼값 담기
			// -> List 추가
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID");
				// EMP_ID 컬럼은 문자열 컬럼이지만
				// 저장된 값들이 모두 숫자 형태
				// -> DB에서 자동으로 형변환 진행해서 얻어옴
				
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName, empNo, 
						email, phone, departmentTitle, jobName, salary );
				
				empList.add(emp); // List 담기
			
			} // while문 종료
			
			
		} finally {
			
			close(stmt);
			
		}
		
		// 결과 반환
		return empList;
		
		
	}

	/** 사번이 일치하는 사원 정보 조회 DAO
	 * @param conn
	 * @param deptCode
	 * @return
	 */
	public Employee selectDeptCode(Connection conn, String deptCode) throws Exception {
		
		Employee emp = null;
		
		try {
			
			String sql = prop.getProperty("selectDeptCode");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, deptCode);
			
			rs = pstmt.executeQuery();  
			
			while(rs.next()){
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(empId,empName,email,phone
						,departmentTitle,jobName,salary); 
				// 문제 발생 ..
			}
			
		} finally {
			close(pstmt);
		}
		return null;
	}

	
	
	/**입력 받은 급여 이상을 받는 모든 사원 정보 조회
	 * @param conn
	 * @param salary
	 * @return
	 */
	public Employee selectSalary(Connection conn, int salaryco) {
		
    Employee emp = null;
		
		try {
			
			String sql = prop.getProperty("selectSalary");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, salaryco);
			
			rs = pstmt.executeQuery();  
			
			while(rs.next()){
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(empId,empName,email,phone
						,departmentTitle,jobName,salary); 
				// 문제 발생 ..
			}
			
		} finally {
			close(pstmt);
		}
		return null;
	}
}
