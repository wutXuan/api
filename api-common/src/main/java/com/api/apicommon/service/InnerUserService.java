package com.api.apicommon.service;


import com.api.apicommon.model.entity.User;

/**
 * 用户服务
 *
 * @author yupi
 */
public interface InnerUserService {
    /**
     * 根据key查询用户密钥
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);


}
