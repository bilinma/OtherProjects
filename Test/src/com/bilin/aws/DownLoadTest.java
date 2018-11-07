package com.bilin.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
 
 
import java.io.*;

import org.apache.log4j.Logger;
 
 
public class DownLoadTest {
 
	
	private static Logger logger = Logger.getLogger(DownLoadTest.class);
 
    public static String accessKeyID = "AKIAJ56CUMYNHH5A7EXA";
 
    public static String secretKey = "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";
    //桶名称
    public static String bucketName = "ukl-devtest-bss-terminal-configure-001";
    //要下载文件在S3上的key
    public static String key = "mpms/d18f7456dbd25629ada4c07781332b0a.jpg";
 
    public static void main(String[] args) {
 
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyID, secretKey);
 
        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials));
        //设置S3的地区
        builder.setRegion(Regions.AP_SOUTHEAST_1.getName());
 
        AmazonS3 s3Client = builder.build();
        
        //本地路径
        String targetFilePath = "C:\\test\\demofile.jpg";
 
        amazonS3Downloading(s3Client,bucketName,key,targetFilePath);
 
    }
 
    public static void amazonS3Downloading(AmazonS3 s3Client,String bucketName,String key,String targetFilePath){
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName,key));
        if(object!=null){
            System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
            InputStream input = null;
            FileOutputStream fileOutputStream = null;
            byte[] data = null;
            try {
                //获取文件流
                input=object.getObjectContent();
                data = new byte[input.available()];
                int len = 0;
                fileOutputStream = new FileOutputStream(targetFilePath);
                while ((len = input.read(data)) != -1) {
                    fileOutputStream.write(data, 0, len);
                }
                System.out.println("下载文件成功");
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(input!=null){
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
 
 
}
