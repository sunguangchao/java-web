package com.taotao.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FtpUtil {
	private static final Logger LOGGER = Logger.getLogger(FtpUtil.class);
	public static boolean uploadFile(String host, int port, String username, String password, 
			String basePath, String filePath, String filename, InputStream input) {
		boolean result = false;
		FTPClient ftpClient = new FTPClient();
		
		try {
			int reply;
			ftpClient.connect(host, port);
			ftpClient.login(username, password);
			reply = ftpClient.getReplyCode();
			ftpClient.enterLocalPassiveMode();//设置为被动模式
			
			//??
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return result;
			}
			//切换到上传目录
			if (!ftpClient.changeWorkingDirectory(basePath+filePath)) {
				//如果目录不存在，则创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for(String dir : dirs) {
					if (null == dir || "".equals(dir))  continue;
					tempPath += "/" + dir;
					if (!ftpClient.changeWorkingDirectory(tempPath)) {
						if (!ftpClient.makeDirectory(tempPath)) {
							return result;
						}
					}else {
						ftpClient.changeWorkingDirectory(tempPath);
					}
				}
			}

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			if (!ftpClient.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftpClient.logout();
			result = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e2) {
					// TODO: handle exception
					
				}
			}
		}
		return result;
	}
	
	public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			ftp.enterLocalPassiveMode();//将FTP client改为被动模式
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.out.println("passive mode");
				return result;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();//找到该目录下的所有文件
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\11981\\Pictures\\Saved Pictures\\MyJsp.png"));
			boolean flag = uploadFile("192.168.56.101", 21, "ftpuser", "Qwer!234", "/home/ftpuser/Image", "/2015/01/21", "test2.png", inputStream);
			System.out.println(flag);
			boolean downflag = downloadFile("192.168.56.101", 21, "ftpuser", "Qwer!234", "/home/ftpuser/Image/2015/01/21", "test2.png", "E:\\CNTV");
			System.out.println(downflag);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}
	}
}
