package kr.or.ddit.case10.servlet;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("serial")
//@WebServlet("/case10/servlet/upload")
@MultipartConfig()
public class FileUploadServlet extends HttpServlet {
	
	private File saveFolder = new File("D:/uploadFiles");
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/case10/uploadForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");
		Part uploadFile = req.getPart("uploadFile");
		String mime = uploadFile.getContentType();
		if(!mime.startsWith("image/")) {
			resp.sendError(400);
			return;
		}
		log.info("uploadFile: {}", uploadFile);
		String originalFileName = uploadFile.getSubmittedFileName();
		String saveName = UUID.randomUUID().toString();
		//절대 파일업로드 처리할 때 원본명과 확장자를 남겨두어선 안 됨.
		File saveFile = new File(saveFolder, saveName);
		uploadFile.write(saveFile.getAbsolutePath());
	}
}
