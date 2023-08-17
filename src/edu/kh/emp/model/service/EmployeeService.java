package edu.kh.emp.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

public class EmployeeService {

	private EmployeeDAO dao = new EmployeeDAO();
	
	/** 전체 사원 정보 조회 서비스
	 * 
	 */
	public List<Employee> selectAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<Employee> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
		
	}
	
	/** 부서명이 일치하는 모든사원 정보 조회
	 * @param deptName
	 * @return
	 * @throws Exception
	 */
	public Employee selectDeptCode(String deptCode) throws Exception {
		
		Connection conn = getConnection();
		
		Employee emp = dao.selectDeptCode(conn, deptCode);
		
		close(conn);
		
		return emp;
		
	}
	
	/**입력 받은 급여 이상을 받는 모든 사원 정보 조회
	 * @param salary
	 * @return
	 * @throws Exception
	 */
	public Employee selectSalary(int salaryco) throws Exception{
		
		Connection conn = getConnection();
		
		Employee emp = dao.selectSalary(conn, salaryco);
		
		close(conn);
		
		return emp;
	}

}
