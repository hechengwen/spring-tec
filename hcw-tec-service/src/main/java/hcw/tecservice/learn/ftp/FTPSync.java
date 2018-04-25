package hcw.tecservice.learn.ftp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/23 13:56
 * Description:
 * Others:
 */
@Component
public class FTPSync {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *
     * @param uploadDirectory 待上传的目录
     * @param downloadSaveRootPath
     * @throws Exception
     */
    public void uploadDirectory(String uploadDirectory, String downloadSaveRootPath) throws Exception {
        boolean unloadSuccess = uploadDirectoryToServer(uploadDirectory, FTPConfig.getRootPath());
        if (!unloadSuccess) {
            throw new RuntimeException("上传FTP失败 ：=> " + uploadDirectory);
        }

    }

    private boolean uploadDirectoryToServer(String localDirectory, String serverSaveRootPath) {
        logger.info("start upload dir: " + localDirectory + " to server: " + serverSaveRootPath);
        File localDirectoryFile = new File(localDirectory);
        if (!localDirectoryFile.exists()) {
            logger.error("target upload directory file not exists " + localDirectory);
            return false;
        }
        try {
            localDirectory = localDirectoryFile.getCanonicalPath();// 返回的就是标准的将符号完全解析的绝对路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("".equals(localDirectory)) {
            logger.error("target upload directory is empty");
            return false;
        }
        localDirectory.replaceAll("\\\\", "/");
        if (!localDirectory.endsWith("/")) {
            localDirectory += "/";
        }
        FTPSyncCore ftpClient = new FTPSyncCore(localDirectory);
        if (ftpClient.connectServer()) {
            List<Object> list = ftpClient.uploadManyFile(localDirectory, serverSaveRootPath);
            if (list == null || list.size() < 3) {
                logger.error("uploadManyFile failed");
                return false;
            }
            int failCount = (int) list.get(0);
            int successCount = (int) list.get(1);
            String resultString = (String) list.get(2);
            logger.info("上传文件完成, 失败数量：" + failCount + " 成功数量：" + successCount + " 详情：" + resultString);
            ftpClient.closeConnect();	// 关闭连接
            if (failCount <= 0) {
                return true;
            }
        }
        return false;
    }
}
