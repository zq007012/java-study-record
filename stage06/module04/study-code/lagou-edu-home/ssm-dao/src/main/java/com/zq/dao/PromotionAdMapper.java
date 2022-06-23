package com.zq.dao;

import com.zq.domain.PromotionAd;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/1 19:47
 */
public interface PromotionAdMapper {
    /**
     * dao层要做的是: 查询所有广告信息, 每条广告信息除了广告信息外, 还携带着对应的广告位信息
     * @return
     */
    List<PromotionAd> findAllPromotionAd();

    /**
     * 向promotion_ad表中插入一条记录, 插入的字段有
     * <code>name, img, link, priority, spaceId, startTime, endTime, status, text, createTime, updateTime</code>
     * @return
     */
    int savePromotionAd(PromotionAd promotionAd);

    /**
     * 根据广告id更新promotion_ad表中的一条记录, 更新到字段有
     * <code>name, img, link, priority, spaceId, startTime, endTime, status, text, updateTime</code>
     * @param promotionAd
     * @return
     */
    int updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据广告id回显广告信息
     * @param promotionAdId
     * @return
     */
    PromotionAd findPromotionAdById(Integer promotionAdId);

    /**
     * 根据广告id修改广告status
     * 修改promotion_ad表中指定id的记录的status, updateTime
     * @param promotionAd
     * @return
     */
    int updatePromotionAdStatus(PromotionAd promotionAd);
}
