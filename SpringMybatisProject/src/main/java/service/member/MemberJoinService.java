package service.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Model.MemberDTO;
import command.MemberCommand;
import controller.MailService;
import repository.MemberRepository;

public class MemberJoinService {
	@Autowired
	MailService mailService;//메일받아오기
	
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	public void memJoin(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		dto.setDetailAdd(memberCommand.getDetailAdd());
		dto.setMemAccount(memberCommand.getMemAccount());
		dto.setMemAddress(memberCommand.getMemAddress());
		dto.setMemBirth(memberCommand.getMemBirth());
		dto.setMemEmail(memberCommand.getMemEmail());
		dto.setMemEmailCk(memberCommand.getMemEmailCk());
		dto.setMemGender(memberCommand.getMemGender());
		dto.setMemId(memberCommand.getMemId());
		dto.setMemName(memberCommand.getMemName());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setMemPw(bcryptPasswordEncoder.encode(memberCommand.getMemPw()));
		dto.setPostNumber(memberCommand.getPostNumber());
		memberRepository.memJoin(dto);
		
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMddHHmmss");
		String num = dateForm.format(new Date());
		//http주소 뒤에있는 num 받아오는값
		String subject = "가입을 환여합니다.";
		String content = "<html><body>"
					    + "안녕하세요." + dto.getMemId() + "님 가입을 축하드립니다. </br>"
					    + "아래 가입확인을 눌러야 가입이 완료됩니다.</br>"
					    + "<a href='http://128.134.178.158/SpringMybatisProject/register/memberMail?"
					    + "num="+num
						+ "&reciver="+dto.getMemEmail()+"'>가입을 완료하시려면 클릭하세요</a>"
						+ "</body></html>";
		
		//메일
		try {
			mailService.sendMail(dto.getMemEmail(), content, subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}