package service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import Model.GoodsDTO;
import repository.GoodsRepository;
//jsp로 : 모델
//jsp에서: 커맨드
public class GoodsListService {
	@Autowired
	GoodsRepository goodsRepository;
	public void goodsList(Model model) {
				//등록한 상품을(값)을 가져오려면 무줙건 Model이 필요하다.
				//리스트페이지로 값 전달
		List<GoodsDTO> list = goodsRepository.goodsList();//리스트 받아오기
		model.addAttribute("lists", list);
	}

}
