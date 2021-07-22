package controller.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.GoodsOrderCommand;
import command.ReviewCommand;
import service.goods.CartProdDeleteService;
import service.goods.DoPaymentService;
import service.goods.GoodsBuyService;
import service.goods.GoodsCartAddService;
import service.goods.GoodsCartListService;
import service.goods.GoodsCartQtyDownService;
import service.goods.GoodsCartRemoveService;
import service.goods.GoodsOrderService;
import service.goods.GoodsReviewUpdateService;
import service.goods.OrderProcessListService;
import service.goods.ReviewWriteService;

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
	@Autowired
	OrderProcessListService orderProcessListService;
	@Autowired
	DoPaymentService doPaymentService;
	@Autowired
	ReviewWriteService reviewWriteService;
	@Autowired
	GoodsReviewUpdateService goodsReviewUpdateService;
	@Autowired
	CartProdDeleteService cartProdDeleteService;
	@Autowired
	GoodsCartRemoveService goodsCartRemoveService;


	@RequestMapping("goodsCartRemove")
	public String goodsCartRemove(@RequestParam(value ="prodNums") String prodNums,
			HttpSession session) {
		goodsCartRemoveService.cartRemove(prodNums, session);
		return "redirect:goodsCartList";
	}
	
	@RequestMapping("cartProdDel")
	public String cartProdDel(@RequestParam(value = "prodNum") String prodNum,
			HttpSession session) {
		cartProdDeleteService.cartProdDel(prodNum,session);
		return "redirect:goodsCartList";
	}

	//리뷰업데이트
	@RequestMapping("reviewUpdate")
	public String reviewUpdate(ReviewCommand reviewCommand) {
		goodsReviewUpdateService.reviewUpdate(reviewCommand);
		return "redirect:OrderProcessList";
	}
	
	
	//리뷰 수정
	@RequestMapping("goodsReviewUpdate")
	public String ReviewUpdate(
			@RequestParam(value = "purchaseNum") String purchaseNum,
			@RequestParam(value = "prodNum") String prodNum,
			HttpSession session,Model model) {
		//리뷰 정보 가져올려면 서비스 필요 (GoodsReviewUpdateService)
		goodsReviewUpdateService.reviewInfo(purchaseNum, prodNum, session, model);
		return ("goods/goodsReviewModify");
	}	
	
	
	@RequestMapping(value = "reviewWrite", method = RequestMethod.POST)
	public String reviewWrite(ReviewCommand reviewCommand,
			HttpSession session) {
		reviewWriteService.reviewWrite(reviewCommand,session);
		return "redirect:OrderProcessList";
	}
	
	//리뷰작성
	@RequestMapping("goodsReview")
	public String goodsReview(
			@ModelAttribute(value = "purchaseNum") String purchaseNum,
			@ModelAttribute(value = "prodNum") String prodNum) {
		return "goods/goodsReview";
	}	


	@RequestMapping(value="doPayment",  method = RequestMethod.POST)
	public String doPayment(
			@RequestParam(value="purchaseNum") String purchaseNum,
			@RequestParam(value = "paymentApprPrice") String paymentApprPrice,
			@RequestParam(value = "paymentNumber") String paymentNumber,
			HttpSession session,Model model) {
		doPaymentService.payment(purchaseNum,paymentApprPrice,
				paymentNumber, session, model);
		return "goods/buyfinished";
	}
	
	
	@RequestMapping("OrderProcessList")
	public String purchaseCon(HttpSession session, Model model) {
		orderProcessListService.orderList(session, model);
		return "goods/purchaseCon";
	}	
	
	
	//결제 페이지
	@RequestMapping("goodsOrder")
	public String goodsOrder(GoodsOrderCommand goodsOrderCommand, HttpSession session) {
							//커멘드객체생성 후 받아오기, 테이블에 전달하기 위해 HttpSession 필요
		//GoodsOrderCommand가 가지고있는 값을 구매 테이블에 전달
		String purchaseNum = goodsOrderService.goodsOrder(goodsOrderCommand, session);
		return "redirect:paymentOk?purchNo="+purchaseNum 
				+"&payPrice="+goodsOrderCommand.getPurchaseTotPrice();
	}
	
	//페이먼트페이지로 이동
	@RequestMapping("paymentOk")
	public String paymentOk(
			@ModelAttribute(value="purchNo") String purchNo,
			@ModelAttribute(value="payPrice") String payPrice) {
		
		System.out.println(purchNo+"purchNo");
		System.out.println(payPrice+"payPrice");
		return "goods/payment";//payment.jsp로 이동
		
	}
	
	
	// 장바구니 구매하기
	@RequestMapping(value = "goodsBuy" , method = RequestMethod.POST)
	public String goodsBuy(
			@RequestParam(value="prodCk") String [] prodNums,
			HttpSession session, Model model) {
		//goodsCart 가져올 벨류명 prodCk
		//HttpSession 저장되는 값, Model 저장하여 가져올곳
		goodsBuyService.goodsBuy(prodNums, session, model);
		System.out.println(prodNums+"개 주문하기");
		return "goods/order";	//order.jsp 파일 열기
	}

	@RequestMapping(value="goodsCartQtyDown" , method = RequestMethod.GET)
	public String goodsCartQtyDown(
			@RequestParam(value="prodNum") String prodNum,
			@RequestParam(value = "prodPrice") int prodPrice,
			HttpSession session) {
		goodsCartQtyDownService.goodsQtyDown(prodNum, prodPrice, session);
		return "redirect:goodsCartList";
	}
	

	@RequestMapping("goodsCartList")
	public String goodsCartList(HttpSession session, Model model) {
		goodsCartListService.cartList(session, model);
		return "goods/goodsCart";
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
	
	
	
	
	
	
	
}