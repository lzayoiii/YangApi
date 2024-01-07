package com.yupi.project.service.impl.inner;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;

import com.yupi.project.service.UserService;
import entity.User;
import org.apache.dubbo.config.annotation.DubboService;
import service.InnerUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.yupi.project.constant.UserConstant.ADMIN_ROLE;
import static com.yupi.project.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 内部用户服务实现类
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserService userService;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "yupi";

    /***
     * 获取签名信息
     * @param body
     * @return
     */
    @Override
    public User getSigntoDataSource(String body) {
        User user = JSONUtil.toBean(body, User.class);
        //前端只传了username
        String userName = user.getUserName();
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        User one = userService.getOne(wrapper);
        return one;
    }
}
