package kr.co.itcen.mysite.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.mysite.security.Auth;
import kr.co.itcen.mysite.security.AuthUser;
import kr.co.itcen.mysite.service.UserService;
import kr.co.itcen.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired //주입
	private UserService userService;	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
			
		return "/user/join";
	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, 
			           BindingResult result,
			           Model model) {
		
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		userService.join(vo);
		
		return "redirect:/user/joinsuccess"; //insert 후 redirect
	}
	
	
	@RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
	public String joinsuccess() {
	
		return "/user/joinsuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute UserVo vo) {
		
		return "/user/login";
	}
		
	//@Auth(role = Role.USER)
	@Auth("USER")
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		Long no = authUser.getNo();	
		UserVo userVo = userService.getUpdateUser(no);
		model.addAttribute("userVo", userVo);
		return "user/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute UserVo vo, HttpSession session) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		vo.setNo(authUser.getNo());
		authUser.setName(vo.getName());
		userService.update(vo);
		
		return "redirect:/user/update";
	}
	
}
