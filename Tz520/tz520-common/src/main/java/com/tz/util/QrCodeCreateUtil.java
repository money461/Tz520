package com.tz.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


/**
 * 
 * @FileName: 二维码生成工具
 *
 * 
 * @Description: TODO
 *
 */
public class QrCodeCreateUtil {

	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 180;

	// LOGO宽度
	private static final int WIDTH = 30;
	// LOGO高度
	private static final int HEIGHT = 30;

	/**
	 * 
	 * @param content
	 * @param needCompress
	 * @return
	 * @throws Exception
	 * @author wolf
	 * @date 2016年12月17日
	 */
	// 参生的二维码无边框
	private static BufferedImage createImageNoBorder(String content, boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);// 控制边框大小

		// 去掉边框begin
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
				hints);
		// 1.1去白边
		int kongBai = 10;// 控制边框大小必须大于0
		int[] rec = bitMatrix.getEnclosingRectangle();
		int resWidth = rec[2];
		int resHeight = rec[3];
		BitMatrix resMatrix = new BitMatrix(resWidth + kongBai, resHeight + kongBai);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (bitMatrix.get(i + rec[0], j + rec[1])) {
					resMatrix.set(i + kongBai / 2, j + kongBai / 2);
				}
			}
		}
		// 2
		int qSOtherW = resMatrix.getWidth();
		int qSOtherH = resMatrix.getHeight();
		BufferedImage image = new BufferedImage(qSOtherW, qSOtherH, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < qSOtherW; x++) {
			for (int y = 0; y < qSOtherH; y++) {
				image.setRGB(x, y, resMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}

		// 插入图片
		QrCodeCreateUtil.insertImageOther(image, qSOtherW, qSOtherH, needCompress);
		// 去掉边框end

		return image;
	}

	/**
	 * 插入LOGO,去掉边框
	 * 
	 * @param source
	 *            二维码图片
	 * @param imgPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 * @author wolf
	 * @date 2016年11月17日
	 */
	private static void insertImageOther(BufferedImage source, int qSOtherW, int qSOtherH, boolean needCompress)
			throws Exception {

		Image src = ImageIO.read(QrCodeCreateUtil.class.getResourceAsStream("/logo.jpg"));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (qSOtherW - width) / 2;
		int y = (qSOtherH - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存放目录
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 * @author wolf
	 * @date 2016年11月17日
	 */
	private static void encode(String content, String destPath, String fileName, boolean needCompress)
			throws Exception {
		BufferedImage image = QrCodeCreateUtil.createImageNoBorder(content, needCompress);
		mkdirs(destPath);

		String file = fileName + ".jpg";
		String pathFile = destPath + "/" + file;
		deleteFile(pathFile);
		ImageIO.write(image, FORMAT_NAME, new File(pathFile));
	}

	/**
	 * 
	 * @param destPath
	 *            存放目录
	 * @author wolf
	 * @date 2016年11月17日
	 * @Description: 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
	 */
	public static void mkdirs(String destPath) {
		File file = new File(destPath);
		// 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	/**
	 * 
	 * @param fileName
	 * @author wolf
	 * @date 2016年11月17日
	 * @Description: 删除已有文件
	 */
	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}

	/**
	 * 
	 * @param fileName
	 * @author wolf
	 * @date 2016年11月17日
	 * @Description: 删除所有文件
	 */
	public static void delete(File file) {
		if (!file.exists()) {
			return;// 文件不存在，返回
		}
		File[] ff = file.listFiles();
		for (File __f : ff) {
			if (__f.isDirectory()) {// 检查是否是一个文件夹
				delete(__f);
			}
			__f.delete();
		}
	}

	/**
	 * (打印标签)生成二维码(内嵌LOGO)
	 * 
	 * @param content(json串)
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存储地址
	 * @fileName 生成二位码图片的名字
	 * @throws Exception
	 * @author wolf
	 * @date 2016年11月17日
	 */
	public static void encode(String content, String destPath, String fileName) throws Exception {
		QrCodeCreateUtil.encode(content, destPath, fileName, true);
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param output
	 *            输出流
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 * @author wolf
	 * @date 2016年11月17日
	 */
	private static void encode(String content, OutputStream output, boolean needCompress) throws Exception {
		BufferedImage image = QrCodeCreateUtil.createImageNoBorder(content, needCompress);
		ImageIO.write(image, FORMAT_NAME, output);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param output
	 *            输出流
	 * @throws Exception
	 * @author wolf
	 * @date 2016年11月17日
	 */
	public static void encode(String content, OutputStream output) throws Exception {
		QrCodeCreateUtil.encode(content, output, true);
	}

	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码图片
	 * @return
	 * @throws Exceptio
	 * @author wolf
	 * @date 2016年11月17日
	 */
	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}

	/**
	 * 解析二维码
	 * 
	 * @param path
	 *            二维码图片地址
	 * @return
	 * @throws Exception
	 * @author wolf
	 * @date 2016年11月17日
	 */
	public static String decode(String path) throws Exception {
		return QrCodeCreateUtil.decode(new File(path));
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
    public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException{  
            //设置二维码纠错级别ＭＡＰ
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();  
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // 矫错级别  
            QRCodeWriter qrCodeWriter = new QRCodeWriter();  
            //创建比特矩阵(位矩阵)的QR码编码的字符串  
            BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);  
            // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
            int matrixWidth = byteMatrix.getWidth();  
            BufferedImage image = new BufferedImage(matrixWidth-200, matrixWidth-200, BufferedImage.TYPE_INT_RGB);  
            image.createGraphics();  
            Graphics2D graphics = (Graphics2D) image.getGraphics();  
            graphics.setColor(Color.WHITE);  
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);  
            // 使用比特矩阵画并保存图像
            graphics.setColor(Color.BLACK);  
            for (int i = 0; i < matrixWidth; i++){
                for (int j = 0; j < matrixWidth; j++){
                    if (byteMatrix.get(i, j)){
                        graphics.fillRect(i-100, j-100, 1, 1);  
                    }
                }
            }
            return ImageIO.write(image, imageFormat, outputStream);  
    }  
      
    /**
     * 读二维码并输出携带的信息
     */
    public static void readQrCode(InputStream inputStream) throws IOException{  
        //从输入流中获取字符串信息
        BufferedImage image = ImageIO.read(inputStream);  
        //将图像转换为二进制位图源
        LuminanceSource source = new BufferedImageLuminanceSource(image);  
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
        QRCodeReader reader = new QRCodeReader();  
        Result result = null ;  
        try {
         result = reader.decode(bitmap);  
        } catch (ReaderException e) {
            e.printStackTrace();  
        }
        System.out.println(result.getText());  
    }
	public static void main(String[] args) throws Exception {
		 createQrCode(new FileOutputStream(new File("http://120.55.43.176"+File.separator+"home"+File.separator+"test.png")),"https://www.baidu.com/",900,"png");
	    /* readQrCode(new FileInputStream(new File("d:\\qrcode.jpg")));*/  
	}
}