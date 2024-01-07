package com.yupi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.UserInterfaceInfo;

import java.util.List;

/**
* @author lzayoi
* @description 针对表【user_interface_info(yangapi.`user_interface_info`)】的数据库操作Mapper
* @createDate 2023-12-27 14:14:11
* @Entity generator.domain.UserInterfaceInfo
*/

public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {



    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);

}




