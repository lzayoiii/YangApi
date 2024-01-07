package service;

import entity.InterfaceInfo;

/**
* @author lzayoi
* @description 针对表【interface_info(yangapi.`interface_info`)】的数据库操作Service
* @createDate 2023-12-20 22:35:25
*/
public interface InnerInterfaceInfoService {

    /***
     * 校验是否有该接口,通过路径和请求方式
     * @param path
     * @param post
     * @return
     */
    InterfaceInfo verifyInterfaceHave(String path,String post);


}
