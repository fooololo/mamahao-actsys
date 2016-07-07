package com.mamahao.actsys.api.framework.redis;

import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/7
 * Time           :   10:25
 * Description    :
 */
public abstract class AbstractRedisService implements RedisService{
    protected abstract RedisTemplate getRedisTemplate();

    @Override
    public Boolean expire(Object key, long timeout, TimeUnit timeUnit) {
        return getRedisTemplate().expire(key,timeout,timeUnit);
    }

    @Override
    public Boolean expireAt(Object key, Date date) {
        return getRedisTemplate().expireAt(key,date);
    }

    @Override
    public Long getExpire(Object key) {
        return getRedisTemplate().getExpire(key);
    }

    @Override
    public Long getExpire(Object key, TimeUnit timeUnit) {
        return getRedisTemplate().getExpire(key,timeUnit);
    }

    @Override
    public void delete(Object key) {
        getRedisTemplate().delete(key);
    }

    @Override
    public void delete(Object... keys) {
        getRedisTemplate().delete(keys);
    }

    @Override
    public void delete(Collection keys) {
        getRedisTemplate().delete(keys);
    }

    @Override
    public Boolean hasKey(Object key) {
        return getRedisTemplate().hasKey(key);
    }

    @Override
    public void rename(Object oldKey, Object newKey) {
        getRedisTemplate().rename(oldKey,newKey);
    }

    @Override
    public Boolean renameIfAbsent(Object oldKey, Object newKey) {
        return getRedisTemplate().renameIfAbsent(oldKey,newKey);
    }

    @Override
    public <T> T get(Object key) {
        return (T)getRedisTemplate().opsForValue().get(key);
    }

    @Override
    public <T> T get(Object key, long start, long end) {
        return (T)getRedisTemplate().opsForValue().get(key,start,end);
    }

    @Override
    public <T> T getAndSet(Object key, T value) {
        return (T)getRedisTemplate().opsForValue().getAndSet(key,value);
    }

    @Override
    public <T> T getBit(Object key, long offset) {
        return (T)getRedisTemplate().opsForValue().getBit(key,offset);
    }

    @Override
    public <E> List<E> multiGet(Collection keys) {
        return getRedisTemplate().opsForValue().multiGet(keys);
    }

    @Override
    public Integer append(Object key, String value) {
        return getRedisTemplate().opsForValue().append(key,value);
    }

    @Override
    public void set(Object key, Object value) {
        getRedisTemplate().opsForValue().set(key,value);
    }

    @Override
    public void set(Object key, Object value, long offset) {
        getRedisTemplate().opsForValue().set(key,value,offset);
    }

    @Override
    public void set(Object key, Object value, long timeout, TimeUnit timeUnit) {
        getRedisTemplate().opsForValue().set(key,value,timeout,timeUnit);
    }

    @Override
    public Boolean setBit(Object key, long offset, boolean value) {
        return getRedisTemplate().opsForValue().setBit(key,offset,value);
    }

    @Override
    public Boolean setIfAbsent(Object key, Object value) {
        return getRedisTemplate().opsForValue().setIfAbsent(key,value);
    }

    @Override
    public void multiSet(Map map) {
        getRedisTemplate().opsForValue().multiSet(map);
    }

    @Override
    public Boolean multiSetIfAbsent(Map map) {
        return getRedisTemplate().opsForValue().multiSetIfAbsent(map);
    }

    @Override
    public Long increment(Object key) {
        return getRedisTemplate().opsForValue().increment(key,1L);
    }

    @Override
    public Double incrementBy(Object key, double increment) {
        return getRedisTemplate().opsForValue().increment(key,increment);
    }

    @Override
    public Long incrementBy(Object key, long increment) {
        return getRedisTemplate().opsForValue().increment(key,increment);
    }

    @Override
    public Long decrement(Object key) {
        return getRedisTemplate().opsForValue().increment(key,-1L);
    }

    @Override
    public Double decrementBy(Object key, Double decrement) {
        return getRedisTemplate().opsForValue().increment(key,-decrement);
    }

    @Override
    public Long decrementBy(Object key, long decrement) {
        return getRedisTemplate().opsForValue().increment(key,-decrement);
    }

    @Override
    public void hDel(Object key, Object... hashKeys) {
        getRedisTemplate().opsForHash().delete(key,hashKeys);
    }

