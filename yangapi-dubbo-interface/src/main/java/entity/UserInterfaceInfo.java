package entity;



import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * yangapi.`user_interface_info`
 * @TableName user_interface_info
 */
@Data
//@TableName(value ="user_interface_info")
public class UserInterfaceInfo implements Serializable {
    /**
     * id
     */
//    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 调用用户id
     */
    private Long userId;

    /**
     * 接口id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

    /**
     * 0-正常，1-禁用
     */
    private Integer status;

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

    private static final long serialVersionUID = 1L;
}
