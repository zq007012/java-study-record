package com.zq.domain;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/26 7:23
 */
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MapDemo {
    @Getter
    @Setter
    private HashMap<String,Account> accountMap;
}
