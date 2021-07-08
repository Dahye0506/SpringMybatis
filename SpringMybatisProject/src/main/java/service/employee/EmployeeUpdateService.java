package service.employee;

import org.springframework.beans.factory.annotation.Autowired;

import Model.EmployeeDTO;
import command.EmployeeCommand;
import repository.EmployeeRepository;

public class EmployeeUpdateService {//empcontext에 등록해야 사용할수있음
	@Autowired
	EmployeeRepository employeeRepository;//리포시토리불러오기
	public void empUpdate(EmployeeCommand employeeCommand) {//컨트롤러에도 등록
		EmployeeDTO dto = new EmployeeDTO();
		//수정할 수 있는것들 작성
		dto.setEmail(employeeCommand.getEmail());
		dto.setEmpAddress(employeeCommand.getEmpAddress());
		dto.setEmployeeId(employeeCommand.getEmployeeId());
		dto.setJobId(employeeCommand.getJobId());
		dto.setOfficeNumber(employeeCommand.getOfficeNumber());
		dto.setPhNumber(employeeCommand.getPhNumber());
		employeeRepository.empUpdate(dto);
		
	}

}
