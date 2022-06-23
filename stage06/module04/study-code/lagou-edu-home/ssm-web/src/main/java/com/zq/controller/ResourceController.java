package com.zq.controller;

import com.github.pagehelper.PageInfo;
import com.zq.domain.Resource;
import com.zq.domain.ResourceVo;
import com.zq.domain.ResponseResult;
import com.zq.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/10 15:18
 */
@Controller("resourceController")
@ResponseBody
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    @Qualifier("resourceService")
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo) {
        PageInfo<Resource> content = resourceService.findAllResourceInPaging(resourceVo);
        return new ResponseResult(true, 200, "响应成功", content);
    }

    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if (resource.getId() == null) {
            resourceService.saveResource(resource);
        } else {
            resourceService.updateResource(resource);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }

    /**
     * 删除资源信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResourceByResourceId(id);
        return new ResponseResult(true,200,"响应成功",null);
    }
}
