package com.student.shop.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Yike Du
 * @version 2023/3/15
 */
@Setter
@Getter
public class OrderBase implements Serializable {

    /**
     * 请求流水号
     */
    private String reqId;


}
