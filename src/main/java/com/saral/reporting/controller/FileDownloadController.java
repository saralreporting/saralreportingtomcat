package com.saral.reporting.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saral.reporting.utils.StringConstants;

@RestController
@RequestMapping("/download")
public class FileDownloadController {

	private static final String EXTERNAL_FILE_PATH = StringConstants.FILE_PATH_DOWNLOAD_LOCAL;

	@RequestMapping("/file/{fileName:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {

		File file = new File(EXTERNAL_FILE_PATH + fileName);
		if (file.exists()) {

			// get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to
				// application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			/**
			 * In a regular HTTP response, the Content-Disposition response
			 * header is a header indicating if the content is expected to be
			 * displayed inline in the browser, that is, as a Web page or as
			 * part of a Web page, or as an attachment, that is downloaded and
			 * saved locally.
			 * 
			 */

			/**
			 * Here we have mentioned it to show inline
			 */
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			// Here we have mentioned it to show as attachment
			// response.setHeader("Content-Disposition",
			// String.format("attachment; filename=\"" + file.getName() +
			// "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

			
		}
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/zip", produces = "application/zip")
	public void zipFiles(HttpServletResponse response, @PathVariable("fileName") String fileName,
			@PathVariable("fileName1") String fileName1, String signNo) throws IOException {

		// setting headers
		response.setStatus(HttpServletResponse.SC_OK);
		response.addHeader("Content-Disposition", "attachment; filename="+signNo+".zip");

		ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

		// create a list to add files to be zipped
		ArrayList<File> files = new ArrayList<>();
		ArrayList<String> filestring = new ArrayList<>();
		filestring.add(EXTERNAL_FILE_PATH + fileName);
		filestring.add(EXTERNAL_FILE_PATH + fileName1);
		// files.add(new File("README.md"));
		File file1 = new File(EXTERNAL_FILE_PATH + fileName);
		File file2 = new File(EXTERNAL_FILE_PATH + fileName1);
		files.add(file1);
		files.add(file2);
		// package files
		System.out.println("files-----------------> " + files);
		InputStream inputStream=null;
		 try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
		        for (String file : filestring) {
		            FileSystemResource resource = new FileSystemResource(file);

		            ZipEntry e = new ZipEntry(resource.getFilename());
		            // Configure the zip entry, the properties of the file
		            e.setSize(resource.contentLength());
		            e.setTime(System.currentTimeMillis());
		            // etc.
		            zippedOut.putNextEntry(e);
		            // And the content of the resource:
		            StreamUtils.copy(resource.getInputStream(), zippedOut);
		            zippedOut.closeEntry();
		        }
		        zippedOut.finish();
		    } catch (Exception e) {
		        // Exception handling goes here
		    }
	}
}