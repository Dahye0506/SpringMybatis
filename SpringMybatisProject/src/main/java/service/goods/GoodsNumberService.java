package service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import repository.GoodsRepository;
			// GoodsNumberService => 컨텍스트 등록
public class GoodsNumberService {//서비스의 역활: 데이터를 데이터디비에 전달하거나, 디비로부터 가져온 값을 model에 저장
	@Autowired
	GoodsRepository goodsRepository;//데이터를 디비에서 가져오기위해 리포시토리 생성
	public void goodsNum(Model model) {//db로부터 goodsNum을 가져오면 goodsJoin으로 넘겨줘야한다. 이때 Model이 필요
		String goodsNum = goodsRepository.goodsNum();
		//db로부터 가져온 goodsnumber는 goodsJoin페이지로 전달, 이때 모델(Model)필요
		model.addAttribute("goodsNum", goodsNum);
	}
}
