package entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * yangapi.`interface_info`
 * @TableName interface_info
 */
@Data
//@TableName(value ="interface_info")
public class InterfaceInfo implements Serializable {
    /**
     * 接口id
     */
//    @TableId(type = IdType.AUTO)
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

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 创建人id
     */
    private Long userId;

    private String requestParams;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    private Integer isDeleted;

}
