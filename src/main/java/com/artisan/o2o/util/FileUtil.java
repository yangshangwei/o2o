package com.artisan.o2o.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * 
 * @ClassName: FileUtil
 * 
 * @Description: 文件工具类
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月18日 下午11:32:05
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	// 路径分隔符. 在 类UNIX系统上为 '/',在 windows 系统上，它为 '\'
	private static String seperator = System.getProperty("file.separator");

	/**
	 * 
	 * 
	 * @Title: getImgBasePath
	 * 
	 * @Description: 根据不同的操作系统,获取图片的存放路径。
	 * 
	 *               一般情况下都是将图片存放到专门的图片服务器或者主机上的其他目录。
	 *               而存放的目录路径，都是以配置项的形式存放在数据库配置表中或者配置文件中。
	 * 
	 *               这里为了简单起见，我们直接将路径硬编码在代码中。
	 * 
	 *               图片存储的根路径
	 * @return
	 * 
	 * @return: String
	 */
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		logger.debug("os.name: {}", os);
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/o2o/image";
		} else {
			basePath = "/home/artisan/o2o/image";
		}
		// 根据操作系统的不同，使用当前操作系统的路径分隔符替换掉，我们写的basePath中的路径分隔符，当然了也可以在basePath赋值的时候直接使用seperator
		basePath = basePath.replace("/", seperator);
		logger.debug("basePath: {}", basePath);
		return basePath;
	}

	/**
	 * 
	 * 
	 * @Title: getShopImagePath
	 * 
	 * @Description: 获取特定商铺的图片的路径,根据shopId来区分。理应配置到数据库配置表中或者配置文件中。
	 * 
	 *               同样的这里为了简化操作,硬编码
	 * 
	 *               约定每个店铺下的图片分别存放在对应的店铺id下
	 * 
	 *               图片存储的相对路径.图片最终存储的位置需要加上getImgBasePath方法返回的basePath
	 * 
	 *               数据库tb_shop中的shop_img字段存储的是该相对路径，即该方法的返回值
	 * 
	 * @param shopId
	 * @return
	 * 
	 * @return: String
	 */
	public static String getShopImagePath(long shopId) {
		String shopImgPath = "/upload/item/shopImage/" + shopId + "/";
		shopImgPath = shopImgPath.replace("/", seperator);
		logger.debug("shopImgPath: {}", shopImgPath);
		return shopImgPath;
	}

	/**
	 * 
	 * 
	 * @Title: getWaterMarkFile
	 * 
	 * @Description: 水印文件的位置。理应配置到数据库配置表中或者配置文件中。
	 * 
	 *               同样的这里为了简化操作,硬编码
	 * 
	 * @return
	 * 
	 * @return: File
	 */
	public static File getWaterMarkFile() {
		String basePath = FileUtil.getImgBasePath();
		String waterMarkImg = basePath + "/watermark/watermark.png";
		waterMarkImg = waterMarkImg.replace("/", seperator);
		logger.debug("waterMarkImg path: {}", waterMarkImg);
		return new File(waterMarkImg);
	}

}
