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
	
	
	public void goodsWrite(GoodsDTO dto) { //작동안함
		statement = namespace + ".goodsWrite";
		sqlSession.insert(statement,dto);
	}
}
