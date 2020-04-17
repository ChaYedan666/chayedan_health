package com.chayedan.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chayedan.health.common.MessageConst;
import com.chayedan.health.common.RedisConst;
import com.chayedan.health.entity.PageResult;
import com.chayedan.health.entity.QueryPageBean;
import com.chayedan.health.entity.Result;
import com.chayedan.health.pojo.Setmeal;
import com.chayedan.health.service.SetmealService;
import com.chayedan.health.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author chayedan666
 * @version 1.0
 * @className: SetmealController
 * @description:
 * @date: 2020/4/16
 */

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

    /**
     * 上传图片到七牛云并且回显
     * @param imgFile
     * @return
     */
    @RequestMapping("upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        try {
            // 获得原文件的名字
            String filename = imgFile.getOriginalFilename();
            // 生成传上云服务器的名字
            String saveUploadName = UUID.randomUUID().toString().replace("-", "") + "_" + filename;
            // 调用七牛云上传
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),saveUploadName);
            // 将路径返回给客户端
            String file_url = QiniuUtils.qiniu_img_url_pre + saveUploadName;
            // 把文件名存入redis，基于redis的Set集合
            jedisPool.getResource().sadd(RedisConst.SETMEAL_PIC_RESOURCES,saveUploadName);
            // 将全路径返回给客户端显示
            return new Result(true, MessageConst.PIC_UPLOAD_SUCCESS,file_url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.PIC_UPLOAD_FAIL);
        }
    }

    /**
     * 新增套餐
     * @param setmeal 套餐基本信息
     * @param checkgroupIds 选择的检查组列表
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try{
            // 去掉图片路径中，七牛访问域的前缀部分
            setmeal.setImg(setmeal.getImg().replace(QiniuUtils.qiniu_img_url_pre,""));
            setmealService.add(setmeal,checkgroupIds);
            return new Result(true, MessageConst.ADD_SETMEAL_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.ADD_CHECKGROUP_FAIL);
        }
    }

    /**
     * 分页获取套餐数据
     * @param queryPageBean 查询参数
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = null;
        try {
            pageResult = setmealService.pageQuery(
                    queryPageBean.getCurrentPage(),
                    queryPageBean.getPageSize(),
                    queryPageBean.getQueryString());
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L,new ArrayList());
        }
    }
}
