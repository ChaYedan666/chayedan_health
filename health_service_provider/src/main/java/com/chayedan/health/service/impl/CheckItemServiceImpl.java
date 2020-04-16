package com.chayedan.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chayedan.health.dao.CheckItemDao;
import com.chayedan.health.entity.PageResult;
import com.chayedan.health.entity.QueryPageBean;
import com.chayedan.health.pojo.CheckItem;
import com.chayedan.health.service.CheckItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chayedan666
 * @version 1.0
 * @className: CheckItemServiceImpl
 * @description: 检查业务实现类
 * @date: 2020/4/15
 */
@Service
@Slf4j
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    @Transactional
    public void add(CheckItem checkItem) {
        log.debug("CheckItemService{}",checkItem);
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
//        log.debug("QueryPageBean{}",queryPageBean);
        //使用分页插件,会动态的给mybatis中的语句加上limit，并且包装返回
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        // 获取分页数据
        Page<CheckItem> pageData = checkItemDao.selectByCondition(queryPageBean.getQueryString());
        // 封装数据，返回分页对象
        return new PageResult(pageData.getTotal(),pageData.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        // 检查是否有关联项
        if (checkItemDao.countCheckItemsById(id)>0){
            throw new RuntimeException("当前项目有数据，不能删除");
        }
        checkItemDao.deleteCheckItemById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
      return checkItemDao.findById(id);
    }

    @Override
    @Transactional
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
        System.out.println(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
