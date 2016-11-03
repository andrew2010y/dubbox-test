package com.nova.app.sys.dfs;

import java.io.File;
import java.io.IOException;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * 分布式文件系统客户端类.
 * 
 * @author juwenguang
 *
 */
public class DfsClient {
	
	private static DfsClient client = null;
	private TrackerServer trackerServer = null;
	
	synchronized public static DfsClient getInstance() throws Exception{
		String classPath = new File(DfsClient.class.getResource("/").getFile()).getCanonicalPath();
	    String configFilePath = classPath + File.separator + "client.conf";  
		ClientGlobal.init(configFilePath);
		
	    if(client == null){
	    	client = new DfsClient();
	    }
	    
	    return client;
	}
	
	private DfsClient() throws IOException{
		TrackerClient trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
	}
	
	/**
	 * 上传文件
	 * @param fileBuff 文件内容
	 * @param fileExtName 文件扩展名
	 * @return 文件绝对路径
	 * @throws IOException
	 * @throws Exception
	 */
	public String uploadFile(byte[] fileBuff, String fileExtName) throws IOException, Exception{
		StorageClient storageClient = new StorageClient(trackerServer, null);
		
		String[] filePaths = storageClient.upload_file(fileBuff, fileExtName, null);
		String filePath = "/" + filePaths[0] + "/" + filePaths[1];
		
		return filePath;
	}
	
	/**
	 * 根据文件路径删除文件
	 * @param filePath 文件绝对路径
	 * @return 删除成功标志
	 * @throws IOException
	 * @throws Exception
	 */
	public int deleteFile(String filePath) throws IOException, Exception{
		StorageClient storageClient = new StorageClient(trackerServer, null);
		int i = filePath.indexOf('/', 1);
		String groupName =filePath.substring(1, i);
		String fileName = filePath.substring(i + 1);
		
		int resultFlag = storageClient.delete_file(groupName, fileName);
		return resultFlag;
	}

	/**
	 * 根据文件路径下载文件
	 * @param filePath 文件的绝对路径
	 * @return 文件内容
	 * @throws IOException
	 * @throws Exception
	 */
	public byte[] downloadFile(String filePath) throws IOException, Exception{
		StorageClient storageClient = new StorageClient(trackerServer, null);
		int i = filePath.indexOf('/', 1);
		String groupName =filePath.substring(1, i);
		String fileName = filePath.substring(i + 1);
		
		byte[] bytes = storageClient.download_file(groupName, fileName);
		return bytes;
	}
}
