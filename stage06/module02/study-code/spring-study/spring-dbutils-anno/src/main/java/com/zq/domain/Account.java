package com.zq.domain;

import lombok.*;

import java.io.Serializable;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022-04-08
 */
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Account implements Serializable {

    private static final long serialVersionUID = 4270826293090116928L;

    @Setter
    @Getter
    private Integer id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Double money;

}
