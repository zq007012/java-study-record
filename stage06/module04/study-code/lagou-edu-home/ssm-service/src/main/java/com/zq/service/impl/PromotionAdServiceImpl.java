package com.zq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zq.dao.PromotionAdMapper;
import com.zq.domain.PromotionAd;
import com.zq.domain.PromotionAdVo;
import com.zq.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/2 8:01
 */
@Service("promotionAdService")
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    @Qualifier("promotionAdMapper")
    private PromotionAdMapper promotionAdMapper;

    /**
     * 根据广告id回显广告信息
     *
     * @param promotionAdId
     * @return
     */
    @Override
    public PromotionAd findPromotionAdById(Integer promotionAdId) {
        return promotionAdMapper.findPromotionAdById(promotionAdId);
    }

    /**
     * 向promotion_ad表中插入一条记录, 用户发来的数据有
     * <code>name, img, link, priority, spaceId, startTime, endTime, status, text</code>
     * 需要业务层创建的有<code>createTime, updateTime</code>
     *
     * @param promotionAd
     * @return
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void savePromotionAd(PromotionAd promotionAd) {
        LocalDateTime now = LocalDateTime.now();
        promotionAd.setCreateTime(now);
        promotionAd.setUpdateTime(now);
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    /**
     * 向promotion_ad表中插入一条记录, 用户发来的数据有
     * <code>id, name, img, link, priority, spaceId, startTime, endTime, status, text</code>
     * 需要业务层创建的有<code>updateTime</code>
     *
     * @param promotionAd
     * @return
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAd.setUpdateTime(LocalDateTime.now());
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    /**
     * 对广告信息进行分页列表展示,
     * 请求中会携带参数currentPage, pageSize,
     * 参数pageSize表示每页有几条记录, currentPage表示获取第几页的记录,
     * 这两个参数被封装在PromotionAdVo对象中了
     *
     * @param promotionAdVo
     * @return
     */
    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {
        PageHelper.startPage(promotionAdVo.getCurrentPage(), promotionAdVo.getPageSize());
        List<PromotionAd> promotionAdList = promotionAdMapper.findAllPromotionAd();
        return new PageInfo<PromotionAd>(promotionAdList);
    }

    /**
     * 根据广告id修改广告status, 请求中携带的参数有id, status
     * 业务层需要生成updateTime
     *
     * @param promotionAd
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updatePromotionAdStatus(PromotionAd promotionAd) {
        promotionAd.setUpdateTime(LocalDateTime.now());
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
