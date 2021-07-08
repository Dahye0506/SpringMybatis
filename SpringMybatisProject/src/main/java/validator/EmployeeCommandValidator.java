package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import command.EmployeeCommand;

public class EmployeeCommandValidator implements Validator{
									  //Validator 상속 받기
	public boolean supports(Class<?> clazz) {
		return false;
	}

	public void validate(Object target, Errors errors) {
						//Object target이    (EmployeeCommand)target 로 부터 받아짐
		EmployeeCommand employeeCommand = (EmployeeCommand)target;

		//패스워드가 일치하는지
		if(!employeeCommand.isEmpPwEqualsempPwCon()) {
			//비밀번호가 일치하지 않는다면 에러메세지 생성
			errors.rejectValue("empPwCon", "nomatch");
			
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeId", "required");
		//공백문자 or 비어있다면 거절(reject)하겠다.    (에러가 있다,employeeId에, 리콰이어드메세지)
		ValidationUtils.rejectIfEmpty(errors, "empUserid", "required");
		ValidationUtils.rejectIfEmpty(errors, "empPw", "required");
		ValidationUtils.rejectIfEmpty(errors, "empPwCon", "required");
		ValidationUtils.rejectIfEmpty(errors, "empName", "required");
		ValidationUtils.rejectIfEmpty(errors, "hireDate", "required");
		ValidationUtils.rejectIfEmpty(errors, "jobId", "required");
		ValidationUtils.rejectIfEmpty(errors, "phNumber", "required");
		ValidationUtils.rejectIfEmpty(errors, "officeNumber", "required");
		ValidationUtils.rejectIfEmpty(errors, "email", "required");
		ValidationUtils.rejectIfEmpty(errors, "empAddress", "required");
	}

	          
}
