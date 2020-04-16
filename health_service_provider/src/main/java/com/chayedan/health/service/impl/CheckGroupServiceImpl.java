package com.chayedan.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chayedan.health.dao.CheckGroupDao;
import com.chayedan.health.entity.PageResult;
import com.chayedan.health.pojo.CheckGroup;
import com.chayedan.health.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: CheckGroupServiceImpl
 * @description: 检查组业务实现类
 * @date: 2020/4/15
 */

@Service
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    @Transactional
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) {
        // 先添加检查组，获取ID
        checkGroupDao.add(checkGroup);
        System.out.println(checkItemIds);
        for (Integer checkItemId : checkItemIds) {
            Map oneMap = new HashMap();
            oneMap.put("checkgroup_id", checkGroup.getId());
            oneMap.put("checkitem_id", checkItemId);
            checkGroupDao.addCheckGroupAndCheckItem(oneMap);
        }
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.selectByCondition(queryString);
        PageResult pageResult = new PageResult(page.getTotal(), page.getResult());
        return pageResult;
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Transactional
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkItemIds) {
        // 保存检查组信息
        checkGroupDao.edit(checkGroup);
        // 删除检查组之前关联关系
        checkGroupDao.deleteCheckItemsListByCheckGroupId(checkGroup.getId());
        // 保存新的关系
        for (Integer checkItemId :checkItemIds) {
            Map maps = new HashMap();
            maps.put("checkgroup_id",checkGroup.getId());
            maps.put("checkitem_id",checkItemId);
            checkGroupDao.addCheckGroupAndCheckItem(maps);
        }
    }
}
