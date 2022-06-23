package com.zq.service;

import com.github.pagehelper.PageInfo;
import com.zq.domain.PromotionAd;
import com.zq.domain.PromotionAdVo;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/2 7:57
 */
public interface PromotionAdService {

    /**
     * 根据广告id回显广告信息
     * @param promotionAdId
     * @return
     */
    PromotionAd findPromotionAdById(Integer promotionAdId);

    /**
     * 向promotion_ad表中插入一条记录, 用户发来的数据有
     * <code>name, img, link, priority, spaceId, startTime, endTime, status, text</code>
     * 需要业务层创建的有<code>createTime, updateTime</code>
     * @return
     */
    void savePromotionAd(PromotionAd promotionAd);

    /**
     * 向promotion_ad表中插入一条记录, 用户发来的数据有
     * <code>id, name, img, link, priority, spaceId, startTime, endTime, status, text</code>
     * 需要业务层创建的有<code>updateTime</code>
     * @return
     */
    void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 对广告信息进行分页列表展示,
     * 请求中会携带参数currentPage, pageSize,
     * 参数pageSize表示每页有几条记录, currentPage表示获取第几页的记录,
     * 这两个参数被封装在PromotionAdVo对象中了
     * @param promotionAdVo
     * @return
     */
    PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    /**
     * 根据广告id修改广告status, 请求中携带的参数有id, status
     * 业务层需要生成updateTime
     * @param promotionAd
     */
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
