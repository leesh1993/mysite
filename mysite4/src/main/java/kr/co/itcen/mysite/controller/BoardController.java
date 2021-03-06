package kr.co.itcen.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.mysite.exception.FileUploadException;
import kr.co.itcen.mysite.service.BoardService;
import kr.co.itcen.mysite.service.Paging;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.FileUploadVo;
import kr.co.itcen.mysite.vo.UserVo;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired //주입
	private BoardService boardService;
	
	@Autowired
	private Paging paging;
	
	@RequestMapping(value = "/list/{cCount}", method = RequestMethod.GET)
	public String getList(@PathVariable("cCount") int cCount,  
			              HttpSession session, 
			              Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			model.addAttribute("user", "logO");
			model.addAttribute("userNo", authUser.getNo());
		}
			
		//페이징 처리===============================================================
//		Paging p = new Paging();
		paging.makeLastPageNum();
		paging.makeBlock(cCount);
		model.addAttribute("p", paging);
		model.addAttribute("cCount", cCount);
		// =====================================================================
			
		List<BoardVo> list = boardService.getList(cCount);
		model.addAttribute("list", list);
		model.addAttribute("what", "list");

		return "board/list";
	}
	
	@RequestMapping(value = "/search/{cCount}", method = RequestMethod.POST)
	public String getSearchList(@PathVariable("cCount") int cCount,  
								@RequestParam("text") String text,
			                    HttpSession session,
			                    Model model) {
		System.out.println("검색");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			model.addAttribute("user", "logO");
			model.addAttribute("userNo", authUser.getNo());
		}
		
		//페이징 처리==============================================================
		//Paging p = new Paging();
		paging.makeLastPageNum(text);
		paging.makeBlock(cCount);		
		model.addAttribute("p", paging);
		model.addAttribute("cCount", cCount);
		// =====================================================================
		
		//for (BoardVo k : list) {
		//	System.out.println(k.getNo() + ":  " + k.getState());
		//}
		
		List<BoardVo> list = boardService.getList(cCount, text);
		model.addAttribute("list", list);
		model.addAttribute("what", "search");
		model.addAttribute("text", text);
		
		
		return "board/list";
	}
	
	@RequestMapping(value = "/search/{cCount}/{text}", method = RequestMethod.GET)
	public String getList2(@PathVariable("cCount") int cCount, 
						   @PathVariable("text") String text, 
			               HttpSession session, 
			               Model model) {
		
		System.out.println("페이지 변경");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			model.addAttribute("user", "logO");
			model.addAttribute("userNo", authUser.getNo());
		}
		
		//페이징 처리==============================================================
		//Paging p = new Paging();
		paging.makeLastPageNum(text);
		paging.makeBlock(cCount);		
		model.addAttribute("p", paging);
		model.addAttribute("cCount", cCount);
		// =====================================================================
		
		//for (BoardVo k : list) {
		//	System.out.println(k.getNo() + ":  " + k.getState());
		//}
		
		List<BoardVo> list = boardService.getList(cCount, text);
		model.addAttribute("list", list);
		model.addAttribute("what", "search");
		model.addAttribute("text", text);
		
		return "board/list";
	}
	
	@RequestMapping(value = "/view/{no}/{user_no}/{cCount}", method = RequestMethod.GET)
	public String view(@PathVariable("no") Long no, 
			           @PathVariable("user_no") int userNo, 
			           @PathVariable("cCount") int cCount,
			           HttpSession session, 
			           Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			model.addAttribute("loginO", "loginO");
			model.addAttribute("authUserNo", authUser.getNo());
		}
			
		model.addAttribute("boardUserNo", userNo);
		model.addAttribute("cCount", cCount);
		
		//해당 타이틀 번호의 데이터 가져오기
		BoardVo boardVo = boardService.getBoard(no);
		model.addAttribute("boardVo", boardVo);
		
		//파일 가져오기
		FileUploadVo fileUploadVo = boardService.getFile(no);
		if(fileUploadVo !=null) {
			model.addAttribute("fileUploadVo", fileUploadVo);
			model.addAttribute("fileO", "fileO");
		}
		
		//조회수 증가
		BoardVo hitCount = boardService.getBoardHit(no);
		boardService.updateHit(hitCount.getHit() + 1 ,no);
	
		return "board/view";
	}
	
	@RequestMapping(value = "/write/{cCount}", method = RequestMethod.GET)
	public String write(Model model,
			            @PathVariable("cCount") int cCount) {
		
		model.addAttribute("write","write");
		model.addAttribute("cCount",cCount);
		
		return "board/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo,
						@RequestParam("title") String title,
						@RequestParam(value="file",required=false) MultipartFile multipartFile,
			            HttpSession session,
			            Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			vo.setUser_no(authUser.getNo());
		} else {
			return "/user/login";
		}
		
		System.out.println("title : " + title);
		
		//boardService.write(vo);
		
		if(multipartFile != null) {
			//String url = boardService.restore(vo.getNo(),multipartFile);
			//System.out.println("url : " + url);
			System.out.println("multipartFile 넘어오나");
		}
		
		return "redirect:/board/list/1";
	}
	
	@RequestMapping(value = "/modify/{no}/{cCount}", method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, 
						 @PathVariable("cCount") int cCount,
			             HttpSession session,
			             Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			return "/user/login";
		}
		
		BoardVo boardVo =  boardService.getBoard(no);
		model.addAttribute("boardVo",boardVo);
		model.addAttribute("cCount",cCount);
		
		return "/board/modify";
	}	
	
	@RequestMapping(value = "/modify/{no}/{cCount}", method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no,
						 @PathVariable("cCount") int cCount,
						 @RequestParam("modifiedTitle") String modifiedTitle,
						 @RequestParam("modifiedContents") String modifiedContents) {
		
		boardService.modify(no, modifiedTitle, modifiedContents);
		
		return "redirect:/board/list/" + cCount;
	}
	
	@RequestMapping(value = "/cancel/{cCount}", method = RequestMethod.GET)
	public String cancel(@PathVariable("cCount") int cCount) {

		return "redirect:/board/list/" + cCount;
	}
	
	@RequestMapping(value = "/boardreply/{g_no}/{o_no}/{depth}/{cCount}", method = RequestMethod.GET)
	public String boardreply(@PathVariable("g_no") int g_no,
			                 @PathVariable("o_no") int o_no,
			                 @PathVariable("depth") int depth,
			                 @PathVariable("cCount") int cCount,
			                 Model model) {
		
		model.addAttribute("write","boardreply");
		model.addAttribute("g_no",g_no);
		model.addAttribute("o_no",o_no);
		model.addAttribute("depth",depth);
		model.addAttribute("cCount",cCount);
		
		System.out.println("boardreply2 " + g_no);
		
		return "/board/write";
	}
	
	@RequestMapping(value = "/boardreply/{g_no}/{o_no}/{depth}/{cCount}", method = RequestMethod.POST)
	public String boardreply(@PathVariable("g_no") Long g_no,
			                 @PathVariable("o_no") Long o_no,
			                 @PathVariable("depth") Long depth,
			                 @RequestParam("title") String title,
							 @RequestParam("contents") String contents,
							 @PathVariable("cCount") int cCount,
							 @ModelAttribute BoardVo vo,
			                 Model model,
			                 HttpSession session) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			vo.setUser_no(authUser.getNo());
		} else {
			return "/user/login";
		}
		
		boardService.boardReply(vo);
			
		return "redirect:/board/list/" + cCount;
	}
	
	@RequestMapping(value = "/delete/{no}/{cCount}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no,
			             @PathVariable("cCount") int cCount) {

		
		boardService.delete(no);
		
		return "redirect:/board/list/" + cCount;
	}
	
	@RequestMapping(value = "/filedownload", method = RequestMethod.GET)
	public String fileDownload() {
		
		return "";
	}
}
