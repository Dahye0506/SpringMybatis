package service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import Model.MemberDTO;
import repository.MemberRepository;

public class MemberListService {
	//jsp페이지에 전달
	@Autowired
	MemberRepository memberRepository; //받아오기 
	public void memList(Model model, String memId) {
		List<MemberDTO> list = memberRepository.memList(memId);
		model.addAttribute("lists",list);
	}

}