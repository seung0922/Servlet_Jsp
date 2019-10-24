package org.zerock.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class FileViewController
 */
@WebServlet("/fileView")
public class FileViewController extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// �� ��Ʈ�ѷ��� ������ ����...?
		// �� ȭ�鿡 �Ķ����(������ �̸�)�� �������� ���� �����ش�.
		
		// �Ķ���ͷ� �������� �� �����;߰���? fname���� �����!
		String fname = request.getParameter("fname");
		
		// ���� ��ü ������! ( ���� �� ������ ����ִ� ��η� )
		File file = new File("C:\\zzz\\upload\\" + fname);
		
		Path path = new File(fname).toPath();
		String mineType = Files.probeContentType(path);
		
		// �����̸� ����� ��ȯ��! �ѱ��� �����ϱ�....
		fname = new String(fname.getBytes("UTF-8"), "8859_1");
		
		// �ٵ� �̹����� �׳� �����ְ� �ƴϸ� �ٿ�ε� ���ְ�;�
		if(!mineType.equals("image/jpeg")) {
			
			response.setContentType("application/octet-stream");	// �������� �������� �� �ְ� ����� �ɾ��ش�.
			response.setHeader("Content-Disposition", "attachment; filename=" + fname + ";");
			
		}
		
		// ����Ϸ��� outputStream �ʿ���
		OutputStream os = response.getOutputStream();
		
		// �� ��ο� �ִ� ���� ȭ�鿡 ������
		FileUtils.copyFile(file, os);
		
	}

}
