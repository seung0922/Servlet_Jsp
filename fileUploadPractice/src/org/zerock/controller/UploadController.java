package org.zerock.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.websocket.SendResult;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class UploadController
 */
@WebServlet("/upload")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50,
		fileSizeThreshold = 1024 * 1024,
		location = "C:\\zzz"
)
public class UploadController extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �츮�� �� ��Ʈ�ѷ��� ���� ������ ����?
		// ���ϼ����ؼ� ���ε带 ������ �츮�� ������ ������ ������ ����ǰ� �ϴ� ��Ʈ�ѷ�
		
		// ��û���� ���ϵ��� �ٿ���� ���� ����� �÷������� ����
		Collection<Part> parts =  request.getParts();
		
		// parts�� �ݺ������� ������ �ϳ��� �۾��Ѵ�.
		parts.forEach(part -> {
			
			// part�� fname �����´�.
			String fname = part.getSubmittedFileName();
			
			System.out.println(fname);
			
			try {
			
				// part�� ���� ���
				System.out.printf("�̸�: %s, Ÿ��: %s, ũ��: %d", part.getSubmittedFileName(), part.getContentType(), part.getSize());
				
				// inputStream ����
				InputStream in = part.getInputStream();
				System.out.println(in);
				
				// ������ ������ �����ϰ� ���� ��� + �̸� �������ش�.
				File file = new File("C:\\zzz\\upload\\" + System.currentTimeMillis() + fname);
				
				// ���� ��ο� ���� ����
				FileUtils.copyInputStreamToFile(in, file);
				

			} catch(Exception e) {
				e.printStackTrace();
			}
			
		});
		
	}

}
