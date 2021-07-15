package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import Model.CartDTO;
import Model.GoodsDTO;
import Model.ProductCartDTO;
import Model.PurchaseDTO;


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
	
	
	//제품상세페이지 받아오기
	public GoodsDTO goodsDetail(String prodNum) {
		statement = namespace + ".goodsDetail";
		return sqlSession.selectOne(statement, prodNum);

	}
	public void goodsUpdate(GoodsDTO dto) {
		statement = namespace +".goodsUpdate";
		sqlSession.update(statement, dto);
	}
	
	//반환값이 없어서 void처리
	public void goodsDel(String prodNum) {
		statement = namespace +".goodsDel";
		sqlSession.delete(statement, prodNum);
	}
	
	public void goodsQtyDown(CartDTO dto) {
		statement = namespace + ".goodsQtyDown";
		sqlSession.update(statement, dto);
	}
	public ProductCartDTO cartList(CartDTO dto) {
		statement = namespace + ".cartList";
		return sqlSession.selectOne(statement, dto);
	}
	public List<String> memProdNum(String memId){
		statement = namespace + ".memProdNum";
		return sqlSession.selectList(statement,memId);
	}
	public int cartAdd(CartDTO dto) {
		statement = namespace + ".cartAdd";
		return sqlSession.insert(statement, dto);
	}
	
	//goodsOrderservice 에 넘기기
	public void purchseInsert(PurchaseDTO dto) {
		statement = namespace + ".purchseInsert";
		//sql에 넘기기
		sqlSession.insert(statement, dto);
	}
	
	//goodsOrder에 전달및 저장
	public int purchseListInsert(CartDTO dto) {
		statement = namespace + ".purchseListInsert";
		return sqlSession.insert(statement, dto);
	}
	

	// 장바구니 상품삭제
	public void cartDelete(CartDTO dto) {
		statement = namespace + ".cartDelete";
		sqlSession.delete(statement, dto);
	}
	
	
	
	
}
