package com.mohit.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author Mohit Sindhpure
 * @useClass {FileService.interface}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @see PackageName, allMethods
 */

public interface FileService {

	String uploadImage(String path,MultipartFile file) throws IOException;
	InputStream getResource(String path, String fileName) throws FileNotFoundException;
}
