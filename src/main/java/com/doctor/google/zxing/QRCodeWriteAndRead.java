package com.doctor.google.zxing;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Java QR Code java之二维码生成和解析
 * 
 * @see http://javapapers.com/core-java/java-qr-code/
 * 
 * @author doctor
 *
 * @time 2015年5月23日 下午11:58:31
 */
public class QRCodeWriteAndRead {

	public static void main(String[] args) throws WriterException, IOException, NotFoundException {
		String qrCodeData = "https://github.com/sdcuike/sdcuike.github.io/issues";
		String filePath = "QRCode.png";
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		createQRCode(qrCodeData, filePath, StandardCharsets.UTF_8, hintMap, 200, 200);

		Map<DecodeHintType, Object> decodeHintMap = new HashMap<>();

		String readQRCode = readQRCode(filePath, StandardCharsets.UTF_8, decodeHintMap);
		System.out.println(readQRCode);
	}

	public static void createQRCode(String qrCodeData, String filePath, Charset charset, Map<EncodeHintType, ErrorCorrectionLevel> hintMap, int qrCodeHeight, int qrCodeWidth) throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset), BarcodeFormat.QR_CODE, qrCodeWidth, qrCodeHeight, hintMap);
		MatrixToImageWriter.writeToPath(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), Paths.get(filePath));
	}

	public static String readQRCode(String filePath, Charset charset, Map<DecodeHintType, Object> hintMap) throws NotFoundException, FileNotFoundException, IOException {
		BufferedImage image = ImageIO.read(new FileInputStream(filePath));
		BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(image);
		HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
		BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
		Result result = new MultiFormatReader().decode(binaryBitmap, hintMap);
		return result.getText();
	}
}
