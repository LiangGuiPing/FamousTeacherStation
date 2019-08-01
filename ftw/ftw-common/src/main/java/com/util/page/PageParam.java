package com.util.page;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class PageParam {

    @ApiParam(value = "当前页", required = false, defaultValue = "1")
    @ApiModelProperty(value = "当前页", required = true)
    private int pageNo = 1;

    @ApiParam(value = "每页条数", required = false, defaultValue = "10")
    @ApiModelProperty(value = "每页条数", required = true)
    private int pageSize = 10;

}
