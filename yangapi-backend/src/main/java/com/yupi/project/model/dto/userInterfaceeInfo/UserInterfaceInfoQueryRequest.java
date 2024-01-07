package com.yupi.project.model.dto.userInterfaceeInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yupi.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author yupi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {

    private Long id;

    /**
     * 接口名
     */
    private String name;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 接口地址
     */
    private String url;

//    /**
//     * 请求头
//     */
//    private String requestheader;
//
//    /**
//     * 响应头
//     */
//    private String responseheader;



    /**
     * 请求类型
     */
    private String method;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}