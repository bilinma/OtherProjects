package com.bilin.aws;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.bilin.utils.DateUtil;

/**
 * 
 * @author xiaobin.ma
 *
 */
public class S3UploadTest {
	private static Logger logger = Logger.getLogger(S3UploadTest.class);
	
    public static String accessKeyID = "AKIAJ56CUMYNHH5A7EXA";
    public static String secretKey = "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";
    //桶名称
    public static String bucketName = "ukl-devtest-bss-terminal-configure-001";
	public static AmazonS3 s3;

	static {
		//首先创建一个s3的客户端操作对象（需要amazon提供的密钥）  
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyID, secretKey);
        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials));
        //设置S3的地区
        builder.setRegion(Regions.AP_SOUTHEAST_1.getName());
        s3 = builder.build();
    }

	/**
	 * 
	 * @param tempFile
	 * @param remoteFileName
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused" })
	public static String uploadToS3(File tempFile, String remoteFileName) throws IOException, Exception {  
		//设置bucket,key  
		String key = UUID.randomUUID() + ".pg";
		logger.info("key : " + key ); 
		try {
			//验证名称为bucketName的bucket是否存在，不存在则创建  
			if (!checkBucketExists(s3, bucketName)) {  
				s3.createBucket(bucketName);  
			}
			//上传文件  
			s3.putObject(new PutObjectRequest(bucketName, key, tempFile));  
			
			S3Object s3Object = s3.getObject(new GetObjectRequest(bucketName, key));  
			//获取一个request  
			GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);
			/**
			 * 设置过期时间  
			 */
			Date expirationDate = null;  
			try {  
				expirationDate = DateUtil.parse("2018-11-10 00:00:00");  
				urlRequest.setExpiration(expirationDate);  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
			//生成公用的url  
			URL url = s3.generatePresignedUrl(urlRequest);  
			System.out.println("=========URL=================" + url + "============URL=============");  
			if (url == null) {  
				throw new Exception("can't get s3 file url!");  
			}  
			return url.toString();  
		} catch (AmazonServiceException ase) {
			ase.printStackTrace();  
			logger.info("====================================AWS S3 UPLOAD ERROR START======================================");  
			logger.info("Caught an AmazonServiceException, which means your request made it "  
					+ "to Amazon S3, but was rejected with an error response for some reason.");  
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
			logger.info(ase.getMessage(), ase);  
			logger.info("====================================AWS S3 UPLOAD ERROR END======================================");  
			throw new Exception("error occurs during upload to s3!");  
		} catch (AmazonClientException ace) {  
			ace.printStackTrace();
			logger.info("====================================AWS S3 UPLOAD ERROR START======================================");  
			logger.info("Caught an AmazonClientException, which means the client encountered "  
					+ "a serious internal problem while trying to communicate with S3, "  
					+ "such as not being able to access the network.");  
			logger.info("Error Message: " + ace.getMessage());  
			logger.info("====================================AWS S3 UPLOAD ERROR END======================================");  
			throw new Exception("error occurs during upload to s3!");  
		}  
	}  
	/**
	 * 
	 * @param inputStream
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings({"unused" })
	public static String uploadToS3(InputStream inputStream, String key) throws IOException, Exception {
		System.out.println("key: " + key);
		try {
			//验证名称为bucketName的bucket是否存在，不存在则创建  
			if (!checkBucketExists(s3, bucketName)) {  
				s3.createBucket(bucketName);  
			}
			//上传文件  
			s3.putObject(bucketName, key, inputStream, null);
			
			S3Object s3Object = s3.getObject(new GetObjectRequest(bucketName, key));  
			//获取一个request  
			GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);
			/**
			 * 设置过期时间  
			 */
			urlRequest.setExpiration(DateUtil.parse("2018-11-10 00:00:00"));
			//生成公用的url  
			URL url = s3.generatePresignedUrl(urlRequest);  
			System.out.println("=========URL=================" + url + "============URL=============");  
			if (url == null) {  
				throw new Exception("can't get s3 file url!");  
			}  
			return url.toString();  
		} catch (AmazonServiceException ase) {
			ase.printStackTrace();  
			logger.info("====================================AWS S3 UPLOAD ERROR START======================================");  
			logger.info("Caught an AmazonServiceException, which means your request made it "  
					+ "to Amazon S3, but was rejected with an error response for some reason.");  
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
			logger.info(ase.getMessage(), ase);  
			logger.info("====================================AWS S3 UPLOAD ERROR END======================================");  
			throw new Exception("error occurs during upload to s3!");  
		} catch (AmazonClientException ace) {  
			ace.printStackTrace();
			logger.info("====================================AWS S3 UPLOAD ERROR START======================================");  
			logger.info("Caught an AmazonClientException, which means the client encountered "  
					+ "a serious internal problem while trying to communicate with S3, "  
					+ "such as not being able to access the network.");  
			logger.info("Error Message: " + ace.getMessage());  
			logger.info("====================================AWS S3 UPLOAD ERROR END======================================");  
			throw new Exception("error occurs during upload to s3!");  
		}  
	}  
	/**
	 * 
	 * @param tempFile
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	public static String uploadToS3(File tempFile) throws IOException, Exception {  
		//设置bucket,key  
		String key = UUID.randomUUID() + ".pg";
		logger.info("key : " + key ); 
		try {
			//验证名称为bucketName的bucket是否存在，不存在则创建  
//			if (!checkBucketExists(s3, bucketName)) {  
//				s3.createBucket(bucketName);  
//			}
			//上传文件  
			s3.putObject(new PutObjectRequest(bucketName, key, tempFile));  
			S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));  
			//获取一个request  
			GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);  
			Date expirationDate = null;  
			try {  
				expirationDate = DateUtil.parse("2018-11-10 00:00:00");  
				//设置过期时间  
				urlRequest.setExpiration(expirationDate);  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
			//生成公用的url  
			URL url = s3.generatePresignedUrl(urlRequest);  
			System.out.println("=========URL=================" + url + "============URL=============");  
			if (url == null) {  
				throw new Exception("can't get s3 file url!");  
			}  
			return url.toString();  
		} catch (AmazonServiceException ase) {
			ase.printStackTrace();  
			logger.info("====================================AWS S3 UPLOAD ERROR START======================================");  
			logger.info("Caught an AmazonServiceException, which means your request made it "  
					+ "to Amazon S3, but was rejected with an error response for some reason.");  
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
			logger.info(ase.getMessage(), ase);  
			logger.info("====================================AWS S3 UPLOAD ERROR END======================================");  
			throw new Exception("error occurs during upload to s3!");  
		} catch (AmazonClientException ace) {  
			ace.printStackTrace();
			logger.info("====================================AWS S3 UPLOAD ERROR START======================================");  
			logger.info("Caught an AmazonClientException, which means the client encountered "  
					+ "a serious internal problem while trying to communicate with S3, "  
					+ "such as not being able to access the network.");  
			logger.info("Error Message: " + ace.getMessage());  
			logger.info("====================================AWS S3 UPLOAD ERROR END======================================");  
			throw new Exception("error occurs during upload to s3!");  
		}  
	}	
	/** 
	 * 验证s3上是否存在名称为bucketName的Bucket 
	 * @param s3 
	 * @param bucketName 
	 * @return 
	 */  
	public static boolean checkBucketExists (AmazonS3 s3, String bucketName) {  
		List<Bucket> buckets = s3.listBuckets();  
		for (Bucket bucket : buckets) {  
			if (Objects.equals(bucket.getName(), bucketName)) {  
				return true;  
			}  
		}  
		return false;  
	}
	@SuppressWarnings({ "unused" })
	public static String getFileUrlByKey(String key) throws IOException, Exception {  
		try {
			S3Object s3Object = s3.getObject(new GetObjectRequest(bucketName, key));  
			//获取一个request  
			GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);
			//生成公用的url  
			URL url = s3.generatePresignedUrl(urlRequest);  
			System.out.println("=========URL=================" + url + "============URL=============");  
			if (url == null) {  
				throw new Exception("can't get s3 file url!");  
			}  
			return url.toString();  
		} catch (AmazonServiceException ase) {
			ase.printStackTrace();  
			logger.info("====================================AWS S3 UPLOAD ERROR START======================================");  
			logger.info("Caught an AmazonServiceException, which means your request made it "  
					+ "to Amazon S3, but was rejected with an error response for some reason.");  
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
			logger.info(ase.getMessage(), ase);  
			logger.info("====================================AWS S3 UPLOAD ERROR END======================================");  
			throw new Exception("error occurs during upload to s3!");  
		} catch (AmazonClientException ace) {  
			ace.printStackTrace();
			logger.info("====================================AWS S3 UPLOAD ERROR START======================================");  
			logger.info("Caught an AmazonClientException, which means the client encountered "  
					+ "a serious internal problem while trying to communicate with S3, "  
					+ "such as not being able to access the network.");  
			logger.info("Error Message: " + ace.getMessage());  
			logger.info("====================================AWS S3 UPLOAD ERROR END======================================");  
			throw new Exception("error occurs during upload to s3!");  
		}  
	}  
	
	
	public static void main(String[] args) {
		//1
//		String key = UUID.randomUUID() + ".wangmeng";
//		logger.info("key : "+key); 
//		uploadFile("/Users/zhangjiamei/Downloads/FAQ.xls", bucketName, key, true);
		//2.
//		File file = new File("/Users/zhangjiamei/Downloads/order_4.xls");
//		try {
//			uploadToS3(file, bucketName);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//3.
		File file = new File("C:/aaa.txt");
		try {
			uploadToS3(new FileInputStream(file),UUID.randomUUID()+".txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}