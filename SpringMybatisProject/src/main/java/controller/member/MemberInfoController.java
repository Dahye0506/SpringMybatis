package controller.member;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.activation.URLDataSource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.MemberCommand;
import service.member.MemberDeleteService;
import service.member.MemberListService;
import service.member.MemberUpdateService;


@Controller
@RequestMapping("member")
public class MemberInfoController {
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberUpdateService  memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	
	//회원 강퇴
	@RequestMapping ("memDel")
	public String memDel(@RequestParam(value = "memId") String memId) {
		memberDeleteService.memDel(memId);
		//return "redirect:memList";// 회원탈퇴,강퇴 후  멤버리스트로 이동
		return "redirect:/"; //이렇게하면 메인페이지로 간다.
	}
	
	//회원 수정
	@RequestMapping(value="memModifyOk", method = RequestMethod.POST)
	public String memUpdate(MemberCommand memberCommand) {
		memberUpdateService.memUpdate(memberCommand);
		String encodedParam = "";
		try {
			encodedParam = URLEncoder.encode(memberCommand.getMemId(),"utf-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//MemberCommand를 memberUpdateServie로 넘겨야한다.
		//return "redirect:memList";
		System.out.println(memberCommand.getMemId());
		return "redirect:memInfo/" + encodedParam;
		//회원리스트페이지가 아니라 인포페이지로
	}
	
		
	@RequestMapping("memList")
	public String memList(Model model) {
		memberListService.memList(model,null);
		return "member/memberList"; //멤버리스트 가져오기
	}
	
	
	@RequestMapping("memInfo/{memId}")
	public String memInfo(
			@PathVariable(value = "memId") String memId, Model model) { //모델에 담아서 받아오기
			//{memId} 안에 있는 값을 String memId로 받아오겠다.
		memberListService.memList(model,memId);
		return "member/memberInfo";
	}
	
	//memMod 수정버튼 누르면
	@RequestMapping("memMod/{memId}")
	public String memMod(
			@PathVariable(value = "memId") String memId, Model model) { //모델에 담아서 받아오기
			//{memId} 안에 있는 값을 String memId로 받아오겠다.
		memberListService.memList(model, memId);
		return "member/memberModify";
	}
	
	

}