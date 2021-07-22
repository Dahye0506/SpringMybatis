package service.goods;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import Model.AuthInfoDTO;
import Model.CartDTO;
import Model.PurchaseDTO;
import command.GoodsOrderCommand;
import repository.GoodsRepository;

public class GoodsOrderService {
	@Autowired
	GoodsRepository goodsRepository;
	
	//구매번호를 리턴해줘야 하기때문에 void 가 아니라 String
	public String goodsOrder(GoodsOrderCommand goodsOrderCommand, HttpSession session) {
						//굿즈커멘드를 받아오고, 누가 구매했는지 알아야함
		PurchaseDTO dto = new PurchaseDTO();
		
		dto.setPurchaseAddr(goodsOrderCommand.getPurchaseAddr());
		dto.setPurchaseMethod(goodsOrderCommand.getPurchaseMethod());
		dto.setPurchaseRequest(goodsOrderCommand.getPurchaseRequest());
		dto.setPurchaseTotPrice(goodsOrderCommand.getPurchaseTotPrice());
		dto.setReceiverName(goodsOrderCommand.getReceiverName());
		dto.setReceiverPhone(goodsOrderCommand.getReceiverName());
		
		//구매번호 날짜,시간으로 생성
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String purchaseNum =  df.format(new Date());
		dto.setPurchaseNum(purchaseNum);
		/////////////////////////////////여기까지 구매번호 생성 소스
		
		
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
		dto.setMemId(authInfo.getUserId());
		goodsRepository.purchaseInsert(dto);// 받아온것 리포시토리에 삽입
		
				
		
		//카트로부터 가져와서 구매리스트에 저장(넘기기)
		String [] prodNums = goodsOrderCommand.getProdNums().split(",");
		for(String prodNum : prodNums) {//1:1로 한개씩 가져오기
			// 구매리스트에는 구매 번호가 있어야한다.
			CartDTO d = new CartDTO();
			d.setPurchaseNum(purchaseNum);
			d.setMemId(authInfo.getUserId());
			d.setProdNum(prodNum);
			int i = goodsRepository.purchaseListInsert(d);//구매 리스트에 전달
			
			if(i == 1) {
				goodsRepository.cartDelete(d);//장바구니에서 삭제가 되었다면 d 넘겨주기
			}
			
		}
		
		return purchaseNum; // 상품을 구매하기 위해서는 구매 번호를 return 해야한다.
	}

}
