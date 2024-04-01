package com.yupi.project.service;


import com.api.apicommon.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author DELL
* @description 针对表【user_interface_info】的数据库操作Service
* @createDate 2024-03-26 08:42:04
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo,boolean add);

    boolean invokeCount(long userId,long interfaceInfoId);

}
