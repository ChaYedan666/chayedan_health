package com.chayedan.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chayedan.health.common.MessageConst;
import com.chayedan.health.entity.PageResult;
import com.chayedan.health.entity.QueryPageBean;
import com.chayedan.health.entity.Result;
import com.chayedan.health.pojo.CheckItem;
import com.chayedan.health.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chayedan666
 * @version 1.0
 * @className: CheckItemController
 * @description: 检查项控制器
 * @date: 2020/4/15
 */
@RestController
@Slf4j
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
            log.debug("CheckItemController.add调用了");
            return new Result(true, MessageConst.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConst.ADD_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            log.debug("queryPageBean{}",queryPageBean);
            return checkItemService.pageQuery(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageResult(0L,new ArrayList());
    }
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkItemService.deleteById(id);
            return new Result(true,MessageConst.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,e.getMessage());
        }
    }
    @RequestMapping("findById")
    public Result findById(Integer id){
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true,MessageConst.QUERY_CHECKITEM_SUCCESS,checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_CHECKITEM_FAIL);
        }
    }
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            // 调用Service更新数据
            System.out.println(checkItem);
            checkItemService.edit(checkItem);
            return new Result(true,MessageConst.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.EDIT_CHECKITEM_FAIL);
        }
    }
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<CheckItem> checkItems = checkItemService.findAll();
            return new Result(true,MessageConst.QUERY_CHECKITEM_SUCCESS,checkItems);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_CHECKITEM_FAIL);
        }
    }

}
