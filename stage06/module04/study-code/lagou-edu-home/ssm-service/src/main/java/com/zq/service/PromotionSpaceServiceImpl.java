package com.zq.service;

import com.zq.dao.PromotionSpaceMapper;
import com.zq.domain.PromotionSpace;
import com.zq.utils.UUIDUtils;
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
 * @date 2022/5/30 20:29
 */
@Service("promotionSpaceService")
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    @Qualifier("promotionSpaceMapper")
    private PromotionSpaceMapper promotionSpaceMapper;

    /**
     * 根据广告位id回显广告位信息, 回显的广告位信息有id, name
     *
     * @param promotionSpaceId
     * @return
     */
    @Override
    public PromotionSpace findPromotionSpaceById(Integer promotionSpaceId) {
        return promotionSpaceMapper.findPromotionSpaceById(promotionSpaceId);
    }

    /**
     * 添加广告位时, 请求中会携带参数name, 服务器需要生成spaceKey, createTime, updateTime
     *
     * @param promotionSpace
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setSpaceKey(UUIDUtils.generateUUID());
        LocalDateTime now = LocalDateTime.now();
        promotionSpace.setCreateTime(now);
        promotionSpace.setUpdateTime(now);
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    /**
     * 修改广告位时, 请求中会携带参数id, name, 服务器需要生成updateTime
     *
     * @param promotionSpace
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setUpdateTime(LocalDateTime.now());
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }

    /**
     * 获取所有promotion_space表中的记录
     * 每条记录中的属性有id, name, spaceKey, createTime, updateTime, isDel
     *
     * @return
     */
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return promotionSpaceMapper.findAllPromotionSpace();
    }
}
