package com.yupi.project.model.dto.userInterfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {
    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private Long interfaceId;

    /**
     *
     */
    private Integer totalNum;

    /**
     *
     */
    private Integer leftNum;

}