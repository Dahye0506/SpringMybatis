package service.library;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import Model.AuthInfoDTO;
import Model.LibraryDTO;
import command.LibraryCommand;
import repository.LibraryRepository;


public class LibraryService {
	@Autowired
	LibraryRepository libraryRepository;
	
	
	public void libWrite(LibraryCommand libraryCommand, HttpSession session) {
		LibraryDTO dto = new LibraryDTO();
		dto.setNoticeCon(libraryCommand.getNoticeCon());
		dto.setNoticeSub(libraryCommand.getNoticeSub());
		dto.setNoticeKind("자료실");
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
		dto.setEmployeeId(authInfo.getGrade());

		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal = "";
		
		if(libraryCommand.getNoticeFile()[0].getOriginalFilename() != null) {
			for(MultipartFile mf : libraryCommand.getNoticeFile()) {
				String original = mf.getOriginalFilename();
				String originalExt = original.substring(original.lastIndexOf("."));//파일명가져오기
				String store = UUID.randomUUID().toString().replace("-", "")
						+ originalExt;//파일명중에 -대쉬는 ""지운다
				//파일사이즈
				String fileSize = Long.toString(mf.getSize());
				
				originalTotal += original+",";
				storeTotal += store+",";
				fileSizeTotal += fileSize+",";
				
				String path = "WEB-INT/VIEW/upload";
				String realPath = 
						session.getServletContext().getRealPath(path);
				File file = new File(realPath + "/" + store);
				try {
					mf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				dto.setNoticeOrgFile(originalTotal);
				dto.setNoticeFile(storeTotal);
				dto.setNoticeFileSize(fileSizeTotal);
				
			}
		}
		
		
		libraryRepository.libWrite(dto);
		
	}
	
	
}
