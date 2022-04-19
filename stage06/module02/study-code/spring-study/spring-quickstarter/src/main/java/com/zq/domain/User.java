package com.zq.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/7 10:16
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1719848061722996915L;

    private String name;
    private int age;
}
