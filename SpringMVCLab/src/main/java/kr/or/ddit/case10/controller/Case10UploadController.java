package kr.or.ddit.case10.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import kr.or.ddit.case10.vo.DummyFileVO;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case10/upload")
public class Case10UploadController {
	@Autowired
	private ErrorsUtils errorUtils;
	
	//파일형태의 자원을 대표하는 인터페이스.
	@Value("file:D:/uploadFiles/")
	private Resource saveRes;
	
	
	@GetMapping
	public String formUI() {
		return "case10/uploadForm";
	}
	
	@PostMapping
	public String formProcess2(
			@Valid @ModelAttribute("dummyFile") DummyFileVO dummyFile
			, BindingResult errors
			, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		
		if(errors.hasErrors()) {
			Map<String, List<String>> customErrors = errorUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
		} else {
			/*^0^*/MultipartFile other = dummyFile.getUploadFile();
			String mime = other.getContentType();
			if(!mime.startsWith("image/")) {
				throw new ResponseStatusException(HttpStatusCode.valueOf(400));
			}
			String originalFileName = other.getOriginalFilename();
			String saveName = System.currentTimeMillis() +"_"+ originalFileName;
			
			
			File saveFolder = saveRes.getFile();
			File saveFile = new File(saveFolder, saveName);
			
			other.transferTo(saveFile);
			
			log.info("uploader: {}", /*^0^*/dummyFile.getUploader());
			log.info("uploadFile: {}", other);
			
			redirectAttributes.addFlashAttribute("saveName", saveName);
		}
		
		
		return "redirect:/case10/upload";
	}
	
	

//	@PostMapping
	public String formProcess(
			@RequestParam String uploader
			, @RequestPart(name="uploadFile") MultipartFile other
			, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		if(other.isEmpty())
			throw new ResponseStatusException(HttpStatusCode.valueOf(400));
		
		String mime = other.getContentType();
		if(!mime.startsWith("image/")) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(400));
		}
		String originalFileName = other.getOriginalFilename();
		String saveName = System.currentTimeMillis() +"_"+ originalFileName;
		
		
		File saveFolder = saveRes.getFile();
		File saveFile = new File(saveFolder, saveName);
	
		other.transferTo(saveFile);
		
		log.info("uploader: {}", uploader);
		log.info("uploadFile: {}", other);
		
		redirectAttributes.addFlashAttribute("saveName", saveName);
		
		return "redirect:/case10/upload";
	}
	

}
