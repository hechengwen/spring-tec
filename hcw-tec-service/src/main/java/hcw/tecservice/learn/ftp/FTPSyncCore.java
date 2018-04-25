package hcw.tecservice.learn.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/23 13:59
 * Description:
 * Others:
 */
public class FTPSyncCore {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static int READ_TIMEOUT = 120000;
    private static String ENCODING = "GBK";

    private FTPClient ftpClient = null; // FTP 客户端代理
    private String mLocalRootPath = "";

    public FTPSyncCore(String mLocalRootPath) {
        this.mLocalRootPath = mLocalRootPath;
    }

    /**
     * 连接到服务器
     *
     * @return true 连接服务器成功，false 连接服务器失败
     */
    public boolean connectServer() {
        boolean flag = false;
        String address = FTPConfig.getServerAddress();
        int port = FTPConfig.getServerPort();

        if (ftpClient == null) {
            try {
                ftpClient = new FTPClient();
                ftpClient.setControlEncoding(ENCODING);
                ftpClient.configure(getFtpConfig());
                ftpClient.connect(address, port);
                ftpClient.login(FTPConfig.getUserName(), FTPConfig.getPassword());
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                ftpClient.setDataTimeout(READ_TIMEOUT);
                int reply = ftpClient.getReplyCode();

                if (FTPReply.isPositiveCompletion(reply)) {
                    flag = true;
                    logger.info("FTP服务连接成功。。。");
                } else {
                    logger.warn("FTP服务拒绝连接。。。");
                    ftpClient.disconnect();
                }
            } catch (Exception e) {
                logger.error("Failed to login ftp " + address + ":" + port, e);
            }
        }
        return flag;
    }

    /**
     * 设置FTP客服端的配置--一般可以不设置
     *
     * @return ftpConfig
     */
    private FTPClientConfig getFtpConfig() {
        FTPClientConfig ftpConfig = new FTPClientConfig(FTPConfig.getSystem());
        ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
        return ftpConfig;
    }

    /**
     * 上传文件夹内的所有文件
     *
     * @param filename   本地文件夹绝对路径
     * @param uploadpath 上传到FTP的路径,形式为/或/dir1/dir2/../
     * @return true 上传成功，false 上传失败
     * @throws IOException
     */
    public List<Object> uploadManyFile(String filename, String uploadpath) {
        boolean flag = true;
        List<Object> list = new ArrayList<Object>();
        StringBuffer strBuf = new StringBuffer();
        int failCount = 0; // 上传失败的文件个数
        int m = 0; // 上传成功的文件个数
        try {
            ftpClient.changeWorkingDirectory("/");
            File file = new File(filename);
            File fileList[] = file.listFiles();
            for (File upfile : fileList) {
                if (upfile.isDirectory()) {
                    uploadManyFile(upfile.getAbsoluteFile().toString(), uploadpath);
                } else {
                    String local = upfile.getCanonicalPath().replaceAll("\\\\", "/");
                    String remote = uploadpath.replaceAll("\\\\", "/");
                    if (!remote.endsWith("/")) {
                        remote += "/";
                    }
                    remote += local.substring(mLocalRootPath.length() + (mLocalRootPath.endsWith("/") ? 0 : 1));
                    flag = uploadFile(local, remote);
                    ftpClient.changeWorkingDirectory("/");
                }
                if (!flag) {
                    failCount++;
                    strBuf.append(upfile.getName() + ",");
                    logger.info("File［" + upfile.getName() + "］upload failed");
                } else {
                    m++;
                }
            }
            list.add(0, failCount);
            list.add(1, m);
            list.add(2, strBuf.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.error("local file upload failed, the file not found！" + e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("local file upload failed ！" + e);
        }
        return list;
    }

    /**
     * 上传单个文件，并重命名
     *
     * @param local  --本地文件路径
     * @param remote --远程文件路径
     * @return true 上传成功，false 上传失败
     * @throws IOException
     */
    public boolean uploadFile(String local, String remote) throws IOException {
        boolean flag = true;
        String remoteFileName = remote;
        if (remote.contains("/")) {
            remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
            // 创建服务器远程目录结构，创建失败直接返回
            if (!createDirecroty(remote)) {
                return false;
            }
        }
        File f = new File(local);
        if (!uploadFile(remoteFileName, f)) {
            flag = false;
        }
        return flag;
    }

    /**
     * upload file
     *
     * @param remoteFile 远程文件路径,支持多级目录嵌套
     * @param localFile  本地文件名称，绝对路径
     */
    public boolean uploadFile(String remoteFile, File localFile) throws IOException {
        boolean flag = false;
        InputStream input = null;

        try {
            input = new FileInputStream(localFile);
            String remote = new String(remoteFile.getBytes("GBK"), "iso-8859-1");
            if (ftpClient.storeFile(remote, input)) {
                flag = true;
            }
        } finally {
            IOUtils.silenceClose(input);
        }

        logger.info("push file (" + localFile.getCanonicalPath() + ") => " + (flag ? "SUCCESS" : "FAILED"));
        return flag;
    }

    /**
     * 递归创建远程服务器目录
     *
     * @param remote 远程服务器文件绝对路径
     * @return 目录创建是否成功
     * @throws IOException
     */
    public boolean createDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/")
                && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = new String(remote.substring(start, end)
                        .getBytes(ENCODING), "iso-8859-1");
                if (!changeWorkingDirectory(subDirectory)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        logger.info("make directory [" + subDirectory + "] failed");
                        success = false;
                        return success;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    /**
     * 进入到服务器的某个目录下
     *
     * @param directory
     */
    public boolean changeWorkingDirectory(String directory) {
        boolean change = true;
        try {
            change = ftpClient.changeWorkingDirectory(directory);
            if (change) {
                logger.info("change FTP Directory => " + ftpClient.printWorkingDirectory());
            } else {
                logger.info("change FTP Directory(" + directory + ") => FAILED");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return change;
    }

    /**
     * 在服务器上创建一个文件夹
     *
     * @param dir 文件夹名称，不能含有特殊字符，如 \ 、/ 、: 、* 、?、 "、 <、>...
     */
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                logger.info("make directory " + dir + " successfully ！");
            } else {
                logger.info("make directory " + dir + " failed ！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 关闭连接
     */
    public void closeConnect() {
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
