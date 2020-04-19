package com.chayedan.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chayedan.health.common.MessageConst;
import com.chayedan.health.entity.Result;
import com.chayedan.health.pojo.OrderSetting;
import com.chayedan.health.service.OrderSettingService;
import com.chayedan.health.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: OrderSettingController
 * @description: 预约设置控制器
 * @date: 2020/4/17
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile multipartFile){
        System.out.println("excelFile:"+multipartFile.getOriginalFilename());
        try {
            // 从上传的文件中读取内容，利用POI工具类
            List<String[] >list = POIUtils.readExcel(multipartFile);
            // 判断是否存在数据
            if (list != null && list.size() > 0){
                // 遍历数据并且封装为对象
                ArrayList<OrderSetting> orderSettingList = new ArrayList<>();
                for (String[] strings : list) {
                    // 可能中间会出现空行
                    if(strings[0] == null){
                        continue;
                    }
                    OrderSetting orderSetting = new OrderSetting();
                    orderSetting.setOrderDate(new Date(strings[0]));
                    orderSetting.setNumber(Integer.parseInt(strings[1]));
                    orderSettingList.add(orderSetting);
                }
                // 调用Service保存数据
                orderSettingService.add(orderSettingList);
                return new Result(true, MessageConst.IMPORT_ORDERSETTING_SUCCESS);
            }else {
                throw new RuntimeException("数据不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.IMPORT_ORDERSETTING_FAIL);
        }

    }

    /**
     * 获取某月预约数据
     * @param date 月份 yyyy-mm
     * @return
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        try{
            System.out.println(">>>>>>>>>>>date:"+date);
            // 获取数据
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            System.out.println(">>>>>>>>>>>>list:" +list);
            // 返回数据
            return new Result(true,MessageConst.GET_ORDERSETTING_SUCCESS,list);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.GET_ORDERSETTING_FAIL);
        }
    }

    /**
     * 基于日期编辑预约设置数据
     * @param orderSetting 预约设置数据
     * @return
     */
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try{
            // 编辑预约设置
            orderSettingService.editNumberByDate(orderSetting);
            // 返回设置成功
            return new Result(true,MessageConst.ORDERSETTING_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            // 返回设置失败
            return new Result(false,MessageConst.ORDERSETTING_FAIL);
        }
    }

}
