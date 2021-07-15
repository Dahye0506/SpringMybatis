package controller.goods;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.GoodsOrderCommand;
import service.goods.GoodsBuyService;
import service.goods.GoodsCartAddService;
import service.goods.GoodsCartListService;
import service.goods.GoodsCartQtyDownService;
import service.goods.GoodsOrderService;

@Controller
@RequestMapping("cart")
public class GoodsCartController {
	@Autowired
	GoodsCartAddService goodsCartAddService;
	@Autowired
	GoodsCartListService goodsCartListService;
	@Autowired
	GoodsCartQtyDownService goodsCartQtyDownService;
	@Autowired
	GoodsBuyService goodsBuyService;
	@Autowired
	GoodsOrderService goodsOrderService;


	
	
	@RequestMapping("goodsCartList")
	public String goodsCartList(HttpSession session, Model model) {
		goodsCartListService.cartList(session, model);
		return "goods/goodsCart";
	}
	@RequestMapping(value="goodsCartQtyDown" , method = RequestMethod.GET)
	public String goodsCartQtyDown(
			@RequestParam(value="prodNum") String prodNum,
			@RequestParam(value = "prodPrice") int prodPrice,
			HttpSession session) {
		goodsCartQtyDownService.goodsQtyDown(prodNum, prodPrice, session);
		return "redirect:goodsCartList";
	}
	@RequestMapping(value = "goodsCartAdd", method = RequestMethod.GET)
	public String goodsCartQtyAdd(
			@RequestParam(value="cartQty") int cartQty,
			@RequestParam(value="prodNum") String prodNum,
			@RequestParam(value="prodPrice") int prodPrice,
			Model model,HttpSession session) {
		goodsCartAddService.cartAdd(cartQty, prodNum,prodPrice,session,model);
		return "redirect:goodsCartList";
	}
	@RequestMapping(value = "goodsCartAdd" ,method = RequestMethod.POST)
	public String goodsCartAdd(
			@RequestParam(value="cartQty") int cartQty,
			@RequestParam(value="prodNum") String prodNum,
			@RequestParam(value="prodPrice") int prodPrice,
			Model model,HttpSession session) {
		goodsCartAddService.cartAdd(cartQty, prodNum,prodPrice,session,model);
		return "goods/cartAdd";
	}

	// 장바구니 구매하기
	@RequestMapping(value="goodsBuy", method = RequestMethod.POST)
	public String goodsBuy(
			@RequestParam(value="prodCk") String [] prodNums,
			HttpSession session, Model model) {
		//goodsCart 가져올 벨류명 prodCk
		//HttpSession 저장되는 값, Model 저장하여 가져올곳
		goodsBuyService.goodsBuy(prodNums, session, model);
		return "goods/order";	//order.jsp 파일 열기
	}
	
	//결제 페이지
	@RequestMapping("goodsOrder")
	public String goodsOrder(GoodsOrderCommand goodsOrderCommand, HttpSession session) {
							//커멘드객체생성 후 받아오기, 테이블에 전달하기 위해 HttpSession 필요
		//GoodsOrderCommand가 가지고있는 값을 구매 테이블에 전달
		String purchaseNum = 
				goodsOrderService.goodsOrder(goodsOrderCommand, session);
		return "redirect:paymentOk?purchNo=" + purchaseNum + "&payPrice="+goodsOrderCommand.getPurchaseTotPrice();
	}
	@RequestMapping("paymentOk")
	public String paymentOk(
			@ModelAttribute(value="purchNo") String purchNo, 
			@ModelAttribute(value="payPrice") String payPrice ) {
		return "goods/payment";//payment.jsp로 이동
	}
	
}