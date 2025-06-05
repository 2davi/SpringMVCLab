package kr.or.ddit.case10.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import kr.or.ddit.case10.vo.DummyFileVO;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 	특정 폴더 아래의 파일을 관리하기 위한 컨트롤러
 * 	/case10/files (GET) 전체조회
 * 	/case10/files/{파일명} (GET) 단건조회
 * 	/case10/files (POST) 신규등록
 * 	/case10/files/{파일명} (DELETE) 단건삭제
 */
@Slf4j
@Controller
@RequestMapping("/case10/files")
public class MediasImageMngController {
	
	@Value("${imagesFolder}")
	private Resource imageRes; //<--"특정 폴더.."까지 해결.

	@Value("${dummyUpload}")
	private Resource saveRes; //Resource는 readonly,, 지우려면 File이 필요;
	
	private File saveFolder;
	
	//ServletContext는 따로 등록하지 않아도 알아서 주입된다.
	@Autowired
	private ServletContext application;
	
	@PostConstruct
	public void init() throws IOException {
		saveFolder = imageRes.getFile();
	}
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	
	@GetMapping
	public void fileList(Model model) {
		String[] fileList = saveFolder.list();
		model.addAttribute("fileNames", fileList);
	}
	
	/**
	 * 
	 * 	파일 다운로드 처리(응답 데이터의 소비 형태를 처리)
	 * 	Content-Disposition 헤더의 사용
	 * 	1) inline (기본 처리 형태) : 브라우저의 창 내에서 응답 컨텐츠를 소비함.
	 * 	2) attachment ex) attachment;filename="파일명"
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value = "{fileName}")
	@ResponseBody
	public ResponseEntity<Resource> fileDownload(@PathVariable String fileName) throws IOException {
		Resource targetRes = imageRes.createRelative(fileName);
		if(!targetRes.exists()) 
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		
		////파일 다운로드 시 신경써야 할 헤더.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentLength(targetRes.contentLength());
		
		MediaType mediaType = Optional.ofNullable(application.getMimeType(targetRes.getFilename()))
							  .map(MediaType::parseMediaType)
							  .orElse(MediaType.APPLICATION_OCTET_STREAM);//<--관례적으로 정한 기본 타입
		headers.setContentType(mediaType);
		headers.setContentDisposition(
				ContentDisposition.attachment()
								  .filename(targetRes.getFilename(), Charset.defaultCharset())
								  .build()
		);
		
		return ResponseEntity.ok()
							 .headers(headers)
							 .body(targetRes);
//							 .build();
	}

//	@PostMapping
//	public String upload(
//			MultipartFile uploadFile
//			, RedirectAttributes redirectAttributes
//	) throws IllegalStateException, IOException {		
//		if(!uploadFile.isEmpty()) {
//			// 검증이 끝난 후...
//			String saveName = UUID.randomUUID().toString();
//			File saveFile = new File(saveFolder, saveName);
//			uploadFile.transferTo(saveFile);
//			redirectAttributes.addFlashAttribute("uploaded", saveName);	
//		}
//
//		return "redirect:/case10/files";
//	}
	
	@PostMapping
	public String uploadFile(
			@Valid @ModelAttribute DummyFileVO dummyFile
			, BindingResult errors
			, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		
		if(errors.hasErrors()) {
			Map<String, List<String>> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
		} else {
			/*^0^*/MultipartFile other = dummyFile.getUploadFile();
			String mime = other.getContentType();
			if(!mime.startsWith("image/")) {
				throw new ResponseStatusException(HttpStatusCode.valueOf(400));
			}
			String originalFileName = other.getOriginalFilename();
			String saveName = System.currentTimeMillis() +"_"+ originalFileName;
			
			File saveFile = new File(saveFolder, saveName);
			
			other.transferTo(saveFile);
			
			log.info("uploader: {}", /*^0^*/dummyFile.getUploader());
			log.info("uploadFile: {}", other);
			
			redirectAttributes.addFlashAttribute("saveName", saveName);
		}
		
		return "redirect:/case10/files";
	}
	//파일 CRUD에 (PUT)업데이트는 없음
	@DeleteMapping("{fileName}") //Put,DELETE는 비동기 방식을 사용한다.
	@ResponseBody
	public Map<String, Object> deleteOne(@PathVariable String fileName) throws IOException {
		Resource targetRes = imageRes.createRelative(fileName);
		if(!targetRes.exists()) 
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		
		targetRes.getFile().delete();
		
		return Map.of("success", "true", "target", fileName);
	} 
}
