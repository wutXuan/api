package com.api.apicommon.service;


import com.api.apicommon.model.entity.InterfaceInfo;

/**
* @author DELL
* @description 针对表【interface_info(api_db.`interface_info`)】的数据库操作Service
* @createDate 2024-03-21 19:51:16
*/
public interface InnerInterfaceInfoService {
    /**
     * 查询接口是否存在
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path,String method);
}
