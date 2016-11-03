package com.nova.app.sys.cache;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CacheTemplate { //extends StringRedisTemplate
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	   
	public void delete(String key){
		stringRedisTemplate.delete(key);
	}
	
    public void set(final byte[] key, final byte[] value, final long liveTime) {
    	stringRedisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }
    
    
    public void set(String key, String value) {
    	stringRedisTemplate.opsForValue().set(key, value);
    }
    
    
	public Object get(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}
	
}
