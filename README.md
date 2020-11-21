
## 愿景：go-fastdfs-sdk实现一行代码接入go-fastdfs



# go-fastdfs是一个基于http协议的分布式文件系统，它基于大道至简的设计理念，一切从简设计，使得它的运维及扩展变得更加简单，它具有高性能、高可靠、无中心、免维护等优点。



go-fastdfs辅助学习信息  [使用文档](https://sjqzhang.github.io/go-fastdfs/#character) 或 [视频教程](https://www.bilibili.com/video/av92526484)。



#### go-fastdfs-sdk主要功能

- 查询系统信息
- 查询统计信息
- 上传文件
- 删除文件
- 修复统计信息
- 删除空目录
- 备份元数据
- 同步失败修复 
- 文件列表 
- 文件信息
- 文件下载
- 内置一套Token生成机制



#### 示例

~~~java
private static String url = "http://127.0.0.1:8080/group1";

//查询系统信息
GoFastdfsResult<Status> result = GoFastdfsApi.status(url);

//查询统计信息
GoFastdfsResult<List<FileStats>> result = GoFastdfsApi.stat(url);

//修复统计信息
GoFastdfsResult<FileStats> result = GoFastdfsApi.repairStat(url, "20201119");

//删除空目录
GoFastdfsApi.removeEmptyDir(url);

//备份元数据
GoFastdfsApi.backup(url, "20201121");

//同步失败修复 
GoFastdfsApi.repair(url, true);

//上传文件
UploadInfo result = GoFastdfsApi.upload(url, "","", new File("d:/go-fastdfs/test.txt"));

//文件信息
GoFastdfsResult<FileInfo> result = GoFastdfsApi.fileInfo(url, "a4f80e8f25c4e5069b6739bfeba7f888");

//删除文件
GoFastdfsApi.delete(url, "a4f80e8f25c4e5069b6739bfeba7f888");

//文件列表 
GoFastdfsResult<List<ListDirInfo>>  result = GoFastdfsApi.listdir(url, "");

//文件下载
GoFastdfsApi.download(url+"test.txt");

~~~




#### 如果你觉得本项目不错，请点击项目顶部的 `star` 按钮关注本项目
