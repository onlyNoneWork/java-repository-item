package com.changgou.goods.service.file;

import com.changgou.domain.FastDFSFile;
import com.changgou.exception.RunFileException;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: hui.jin
 * @date: 2021/9/27 16:47
 */
@Slf4j
@Component
public class FastDFSClient {

    /**
     * 初始化加载fastDFS的trackerServer配置
     */
    static {
        try {
            String path = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(path);
        } catch (Exception e) {
            throw new RunFileException("初始化文件管理");
        }
    }

    /**
     * 文件上传
     * @return
     */
    public static String[] upload(FastDFSFile file){
        //获取文件的作者
        NameValuePair[] nameValuePairs = new NameValuePair[1];
        nameValuePairs[0] = new NameValuePair("author", file.getAuthor());

        String[] uploadFile;
        StorageClient storageClient;
        try {
            storageClient = getStorageClient();
            uploadFile = storageClient.upload_file(file.getContext(), file.getExt(), nameValuePairs);
        } catch (Exception e) {
            throw new RunFileException("文件"+file.getName()+"上传", e);
        }

        if (uploadFile == null && storageClient != null){
            log.error("file upload fail, error code:"+storageClient.getErrorCode());
        }

        //获取组名
        String groupName = uploadFile[0];
        //文件存储路径
        String remoteFileName = uploadFile[1];

        return uploadFile;
    }

    /**
     * 获取文件信息
     * @param groupName 组名
     * @param remoteFileName 文件存储路径
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName){

        FileInfo fileInfo;
        try {
            StorageClient storageClient = getStorageClient();
            fileInfo = storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            throw new RunFileException("获取文件信息", e);
        }

        return fileInfo;
    }

    /**
     * 下载文件
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static byte[] downFile(String groupName, String remoteFileName){
        StorageClient storageClient = getStorageClient();
        byte[] bytes;
        try {
            bytes = storageClient.download_file(groupName, remoteFileName);
        } catch (Exception e) {
            throw new RunFileException("下载文件", e);
        }

        return bytes;
    }

    /**
     * 删除文件
     * @param groupName 组名
     * @param remoteFileName 路径
     * @return
     */
    public static int deleteFile(String groupName, String remoteFileName){

        StorageClient storageClient = getStorageClient();
        int i ;
        try {
            i = storageClient.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            throw new RunFileException("删除文件", e);
        }
        return i;
    }

    /**
     * 获取文件存储组
     * @param groupName
     * @return
     */
    public static StorageServer[] getStorageServerGroup(String groupName){
        TrackerClient trackerClient = new TrackerClient();
        StorageServer[] storeStorages = new StorageServer[0];
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            storeStorages = trackerClient.getStoreStorages(trackerServer, groupName);
        } catch (IOException e) {
            throw new RunFileException("获取文件存储组", e);
        }

        return storeStorages;
    }

    /**
     * 获取文件存储信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static ServerInfo[] getServerInfos(String groupName, String remoteFileName){
        TrackerClient trackerClient = new TrackerClient();
        ServerInfo[] fetchStorages;
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            fetchStorages = trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
        } catch (IOException e) {
            throw new RunFileException("获取文件存储信息", e);
        }
        return fetchStorages;
    }

    /**
     * 获取文件的储存客户端
     * @return
     * @throws IOException
     */
    public static StorageClient getStorageClient(){
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 获取传输的url
     * @return
     */
    public static String getTrackerUrl(){

        return "https://"+getTrackerServer().getInetSocketAddress().getHostString()+":"
                +ClientGlobal.getG_tracker_http_port()+"/";
    }

    /**
     * 获取文件调用者的服务
     * @return
     * @throws IOException
     */
    private static TrackerServer getTrackerServer(){
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer connection = null;
        try {
            connection = trackerClient.getConnection();
        } catch (IOException e) {
            throw new com.changgou.exception.IOException("文件流");
        }
        return connection;
    }
}
