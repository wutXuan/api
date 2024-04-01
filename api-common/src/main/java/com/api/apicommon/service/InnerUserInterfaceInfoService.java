package com.api.apicommon.service;


import com.api.apicommon.model.entity.UserInterfaceInfo;

/**
* @author DELL
* @description 针对表【user_interface_info】的数据库操作Service
* @createDate 2024-03-26 08:42:04
*/
public interface InnerUserInterfaceInfoService {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);
    /**
     * 统计调用次数+1
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    boolean invokeCount(long userId,long interfaceInfoId);

}
