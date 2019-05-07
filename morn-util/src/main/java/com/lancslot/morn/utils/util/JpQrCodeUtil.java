package com.lancslot.morn.utils.util;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

/**
 * @Description 基于jp qrcode第三方工具包封装的二维码工具类
 * http://qrcode.osdn.jp/
 * @Date 2018/11/30 10:48
 **/
public class JpQrCodeUtil {
    private static final Logger logger = LoggerFactory.getLogger(JpQrCodeUtil.class);

    public static String decodeFromUrl(String url) throws Exception {
        InputStream inputStream = HttpRequestUtil.doGetAndReturnInputStream(url, 5000, 10000);

        return decode(inputStream);
    }

    public static String decode(InputStream inputStream) throws Exception {
        BufferedImage image = ImageIO.read(inputStream);
        return decode(image);
    }


    /**
     * 识别二维码图片
     * 图片宽高不能过大，建议先压缩
     * @param bufImg
     * @return
     */
    public static String decode(BufferedImage bufImg){
        try {
            QRCodeDecoder decoder = new QRCodeDecoder();
            String decodedData = new String(decoder.decode(new J2SEImage(bufImg)));
            return decodedData;

        } catch (DecodingFailedException e) {
            logger.error("二维码decode error", e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        BufferedImage image = ImageIO.read(new File("F:\\temp\\test3333.jpg"));
        System.out.println(image.getWidth());
        System.out.println(image.getHeight());
        float sacle = 720f / image.getWidth();
        int width = (int)(image.getWidth() * sacle);
        int height = (int)(image.getHeight() * sacle);
        System.out.println(width);
        System.out.println(height);
       image =  ImageUtils.compressPic(image,width,height);
        ImageIO.write(image,"jpg",new File("F:\\temp\\a.jpg"));
        String decode = null;
        try {
             decode = ZingQrCodeUtil.decode(image);
//            decode = decode(image);
            System.out.println(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class J2SEImage implements QRCodeImage {
    BufferedImage image;

    public J2SEImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public int getPixel(int x, int y) {
        return image.getRGB(x, y);
    }

}
