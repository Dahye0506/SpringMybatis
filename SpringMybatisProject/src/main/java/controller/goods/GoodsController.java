package controller.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import command.GoodsCommand;
import service.goods.GoodsListService;
import service.goods.GoodsNumberService;
import service.goods.GoodsWriteService;
import validator.GoodsCommandValidator;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsNumberService goodsNumberService;
	@Autowired
	GoodsWriteService goodsWriteService;
	@Autowired
	GoodsListService goodsListService;

	//상품리스트
	@RequestMapping("goodsList")
	public String list(Model model) {
		//서비스 필요
		goodsListService.goodsList(model);
		return "goods/goodsList";
	}

	//상품등록
	@RequestMapping("goodsRegist")
	public String regist(Model model) {
		//Model : 자바에서 만들어진 값을 jsp에 전달하기 위해 사용하는것
		goodsNumberService.goodsNum(model);//db에서 상품번호 가져오기
		return "goods/goodsJoin";//goodsJoin.jsp파일 열기 이때 시퀀스때문에 db에 먼저 갔다와야함
	}
	
	//상품 등록 버튼누르면 / 굿즈 조인은 레지스트로부터 값 받는곳 
	@RequestMapping(value="goodsJoin", method=RequestMethod.POST)
	public String join(GoodsCommand goodsCommand, Errors errors, HttpSession session) {
		new GoodsCommandValidator().validate(goodsCommand, errors);
		if (errors.hasErrors()) {
			return "goods/goodsJoin";
					//에러가 생기면 다시 상품등록으로 열리게 설정
		}else {
		//에러가없다면 굿즈커멘드에 값을 저장하고 그값은 goodswirte에 있음 
		goodsWriteService.goodsWrite(goodsCommand,session);
		return "redirect:/goods/goodsList";
		}
	}
	
	

	
	
}
