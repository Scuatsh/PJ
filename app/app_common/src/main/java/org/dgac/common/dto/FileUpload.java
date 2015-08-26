package org.dgac.common.dto;

import java.io.Serializable;
import java.util.Arrays;

public class FileUpload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1155257461587911214L;
	private byte[] file;
	private String fileName;


	public FileUpload() {
		super();
	}
	public FileUpload(byte[] file, String fileName) {		
		super();
		this.file = file;
		this.fileName = fileName;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
