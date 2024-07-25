package asia.huangzhitao.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import asia.huangzhitao.annotation.AccessLimit;
import asia.huangzhitao.annotation.LogAnnotation;
import asia.huangzhitao.constants.LogConst;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.service.PublicService;
import asia.huangzhitao.utils.ControllerUtils;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/16 17:00
 * 公共接口
 */
@RestController
@Tag(name = "公共接口")
@RequestMapping("/public")
@Validated
public class PublicController {

    @Resource
    private PublicService publicService;

    /**
     * 邮件发送
     */
    @Operation(summary = "邮件发送")
    @Parameters({
            @Parameter(name = "email", description = "邮箱", required = true),
            @Parameter(name = "type", description = "邮箱类型", required = true)
    })
    @AccessLimit(seconds = 60, maxCount = 1)
    @LogAnnotation(module="邮件发送",operation= LogConst.EMAIL_SEND)
    @GetMapping("/ask-code")
    public ResponseResult<String> askVerifyCode(
            @RequestParam @Email String email,
            @RequestParam @Pattern(regexp = "(register|reset|resetEmail)",message = "邮箱类型错误" ) String type
    ) {
        return ControllerUtils.messageHandler(() -> publicService.registerEmailVerifyCode(type, email));
    }

}
