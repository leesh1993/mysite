package kr.co.itcen.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.mysite.exception.FileUploadException;
import kr.co.itcen.mysite.repository.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.FileUploadVo;


@Service
public class BoardService {
	
	private static final String SAVE_PATH = "/uploads";
	private static final String URL_PREFIX = "/images";
	
	@Autowired
	private BoardDao boardDao; 
	
	public Boolean write(BoardVo vo) {
		return boardDao.write(vo);
	}
	
	public Boolean boardReply(BoardVo vo) {
		return boardDao.boardReply(vo);
	}
	
	public List<BoardVo> getList(int cCount) {
		
		return boardDao.getList(cCount);
	}
	
	public List<BoardVo> getList(int cCount, String search) {
		
		return boardDao.getList(cCount, search);	
	}
	
	public BoardVo getBoard(Long titleNo) {
		
		return boardDao.getBoard(titleNo);
	}
	
	public boolean modify(Long no, String title, String contents) {
		
		return boardDao.modify(no, title, contents);
	}
	
	public Boolean delete(Long no) {
		
		return boardDao.delete(no);
	}
	
	public Boolean updateHit(Long hit, Long no) {
		
		return boardDao.updateHit(hit, no);
	}
	
	public BoardVo getBoardHit(Long titleNo) {
		
		return boardDao.getBoardHit(titleNo);
	}
	
	public Long getCount() {
		
		return boardDao.getCount();
	}
	
	public Long getCount(String kwd) {
		
		return boardDao.getCount(kwd);
	}
	
	
	public String restore(Long no, MultipartFile multipartFile) {
		
		String url = "";

		try {

			if(multipartFile == null) {
				return url;
			}
			
			String originalFilename = multipartFile.getOriginalFilename();
			String saveFileName = generateSaveFilename(originalFilename.substring(originalFilename.lastIndexOf('.')+1));
			long fileSize = multipartFile.getSize();
			
			System.out.println("###############" + originalFilename);
			System.out.println("###############" + saveFileName);
			System.out.println("###############" + fileSize);
		
			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			
			url = URL_PREFIX + "/" + saveFileName;
			
			
			FileUploadVo vo = new FileUploadVo() ;
			
			vo.setOriginal_name(originalFilename);
			vo.setSave_name(saveFileName);
			vo.setBoard_no(no);
					
			boardDao.upload(vo);
		} catch (IOException e) {
			throw new FileUploadException();
		}
	
		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}	
	
	public FileUploadVo getFile(Long titleNo) {
		
		return boardDao.getFile(titleNo);
	}
	
	public void fileDownload() {
		
		
	}
}
