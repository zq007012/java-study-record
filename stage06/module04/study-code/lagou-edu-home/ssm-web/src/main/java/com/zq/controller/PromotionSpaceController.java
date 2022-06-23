package com.zq.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zq.domain.PromotionSpace;
import com.zq.domain.ResponseResult;
import com.zq.service.PromotionSpaceService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/30 20:35
 */
@Controller("promotionSpaceController")
@RequestMapping("/promotionSpace")
@ResponseBody
public class PromotionSpaceController {
    @Autowired
    @Qualifier("promotionSpaceService")
    private PromotionSpaceService promotionSpaceService;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    /**
     * 获取所有promotion_space表中的记录
     * 每条记录中的属性有id, name, spaceKey, createTime, updateTime, isDel
     *
     * @return
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {
        List<PromotionSpace> content = promotionSpaceService.findAllPromotionSpace();
        return new ResponseResult(true, 200, "响应成功", content);
    }

    /**
     * 添加或者修改广告位
     *
     * @param promotionSpace
     * @return
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {
        if (promotionSpace.getId() == null) {
            promotionSpaceService.savePromotionSpace(promotionSpace);
        } else {
            promotionSpaceService.updatePromotionSpace(promotionSpace);
        }

        return new ResponseResult(true, 200, "响应成功", null);
    }

    /**
     * 根据广告位id回显广告位信息, 回显的广告位信息有id, name
     * @param id
     * @return
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id) {
        PromotionSpace content = promotionSpaceService.findPromotionSpaceById(id);
        logger.info("----------------");
        return new ResponseResult(true, 200, "响应成功", content);
    }
}
