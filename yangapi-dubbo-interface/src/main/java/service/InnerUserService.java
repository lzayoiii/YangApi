package service;

import entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author yupi
 */
public interface InnerUserService {

    /***
     * 获取签名信息
     * @param body
     * @return
     */
    User getSigntoDataSource(String body);

}
