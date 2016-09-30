package com.orange.pay.utils.wx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.orange.util.StringUtil;

public class QRCodeUtil {
	
	private static int QR_IMG_WIDTH = 60;
	private static int QR_IMG_HEIGHT = 60;
	
	private static final int BLACK = 0xFFFFFFFF;
	private static final int WHITE = 0xFF000000;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void generateQRCodeImage(HttpServletResponse response, String content, int width, int height) throws Exception{
		if(StringUtil.isNullOrBlank(content)){
			return;
		}
		if(width <=0){
			width = QR_IMG_WIDTH;
		}
		if(height <=0){
			height = QR_IMG_HEIGHT;
		}
		 String format = "png";
	     Hashtable hints= new Hashtable();
	     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	     BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);
	     MatrixToImageWriter.writeToStream(bitMatrix, format, response.getOutputStream());
//		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//		Map hints = new HashMap();
//		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//		hints.put(EncodeHintType.MARGIN, 1);
//		BitMatrix bitMatrix = null;
//		try {
//			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
//		} catch (WriterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BufferedImage image = toBufferImage(bitMatrix);
//		try {
//			ImageIO.write(image, "png", response.getOutputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	

}
