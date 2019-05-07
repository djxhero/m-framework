package com.lancslot.morn.utils.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * 图片工具
 */
public class ImageUtils {
	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);


	/**
	 * 圆角处理
	 * @param image
	 * @param cornerRadius
	 * */
	public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {

		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = output.createGraphics();
		output = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
		g2.dispose();
		g2 = output.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// 添加高度，然后再进行画圆角，方便画图时，只取上两个圆角
		g2.fillRoundRect(0, 0,w, h + cornerRadius, cornerRadius, cornerRadius);
		g2.setComposite(AlphaComposite.SrcIn);
		g2.drawImage(image, 0, 0, w, h, null);
		g2.dispose();

		return output;
	}

	/**
	 * 传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的
	 *
	 * @param image
	 *            用户头像地址
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage convertCircular(BufferedImage image) throws Exception {
		if(image == null){
			return null;
		}
		BufferedImage bi2 = null;
		if(image.getWidth() != image.getHeight()){
			image = scaleByPercentage(image,150,150);
		}
		//圆形---> 透明底的图片TYPE_INT_RGB(jpg)
		bi2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, image.getWidth(), image.getHeight());
		Graphics2D g2 = bi2.createGraphics();
		g2.setClip(shape);
		// 使用 setRenderingHint 设置抗锯齿
		g2.drawImage(image, 0, 0, null);
		// 设置颜色
		g2.setBackground(Color.green);
		g2.dispose();
		return bi2;
	}

	/**
	 * 缩小Image，此方法返回源图像按给定宽度、高度限制下缩放后的图像
	 *
	 * @param inputImage
	 * @param newWidth
	 *            ：压缩后宽度
	 * @param newHeight
	 *            ：压缩后高度
	 * @throws IOException
	 *             return
	 */
	public static BufferedImage scaleByPercentage(BufferedImage inputImage, int newWidth, int newHeight) throws Exception{
		// 获取原始图像透明度类型
		int type = inputImage.getColorModel().getTransparency();
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		// 开启抗锯齿
		RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// 使用高质量压缩
		renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		BufferedImage img = new BufferedImage(newWidth, newHeight, type);
		Graphics2D graphics2d = img.createGraphics();
		graphics2d.setRenderingHints(renderingHints);
		graphics2d.drawImage(inputImage, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
		graphics2d.dispose();
		return img;
	}

	public static BufferedImage compressPic(BufferedImage image,int width,int height){
		// 图片压缩
		//大于200K , 按原比例压缩0.8
		BufferedImage bufferedImage = null;
		ByteArrayInputStream in = null;
		ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
		try{
			 bufferedImage = new BufferedImage(width , height , BufferedImage.TYPE_INT_RGB);
			bufferedImage.getGraphics().drawImage(
					image.getScaledInstance(width , height , Image.SCALE_SMOOTH) , 0 , 0, null);

			ImageIO.write(bufferedImage , "png" , byteArrayOutputStream);

			in = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());    //将b作为输入流；
			return ImageIO.read(in);
		}catch (Exception e){
			logger.error("",e);
		}finally {
			org.apache.commons.io.IOUtils.closeQuietly(byteArrayOutputStream);
			org.apache.commons.io.IOUtils.closeQuietly(in);
		}
		return null;
	}

	/**
	 * @Title: compressPic
	 * @Description: 压缩图片,通过压缩图片质量，保持原图大小(如果传入newWidth和 newHeight，则是新的宽高)
	 * @param  quality:0-1
	 * @return byte[]
	 * @throws
	 */
	public static BufferedImage compressPic(BufferedImage bufferedImage,float newWidth ,float newHeight ,boolean reSize,  float quality,boolean isCircular) {
		byte[] inByte = null;
		BufferedImage tagImage = null;
		try {
			//ByteArrayInputStream byteInput = new ByteArrayInputStream(imageByte);
			//BufferedImage inputImage = ImageIO.read(byteInput);
			float width = bufferedImage.getWidth(null);
			float height = bufferedImage.getHeight(null);
			Image image = bufferedImage.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
			// 缩放图像
			if(newWidth > 0f && reSize){
				width = newWidth;
			}
			if(newHeight >  0 && reSize){
				height = newHeight;
			}
			tagImage = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = tagImage.createGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", out);
			//byte[] imageByte =  out.toByteArray();
			//ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(imageByte.length);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tagImage);
			/* 压缩质量 */
			jep.setQuality(quality, true);
			encoder.encode(tagImage, jep);
			inByte = out.toByteArray();
			out.close();
			ByteArrayInputStream in = new ByteArrayInputStream(inByte);    //将b作为输入流；
			tagImage = ImageIO.read(in);
			if(isCircular){
				/*// 缩小Image，此方法返回源图像按给定宽度、高度限制下缩放后的图像(这一步是正方形，上一步已经处理，暂时注释代码)
				int type = tagImage.getColorModel().getTransparency();
				width = tagImage.getWidth();
				height = tagImage.getHeight();
				// 开启抗锯齿
				RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// 使用高质量压缩
				renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				tagImage = new BufferedImage((int)newWidth, (int)newHeight, type);
				Graphics2D graphics2d = tagImage.createGraphics();
				graphics2d.setRenderingHints(renderingHints);
				graphics2d.drawImage(inputImage, 0, 0, (int)newWidth, (int)newHeight, 0, 0, (int)width,(int) height, null);
				graphics2d.dispose();*/
				// 圆形---> 透明底的图片TYPE_INT_RGB(jpg)
				tagImage = new BufferedImage(tagImage.getWidth(), tagImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
				Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, tagImage.getWidth(), tagImage.getHeight());
				Graphics2D g2 = tagImage.createGraphics();
				g2.setClip(shape);
				// 使用 setRenderingHint 设置抗锯齿
				g2.drawImage(image, 0, 0, null);
				// 设置颜色
				g2.setBackground(Color.white);
				g2.dispose();

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return tagImage;
	}

	/**
	 * 根据设置的宽高等比例压缩图片文件<br> 先保存原文件，再压缩
	 * @param url  要进行压缩的文件
	 * @param width  宽度 //设置宽度时（高度传入0，等比例缩放）
	 * @param height 高度 //设置高度时（宽度传入0，等比例缩放）
	 * @return 返回压缩后的文件
	 */
	public static BufferedImage zipImageFile(String url , int width, int height) throws Exception{
		if (org.apache.commons.lang.StringUtils.isEmpty(url)) {
			return null;
		}
		BufferedImage buffImg = null;
		try {
			Image srcFile = ImageIO.read(new URL(url));
			int w = srcFile.getWidth(null);
			int h = srcFile.getHeight(null);
			double bili;
			if(width>0){
				bili=width/(double)w;
				height = (int) (h*bili);
			}else{
				if(height>0){
					bili=height/(double)h;
					width = (int) (w*bili);
				}
			}
			String subfix = "jpg";
			if("png".equals(subfix)){
				buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			}else{
				buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			}
			Graphics2D graphics = buffImg.createGraphics();
			graphics.setBackground(new Color(255,255,255));
			graphics.setColor(new Color(255,255,255));
			graphics.fillRect(0, 0, width, height);
			graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			throw  new FileNotFoundException(e.getMessage());
		} catch (IOException e) {
			throw  new IOException(e.getMessage());
			//e.printStackTrace();
		}
		return buffImg;
	}

	public static BufferedImage coverImage(BufferedImage baseBufferedImage, BufferedImage coverBufferedImage, int x, int y, int width, int height) throws Exception{

		// 创建Graphics2D对象，用在底图对象上绘图
		Graphics2D g2d = baseBufferedImage.createGraphics();

		// 绘制
		g2d.drawImage(coverBufferedImage, x, y, width, height, null);
		g2d.dispose();// 释放图形上下文使用的系统资源

		return baseBufferedImage;
	}


	public static BufferedImage coverImage(String baseFilePath, String coverFilePath, int x, int y, int width, int height) throws Exception{

		File baseFile = new File(baseFilePath);//底图
		BufferedImage buffImg = ImageIO.read(baseFile);

		File coverFile = new File(coverFilePath); //覆盖层
		BufferedImage coverImg = ImageIO.read(coverFile);

		buffImg = coverImage(buffImg, coverImg, x, y, width, height);

		return buffImg;
	}


	public static BufferedImage createPoster(BufferedImage poster, BufferedImage banner, BufferedImage logo, BufferedImage qr, Font font
			, String text){
		// 图片大小
		BufferedImage img = new BufferedImage(750, 1146, BufferedImage.TYPE_INT_RGB);
		// 开启画图
		Graphics2D g = (Graphics2D)img.getGraphics();
		// 画底图
		g.drawImage(poster.getScaledInstance(750,1146, Image.SCALE_DEFAULT), 0, 0, null);
		// 画banner，圆角
		g.drawImage(banner.getScaledInstance(633,480, Image.SCALE_DEFAULT), 58, 80, null);
		// 画logo
		g.drawImage(logo.getScaledInstance(130,130, Image.SCALE_DEFAULT), 328 - (130-95)/2, 503 - (130-95)/2, null);
		// 画二维码
		g.drawImage(qr.getScaledInstance(220,220, Image.SCALE_DEFAULT), 450, 791+(197-160), null); // 绘制缩小后的图

		// 设置字体大小
		font = font.deriveFont(46.0f);
		// 设置字体颜色
		Color color = new Color(2, 2, 4 );
		g.setColor(color);
		g.setFont(font);

//		FontMetrics fm = g.getFontMetrics(font);
//		int textWidth = fm.stringWidth(text);
		FontRenderContext context = g.getFontRenderContext();
		int textWidth = getWidth(context , g , text);
		int textHeigh = 150;

		// 判断文字是否需要换行
		if (textWidth <= 609){
			int widthX = (750 - textWidth) / 2;
			// 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
			g.drawString(text, widthX,680 + 65 / 2);
		}else {
			int stringCount = 12;
			int count = text.length() / stringCount;
			int remain = text.length() % stringCount;
			int baseHeigh = 0;


			if (count + remain <= 2){
				// 两行时的行高
				textHeigh = 80;
				baseHeigh = 666 + 65 / 2;
			}else {
				// 两行+的行高
				textHeigh = 68;
				baseHeigh = 656 + 65 / 2;
			}


			for (int i = 0 ; i < count ; i ++){
//				int widthX = (750 - fm.stringWidth(text.substring( i * stringCount, (i + 1) * stringCount))) / 2;
				int temp = getWidth(context , g , text.substring( i * stringCount, (i + 1) * stringCount));
				int widthX = (750 - temp) / 2;
				g.drawString(text.substring( i * stringCount, (i + 1) * stringCount), widthX,
						baseHeigh +  (i * textHeigh));
			}

			if (remain > 0){
//				int widthX = (750 - fm.stringWidth(text.substring( count * stringCount, text.length()))) / 2;
				int temp = getWidth(context , g , text.substring( count * stringCount, text.length()));
				int widthX = (750 - temp) / 2;

				g.drawString(text.substring( count * stringCount, text.length()), widthX,
						baseHeigh + (count  * textHeigh));
			}

		}
		return img;
	}

	public static int getWidth(FontRenderContext context , Graphics2D g , String text) {
		return (int) g.getFont().getStringBounds(text , context).getWidth();
	}

}