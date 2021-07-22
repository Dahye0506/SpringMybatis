package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import Model.MemberDTO;

public class MemberRepository {//컨텍스트에등록
	@Autowired
	SqlSession sqlSession;
	
	String namespace ="mappers.memberMapper"; //네임스페이스이름과 마이바티스 매퍼이름은 같지않아도 됨
	String statement; //
	
	
	public String idFind(MemberDTO dto) {
		statement = namespace + ".idFind";
		return sqlSession.selectOne(statement, dto);
	}
	//멤버업데이트
	public int updateCkOk(MemberDTO dto) {
		statement = namespace +".updateCkOk";
		return sqlSession.update(statement, dto);
	}
	public void memDelete(String memId) {
		statement = namespace +".memDelete";
		sqlSession.delete(statement, memId);
	}
	public void memPwUpdate(MemberDTO dto) {
		statement = namespace +".memPwUpdate";
		sqlSession.update(statement, dto);
	}
	public MemberDTO memInfo(String memId) {
		statement = namespace + ".memInfo";
		return sqlSession.selectOne(statement, memId);
	}
	//회원 삭제
	public void memDel(String memId) {
		statement = namespace +".memDel";
		sqlSession.delete(statement, memId);
	}
	//수정
	public void memUpdate(MemberDTO dto) {
		statement = namespace + ".memUpdate";
		sqlSession.update(statement, dto);
	}
	public Integer getMemberCount() {
		statement = namespace +".getMemberCount";
		return sqlSession.selectOne(statement);
	}

	public List<MemberDTO> memList(MemberDTO dto) {
		statement = namespace +".memList";
		return sqlSession.selectList(statement,dto);
	}
	
	//반환형 없으면 void //반환형 있으면 String
	public void memJoin(MemberDTO dto) {
		statement = namespace + ".memJoin";
		sqlSession.insert(statement, dto);
	}
	


	
}
