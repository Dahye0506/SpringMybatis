package service.goods;

import Model.AuthInfoDTO;
import Model.GoodsDTO;
import command.GoodsCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import repository.GoodsRepository;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;


public class GoodsWriteService {
	//DTO가 가지고 있는 값은 repository를 통해 이디에 저장한다.
	@Autowired
	GoodsRepository goodsRepository;
	
	public void goodsWrite(GoodsCommand goodsCommand, HttpSession session) {//db에 전달하기 위해서는 model에 dto전달
		GoodsDTO dto = new GoodsDTO();
		dto.setCtgr(goodsCommand.getCtgr());
		dto.setProdCapacity(goodsCommand.getProdCapacity());
		dto.setProdDelFee(goodsCommand.getProdDelFee());
		dto.setProdDetail(goodsCommand.getProdDetail());
		dto.setProdName(goodsCommand.getProdName());
		dto.setProdNum(goodsCommand.getProdNum());
		dto.setProdPrice(goodsCommand.getProdPrice());
		dto.setProdSupplyer(goodsCommand.getProdSupplyer());
		dto.setRecommend(goodsCommand.getRecommend());
		//employeeId는 로그인 시 session에 저장됨
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
		//상품등록할때는 직원 아이디가 필요함.
		String employeeId = authInfo.getGrade();
		dto.setEmployeeId(employeeId);

		// DB에는 파일 이름만 저장해야한다(파일 관리및 용량 때문에)
		//db에는 파일이름만 저장하기 위해 originalFilename을 가녀와서 확장자 추출
		String prodImage = "";
		for(MultipartFile mf : goodsCommand.getProdImage1()) {
			String original = mf.getOriginalFilename();
			//확장자를 알기위해서는 OriginalFilename 사용
			//original에서 확장자만 추출
													//뒤에있는 . (점)부터 확장자 추출
			String originalExt = original.substring(original.lastIndexOf("."));
			String store = UUID.randomUUID().toString().replace("-", "") + originalExt;
					//전세계 유일한 아이디 생성 . 스트링으로 변환. "-"대쉬는 ""삭제 처리 + 뒤에 익스텐션해야 확장자명 생성

			//디비에 저장할 파일명 추출하여 prodImage에 저장
			prodImage += store + ",";
			//파일을 시스템에 저장
			String filePath = session.getServletContext().getRealPath("WEB-INF/view/goods/upload");
			File file = new File(filePath + "/" + store);
            //파일저장
            try {
                mf.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

		}
		dto.setProdImage(prodImage);
		//확인하기
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★");
		System.out.println("작동확인/GoodsWriteService");
		System.out.println(dto.getCtgr());
		System.out.println(dto.getEmployeeId());
		System.out.println(dto.getProdCapacity());
		System.out.println(dto.getProdDetail());
		System.out.println(dto.getProdImage());
		System.out.println(dto.getProdName());
		System.out.println(dto.getProdSupplyer());
		System.out.println(dto.getRecommend());
		System.out.println(dto.getProdDelFee());
		System.out.println(dto.getProdNum());
		System.out.println(dto.getProdPrice()); 
		
		goodsRepository.goodsInsert(dto);
		
		
	}
}
