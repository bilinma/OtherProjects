package com.ucloudlink.mongodb.util;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoDbUtil {
	
    static final String authenticationDatabase = "newdb";
    static final String ServerAddress = "127.0.0.1"; 
    static final int PORT = 27017;
    private static String userName = "dba";
    private static String password = "dba";
    
    private static Integer maxConnect=50;
    private static Integer maxWaitThread=50;
    private static Integer maxTimeOut=60;
    private static Integer maxWaitTime=60;
    
    

    public static MongoDatabase getMongoDataBase(String database){
        MongoClient mongoClient = null;
        MongoDatabase mongoDataBase = null;
        try {
            // 连接到 mongodb 服务
            MongoCredential credential = MongoCredential.createCredential(userName, authenticationDatabase, password.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
            credentials.add(credential);
            
            MongoClientOptions.Builder build = new MongoClientOptions.Builder();  
            build.connectionsPerHost(maxConnect);  
            build.threadsAllowedToBlockForConnectionMultiplier(maxWaitThread);  
            build.connectTimeout(maxTimeOut * 1000);  
            build.maxWaitTime(maxWaitTime * 1000);  
            MongoClientOptions options = build.build();
            
            // 1 默认认证机制不带参数
            //mongoClient = new MongoClient(new ServerAddress(ServerAddress, PORT), Arrays.asList(credential));
            
            // 2 默认认证机制带参数
            //mongoClient = new MongoClient(new ServerAddress(ServerAddress, PORT),credentials, options); 
            
            // 3 使用连接字符串而不明确指定认证机制
            MongoClientURI uri = new MongoClientURI("mongodb://dba:dba@127.0.0.1/?authSource=newdb");
            mongoClient = new MongoClient(uri);
            
            System.out.println("Connect to mongodb successfully："+mongoClient);
            
            mongoDataBase = mongoClient.getDatabase(database);  
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return mongoDataBase;
    }


    public static void closeMongoClient(MongoDatabase mongoDataBase,MongoClient mongoClient ) {  
        if (mongoDataBase != null) {  
            mongoDataBase = null;  
        }  
        if (mongoClient != null) {  
            mongoClient.close();  
        }  
        System.out.println("CloseMongoClient successfully");  

    }  
}
