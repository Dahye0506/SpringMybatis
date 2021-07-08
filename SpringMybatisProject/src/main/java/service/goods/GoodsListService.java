package service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import Model.GoodsDTO;
import repository.GoodsRepository;

public class GoodsListService {
	@Autowired
	GoodsRepository goodsRepository;
	public void goodsList(Model model) {
				//리스트페이지로 값 전달
		List<GoodsDTO> list = goodsRepository.goodsList();//리스트 받아오기
		model.addAttribute("lists", list);
	}

}
