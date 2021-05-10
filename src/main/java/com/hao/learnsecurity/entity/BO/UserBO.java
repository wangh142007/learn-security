package com.hao.learnsecurity.entity.BO;

import lombok.Builder;
import lombok.Data;

/**
 * @author wang hao
 * @since 2021/5/10 15:10
 */
@Data
@Builder
public class UserBO {

    private Integer userId;
    private String username;
    private String authorities;

}
