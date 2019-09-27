package kr.co.itcen.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.mysite.service.GuestbookService;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired //주입
	private GuestbookService guestbookService ;	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		
		List<GuestbookVo> list = guestbookService.getList();
		model.addAttribute("list", list);
		
		return "/guestbook/list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String join(@ModelAttribute GuestbookVo vo) {
		
		guestbookService.insert(vo);
		
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		
		
		model.addAttribute("no", no);
		return "/guestbook/delete";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo) {
		guestbookService.delete(vo);
		
		return "redirect:/guestbook/list";
	}
}
