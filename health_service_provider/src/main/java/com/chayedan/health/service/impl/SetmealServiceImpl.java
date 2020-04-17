package com.chayedan.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chayedan.health.common.RedisConst;
import com.chayedan.health.dao.SetmealDao;
import com.chayedan.health.entity.PageResult;
import com.chayedan.health.pojo.Setmeal;
import com.chayedan.health.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: SetmealServiceImpl
 * @description: 套餐业务实现类
 * @date: 2020/4/16
 */
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;

    @Transactional
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        // 保存套餐数据
        setmealDao.add(setmeal);
        // 保存套餐与检查组的对应关系
        for (Integer checkgroupId : checkgroupIds) {
            Map<String,Integer> map = new HashMap<>();
            map.put("setmeal_id",setmeal.getId());
            map.put("checkgroup_id",checkgroupId);
            setmealDao.addSetmealAndCheckGroup(map);
        }
        jedisPool.getResource().sadd(RedisConst.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());

    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> pageSetmeal = setmealDao.selectByCondition(queryString);
        return new PageResult(pageSetmeal.getTotal(),pageSetmeal.getResult());
    }
}
