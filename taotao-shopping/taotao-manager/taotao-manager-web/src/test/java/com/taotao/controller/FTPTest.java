package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;


public class FTPTest {
	@Test
	public void testFtpClient() throws Exception{
		//创建FTP连接
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.56.101", 21);
		//登录FTP服务器
		ftpClient.login("ftpuser", "Qwer!234");
		//上传文件
		//参数一：服务端文档名
		//参数二：上传文档的inputStream
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\11981\\Pictures\\Saved Pictures\\MyJsp.png"));
		ftpClient.changeWorkingDirectory("/home/ftpuser/Image");
		//设置为被动模式
		ftpClient.enterLocalPassiveMode();
		//修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.storeFile("test.png", inputStream);
		//关闭连接
		inputStream.close();
		ftpClient.logout();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
