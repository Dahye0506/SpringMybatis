package service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import Model.EmployeeDTO;
import command.EmployeeCommand;
import repository.EmployeeRepository;

public class EmployeeJoinService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder; //비밀번호 암호화 
	
	//사용하기 위해 등록
	public void empInsert(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmail(employeeCommand.getEmail());
		dto.setEmpAddress(employeeCommand.getEmpAddress());
		dto.setEmployeeId(employeeCommand.getEmployeeId());
		dto.setEmpName(employeeCommand.getEmpName());
		//비밀번호 함호화
		dto.setEmpPw(bcryptPasswordEncoder.encode(employeeCommand.getEmpPw()));
		dto.setEmpUserId(employeeCommand.getEmpUserid());
		dto.setHireDate(employeeCommand.getHireDate());
		dto.setJobId(employeeCommand.getJobId());
		dto.setOfficeNumber(employeeCommand.getOfficeNumber());
		dto.setPhNumber(employeeCommand.getPhNumber());
		
		//리포시토리생성
		employeeRepository.empInsert(dto);
		
	}

}
