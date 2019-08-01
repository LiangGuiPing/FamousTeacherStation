package com.api.entity.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class TUserJsonResult implements Serializable {

    @ApiModelProperty(name = "userId", value = "主键ID", dataType = "Integer", required = false)
    private Integer userId;
    @ApiModelProperty(name = "userName", value = "用户名", dataType = "String", required = true)
    private String userName;
    @ApiModelProperty(name = "password", value = "密码", dataType = "String", required = false)
    private String password;

}
