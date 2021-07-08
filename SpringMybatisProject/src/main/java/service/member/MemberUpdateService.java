package service.member;

import org.springframework.beans.factory.annotation.Autowired;

import Model.MemberDTO;
import command.MemberCommand;
import repository.MemberRepository;

public class MemberUpdateService {
	@Autowired
	MemberRepository memberRepository; //리포시토리로 넘겨줌
	public void memUpdate(MemberCommand memberCommand ) {//서비스 만드면 항상 컨텍스트에 등록
		MemberDTO dto = new MemberDTO();
		dto.setDetailAdd(memberCommand.getDetailAdd());
		dto.setMemAccount(memberCommand.getMemAccount());
		dto.setMemAddress(memberCommand.getMemAddress());
		dto.setMemEmail(memberCommand.getMemEmail());
		dto.setMemEmailCk(memberCommand.getMemEmailCk());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setPostNumber(memberCommand.getPostNumber());
		dto.setMemId(memberCommand.getMemId());
		memberRepository.memUpdate(dto);
		
	}

}
