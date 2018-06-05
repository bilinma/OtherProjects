package com.ucloudlink.redis.common;

import java.io.Serializable;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

public class RedisCache implements Cache {

    /**
     * Redis
     */
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 缓存名称
     */
    private String name;
    /**
     * 超时时间
     */
    private long liveTime;
    
    
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

	public long getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(long liveTime) {
		this.liveTime = liveTime;
	}

    @Override
    public void clear() {
        System.out.println("-------緩存清理------");
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    @Override
    public void evict(Object key) {
        System.out.println("-------緩存刪除------");
        final String keyf=key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyf.getBytes());
            }
            
        });

    }

    @Override
    public ValueWrapper get(Object key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }else{
        	System.out.println("------缓存获取-------"+key.toString());
        	final String keyf = key.toString();
        	Object object = null;
        	object = redisTemplate.execute(new RedisCallback<Object>() {
        		@Override
        		public Object doInRedis(RedisConnection connection) throws DataAccessException {
        			byte[] key = keyf.getBytes();
        			byte[] value = connection.get(key);
        			if (value == null) {
        				System.out.println("------缓存不存在-------");
        				return null;
        			}
        			return SerializationUtils.deserialize(value);
        		}
        	});
        	ValueWrapper obj=(object != null ? new SimpleValueWrapper(object) : null);
        	System.out.println("------获取到内容-------"+obj);
        	return  obj;
        }
    }

    @Override
    public void put(Object key, Object value) {
        System.out.println("-------加入缓存------");
        System.out.println("key----:"+key);
        System.out.println("value----:"+value);
        final String keyString = key.toString();
        final Object valuef = value;
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyb = keyString.getBytes();
                byte[] valueb = SerializationUtils.serialize((Serializable) valuef);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        });

    }
    
    @Override
    public <T> T get(Object key, Class<T> type) {
        if (StringUtils.isEmpty(key) || null == type) {
            return null;
        } else {
            final String finalKey;
            final Class<T> finalType = type;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            final Object object = redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] key = finalKey.getBytes();
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    return SerializationUtils.deserialize(value);
                }
            });
            if (finalType != null && finalType.isInstance(object) && null != object) {
                return (T) object;
            } else {
                return null;
            }
        }
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }
    
    @Override
    public ValueWrapper putIfAbsent(Object arg0, Object arg1) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}