package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import command.MemberCommand;

public class MemberCommandValidator implements Validator{
				//멤버커맨드 검사용                         인터페이스(추상메서드)
	public boolean supports(Class<?> clazz) {
		return false;
	}

	public void validate(Object target, Errors errors) {
	      MemberCommand memberCommand = (MemberCommand)target;
	      if(memberCommand.getMemPw()!=null && memberCommand.getMemPwCon() != null) {
	         if(!memberCommand.isMemPwEqualsMemPwCon()) {
	         errors.rejectValue("memPwCon", "noMatch");
	      }
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memId", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memPw", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memPwCon", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memName", "required");
	      ValidationUtils.rejectIfEmpty(errors, "postNumber", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memAddress", "required");
	      ValidationUtils.rejectIfEmpty(errors, "detailAdd", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memPhone", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memBirth", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memGender", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memAccount", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memEmail", "required");
	      ValidationUtils.rejectIfEmpty(errors, "memEmailCk", "required");
	      }
	   }
	}