    @Override
    public <K, V> Map<K, V> hGetAll(K key) {
        return getRedisTemplate().opsForHash().entries(key);
    }

    @Override
    public <T> T hGet(Object key, Object hashKey) {
        return (T)getRedisTemplate().opsForHash().get(key,hashKey);
    }

    @Override
    public <E> List<E> hGets(Object key, Collection hashKeys) {
        return getRedisTemplate().opsForHash().multiGet(key,hashKeys);
    }

    @Override
    public Long hIncrement(Object key, Object hashKey) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,1L);
    }

    @Override
    public Double hIncrementBy(Object key, Object hashKey, double increment) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,increment);
    }

    @Override
    public Long hIncrementBy(Object key, Object hashKey, long increment) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,increment);
    }

    @Override
    public Long hDecrement(Object key, Object hashKey) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,-1L);
    }

    @Override
    public Double hDecrementBy(Object key, Object hashKey, Double decrement) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,-decrement);
    }

    @Override
    public Long hDecrementBy(Object key, Object hashKey, long decrement) {
        return getRedisTemplate().opsForHash().increment(key,hashKey,-decrement);
    }

    @Override
    public Boolean hHasHashKey(Object key, Object hashKey) {
        return getRedisTemplate().opsForHash().hasKey(key,hashKey);
    }

    @Override
    public <E> Set<E> hHashKeys(Object key) {
        return getRedisTemplate().opsForHash().keys(key);
    }

    @Override
    public <E> List<E> hValues(Object key) {
        return getRedisTemplate().opsForHash().values(key);
    }

    @Override
    public void hPut(Object key, Object hashKey, Object value) {
        getRedisTemplate().opsForHash().put(key,hashKey,value);
    }

    @Override
    public Boolean hPutIfAbsent(Object key, Object hashKey, Object value) {
        return getRedisTemplate().opsForHash().putIfAbsent(key,hashKey,value);
    }

    @Override
    public void hPutAll(Object key, Map map) {
        getRedisTemplate().opsForHash().putAll(key,map);
    }

    @Override
    public <T> T lIndex(Object key, Long index) {
        return (T)getRedisTemplate().opsForList().index(key,index);
    }

    @Override
    public void lSet(Object key, long index, Object value) {
        getRedisTemplate().opsForList().set(key,index,value);
    }

    @Override
    public void lRemove(Object key, long index, Object value) {
        getRedisTemplate().opsForList().remove(key,index,value);
    }

    @Override
    public <E> List<E> lRange(Object key, long start, long end) {
        return getRedisTemplate().opsForList().range(key,start,end);
    }

    @Override
    public void lTrim(Object key, long start, long end) {
        getRedisTemplate().opsForList().trim(key,start,end);
    }

    @Override
    public <T> T leftPop(Object key) {
        return (T)getRedisTemplate().opsForList().leftPop(key);
    }

    @Override
    public <T> T leftPop(Object key, Long timeout, TimeUnit timeUnit) {
        return (T)getRedisTemplate().opsForList().leftPop(key,timeout,timeUnit);
    }

    @Override
    public Long lLeftPush(Object key, Object value) {
        return getRedisTemplate().opsForList().leftPush(key,value);
    }

    @Override
    public Long lLeftPush(Object key, Object pivot, Object value) {
        return getRedisTemplate().opsForList().leftPush(key,pivot,value);
    }

    @Override
    public Long lLeftPushAll(Object key, Object... values) {
        return getRedisTemplate().opsForList().leftPushAll(key,values);
    }

    @Override
    public Long lLeftPushAll(Object key, Collection values) {
        return getRedisTemplate().opsForList().leftPushAll(key,values);
    }

    @Override
    public Long lLeftPushIfPresent(Object key, Object value) {
        return getRedisTemplate().opsForList().leftPushIfPresent(key,value);
    }

    @Override
    public <T> T lRightPop(Object key) {
        return (T)getRedisTemplate().opsForList().rightPop(key);
    }

    @Override
    public <T> T lRightPop(Object key, Long timeout, TimeUnit timeUnit) {
        return (T)getRedisTemplate().opsForList().rightPop(key,timeout,timeUnit);
    }

    @Override
    public Long lRightPush(Object key, Object value) {
        return getRedisTemplate().opsForList().rightPush(key,value);
    }

    @Override
    public Long lRightPush(Object key, Object pivot, Object value) {
        return getRedisTemplate().opsForList().rightPush(key,pivot,value);
    }

    @Override
    public Long lRightPushAll(Object key, Object... values) {
        return getRedisTemplate().opsForList().rightPushAll(key,values);
    }

    @Override
    public Long lRightPushAll(Object key, Collection values) {
        return getRedisTemplate().opsForList().rightPushAll(key,values);
    }

    @Override
    public Long lRightPushIfPresent(Object key, Object value) {
        return getRedisTemplate().opsForList().rightPushIfPresent(key,value);
    }

    @Override
    public <T> T lRightPopAndLeftPush(Object sourceKey, Object destinationKey) {
        return (T)getRedisTemplate().opsForList().rightPopAndLeftPush(sourceKey,destinationKey);
    }

    @Override
    public <T> T lRightPopAndLeftPush(Object sourceKey, Object destinationKey, Long timeout, TimeUnit timeUnit) {
        return (T)getRedisTemplate().opsForList().rightPopAndLeftPush(sourceKey,destinationKey,timeout,timeUnit);
    }

    @Override
    public Long sAdd(Object key, Object... values) {
        return getRedisTemplate().opsForSet().add(key,values);
    }

    @Override
    public <E> Set<E> sDifference(Object key, Object otherKey) {
        return getRedisTemplate().opsForSet().difference(key,otherKey);
    }

    @Override
    public <E> Set<E> sDifference(Object key, Collection otherKeys) {
        return getRedisTemplate().opsForSet().difference(key,otherKeys);
    }

    @Override
    public Long sDifferenceAndStore(Object key, Object otherKey, Object destKey) {
        return getRedisTemplate().opsForSet().differenceAndStore(key,otherKey,destKey);
    }

    @Override
    public Long sDifferenceAndStore(Object key, Collection otherKeys, Object destKey) {
        return getRedisTemplate().opsForSet().differenceAndStore(key,otherKeys,destKey);
    }

    @Override
    public <E> Set<E> sIntersect(Object key, Object otherKey) {
        return getRedisTemplate().opsForSet().intersect(key,otherKey);
    }

    @Override
    public <E> Set<E> sIntersect(Object key, Collection otherKeys) {
        return getRedisTemplate().opsForSet().intersect(key,otherKeys);
    }

    @Override
    public Long sIntersectAndStore(Object key, Object otherKey, Object destKey) {
        return getRedisTemplate().opsForSet().intersectAndStore(key,otherKey,destKey);
    }

    @Override
    public Long sIntersectAndStore(Object key, Collection otherKeys, Object destKey) {
        return getRedisTemplate().opsForSet().intersectAndStore(key,otherKeys,destKey);
    }

    @Override
    public <E> Set<E> sUnion(Object key, Object otherKey) {
        return getRedisTemplate().opsForSet().union(key,otherKey);
    }

    @Override
    public <E> Set<E> sUnion(Object key, Collection otherKeys) {
        return getRedisTemplate().opsForSet().union(key,otherKeys);
    }

    @Override
    public Long sUnionAndStore(Object key, Object otherKey, Object destKey) {
        return getRedisTemplate().opsForSet().unionAndStore(key,otherKey,destKey);
    }

    @Override
    public Long sUnionAndStore(Object key, Collection otherKeys, Object destKey) {
        return getRedisTemplate().opsForSet().unionAndStore(key,otherKeys,destKey);
    }

    @Override
    public Boolean sIsMember(Object key, Object value) {
        return getRedisTemplate().opsForSet().isMember(key,value);
    }

    @Override
    public <E> Set<E> sMembers(Object key) {
        return getRedisTemplate().opsForSet().members(key);
    }

    @Override
    public Boolean sMove(Object key, Object value, Object destKey) {
        return getRedisTemplate().opsForSet().move(key,value,destKey);
    }

    @Override
    public <T> T sPop(Object key) {
        return (T)getRedisTemplate().opsForSet().pop(key);
    }

    @Override
    public <T> T sRandomMember(Object key) {
        return (T)getRedisTemplate().opsForSet().randomMember(key);
    }

    @Override
    public <E> List<E> sRandomMembers(Object key, long count) {
        return getRedisTemplate().opsForSet().randomMembers(key,count);
    }

    @Override
    public <E> Set<E> sDistinctRandomMembers(Object key, long count) {
        return getRedisTemplate().opsForSet().distinctRandomMembers(key,count);
    }

    @Override
    public Long sRemove(Object key, Object... values) {
        return getRedisTemplate().opsForSet().remove(key,values);
    }

    @Override
    public Boolean zAdd(Object key, Object value, double score) {
        return getRedisTemplate().opsForZSet().add(key,value,score);
    }

    @Override
    public <T> Long zAdd(Object key, Map<T, Double> values) {
        Iterator<T> iterator = values.keySet().iterator();
        Set<ZSetOperations.TypedTuple> tts = new HashSet<>();
        while (iterator.hasNext()){
            T v = iterator.next();
            Double score = values.get(v);
            ZSetOperations.TypedTuple tt =  new DefaultTypedTuple(v,score);
            tts.add(tt);
        }
        return getRedisTemplate().opsForZSet().add(key,tts);
    }

    @Override
    public Long zCount(Object key, double min, double max) {
        return getRedisTemplate().opsForZSet().count(key,min,max);
    }

    @Override
    public Double zIncrementScore(Object key, Object value, double increment) {
        return getRedisTemplate().opsForZSet().incrementScore(key,value,increment);
    }

    @Override
    public Double zDecrementScore(Object key, Object value, double decrement) {
        return getRedisTemplate().opsForZSet().incrementScore(key,value,-decrement);
    }

    @Override
    public Long zIntersectAndStore(Object key, Object otherKey, Object destKey) {
        return getRedisTemplate().opsForZSet().intersectAndStore(key,otherKey,destKey);
    }

    @Override
    public Long zIntersectAndStore(Object key, Collection otherKeys, Object destKey) {
        return getRedisTemplate().opsForZSet().intersectAndStore(key,otherKeys,destKey);
    }

    @Override
    public <E> Set<E> zRange(Object key, long start, long end) {
        return getRedisTemplate().opsForZSet().range(key,start,end);
    }

    @Override
    public <E> Set<E> zRangeByScore(Object key, double min, double max) {
        return getRedisTemplate().opsForZSet().rangeByScore(key,min,max);
    }

    @Override
    public <E> Set<E> zRangeByScore(Object key, double min, double max, long offset, long count) {
        return getRedisTemplate().opsForZSet().rangeByScore(key,min,max,offset,count);
    }

    @Override
    public <E> Set<E> zReverseRange(Object key, long start, long end) {
        return getRedisTemplate().opsForZSet().reverseRange(key,start,end);
    }

    @Override
    public <E> Set<E> zReverseRangeByScore(Object key, double min, double max) {
        return getRedisTemplate().opsForZSet().reverseRangeByScore(key,min,max);
    }

    @Override
    public <E> Set<E> zReverseRangeByScore(Object key, double min, double max, long offset, long count) {
        return getRedisTemplate().opsForZSet().reverseRangeByScore(key,min,max,offset,count);
    }

    @Override
    public Long zUnionAndStore(Object key, Object otherKey, Object destKey) {
        return getRedisTemplate().opsForZSet().unionAndStore(key,otherKey,destKey);
    }

    @Override
    public Long zUnionAndStore(Object key, Collection otherKeys, Object destKey) {
        return getRedisTemplate().opsForZSet().unionAndStore(key,otherKeys,destKey);
    }

    @Override
    public Long zRank(Object key, Object value) {
        return getRedisTemplate().opsForZSet().rank(key,value);
    }

    @Override
    public Long zReverseRank(Object key, Object value) {
        return getRedisTemplate().opsForZSet().reverseRank(key,value);
    }

    @Override
    public Long zRemove(Object key, Object value) {
        return getRedisTemplate().opsForZSet().remove(key,value);
    }

    @Override
    public Long zRemove(Object key, Object... values) {
        return getRedisTemplate().opsForZSet().remove(key,values);
    }

    @Override
    public Long zRemove(Object key, Collection values) {
        return getRedisTemplate().opsForZSet().remove(key,values);
    }

    @Override
    public Long zRemoveRange(Object key, long start, long end) {
        return getRedisTemplate().opsForZSet().removeRange(key,start,end);
    }

    @Override
    public Long zRemoveRangeByScore(Object key, double min, double max) {
        return getRedisTemplate().opsForZSet().removeRangeByScore(key,min,max);
    }

    @Override
    public Double zScore(Object key, Object value) {
        return getRedisTemplate().opsForZSet().score(key,value);
    }

    @Override
    public Long zCard(Object key) {
        return getRedisTemplate().opsForZSet().zCard(key);
    }
}
