package com.tz.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Set;


/**
 * redis缓存
 *
 * 采用Jedis或Jedis Sentinel
 *
 * @author yingjun10627
 *
 */
@Component
public class RedisCache {
	
	
	public final static String CAHCENAME="cache";//缓存名
	public final static String CAHCEWEB="web";//web端
	public final static String CAHCEADMIN="admin";//后台
	public final static int CAHCETIME=86400;//默认缓存时间1天
	public final static int ACCESSTOKENTIME=7000;//默认缓存时间2小时 公众号的token
	public final static int ORDERTIME=300;//订单确认页面默认缓存时间5分钟
	public final static int PAYTIME=86400;//订单支付有效期默认缓存时间1天
	public final static int CODECAHCETIME=1800;//缓存时间30min 验证码有效期
	public final static int USERCAHCETIME=604800;//缓存时间7天  用户登录token
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public <T> boolean putCache(String key, T obj) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(bkey, bvalue);
			}
		});
		return result;
	}
	
	public <T> void putCacheWithExpireTime(String key, T obj, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
	}

	public <T> boolean putListCache(String key, List<T> objList) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(bkey, bvalue);
			}
		});
		return result;
	}

	public <T> boolean putListCacheWithExpireTime(String key, List<T> objList, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
		return result;
	}

	public <T> T getCache(final String key, Class<T> targetClass) {
		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return ProtoStuffSerializerUtil.deserialize(result, targetClass);
	}

	public <T> List<T> getListCache(final String key, Class<T> targetClass) {
		
		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return ProtoStuffSerializerUtil.deserializeList(result, targetClass);
	}
      
    /** 
     * 取得缓存（字符串类型） 
     * @param key 
     * @return 
     */  
    public String getStr(final String key){  
    	byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return new String(result);
    } 
    /** 
     * 取得缓存（int型） 
     * @param key 
     * @return 
     */  
    public Integer getInt(String key){  
        String value = redisTemplate.boundValueOps(key).get();  
        if(StringUtils.isNotBlank(value)){  
            return Integer.valueOf(value);  
        }  
        return null;  
    }  
    /** 
     * 取得缓存（字符串类型） 
     * @param key 
     * @return 
     */  
    public  String getStr(String key, boolean retain){  
        String value = redisTemplate.boundValueOps(key).get();  
        if(!retain){  
            redisTemplate.delete(key);  
        }  
        return value;  
    }   
	/**
	 * 精确删除key
	 * 
	 * @param key
	 */
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 模糊删除key
	 * 
	 * @param pattern
	 */
	public void deleteCacheWithPattern(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		redisTemplate.delete(keys);
	}

	/**
	 * 清空所有缓存
	 */
	public void clearCache() {
		deleteCacheWithPattern(RedisCache.CAHCENAME+"|*");
	}
	
}