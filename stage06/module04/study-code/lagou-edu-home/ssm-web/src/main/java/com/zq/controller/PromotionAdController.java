package com.zq.controller;

import com.github.pagehelper.PageInfo;
import com.zq.domain.PromotionAd;
import com.zq.domain.PromotionAdVo;
import com.zq.domain.ResponseResult;
import com.zq.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/2 8:15
 */
@Controller("promotionAdController")
@RequestMapping("/promotionAd")
@ResponseBody
public class PromotionAdController {

    @Autowired
    @Qualifier("promotionAdService")
    private PromotionAdService promotionAdService;

    /**
     * 对广告信息进行分页列表展示,
     * 请求中会携带参数currentPage, pageSize,
     * 参数pageSize表示每页有几条记录, currentPage表示获取第几页的记录,
     * 这两个参数被封装在PromotionAdVo对象中了
     *
     * @param promotionAdVo
     * @return
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {
        return promotionAdService.findAllPromotionAdByPage(promotionAdVo);
    }

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        if (promotionAd.getId() == null) {
            promotionAdService.savePromotionAd(promotionAd);
        } else {
            promotionAdService.updatePromotionAd(promotionAd);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }

    /**
     * 根据广告id回显广告信息
     * @param id
     * @return
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id) {
        PromotionAd content = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true, 200, "响应成功", content);
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus( PromotionAd promotionAd){
        promotionAdService.updatePromotionAdStatus(promotionAd);
        HashMap<String, Object> content = new HashMap<>();
        content.put("status",promotionAd.getStatus());
        return new ResponseResult(true,200,"响应成功",content);
    }
}
