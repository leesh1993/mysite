package kr.co.itcen.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.mysite.service.BoardService;

@Controller("boardApiController")
@RequestMapping("/api/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@ResponseBody
	@RequestMapping("/download")
	public String checkEmail(@RequestParam(value = "titleNo", required = false)Long titleNo) {
		System.out.println("titleNo" + titleNo);
		
		boardService.getBoard(titleNo);
		
		return "";
	}
}
