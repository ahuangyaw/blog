package asia.huangzhitao.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import asia.huangzhitao.annotation.AccessLimit;
import asia.huangzhitao.domain.entity.Like;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.service.LikeService;

import java.util.List;

/**
 * @author tao
 * <p>
 * 创建时间：2024/11/3 10:15
 */
@RestController
@Tag(name = "评论相关接口")
@RequestMapping("/like")
@Validated
public class LikeController {

    @Resource
    private LikeService likeService;

    @Operation(summary = "点赞")
    @Parameters({
            @Parameter(name = "type", description = "点赞类型", required = true),
            @Parameter(name = "typeId", description = "点赞id", required = true)
    })
    @AccessLimit(seconds = 60, maxCount = 10)
    @PostMapping("/auth/like")
    public ResponseResult<Void> like(
            @RequestParam("type") @Valid @NotNull Integer type,
            @RequestParam("typeId") @Valid @NotNull Integer typeId
    ) {
        return likeService.userLike(type, typeId);
    }

    @Operation(summary = "取消点赞")
    @Parameters({
            @Parameter(name = "type", description = "点赞类型", required = true),
            @Parameter(name = "typeId", description = "点赞id", required = true)
    })
    @AccessLimit(seconds = 60, maxCount = 10)
    @DeleteMapping("/auth/like")
    public ResponseResult<Void> cancelLike(
            @RequestParam("type") @Valid @NotNull Integer type,
            @RequestParam("typeId") @Valid @NotNull Integer typeId
    ) {
        return likeService.cancelLike(type, typeId);
    }

    @Operation(summary = "是否已经点赞")
    @Parameters({
            @Parameter(name = "type", description = "点赞类型", required = true),
            @Parameter(name = "typeId", description = "点赞id", required = true)
    })
    @AccessLimit(seconds = 60, maxCount = 60)
    @GetMapping("whether/like")
    public ResponseResult<List<Like>> isLike(
            @Valid @NotNull @RequestParam("type") Integer type,
            @RequestParam(value = "typeId", required = false) Integer typeId
    ) {
        return likeService.isLike(type, typeId);
    }

}
