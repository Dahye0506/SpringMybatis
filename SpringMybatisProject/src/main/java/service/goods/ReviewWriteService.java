package service.goods;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import Model.AuthInfoDTO;
import Model.ReviewDTO;
import command.ReviewCommand;
import repository.GoodsRepository;

public class ReviewWriteService {
	@Autowired
	GoodsRepository goodsRepository;
	public void reviewWrite(ReviewCommand reviewCommand, HttpSession session) {
		ReviewDTO dto = new ReviewDTO();
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
		dto.setMemId(authInfo.getUserId());
		dto.setProdNum(reviewCommand.getProdNum());
		dto.setPurchaseNum(reviewCommand.getPurchaseNum());
		dto.setReviewContent(reviewCommand.getReviewContent());
		
		if(!reviewCommand.getReviewImg().getOriginalFilename().isEmpty()) {//이미지가 비어있지않다면
			MultipartFile mf = reviewCommand.getReviewImg();
			String original = mf.getOriginalFilename();
			//확장자 가져오기
			String originalExt = original.substring(original.lastIndexOf("."));
			//스토어파일네임
			String store = UUID.randomUUID().toString().replace("-", "") + originalExt;
			
			//이미지 가져오기(경로생성)
			String realPath = session.getServletContext().getRealPath("goods/upload");
			File f = new File(realPath + "/" + store);
			try{mf.transferTo(f);}catch(Exception e) {e.printStackTrace();}
			dto.setReviewImg(store);
		}
		goodsRepository.reviewWrite(dto);
	}
}

