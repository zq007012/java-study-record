package com.zq.controller;

import com.zq.domain.ResourceCategory;
import com.zq.domain.ResponseResult;
import com.zq.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/10 20:30
 */
@Controller("resourceCategoryController")
@RequestMapping("/resourceCategory")
@ResponseBody
public class ResourceCategoryController {
    @Autowired
    @Qualifier("resourceCategoryService")
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {
        List<ResourceCategory> content = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true, 200, "响应成功", content);
    }
}
