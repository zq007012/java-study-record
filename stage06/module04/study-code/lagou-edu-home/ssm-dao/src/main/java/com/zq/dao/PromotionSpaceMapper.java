package com.zq.dao;

import com.zq.domain.PromotionSpace;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/30 20:00
 */
public interface PromotionSpaceMapper {

    /**
     * 根据广告位id回显广告位信息, 回显的广告位信息有id, name
     * @param id
     * @return
     */
    PromotionSpace findPromotionSpaceById(Integer id);

    /**
     * 获取所有promotion_space表中的记录
     * 每条记录中的属性有id, name, spaceKey, createTime, updateTime, isDel
     * @return
     */
    List<PromotionSpace> findAllPromotionSpace();

    /**
     * 在promotion_space表中添加一条记录,
     * 这条记录中要赋值的字段是<code>name, spaceKey, createTime, updateTime</code>
     *
     * @param promotionSpace
     * @return
     */
    int savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据id修改promotion_space表中的一条记录,
     * 这条记录中要修改的字段时<code>name,updateTime</code>
     *
     * @param promotionSpace
     * @return
     */
    int updatePromotionSpace(PromotionSpace promotionSpace);
}
