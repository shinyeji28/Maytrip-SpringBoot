package com.ssafy.maytrip.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.response.FileInfoResponse;

public class FileUpload {

	public static FileInfoDto makeFileSource(MultipartFile files) {
		
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
	
	public static String getFolderPath() {
//		String uploadPath = System.getProperty("user.dir").replace('\\', '/');
//	    uploadPath += "/src/main/resources/static/images";
//	    System.out.println(uploadPath);
	    String uploadPath = "C:/Maytrip-store/images";
	    
		return uploadPath;
	}
	
	public static FileInfoResponse toImageUrl(FileInfo file) {
		String fileResponsePath="http://localhost:9000/api/upload/";

		String fileUrl = fileResponsePath  + file.getSaveFolder() +"/"+ file.getSaveFile();
		
		FileInfoResponse fileInfo = FileInfoResponse.builder()
				.id(file.getFileId())
				.url(fileUrl)
				.originFileName( file.getOriginalFile())
				.build();
				
	    return fileInfo;	
	}
	
}
