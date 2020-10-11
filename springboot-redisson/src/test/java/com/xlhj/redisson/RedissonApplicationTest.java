package com.xlhj.redisson;

import org.junit.jupiter.api.Test;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;

/**
 * @ClassName RedissonApplicationTest
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/11 10:43
 * @Version 1.0
 */
@SpringBootTest
public class RedissonApplicationTest {

    @Autowired
    RedissonClient redisson;
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 测试可重入锁
     */
    @Test
    public void getLock() {
        //1.获取锁
        RLock lock = redisson.getLock("lock");
        //2.加锁
        lock.lock();//阻塞式等待，默认加锁都是30s时间
        //锁的自动续期，如果业务超长，运行期间自动给锁续上新的30s，不用担心业务时间长，锁自动过期被删掉
        //加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s以后自动删除
        try {
            System.out.println("加锁成功,执行业务......" + Thread.currentThread().getId());
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //3.解锁
            System.out.println("释放锁......" + Thread.currentThread().getId());
            lock.unlock();
        }
    }

    /**
     * 写锁，保证一定能读到最新数据，修改期间，写锁是一个排它锁(互斥锁、独享锁)，读锁是一个共享锁
     * 写 + 读：等待写锁释放
     * 写 + 写：阻塞方式
     * 读 + 写：有读锁，写也需要等待
     * 只要有写的存在，都必须等待
     */
    public void writeValue() {
        RReadWriteLock lock = redisson.getReadWriteLock("rw-lock");
        String s = "";
        RLock rLock = lock.writeLock();
        try {
            //改数据加写锁，读数据加读锁
            rLock.lock();
            System.out.println("写锁加锁成功..." + Thread.currentThread().getId());
            s = UUID.randomUUID().toString();
            Thread.sleep(30000);
            redisTemplate.opsForValue().set("writeValue", s);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("写锁释放" + Thread.currentThread().getId());
        }
    }

    /**
     * 读锁
     */
    public void readValue() {
        RReadWriteLock lock = redisson.getReadWriteLock("rw-lock");
        String s = "";
        RLock rLock = lock.readLock();
        try {
            rLock.lock();
            System.out.println("读锁加锁成功" + Thread.currentThread().getId());
            s = redisTemplate.opsForValue().get("writeValue");
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("读锁释放" + Thread.currentThread().getId());
        }
    }

    /**
     * 信号量获取锁
     * 信号量也可用于分布式限流
     */
    public void acquire() throws InterruptedException {
        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        semaphore.acquire();//获取一个信号，会阻塞
        boolean b = semaphore.tryAcquire();//获取一个信号，有就获取，没有就返回
        if (b) {
            //执行业务
        } else {
            //执行返回
        }
    }

    /**
     * 信号量释放锁
     */
    public void release() {
        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        semaphore.release();
    }

    /**
     * 闭锁，获取锁
     */
    public void latch() throws InterruptedException {
        RCountDownLatch latch = redisson.getCountDownLatch("latch");
        latch.trySetCount(5);
        latch.await();//等待闭锁都完成
    }

    /**
     * 闭锁释放锁
     */
    public void down() {
        RCountDownLatch latch = redisson.getCountDownLatch("latch");
        latch.countDown();//计数减一
    }

    /**
     * 分布式锁
     */
    public void distributedLock() {
        RLock lock = redisson.getLock("lock");
        lock.lock();
        try {
            //执行业务
        } finally {
            lock.unlock();//解锁
        }
    }
















}
