package controller.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import command.LogInCommand;
import command.MemberCommand;
import service.goods.GoodsListService;
import service.main.CookieService;
import service.main.FindPasswordService;
import service.main.IdFindFinishService;

@Controller
public class MainController {
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	IdFindFinishService idFindFinishService;
	@Autowired
	FindPasswordService findPasswordService;
	@Autowired
	CookieService cookieService;
	
	
	@RequestMapping("/search/findPasswordPro")
	public String  findPasswordPro(MemberCommand memberCommand,
			Model model) {
		String path = 
				findPasswordService.findPassword(memberCommand, model);
		return path;
	}
	@RequestMapping("/search/findPassword")
	public String findPassword() {
		return "main/findPassword";
	}
	@RequestMapping("/search/idFindFinish")
	public String idFindFinish(MemberCommand memberCommand,
			Model model) {
		idFindFinishService.idFind(memberCommand, model);
		return "main/idFindFinish";
	}
	@RequestMapping("/search/idFind")
	public String idFind() {
		return "main/idSearch";
	}
	@RequestMapping("/main")
	public String aaa(
			@ModelAttribute LogInCommand logInCommand,
			Model model, HttpServletRequest request) {
		cookieService.getCookie(request);//캐쉬 생성
		goodsListService.goodsList(model);
		return "main/main";
	}

	
	
}