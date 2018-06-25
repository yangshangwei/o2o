package com.artisan.o2o.dto;

import java.io.InputStream;

public class ImageHolder {
	
	private InputStream ins ;
	private String fileName;

	
	/**
	 * 
	 * 
	 * @Title:ImageHolder
	 * 
	 * @Description:构造函数
	 * 
	 * @param ins
	 * @param fileName
	 */
	public ImageHolder(InputStream ins, String fileName) {
		this.ins = ins;
		this.fileName = fileName;
	}

	public InputStream getIns() {
		return ins;
	}

	public void setIns(InputStream ins) {
		this.ins = ins;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
