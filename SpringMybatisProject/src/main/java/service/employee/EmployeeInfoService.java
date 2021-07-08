package service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import Model.EmployeeDTO;
import repository.EmployeeRepository;

public class EmployeeInfoService {
	@Autowired
	EmployeeRepository employeeRepository; 
	public void empInfo(String empId, Model model) {// db로부터 empId값 가져오고 jsp에 넘기기위해 model필요
		EmployeeDTO dto = employeeRepository.empInfo(empId);
		model.addAttribute("emp",dto);// emp는 Info.jsp페이지 따라서
	}
}

