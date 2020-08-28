package com.theonecai.algorithms;

import org.junit.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: theonecai
 * @Date: Create in 2020/5/14 16:03
 * @Description:
 */
public class LRUCache<K, V> {

    private static final long DEFAULT_TTL = 3000;

    /**
     * 存放具体k,v
     */
    private Map<K, V> map;

    /**
     * key引用超时信息
     */
    private Map<K, TTLValue> ttlMap;

    private ExecutorService executorService;

    /**
     * 保护map的readWriteLock
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public LRUCache() {
        map = new HashMap<>();
        ttlMap = new HashMap<>();
        executorService = Executors.newFixedThreadPool(2);
    }

    public void put(K key, V value) {
        put(key, value, DEFAULT_TTL);
    }

    public void put(K key, V value, long ttl) {
        Set<K> keyToRelease = null;
        try {
            writeLock.lock();

            keyToRelease = markAndSwap();
            if (!keyToRelease.isEmpty()) {
                keyToRelease.remove(key);
            }
            map.put(key, value);
            TTLValue ttlValue = new TTLValue(System.currentTimeMillis(), ttl);
            ttlValue.refCount.incrementAndGet();
            ttlMap.put(key, ttlValue);
        } finally {
           writeLock.unlock();
        }
        if (keyToRelease != null && !keyToRelease.isEmpty()) {
            Set<K> finalKeyToRelease = keyToRelease;
            executorService.submit(() -> finalKeyToRelease.forEach(k -> {
                try {
                    writeLock.lock();

                    map.remove(k);
                    ttlMap.remove(k);
                    System.out.println(k + " release success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            }));
        }
    }

    public V get(K key) {
        try {
            readLock.lock();

            V obj = map.get(key);
            if (obj == null) {
                return null;
            }
            TTLValue ttlValue = ttlMap.get(key);
            if (ttlValue != null) {
                ttlValue.refCount.incrementAndGet();
                ttlValue.lastAccessedTimestamp.set(System.currentTimeMillis());
            }
            System.out.println("get " + key);
            return obj;
        } finally {
            readLock.unlock();
        }
    }

    private Set<K> markAndSwap() {
        Set<K> keys = ttlMap.keySet();
        if (keys.isEmpty()) {
            return Collections.emptySet();
        }

        Set<K> keyToRelease = new HashSet<>();
        long currentTime = System.currentTimeMillis();
        keys.forEach(k -> {
            TTLValue ttlValue = ttlMap.get(k);
            if (ttlValue != null) {
                if (ttlValue.refCount.get() <= 0 && (currentTime - ttlValue.lastAccessedTimestamp.get()) > ttlValue.ttl) {
                    keyToRelease.add(k);
                }
            }
        });
        return keyToRelease;
    }

    public void release(K key) {
        try {
            readLock.lock();

            TTLValue ttlvalue = ttlMap.get(key);
            if (ttlvalue != null) {
                ttlvalue.refCount.decrementAndGet();
            }
        } finally {
            readLock.unlock();
        }
    }

    public void releaseAll(K key) {
        try {
            readLock.lock();

            TTLValue ttlvalue = ttlMap.get(key);
            if (ttlvalue != null) {
                ttlvalue.refCount.getAndSet(0);
            }
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        return map.size();
    }

    private static class TTLValue {
        /**
         * 最近一次访问时间
         */
        AtomicLong lastAccessedTimestamp;
        /**
         * 引用计数
         */
        AtomicLong refCount = new AtomicLong(0);
        long ttl;

        public TTLValue(long ts, long ttl) {
            this.lastAccessedTimestamp = new AtomicLong(ts);
            this.ttl = ttl;
        }
    }

    public void destroy() throws InterruptedException {
        if (executorService != null) {
            if (((ThreadPoolExecutor) executorService).getActiveCount() > 0) {
                Thread.sleep(1000);
            }
            executorService.shutdown();
            if (!executorService.isShutdown()) {
                executorService.awaitTermination(3, TimeUnit.SECONDS);
                executorService.shutdownNow();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        LRUCache<String, String> cache = new LRUCache<>();
        String key = "1";
        cache.put(key, "testdata");
        String obj = cache.get(key);
        Assert.assertNotNull(obj);

        cache.releaseAll(key);

        Thread.sleep(4000L);
        cache.put("2", "te2");
//        Thread.sleep(1000);
        obj = cache.get(key);
        Assert.assertNull(obj);
        Thread.sleep(1000);

//        Thread.sleep(10000);
        cache.destroy();
    }

}
