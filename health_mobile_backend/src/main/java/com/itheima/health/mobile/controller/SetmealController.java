package com.itheima.health.mobile.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chayedan.health.common.MessageConst;
import com.chayedan.health.entity.Result;
import com.chayedan.health.pojo.Setmeal;
import com.chayedan.health.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chayedan666
 * @version 1.0
 * @className: SetmealController
 * @description: 移动端套餐控制器
 * @date: 2020/4/20
 */
@RestController
@Slf4j
@RequestMapping("/mobile/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
        try{
            List<Setmeal> list = setmealService.findAll();
            return new Result(true, MessageConst.QUERY_SETMEAL_SUCCESS,list);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConst.QUERY_SETMEAL_FAIL);
        }
    }

    //根据id查询套餐信息
    @RequestMapping("/findById")
    public Result findById(int id){
        try{
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true,MessageConst.QUERY_SETMEAL_SUCCESS,setmeal);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_SETMEAL_FAIL);
        }
    }
}
