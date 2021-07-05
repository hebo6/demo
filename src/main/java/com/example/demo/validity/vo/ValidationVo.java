package com.example.demo.validity.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 递归校验，只要在相应属性上增加{@link Valid}注解即可实现（对于集合同样适用）
 */
@Data
public class ValidationVo {
    @NotNull(message = "name不能为空")
    private String name;
    @NotNull(message = "age不能为空")
    @Min(value = 18, message = "未成年哦")
    private Integer age;
    @Valid
    private SubValidationVo subValidationVo;

    @Data
    private static class SubValidationVo {
        @NotNull(message = "subName不能为空")
        private String subName;
    }
}
