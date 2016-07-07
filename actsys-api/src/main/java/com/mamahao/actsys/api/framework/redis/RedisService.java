package com.mamahao.actsys.api.framework.redis;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/7
 * Time           :   10:23
 * Description    :
 */
public interface RedisService {
    Boolean expire(Object key,long timeout,TimeUnit timeUnit);
    Boolean expireAt(Object key, Date date);
    Long getExpire(Object key);
    Long getExpire(Object key,TimeUnit timeUnit);
    void delete(Object key);
    void delete(Object... keys);
    void delete(Collection keys);
    Boolean hasKey(Object key);
    void rename(Object oldKey,Object newKey);
    Boolean renameIfAbsent(Object oldKey,Object newKey);

    /**  value **/
    <T> T get(Object key);
    <T> T get(Object key,long start,long end);
    <T> T getAndSet(Object key,T value);
    <T> T getBit(Object key,long offset);
    <E> List<E> multiGet(Collection keys);
    Integer append(Object key,String value);
    void set(Object key,Object value);
    void set(Object key,Object value,long offset);
    void set(Object key, Object value, long timeout, TimeUnit timeUnit);
    Boolean setBit(Object key, long offset, boolean value);
    Boolean setIfAbsent(Object key,Object value);
    void multiSet(Map map);
    Boolean multiSetIfAbsent(Map map);
    Long increment(Object key);
    Double incrementBy(Object key,double increment);
    Long incrementBy(Object key,long increment);
    Long decrement(Object key);
    Double decrementBy(Object key,Double decrement);
    Long decrementBy(Object key,long decrement);

    /** Hash **/
    void hDel(Object key,Object... hashKeys);
    <K,V> Map<K,V> hGetAll(K key);
    <T> T hGet(Object key,Object hashKey);
    <E> List<E> hGets(Object key,Collection hashKeys);
    Long hIncrement(Object key,Object hashKey);
    Double hIncrementBy(Object key,Object hashKey,double increment);
    Long hIncrementBy(Object key,Object hashKey,long increment);
    Long hDecrement(Object key,Object hashKey);
    Double hDecrementBy(Object key,Object hashKey,Double decrement);
    Long hDecrementBy(Object key,Object hashKey,long decrement);
    Boolean hHasHashKey(Object key,Object hashKey);
    <E> Set<E> hHashKeys(Object key);
    <E> List<E> hValues(Object key);
    void hPut(Object key,Object hashKey,Object value);
    Boolean hPutIfAbsent(Object key,Object hashKey,Object value);
    void hPutAll(Object key,Map map);

    /** List **/
    <T> T lIndex(Object key,Long index);
    void lSet(Object key,long index,Object value);
    void lRemove(Object key,long index,Object value);
    <E> List<E> lRange(Object key,long start,long end);
    void lTrim(Object key,long start,long end);
    <T> T leftPop(Object key);
    <T> T leftPop(Object key,Long timeout,TimeUnit timeUnit);
    Long lLeftPush(Object key,Object value);
    Long lLeftPush(Object key,Object pivot,Object value);
    Long lLeftPushAll(Object key,Object... values);
    Long lLeftPushAll(Object key,Collection values);
    Long lLeftPushIfPresent(Object key,Object value);
    <T> T lRightPop(Object key);
    <T> T lRightPop(Object key,Long timeout,TimeUnit timeUnit);
    Long lRightPush(Object key,Object value);
    Long lRightPush(Object key,Object pivot,Object value);
    Long lRightPushAll(Object key,Object... values);
    Long lRightPushAll(Object key,Collection values);
    Long lRightPushIfPresent(Object key,Object value);
    <T> T lRightPopAndLeftPush(Object sourceKey,Object destinationKey);
    <T> T lRightPopAndLeftPush(Object sourceKey,Object destinationKey,Long timeout,TimeUnit timeUnit);

    /** Set **/
    Long sAdd(Object key,Object... values);
    <E> Set<E>  sDifference(Object key,Object otherKey);
    <E> Set<E>  sDifference(Object key,Collection otherKeys);
    Long  sDifferenceAndStore(Object key,Object otherKey,Object destKey);
    Long  sDifferenceAndStore(Object key,Collection otherKeys,Object destKey);
    <E> Set<E>  sIntersect(Object key,Object otherKey);
    <E> Set<E>  sIntersect(Object key,Collection otherKeys);
    Long  sIntersectAndStore(Object key,Object otherKey,Object destKey);
    Long  sIntersectAndStore(Object key,Collection otherKeys,Object destKey);
    <E> Set<E>  sUnion(Object key,Object otherKey);
    <E> Set<E>  sUnion(Object key,Collection otherKeys);
    Long  sUnionAndStore(Object key,Object otherKey,Object destKey);
    Long  sUnionAndStore(Object key,Collection otherKeys,Object destKey);
    Boolean sIsMember(Object key,Object value);
    <E> Set<E> sMembers(Object key);
    Boolean sMove(Object key,Object value,Object destKey);
    <T> T sPop(Object key);
    <T> T sRandomMember(Object key);
    <E> List<E> sRandomMembers(Object key,long count);
    <E> Set<E>  sDistinctRandomMembers(Object key,long count);
    Long sRemove(Object key,Object... values);

    /** SortedSet **/
    Boolean zAdd(Object key,Object value,double score);
    <T> Long zAdd(Object key,Map<T,Double> values);
    Long zCount(Object key,double min,double max);
    Double zIncrementScore(Object key,Object value,double increment);
    Double zDecrementScore(Object key,Object value,double decrement);
    Long  zIntersectAndStore(Object key,Object otherKey,Object destKey);
    Long  zIntersectAndStore(Object key,Collection otherKeys,Object destKey);
    <E> Set<E> zRange(Object key,long start,long end);
    <E> Set<E> zRangeByScore(Object key,double min,double max);
    <E> Set<E> zRangeByScore(Object key,double min,double max,long offset,long count);
    <E> Set<E> zReverseRange(Object key,long start,long end);
    <E> Set<E> zReverseRangeByScore(Object key,double min,double max);
    <E> Set<E> zReverseRangeByScore(Object key,double min,double max,long offset,long count);
    Long  zUnionAndStore(Object key,Object otherKey,Object destKey);
    Long  zUnionAndStore(Object key,Collection otherKeys,Object destKey);
    Long zRank(Object key,Object value);
    Long zReverseRank(Object key,Object value);
    Long zRemove(Object key,Object value);
    Long zRemove(Object key,Object... values);
    Long zRemove(Object key,Collection values);
    Long zRemoveRange(Object key,long start,long end);
    Long zRemoveRangeByScore(Object key,double min,double max);
    Double zScore(Object key,Object value);
    Long zCard(Object key);
}
