package com.zq.dao;

import com.zq.domain.Dept;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/11 16:37
 */
public interface DeptDao {
    List<Dept> findAll();
}
