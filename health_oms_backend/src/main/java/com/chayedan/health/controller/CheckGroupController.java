package com.chayedan.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chayedan.health.common.MessageConst;
import com.chayedan.health.entity.PageResult;
import com.chayedan.health.entity.QueryPageBean;
import com.chayedan.health.entity.Result;
import com.chayedan.health.pojo.CheckGroup;
import com.chayedan.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chayedan666
 * @version 1.0
 * @className: CheckGroupController
 * @description: 检查组控制器
 * @date: 2020/4/15
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkItemIds){
        try {
            System.out.println(checkItemIds);
            checkGroupService.add(checkGroup,checkItemIds);
            return new Result(true, MessageConst.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConst.ADD_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean pageBean) {
        PageResult pageResult = null;
        try{
            pageResult = checkGroupService.pageQuery
                    (pageBean.getCurrentPage(),pageBean.getPageSize(),pageBean.getQueryString());
        }catch(Exception e){
            e.printStackTrace();
            pageResult = new PageResult(0L,new ArrayList());
        }
        return pageResult;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConst.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 基于检查组ID，获取检查项选中列表
     * @param id
     * @return
     */
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id){
        try{
            List<Integer> list = checkGroupService.findCheckItemsByCheckGroupId(id);
            return new Result(true,MessageConst.ACTION_SUCCESS,list);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.ACTION_FAIL);
        }
    }

    /**
     * 编辑检查组数据
     * @param checkGroup 检查组基本信息
     * @param checkItemIds 选中的检查项列表
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup,Integer[] checkItemIds){
        try{
            checkGroupService.edit(checkGroup,checkItemIds);
            return new Result(true,MessageConst.EDIT_CHECKGROUP_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.EDIT_CHECKGROUP_FAIL);
        }
    }
}
