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
	
	public void goodsInsert(GoodsDTO dto) {
		statement = namespace + ".goodsInsert";
		sqlSession.insert(statement, dto);
	}
	
	public List<GoodsDTO> goodsList(){
		statement = namespace + ".goodsList";
		return sqlSession.selectList(statement);//리스트에 statement만 넘긴다.

	}
	
		//반환값이 없어서 void처리
	
	//제품상세페이지 받아오기
	public GoodsDTO goodsDetail(String prodNum) {
		statement = namespace + ".goodsDetail";
		return sqlSession.selectOne(statement, prodNum);

	}
	public void goodsUpdate(GoodsDTO dto) {
		statement = namespace +".goodsUpdate";
		sqlSession.update(statement, dto);
	}
	
	
}
