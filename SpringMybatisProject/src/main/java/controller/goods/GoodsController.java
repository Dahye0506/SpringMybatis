package controller.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import command.GoodsCommand;
import service.goods.GoodsDeleteService;
import service.goods.GoodsDetailService;
import service.goods.GoodsListService;
import service.goods.GoodsNumberService;
import service.goods.GoodsUpdateService;
import service.goods.GoodsWriteService;
import validator.GoodsCommandValidate;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsNumberService goodsNumberService;
	@Autowired
	GoodsWriteService goodsWriteService;
	@Autowired
	GoodsDetailService goodsDetailService;
	@Autowired
	GoodsUpdateService goodsUpdateService;
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	GoodsDeleteService goodsDeleteService;

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
	@RequestMapping(value="goodsJoin")
	public String join(GoodsCommand goodsCommand, Errors errors, HttpSession session) {
		new GoodsCommandValidate().validate(goodsCommand, errors);
		if (errors.hasErrors()) {
			return "goods/goodsJoin";
					//에러가 생기면 다시 상품등록으로 열리게 설정
		}else {
		//에러가없다면 굿즈커멘드에 값을 저장하고 그값은 goodswirte에 있음 
		goodsWriteService.goodsWrite(goodsCommand,session);
		return "redirect:goodsList";
		}
	}
	
	@RequestMapping("prodDetail")
	public String prodDetail(@RequestParam(value="prodNum") String prodNum, Model model) {
							//                      	가독성을 높이기위해 변수명 같게 작성함
		// 디비로부터 자료를 가져오기 위해서 리포시토리 생성
		// 리포시토리 : 디비에 접속하기 위해
		// 서비스 : 리포시토리에 데이터를 받아오거나, 전달하기 위해서 사용
		goodsDetailService.goodsDetail(prodNum, model);
		return "goods/goodsDetail";
	}
	@RequestMapping("prodModify")
	public String prodModify(
		@RequestParam(value="prodNum") String prodNum, Model model) {
		goodsDetailService.goodsDetail(prodNum, model);
		//수정페이지 만들기
		return "goods/goodsModify";
	}
	
	@RequestMapping("goodsUpdate")
	public String goodsUpdate(GoodsCommand goodsCommand, 
			Errors errors, HttpSession session) {//session은 로그인값이 아닌 세션을 파일을 저장하기위한 경로를 알아내기위해 사용함
		new GoodsCommandValidate().validate(goodsCommand, errors);
		if(errors.hasErrors()) {
			// 값을 command로 받았으므로 오류 발생하여 값을 보낼때 다시 
			// command로 전달된다.
			return "goods/goodsModify";
		}
		goodsUpdateService.goodsUpdate(goodsCommand, session);//session은 로그인값이 아닌 세션을 파일을 저장하기위한 경로를 알아내기위해 사용함
		return "redirect:/goods/goodsList";
	}
	
	//상품 삭제 하고 상품 리스트로 가라
	@RequestMapping("goodsDel")
	public String goodsDel(@RequestParam(value="prodNum")String prodNum,HttpSession session) {
		//정보를 전달하기 위해서는 서비스 필요
		goodsDeleteService.goodsDel(prodNum,session);
		
		return "redirect:goodsList";//상품리스트로 이동

		
	}

	
}
