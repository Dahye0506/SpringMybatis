package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import Model.GoodsDTO;


public class GoodsRepository {
	@Autowired
	SqlSession sqlSession;//jdbc에있는 sqlsession받아오기
	String namespace = "mappers.goodsMapper";
	String statement;

	public String goodsNum() {
		statement = namespace + ".goodsNum";
		return sqlSession.selectOne(statement);
		
	}
	
	public List<GoodsDTO> goodsList(){
		statement = namespace + ".goodsList";
		return sqlSession.selectList(statement);//리스트에 statement만 넘긴다.

	}
	
		//반환값이 없어서 void처리
	public void goodsWrite(GoodsDTO dto) { //작동안함
		statement = namespace + ".goodsWrite";
		int i = sqlSession.insert(statement, dto);
		System.out.println(i + "행이 추가되었습니다.");
	}
}
