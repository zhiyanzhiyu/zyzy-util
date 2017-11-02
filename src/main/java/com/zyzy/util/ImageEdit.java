package com.zyzy.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import javax.imageio.ImageIO;


/**
 * 
 * @ClassName: ImageEdit 
 * @Description: 图片编辑类，添加水印，图片合成
 * @author: BillZhao
 * @date: 2017年11月2日 下午3:35:59
 */
public class ImageEdit {


	/**
	 * 
	 * @Title: addWaterMark 
	 * @Description: 添加图片，文字水印
	 * @param srcImgPath
	 * @param tarImgPath
	 * @return: void
	 */
	public static void addWaterMark(String srcImgPath, String tarImgPath) {

		try {
			File srcImgFile = new File(srcImgPath);// 得到底图文件
			Image srcImg = ImageIO.read(srcImgFile);// 文件转化为图片
			int srcImgWidth = srcImg.getWidth(null);// 获取图片的宽
			int srcImgHeight = srcImg.getHeight(null);// 获取图片的高
			
			//加水印
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();
			g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
			
			
			//图片合成
			File imgFile = new File("logoImgPath"); //需要合成的图片路径，需要修改为自己实际路径
			Image img = ImageIO.read(imgFile);// 文件转化为图片
			g.drawImage(img, 250, 650, null);

		
			//文字合成
			Font f = new Font("宋体", Font.PLAIN, 30);
			Color mycolor = Color.BLACK;
			g.setColor(mycolor);
			g.setFont(f);
			//消除锯齿设置
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawString("甜甜圈", 150, 150);

			FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
			ImageIO.write(bufImg, "jpg", outImgStream);
			outImgStream.flush();
			outImgStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @Title: resizeImage 
	 * @Description: 修改文件大小
	 * @param is
	 * @param os
	 * @param size
	 * @param format
	 * @return
	 * @throws IOException
	 * @return: OutputStream
	 */
	public static void resizeImage(InputStream is, OutputStream os, int size, String format)
			throws IOException {
		BufferedImage prevImage = ImageIO.read(is);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();
		double percent = size / width;
		int newWidth = (int) (width * percent);
		int newHeight = (int) (height * percent);
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
		ImageIO.write(image, format, os);
		os.flush();
		is.close();
		os.close();
	}

	public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
		return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	public static void main(String[] args) {
	
	}
}