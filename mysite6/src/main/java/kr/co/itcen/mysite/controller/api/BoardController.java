package kr.co.itcen.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.mysite.service.BoardService;
import kr.co.itcen.mysite.vo.FileUploadVo;

@Controller("boardApiController")
@RequestMapping("/api/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@ResponseBody
	@RequestMapping("/download")
	public String checkEmail(@RequestParam(value = "titleNo", required = false)Long titleNo) {
		
		
		FileUploadVo vo = boardService.getFile(titleNo);
		System.out.println("Save_name : " + vo.getSave_name());
		
		
		return "";
	}
	

	
}
