package com.ssafy.maytrip.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.response.FileInfoResponse;
import com.ssafy.maytrip.service.FileInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileInfoController {
	private final FileInfoService fileInfoService;
	
	@Value("${file.response.path}")
	private String fileResponsePath;
	
	
	@PostMapping
	public ResponseEntity<?> registFile(MultipartFile file){
		FileInfoResponse fileInfoResponse= fileInfoService.regist(makeFileSource(file));
		Map<String, Object> fileInfo = toImageUrl(fileInfoResponse);
		return ResponseEntity.ok(fileInfo);
	}
	
	public Map<String, Object> toImageUrl(FileInfoResponse file) {
		String fileUrl = fileResponsePath  + file.getSaveFolder() +"/"+ file.getSaveFile();
		
	    Map<String, Object> imageInfo = new HashMap<>();
	    imageInfo.put("id", file.getFileId());
	    imageInfo.put("url", fileUrl);
	    imageInfo.put("originImageName", file.getOriginalFile());
	    return imageInfo;	
	   }
	
	public FileInfoDto makeFileSource(MultipartFile files) {
		
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = getFolderPath() + File.separator + today;
		File folder = new File(saveFolder);
		if (!folder.exists())folder.mkdirs();
		
		MultipartFile mfile = files;
		FileInfoDto fileInfoDto = new FileInfoDto();
		String originalFileName = mfile.getOriginalFilename();
		if (!originalFileName.isEmpty()) {
			String saveFileName = UUID.randomUUID().toString()
					+ originalFileName.substring(originalFileName.lastIndexOf('.'));
			fileInfoDto.setSaveFolder(today);
			fileInfoDto.setOriginalFile(originalFileName);
			fileInfoDto.setSaveFile(saveFileName);
			try {
//				System.out.println("folder: "+folder);
//				System.out.println("saveFileName: "+saveFileName);
				mfile.transferTo(new File(folder, saveFileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileInfoDto;
	}
	
	public String getFolderPath() {
		String uploadPath = System.getProperty("user.dir").replace('\\', '/');
	    uploadPath += "/src/main/resources/static/images";
//	    System.out.println(uploadPath);
		return uploadPath;
	}
	
}
