package com.ucloudlink.mongodb.test;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.ucloudlink.mongodb.util.MongoDbUtil;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class MongoDbTest {

	public static void main(String[]args){
		MongoDbTest.selectCollectionDoc("newdb", "users");
		
		/*Document document = new Document("_id", 1999);
		document.append("title", "MongoDB Insert Demo");
        document.append("description","database");
        document.append("likes", 30);
        document.append("by", "yiibai point");
        document.append("url", "http://www.yiibai.com/mongodb/");
		MongoDbTest.insertCollectionDoc("newdb", "abc",document);*/
		
		Document document = new Document("_id", 1999);
		document.append("title", "更新了标题3");
		MongoDbTest.updateCollectionDoc("newdb", "abc",document);
	}
	
	
	/**
	 * 查询集合内容
	 * @param dbName
	 * @param collectionName
	 * @return
	 */
	public static FindIterable<Document> selectCollectionDoc(String dbName,String collectionName){
		MongoDatabase mongoDatabase = MongoDbUtil.getMongoDataBase(dbName);
		MongoIterable<String> listCollectionNames = mongoDatabase.listCollectionNames();
		
		System.out.println("当前数据库中的所有集合是：");
        for (String name : listCollectionNames) {
            System.out.println(name);
        }
		
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		System.out.println("集合"+collectionName+"文本数:"+collection.count());
		FindIterable<Document> iterable = collection.find();
		for(Document doc: iterable){
			System.out.println(doc);
		}
		return iterable;
	}
	
	/**
	 * 插入文本
	 * @param dbName
	 * @param collectionName
	 * @param document
	 * @return
	 */
	public static boolean insertCollectionDoc(String dbName,String collectionName,Document document){
		MongoDatabase mongoDatabase = MongoDbUtil.getMongoDataBase(dbName);
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.insertOne(document);
        System.out.println("Document inserted successfully");
        return true;
	}
	
	/**
	 * 更新
	 * @param dbName
	 * @param collectionName
	 * @param document
	 * @return
	 */
	public static boolean updateCollectionDoc(String dbName,String collectionName,Document document){
		MongoDatabase mongoDatabase = MongoDbUtil.getMongoDataBase(dbName);
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		collection.updateOne(eq("_id", 1999),new Document("$set", document));
		return true;
	}
	
	/**
	 * 删除
	 * @param dbName
	 * @param collectionName
	 * @param document
	 * @return
	 */
	public static boolean deleteCollectionDoc(String dbName,String collectionName,Document document){
		MongoDatabase mongoDatabase = MongoDbUtil.getMongoDataBase(dbName);
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		collection.deleteOne(eq("_id", 9999));
		return true;
	}
	
}
