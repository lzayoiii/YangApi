package com.yupi.project.model.dto.interfaceInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class InterfaceInvokeRequest {
    /**
     * 接口id
     */
    private Long id;

    /**
     * 用户请求参数
     */
    private String requestParams;

}
