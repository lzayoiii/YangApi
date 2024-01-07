package com.yupi.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;

import com.yupi.project.service.InterfaceInfoService;
import entity.InterfaceInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import service.InnerInterfaceInfoService;

/**
 * 内部接口服务实现类
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Autowired
    InterfaceInfoService interfaceInfoService;

    /***
     * 校验是否有该接口,通过路径和请求方式
     * @param path
     * @param post
     * @return
     */
    @Override
    public InterfaceInfo verifyInterfaceHave(String path, String post) {
        LambdaQueryWrapper<InterfaceInfo> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(InterfaceInfo::getMethod,post);
        wrapper.eq(InterfaceInfo::getUrl,path);
        InterfaceInfo one = interfaceInfoService.getOne(wrapper);
        return one;
    }

}
