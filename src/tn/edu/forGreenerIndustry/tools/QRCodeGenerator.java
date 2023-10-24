/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import com.google.zxing.client.j2se.MatrixToImageWriter;


/**
 *
 * @author mila
 */
public class QRCodeGenerator {
    public static BufferedImage generateQRCode(String content, int width, int height) throws WriterException {
        com.google.zxing.Writer writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, new java.util.Hashtable<EncodeHintType, Object>());
        return MatrixToImageWriter.toBufferedImage(matrix);
    }
    
}
