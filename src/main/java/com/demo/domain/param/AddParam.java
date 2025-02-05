package com.demo.domain.param;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


/**
 * @author shanli
 */
@Getter
@Setter
public class AddParam {

    @NotNull(message = "a不能为空")
    @ApiModelProperty(name = "加数1")
    private Integer a;

    @NotNull(message = "b不能为空")
    @ApiModelProperty(name = "加数2")
    private Integer b;
}
