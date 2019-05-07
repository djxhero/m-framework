package com.lancslot.morn.utils.util;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * 二维码生成工具类
 * @create 2017-11-28 14:22
 **/

public class QrCodeCreateUtil {

    /**
     * 生成包含字符串信息的二维码图片
     * @param content 二维码携带信息
     * @param qrCodeSize 二维码图片大小
     * @throws WriterException
     * @throws IOException
     */
    public static BufferedImage createQrCode(String content, int qrCodeSize) throws WriterException, IOException {
        return createQrCode(content , qrCodeSize , Color.BLACK);
    }


    /**
     * 生成包含字符串信息的二维码图片
     * @param content 二维码携带信息
     * @param qrCodeSize 二维码图片大小
     * @throws WriterException
     * @throws IOException
     */
    public static BufferedImage createQrCode(String content, int qrCodeSize, Color color) throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String.
        Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hintMap.put(EncodeHintType.MARGIN, 1);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);

        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);

        // Paint and save the image using the ByteMatrix
        graphics.setColor(color);
        for (int i = 0; i < matrixWidth; i++){
            for (int j = 0; j < matrixWidth; j++){
                if (byteMatrix.get(i, j)){
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        return image;
    }




    /**
     * 生成包含字符串信息的二维码图片
     * @param outputStream 文件输出流路径
     * @param content 二维码携带信息
     * @param qrCodeSize 二维码图片大小
     * @param imageFormat 二维码的格式
     * @throws WriterException
     * @throws IOException
     */
    public static void createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException {
        createQrCode(outputStream, content, qrCodeSize, imageFormat , Color.BLACK);
    }


    /**
     * 生成包含字符串信息的二维码图片
     * @param outputStream 文件输出流路径
     * @param content 二维码携带信息
     * @param qrCodeSize 二维码图片大小
     * @param imageFormat 二维码的格式
     * @throws WriterException
     * @throws IOException
     */
    public static void createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat , Color color) throws WriterException, IOException {
        try{
            ImageIO.write(createQrCode(content , qrCodeSize , color), imageFormat, outputStream);
//            ImageIO.write(image, "png", outputStream);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 生成包含字符串信息的二维码图片
     * @param content 二维码携带信息
     * @param qrCodeSize 二维码图片大小
     * @throws WriterException
     * @throws IOException
     */
    public static BufferedImage createQrCode(String content, int qrCodeSize, Color color, int margin) throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String.
        Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hintMap.put(EncodeHintType.MARGIN, margin);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
        byteMatrix = deleteWhite(byteMatrix);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);

        // Paint and save the image using the ByteMatrix
        graphics.setColor(color);
        for (int i = 0; i < matrixWidth; i++){
            for (int j = 0; j < matrixWidth; j++){
                if (byteMatrix.get(i, j)){
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        return image;
    }

    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1])){
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }
}
