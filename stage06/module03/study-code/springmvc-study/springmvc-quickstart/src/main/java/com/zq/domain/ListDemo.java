package com.zq.domain;

import lombok.*;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/26 7:08
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ListDemo {
    @Getter
    @Setter
    private List<Account> accountList;
}
