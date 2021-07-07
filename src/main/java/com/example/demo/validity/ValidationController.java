package com.example.demo.validity;

import com.example.demo.validity.vo.ValidationVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * <b>单个参数</b>校验需要在<b>参数上</b>增加校验注解, 并在<b>类</b>上标注{@link Validated}. 并且类上注解只对{@link RequestParam}和
 * {@link RequestPart}有效, 对{@link RequestPart}<b>无效</b>.<p/>
 * <b>对象</b>校验需要在其<b>属性字段上</b>增加校验注解, 并在<b>参数上</b>标注{@link Validated}.<p/>
 * 课代表总结了三种参数校验时可能引发的异常：
 * <ol>
 * <li>使用<b>form data</b>方式调用接口, 校验异常抛出{@link org.springframework.validation.BindException BindException}</li>
 * <li>使用<b>json</b>请求体调用接口, 校验异常抛出{@link org.springframework.web.bind.MethodArgumentNotValidException MethodArgumentNotValidException}</li>
 * <li><b>单个参数</b>校验异常抛出{@link javax.validation.ConstraintViolationException ConstraintViolationException}</li>
 * </ol>
 */
@Validated
@RestController
@RequestMapping("validation")
public class ValidationController {
    @GetMapping("array")
    public String arrayValidation(@RequestParam(required = false) List<@Positive(message = "ages不能为负数") Integer> ages) {
        return "ok, ages = " + ages;
    }

    @GetMapping
    public String getValidation(@NotNull(message = "requestParam不能为空") @RequestParam(required = false) String requestParam) {
        return "ok, requestParam = " + requestParam;
    }

    @PostMapping
    public String postValidityFormData(@NotNull(message = "requestPart不能为空") @RequestPart(required = false) String requestPart) {
        return "ok, requestPart = " + requestPart;
    }

    @PostMapping("body")
    public ValidationVo postValidityBody(@Validated @RequestBody ValidationVo validationVo) {
        return validationVo;
    }
}
