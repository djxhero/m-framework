package com.lancslot.morn.utils.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.SocketException;
import java.util.Map;
import java.util.TimeZone;

public class FtpUtil {

    public static boolean uploadFile(String host , int port , String userName , String password , String path , String fileName , InputStream is) {
        Logger logger = LoggerFactory.getLogger(FtpUtil.class);

        FTPClient ftpClient = loginToFtp(host , port , userName , password);

        if (ftpClient == null) {
            return false;
        }

        // 创建目录失败
        if (!createDirectory(ftpClient , path)){
            return false;
        }

        try {
            boolean isExisted = ftpClient.changeWorkingDirectory(path);
            if (!isExisted) {
                logger.info("directory {} not existed" , path);
                return false;
            }
            boolean result = ftpClient.storeFile(fileName , is);
            logger.info("upload file {} to {} on ftp server {}" , fileName , path , result ? "success" : "failure");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error(e.getMessage() , e);
            }
        }
        return false;
    }

    public static boolean createDirectory(FTPClient ftpClient , String path) {
        Logger logger = LoggerFactory.getLogger(FtpUtil.class);
        try {
            boolean isExisted = ftpClient.changeWorkingDirectory(path);
            if (isExisted) {
                logger.info("{} has exists" , path);
                return true;
            }
            String[] subDirectories = path.split("/");
            String realPath = "";
            for (String directory : subDirectories) {
                if (StringUtils.hasText(directory)) {
                    realPath = realPath + "/" + directory;
                    isExisted = ftpClient.changeWorkingDirectory(realPath);
                    logger.info("directory {} existed ? {}" , realPath , isExisted);
                    if (!isExisted) {
                        boolean result = ftpClient.makeDirectory(realPath);
                        logger.info("create directory {} on ftp server {}" ,  realPath , result ? "success" : "failure");
                        if (!result) {
                            return result;
                        }
                    }
                }
            }
            logger.info("{} not exists and create it success" , realPath);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
        }
        logger.info("{} not exists and create it failure" , path);
        return false;
    }

    public static FTPClient loginToFtp(String host , int port , String userName , String password){
        Logger logger = LoggerFactory.getLogger(FtpUtil.class);
        try {
            FTPClientConfig ftpClientConfig = new FTPClientConfig();
            ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
            FTPClient ftpClient = new FTPClient();
            ftpClient.configure(ftpClientConfig);
            ftpClient.setControlEncoding("utf-8");
            ftpClient.connect(host , port);
            if (ftpClient.login(userName , password)) {
                ftpClient.setDataTimeout(5*60*1000);
                ftpClient.setSoTimeout(5*60*1000);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setSendDataSocketBufferSize(1024);
                ftpClient.setSendBufferSize(1024);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                logger.info("login to ftp server  host={} port={} userName={} password={} success" , host , port , userName , password);
                return ftpClient;
            }
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
        }
        logger.info("login to ftp server host={} port={} userName={} password={} failure" , host , port , userName , password);
        return null;
    }
    public static boolean uploadFileList(String host , int port , String userName , String password , String path ,
                                         Map<String, InputStream> file) {
        Logger logger = LoggerFactory.getLogger(FtpUtil.class);

        FTPClient ftpClient = loginToFtp(host , port , userName , password);

        if (ftpClient == null) {
            return false;
        }

        // 创建目录失败
        if (!createDirectory(ftpClient , path)){
            return false;
        }

        try {
            boolean isExisted = ftpClient.changeWorkingDirectory(path);
            if (!isExisted) {
                logger.info("directory {} not existed" , path);
                return false;
            }

            for (Map.Entry<String, InputStream> entry : file.entrySet()) {

                if(!ftpClient.storeFile(entry.getKey() , entry.getValue())){
                    logger.info("ftp client store file failue!");
                    return false;
                }
            }
            logger.info("ftp client store file true!");
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error(e.getMessage() , e);
            }
        }
        return false;
    }
    public static void getFile(String host , int port , String userName , String password ,
                                String path , String fileName, String tempPath, String downLoadName){

        InputStream in = null;
        FTPClient ftpClient = loginToFtp(host , port , userName , password);

        OutputStream is = null;
        try {

            ftpClient.changeWorkingDirectory(path);
            File localFile = new File(tempPath+"/"+downLoadName);

            is = new FileOutputStream(localFile);

            ftpClient.retrieveFile(fileName, is);

            ftpClient.logout();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            try {
                if (null != is){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static File downloadFile(String host, int port, String userName, String password,
                                    String path, String fileName, String tempPath, String downloadName) {
        FTPClient ftpClient = null;
        File localFile = null;
        OutputStream os = null;
        try {
            ftpClient = loginToFtp(host, port, userName, password);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            ftpClient.changeWorkingDirectory(path);

            tempPath = System.getProperty("user.dir") + "/../" + tempPath;
            File directory = new File(tempPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            localFile = new File(tempPath + "/" + downloadName);
            if (!localFile.exists()) {
                localFile.createNewFile();
            }
            os = new FileOutputStream(localFile);

            ftpClient.retrieveFile(fileName, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
            if (ftpClient != null) {
                try {
                    ftpClient.logout();
                } catch (Exception e) {}
            }
        }

        return localFile;
    }

    public static void getFile(String host , int port , String userName , String password ,
                               String path , Map<String, String> map, String tempPath){
        FTPClient ftpClient = loginToFtp(host , port , userName , password);
        OutputStream is = null;
        try {
            ftpClient.changeWorkingDirectory(path);
            for(Map.Entry<String, String> entry : map.entrySet()){
                File localFile = new File(tempPath+"/"+ entry.getValue());
                is = new FileOutputStream(localFile);
                ftpClient.retrieveFile(entry.getKey(), is);
                is.close();
            }

            ftpClient.logout();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            try {
                if (null != is){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
