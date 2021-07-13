  
package service.goods;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import Model.GoodsDTO;
import command.GoodsCommand;
import repository.GoodsRepository;

public class GoodsUpdateService {
	@Autowired
	GoodsRepository goodsRepository;
	public void goodsUpdate(GoodsCommand goodsCommand, HttpSession session) {
		GoodsDTO dto = new GoodsDTO();
		//컨텐츠 수정
		dto.setProdNum(goodsCommand.getProdNum());
		dto.setCtgr(goodsCommand.getCtgr());
		dto.setProdCapacity(goodsCommand.getProdCapacity());
		dto.setProdDelFee(goodsCommand.getProdDelFee());
		dto.setProdDetail(goodsCommand.getProdDetail());
		dto.setProdPrice(goodsCommand.getProdPrice());
		dto.setProdSupplyer(goodsCommand.getProdSupplyer());
		dto.setRecommend(goodsCommand.getRecommend());
		//파일 수정
		String [] fileNames = goodsCommand.getFileDel1().split(",");
		
		//디비에 저장되어있는 파일명을 가져오기 위함
		GoodsDTO dto1 =  goodsRepository.goodsDetail(goodsCommand.getProdNum().toString());
		
		//이미 저장되어 있는 이미지 파일명을 먼저 저장
		dto.setProdImage(dto1.getProdImage());
		//파일 추가
		String realPath = session.getServletContext().getRealPath("WEB-INF/view/goods/upload");
		
		//새로운 파일 추가하기위해 변수 생성
		String storeFile = "";
		if(goodsCommand.getProdImage()[0].getOriginalFilename() != "" ) {//이미지가  ""(빈공간)이 아니라면
			for(MultipartFile mf : goodsCommand.getProdImage()) {
				String original = mf.getOriginalFilename();
				String fileNameExt = original.substring(
						original.lastIndexOf("."));
				String store = // 랜덤으로 문자열을 받아옴
						UUID.randomUUID().toString().replace("-", "")
						+fileNameExt;
				File file = new File(realPath + "/" + store);
				try {mf.transferTo(file);} 
				catch (Exception e) {e.printStackTrace();} 
				storeFile = storeFile + store + ","; 
			}
		}
				
		String goodsFileName = dto1.getProdImage();
				
		if(!fileNames[0].equals("")) {//이미지 파일이 있다면
			//파일삭제
			for(String s : fileNames) {
				//db에있는 이미지 파일명을 제거(replace)
				String delfile = s+ ",";
				System.out.println(delfile);
				goodsFileName = goodsFileName.replace(delfile,"");//파일명변경
				File file = new File(realPath + "/" + s);
				if(file.exists()) {file.delete();}
			}
			//이미지 파일이 변경된 경우, 수정된 내용으로 다시 저장
			dto.setProdImage(goodsFileName);
		}
		if(dto.getProdImage() != null){
			dto.setProdImage(storeFile + dto.getProdImage());
		}else {
			dto.setProdImage(storeFile);
		}//추가된 파일에, 기존에 생성된 파일 
		
		goodsRepository.goodsUpdate(dto);

		
	}
}