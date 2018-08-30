package com.bilin.es.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

	private static final Logger logger = Logger.getLogger(ArticleService.class);
	
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    
    public boolean insertOrUpdateNewsInfo(ArticleInf articleInf) {
        try {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(articleInf.getArticleInfId().toString()).withObject(articleInf).build();
            elasticsearchTemplate.index(indexQuery);
            return true;
        } catch (Exception e) {
            logger.error("insert or update news info error.", e);
            return false;
        }
    }

    
}
