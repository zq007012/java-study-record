package com.zq.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 * TODO
 * @author zq007
 * @version V1.0
 * @date 2022-04-30 
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Account implements Serializable {

    private static final long serialVersionUID =  8197841004974884761L;

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
