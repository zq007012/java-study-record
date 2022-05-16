package com.zq.service;

import com.zq.domain.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/12 9:44
 */
public interface DeptService {

    List<Dept> findAll();
}
