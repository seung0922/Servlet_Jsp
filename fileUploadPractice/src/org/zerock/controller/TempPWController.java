package org.zerock.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class TempPWController
 */
@WebServlet("/temp")
public class TempPWController extends HttpServlet {
	
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// �� ��Ʈ�ѷ��� ����� ����?
		// "/temp" ��� �Է��ϸ� ȭ�鿡 ���� �׸��� ����
		
		// ȭ�鿡 ��û�� �ؼ� ȭ�鿡 ��½��Ѿ��ϴϱ� response�� outputStream ����.
		
		// ȭ�鿡 ��Ÿ�� �ſ� Ÿ������
//		response.setContentType("image/gif");
		
		// ȭ�鿡 ��ȭ��....�׸���.
		BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
		
		// graphics...
		Graphics g = img.getGraphics();
		
		// �� �� ����
		g.setColor(Color.YELLOW);
		
		// �׸�ĭ ��ũ�� �׸�
		g.fillRect(0, 0, 300, 300);

		// ���� ���� ���ڿ� ����
		String str = "" + (int)(Math.random() * 1000000);
		
		// �ٽ� �� �� ����
		g.setColor(Color.BLACK);
		
		// �׸�ĭ �ȿ� ���ڿ� ��
		g.drawString(str, 100, 100);
		
		// ��Ʈ ������
		g.setFont(new Font("Serif", Font.BOLD, 50));
		
		// ���
		OutputStream os = response.getOutputStream();
		
		// ȭ�鿡 ��½�Ŵ
		ImageIO.write(img, "GIF", os);
		
	}

}
