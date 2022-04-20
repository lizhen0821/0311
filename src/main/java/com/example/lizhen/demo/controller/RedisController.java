package com.example.lizhen.demo.controller;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.example.lizhen.demo.bean.Aaaa;
import com.example.lizhen.demo.service.AaaaService;
import com.example.lizhen.demo.utils.RedisUtils;
import org.redisson.api.RBuckets;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RedisController {

    public static final Logger log = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    AaaaService aaaaService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    RedissonClient redissonClient;

    @RequestMapping(value = "/helloRedis/{id}")
    public String hello(@PathVariable(value = "id") String id){
        //查询缓存中是否存在
        boolean hasKey = redisUtils.exists(id);
        String str = "";
        if(hasKey){
            //获取缓存
            Object object =  redisUtils.get(id);
            log.info("从缓存获取的数据"+ object);
            str = object.toString();
        }else{
            //从数据库中获取信息
            log.info("从数据库中获取数据");
            Aaaa aaaa = aaaaService.getAaaa(id);
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(id,aaaa.getFdId(),10L,TimeUnit.MINUTES);
            str=aaaa.getFdId();
            log.info("数据插入缓存" + aaaa.getFdId());
        }
        return str;
    }

    @RequestMapping("/redisson")
    public String testRedisson(){
        //获取分布式锁，只要锁的名字一样，就是同一把锁
        RLock lock = redissonClient.getLock("lock");

        //加锁（阻塞等待），默认过期时间是30秒
        lock.lock();
        try{
            List<Aaaa> listA=new ArrayList<>();
            Aaaa a1= new Aaaa("keyss","ss","");
            Aaaa a2= new Aaaa("keyssss1","ss13","");
            Aaaa a3= new Aaaa("keyssss2","ss223","11");
            listA.add(a1);
            listA.add(a2);
            listA.add(a2);
            System.out.print(listA);
            Map<String,String>  map =listA.stream().distinct().collect(Collectors.toMap(Aaaa::getFdId,Aaaa::getFdParentId));
            /* (key1,key2) ->key1 如果key值重复，则以key1为key */
//            Map<String,String>  map =listA.stream().collect(Collectors.toMap(Aaaa::getFdId,Aaaa::getFdParentId,(key1,value) ->key1));
//            System.out.println(map1);
            RBuckets buckets = redissonClient.getBuckets();
            Map<String, Object> set=new HashMap<>();
            set.put("test","lizhen");
            buckets.set(set);
            buckets.set(map);
            //如果业务执行过长，Redisson会自动给锁续期
//            Thread.sleep(1000);
            System.out.println("加锁成功，执行业务逻辑");
        }/* catch (InterruptedException e) {
            e.printStackTrace();
        } */finally {
            //解锁，如果业务执行完成，就不会继续续期，即使没有手动释放锁，在30秒过后，也会释放锁
            lock.unlock();
        }
        Object object =  redisUtils.get("13f85097fc2003bcc90ee32444a81bd0");
        log.info("从缓存获取的数据"+ object);
        return "Hello Redisson!";
    }
}
