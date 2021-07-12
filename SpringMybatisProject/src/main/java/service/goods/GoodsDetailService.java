package service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import Model.GoodsDTO;
import repository.GoodsRepository;

//
public class GoodsDetailService {
	@Autowired
	GoodsRepository goodsRepository;
	public void goodsDetail(String prodNum, Model model) {
		//리포시토리부터 받아온'값'을 jsp에 전달 -> 이때 모델 필요
		//컨텍스트에 등록해야 사용할수있음 "spring-goods" 에 등록
		GoodsDTO dto1 = goodsRepository.goodsDetail(prodNum);
		model.addAttribute("goodsCommand",dto1);
		//model.addAttribute("속성명", 속성값);
		//속성명을  jsp페이지에서 받는다.
		//                  변수명 아무거나 작성가능하나 가독성을 높이기위해 dto로 생성
	
		
	}
}
