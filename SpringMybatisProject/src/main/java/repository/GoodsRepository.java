package repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import Model.CartDTO;
import Model.GoodsDTO;
import Model.GoodsReviewsDTO;
import Model.OrderListDTO;
import Model.PaymentDTO;
import Model.ProductCartDTO;
import Model.PurchaseDTO;
import Model.ReviewDTO;
import Model.WishDTO;


public class GoodsRepository {
	@Autowired
	SqlSession sqlSession;//jdbc에있는 sqlsession받아오기
	String namespace = "mappers.goodsMapper";
	String statement;
	
	
	public Integer wishCount(WishDTO dto) {
		statement = namespace + ".wishCount";
		return sqlSession.selectOne(statement, dto);
	}
	
	
	//wish
	public int wishAdd(WishDTO dto) {
		statement = namespace + ".wishAdd";
		sqlSession.insert(statement, dto);
		
		statement = namespace + ".wishCount";
		return sqlSession.selectOne(statement, dto);
	}
	
//	create table wish(
//			PROD_NUM NUMBER NOT NULL,
//			MEM_ID VARCHAR2(20) NOT NULL
//			);	
	
	public void cartRemove(Map<String, Object> condition) {
		statement = namespace + ".cartRemove";
		sqlSession.delete(statement, condition);
	}
	public void cartProdDel(CartDTO dto) {
		statement = namespace + ".cartProdDel";
		sqlSession.delete(statement, dto);
	}
	
	
	//상품밑에 리뷰
	public GoodsReviewsDTO goodsReviews(String prodNum) {
		statement = namespace + ".goodsReviews";
		return sqlSession.selectOne(statement, prodNum);
	}
	// 리뷰 업데이트
	public void reviewUpdate(ReviewDTO dto) {
		statement = namespace + ".reviewUpdate";
		sqlSession.update(statement, dto);
	}
	
	
	//리뷰 수정
	public ReviewDTO reviewInfo(ReviewDTO dto) {
		statement = namespace + ".reviewInfo";
		return sqlSession.selectOne(statement, dto);
	}
		
	//리뷰
	public void reviewWrite(ReviewDTO dto) {
		statement = namespace + ".reviewWrite";
		sqlSession.insert(statement, dto);
	}
	
	public void payment(PaymentDTO dto) {
		statement = namespace + ".payment";
		sqlSession.insert(statement, dto);
	}
	public List<OrderListDTO> baseOrderList(String memId){
		statement = namespace + ".baseOrderList";
		return sqlSession.selectList(statement, memId);
		
	}	
	
	//goodsOrder에 전달및 저장
	public int purchaseListInsert(CartDTO dto) {
		statement = namespace + ".purchaseListInsert";
		
		//확인용
		System.out.println("★★★★★★★★★★입력값 확인용 ★★★★★★★★★★★★★");
		System.out.println(dto.getCartPrice());
		System.out.println(dto.getCartQty());
		System.out.println(dto.getMemId());
		System.out.println(dto.getProdNum());
		System.out.println(dto.getPurchaseNum());
		
		return sqlSession.insert(statement, dto);
		
		
	}
	// 장바구니 상품삭제
	public void cartDelete(CartDTO dto) {
		statement = namespace + ".cartDelete";
		sqlSession.delete(statement, dto);
	}	
	
	//goodsOrderservice 에 넘기기
	public void purchaseInsert(PurchaseDTO dto) {
		statement = namespace + ".purchaseInsert";
		//sql에 넘기기
		sqlSession.insert(statement, dto);
		
		//확인용
		System.out.println("★★★★★★★★★★시발..여기서도 안됨..입력값 확인용 ★★★★★★★★★★★★★");
		System.out.println(dto.getPurchaseNum());
		System.out.println(dto.getMemId());
		System.out.println(dto.getPurchaseTotPrice());
		System.out.println(dto.getPurchaseAddr());
		System.out.println(dto.getPurchaseMethod());
		System.out.println(dto.getPurchaseRequest());
		System.out.println(dto.getReceiverName());
		System.out.println(dto.getReceiverPhone());
		System.out.println(dto.getPurchaseDate());
		System.out.println("★★★★★★★★★★입력값 확인용 ★★★★★★★★★★★★★");

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
	
	//반환값이 없어서 void처리
	public void goodsDel(String prodNum) {
		statement = namespace +".goodsDel";
		sqlSession.delete(statement, prodNum);
	}	
	
	
	public void goodsUpdate(GoodsDTO dto) {
		statement = namespace +".goodsUpdate";
		sqlSession.update(statement, dto);
	}	
	
	
	//제품상세페이지 받아오기
	public GoodsDTO goodsDetail(String prodNum) {
		statement = namespace + ".goodsDetail";
		return sqlSession.selectOne(statement, prodNum);
	}

	public List<GoodsDTO> goodsList(){
		statement = namespace + ".goodsList";
		return sqlSession.selectList(statement);//리스트에 statement만 넘긴다.
	}
	
	public void goodsInsert(GoodsDTO dto) {
		statement = namespace + ".goodsInsert";
		sqlSession.insert(statement, dto);
	}
	
	public String goodsNum() {
		statement = namespace + ".goodsNum";
		return sqlSession.selectOne(statement);
	}
}