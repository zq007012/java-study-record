package com.zq.service;

import com.zq.domain.PromotionSpace;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/30 20:27
 */
public interface PromotionSpaceService {

    /**
     * 根据广告位id回显广告位信息, 回显的广告位信息有id, name
     * @param promotionSpaceId
     * @return
     */
    PromotionSpace findPromotionSpaceById(Integer promotionSpaceId);

    /**
     * 添加广告位时, 请求中会携带参数name, 服务器需要生成spaceKey, createTime, updateTime
     * @param promotionSpace
     */
    void  savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 修改广告位时, 请求中会携带参数id, name, 服务器需要生成updateTime
     * @param promotionSpace
     */
    void updatePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 获取所有promotion_space表中的记录
     * 每条记录中的属性有id, name, spaceKey, createTime, updateTime, isDel
     * @return
     */
    List<PromotionSpace> findAllPromotionSpace();

}
