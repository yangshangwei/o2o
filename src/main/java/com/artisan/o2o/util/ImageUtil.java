package com.artisan.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageUtil {
		
	private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random random = new Random();

	/**
	 * 
	 * 
	 * @Title: commonsMultipartFileToFile
	 * 
	 * @Description: 众所周知,文件上传spring使用CommonsMultipartFile来接收上传过来的文件，
	 *               而generateThumbnails方法中的第一个入参我们已经调整为了File
	 *               （主要是为了方便service层进行单元测试
	 *               ，在service层无法初始化CommonsMultipartFile，只能在前端传入的时候初始化
	 *               ，我们从底往上搭建项目，还不具备页面,因此做了适当的改造）
	 * 
	 *               需要将CommonsMultipartFile转换为File
	 * 
	 * @param cfile
	 * 
	 * @return: File
	 */
	public File commonsMultipartFileToFile(CommonsMultipartFile cfile) {
		File file = null;
		try {
			// 获取前端传递过来的文件名
			file = new File(cfile.getOriginalFilename());
			// 将cfile转换为file
			cfile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("commonsMultipartFileToFile failed:{}", e.getMessage());
		}
		return file;
	}

	/**
	 * 
	 * 
	 * @Title: generateThumbnails
	 * 
	 * @Description: 处理缩略图，并返回绝对路径 （门面照以及商品的小图）
	 * 
	 *               举例：shop的水印图标要存放在tb_shop的 shop_img字段,所以需要返回 水印图标所在的路径
	 * 
	 * 
	 * 
	 * @param file
	 *            需要添加水印的文件
	 * @param destPath
	 *            添加水印后的文件的存放目录
	 * @return
	 * 
	 * @return: String 返回相对路径的好处是，项目一旦迁移,不会影响，只需要变更basePath即可，尽可能少改动。
	 *          图片存储的绝对路径=basePath+该路径
	 */
	public static String generateThumbnails(File file, String destPath) {
		// 拼接后的新文件的相对路径
		String relativeAddr = null;
		try {
			// 1.为了防止图片的重名，不采用用户上传的文件名，系统内部采用随机命名的方式
			String fileName = generateRandomFileName();
			// 2.获取用户上传的文件的扩展名,用于拼接新的文件名
			String fileExtensionName = getFileExtensionName(file);
			// 3.校验目标目录是否存在，不存在创建目录
			validateDestPath(destPath);
			// 4.拼接新的文件名
			relativeAddr = destPath + fileName + fileExtensionName;
			logger.info("图片相对路径 {}", relativeAddr);
			// 绝对路径的形式创建文件
			String basePath = FileUtil.getImgBasePath();
			File destFile = new File(basePath + relativeAddr);
			logger.info("图片完整路径 {}", destFile.getAbsolutePath());
			// 5.给源文件加水印后输出到目标文件
			Thumbnails.of(file).size(500, 500).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(FileUtil.getWaterMarkFile()), 0.25f).outputQuality(0.8).toFile(destFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建水印图片失败：" + e.toString());
		}
		return relativeAddr;
	}




	/**
	 * 
	 * 
	 * @Title: generateRandomFileName
	 * 
	 * @Description: 系统时间+5位随机数字
	 * 
	 * @param file
	 * @return
	 * 
	 * @return: String
	 */
	private static String generateRandomFileName() {
		String sysdate = sdf.format(new Date());
		// 5位随机数 10000到99999之间 ,下面的取值[ 包括左边，不包括右边 ]，满足10000到99999
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		String randomFileName = sysdate + rannum;
		logger.debug("fileName: {}", randomFileName);
		return randomFileName;
	}

	/**
	 * 
	 * 
	 * @Title: getFileExtensionName
	 * 
	 * @Description: 获取文件的扩展名
	 * 
	 * @param file
	 * 
	 * @return: String
	 */
	private static String getFileExtensionName(File file) {
		String fileName = file.getName();
		String extension = fileName.substring(fileName.lastIndexOf("."));
		logger.debug("extension: {}", extension);
		return extension;
	}

	/**
	 * 
	 * 
	 * @Title: validateDestPath
	 * 
	 * @Description:
	 * 
	 * @param targetAddr
	 *            图片上传的相对路径
	 * 
	 * @return: void
	 */
	private static void validateDestPath(String targetAddr) {
		// 获取绝对路径
		String realFileParentPath = FileUtil.getImgBasePath() + targetAddr;
		// 不存在的话，逐级创建目录
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}


	/**
	 * 
	 * 
	 * @Title: main
	 * 
	 * @Description: 演示thumbnail的基本用法
	 * 
	 * @param args
	 * 
	 * @return: void
	 */
	public static void main(String[] args) {
		try {
			// 需要加水印的图片
			File souceFile = new File("D:/o2o/artisan.jpg");
			// 加完水印后输出的目标图片
			File destFile = new File("D:/o2o/artisan-with-watermark.jpg");
			// 水印图片
			File warterMarkFile = FileUtil.getWaterMarkFile();
			logger.info("warterMarkFileName: {}", warterMarkFile.getName());
			// 加水印
			Thumbnails.of(souceFile).size(500, 500).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(warterMarkFile), 0.25f).outputQuality(0.8).toFile(destFile);
			logger.info("水印添加成功,带有水印的图片{}", destFile.getAbsolutePath());

			generateRandomFileName();
			getFileExtensionName(souceFile);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}

	}
}
