package com.bilin.es.spring;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.fasterxml.jackson.annotation.JsonFormat;
 
@Document(indexName = "article_inf_index", type = "articleInf")
@Setting(settingPath = "elasticsearch-analyser.json")
public class ArticleInf implements Serializable{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 3820377293551002121L;

    @Id
    private Integer articleInfId;
 
    @Field(type = FieldType.Auto, analyzer="ngram_analyzer")//使用ngram进行单字分词
    private String articleTitle;
 
    @Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date releaseTime;
 
    
    public Integer getArticleInfId() {
		return articleInfId;
	}

	public void setArticleInfId(Integer articleInfId) {
		this.articleInfId = articleInfId;
	}

	public Date getReleaseTime() {
        return releaseTime;
    }
 
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
 
    public String getArticleTitle() {
        return articleTitle;
    }
 
 
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
 
}
