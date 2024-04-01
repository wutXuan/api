package com.yupi.project.mapper;

import com.api.apicommon.model.entity.InterfaceInfo;
import com.api.apicommon.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.project.model.vo.InterfaceInfoVO;

import java.util.List;

/**
* @author DELL
* @description 针对表【user_interface_info】的数据库操作Mapper
* @createDate 2024-03-26 08:42:04
* @Entity com.yupi.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    List<UserInterfaceInfo> listTopInterfaceInfo(int limit);
}




