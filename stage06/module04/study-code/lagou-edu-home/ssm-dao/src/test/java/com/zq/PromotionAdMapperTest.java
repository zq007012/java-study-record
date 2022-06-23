package com.zq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zq.dao.PromotionAdMapper;
import com.zq.domain.PromotionAd;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.List;
import java.util.zip.CRC32;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/1 20:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextDao.xml")
public class PromotionAdMapperTest {
    @Autowired
    @Qualifier("promotionAdMapper")
    private PromotionAdMapper promotionAdMapper;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    /**
     *
     */
    @Test
    public void loggerTest(){
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
    }

    /**
     * 测试{@link PromotionAdMapper#findAllPromotionAd()}方法的功能
     */
    @Test
    public void findAllPromotionAdTest(){
        List<PromotionAd> promotionAdList = promotionAdMapper.findAllPromotionAd();
        for (PromotionAd promotionAd : promotionAdList) {
            System.out.println(promotionAd);
        }
    }

    /**
     * pagehelper测试
     */
    @Test
    public void pageHelperTest() throws JsonProcessingException {
        PageHelper.startPage(1,6);
        List<PromotionAd> promotionAdList = promotionAdMapper.findAllPromotionAd();
        PageInfo<PromotionAd> promotionAdPageInfo = new PageInfo<>(promotionAdList);
        promotionAdPageInfo.setNavigatePages(2);
        promotionAdPageInfo.setStartRow(2);
        promotionAdPageInfo.setEndRow(3);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(promotionAdPageInfo);

        logger.info(jsonStr);
        Page<PromotionAd> page = new Page<>();
    }

    /**
     *
     */
    @Test
    public void md5Test(){
        String data = "Hero's never die.";

        String md5Hex = DigestUtils.md5Hex(data);
        String sha1Hex = DigestUtils.sha1Hex(data);
        String sha256Hex = DigestUtils.sha256Hex(data);
        logger.info(md5Hex);
        logger.info(sha1Hex);
        logger.info(sha256Hex);

        String s = "吃了吗?";
        String s1 = null;
        String s2 = s + s1;
        logger.info(s.length());//4
        logger.info(s2.length());//8

        String str = "Hero's never die!";
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());
        long crc32Value = crc32.getValue();
        String crc32Hex = Long.toHexString(crc32Value);
        logger.info("crc32Hex: " + crc32Hex);

        logger.info(DigestUtils.md5Hex("123456"+"zq"));
    }
}
