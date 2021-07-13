package controller.employee;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.employee.EmployeeInfoService;

@Controller
@RequestMapping("/empEdit")
public class EmployeeMypageController {
	@Autowired
	EmployeeInfoService employeeInfoService;
	
	
	//직원마이페이지
	@RequestMapping("empMypage")
	public String empMyPage() {
		return "employee/employeeInfo";
	}
	//값넣기
//	@RequestMapping("memDetail")
//	public String memDetail(HttpSession session,Model model) {
//		memberInfoService.memInfo(model, session);//에러는 모델에 담아서 가져옴
//		return "member/memDetail";
//	}
		

}
