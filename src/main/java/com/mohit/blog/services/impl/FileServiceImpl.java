package com.mohit.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.mohit.blog.services.FileService;
import com.mohit.blog.utils.GlobalResourceLogger;

@Service
public class FileServiceImpl implements FileService{
	
	Logger logger = GlobalResourceLogger.getLogger(FileServiceImpl.class);

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		logger.info("Initiating Dao call for UploadImage Data FileServiceImpl");

		//File name
		String name = file.getOriginalFilename();		//abc.png
		
		//random name genearate file
	String randomId = UUID.randomUUID().toString();
	String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
	
	//Fullpath
	String filepath = path + File.separator + fileName1;
	
	//create Folder if not created
	File f = new File(path);
	if (!f.exists()) {
		f.mkdir();
	}
	
	//file copy
	java.nio.file.Files.copy(file.getInputStream(), Paths.get(filepath));
	logger.info("Completed Dao call for UploadImage Data FileServiceImpl");
	return name;
	}

	@SuppressWarnings("resource")
	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		logger.info("Initiating Dao call for getResourceImage Data FileServiceImpl");
		String fullpath = path + File.separator + fileName;
	InputStream	is = new FileInputStream(fullpath);
	//db logic to return inputstream
	logger.info("Completed Dao call for getResourceImage Data FileServiceImpl");
		return is;
	}

}
