package service.employee;

import org.springframework.beans.factory.annotation.Autowired;

import repository.EmployeeRepository;

public class EmployeeDeleteService {//컨텍스트 등록
	@Autowired
	EmployeeRepository employeeRepository;
	public void empDelete(String empId) {//empId 받아오기
		employeeRepository.empDelete(empId);
	}

}
