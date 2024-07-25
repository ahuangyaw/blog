package asia.huangzhitao.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import asia.huangzhitao.annotation.AccessLimit;
import asia.huangzhitao.annotation.LogAnnotation;
import asia.huangzhitao.constants.LogConst;
import asia.huangzhitao.domain.dto.LinkDTO;
import asia.huangzhitao.domain.dto.LinkIsCheckDTO;
import asia.huangzhitao.domain.dto.SearchLinkDTO;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.domain.vo.LinkListVO;
import asia.huangzhitao.domain.vo.LinkVO;
import asia.huangzhitao.service.LinkService;
import asia.huangzhitao.utils.ControllerUtils;

import java.util.List;

/**
 * (Link)表控制层
 *
 * @author tao
 * @since 2024-11-14 08:48:32
 */
@Tag(name = "友链相关接口")
@RestController
@RequestMapping("link")
public class LinkController {
    /**
     * 服务对象
     */
    @Resource
    private LinkService linkService;

    @Operation(summary = "申请友链")
    @Parameter(name = "linkDTO", description = "友链申请信息")
    @AccessLimit(seconds = 60, maxCount = 10)
    @PostMapping("/auth/apply")
    public ResponseResult<Void> applyLink(@RequestBody @Valid LinkDTO linkDTO) {
        return linkService.applyLink(linkDTO);
    }

    @Operation(summary = "查询所有通过申请的友链")
    @AccessLimit(seconds = 60, maxCount = 30)
    @GetMapping("/list")
    public ResponseResult<List<LinkVO>> getLinkList() {
        return ControllerUtils.messageHandler(() -> linkService.getLinkList());
    }

    @PreAuthorize("hasAnyAuthority('blog:link:list')")
    @Operation(summary = "后台友链列表")
    @AccessLimit(seconds = 60, maxCount = 30)
    @LogAnnotation(module="友链管理",operation= LogConst.GET)
    @GetMapping("/back/list")
    public ResponseResult<List<LinkListVO>> backList() {
        return ControllerUtils.messageHandler(() -> linkService.getBackLinkList(null));
    }

    @PreAuthorize("hasAnyAuthority('blog:link:search')")
    @Operation(summary = "搜索后台友链列表")
    @AccessLimit(seconds = 60, maxCount = 30)
    @LogAnnotation(module="友链管理",operation= LogConst.SEARCH)
    @PostMapping("/back/search")
    public ResponseResult<List<LinkListVO>> backList(@RequestBody SearchLinkDTO searchDTO) {
        return ControllerUtils.messageHandler(() -> linkService.getBackLinkList(searchDTO));
    }

    @PreAuthorize("hasAnyAuthority('blog:link:isCheck')")
    @Operation(summary = "修改友链是否通过")
    @AccessLimit(seconds = 60, maxCount = 30)
    @LogAnnotation(module="友链管理",operation= LogConst.UPDATE)
    @PostMapping("/back/isCheck")
    public ResponseResult<Void> isCheck(@RequestBody @Valid LinkIsCheckDTO linkIsCheckDTO) {
        return linkService.isCheckLink(linkIsCheckDTO);
    }

    @PreAuthorize("hasAnyAuthority('blog:link:delete')")
    @Operation(summary = "删除友链")
    @AccessLimit(seconds = 60, maxCount = 30)
    @LogAnnotation(module="友链管理",operation= LogConst.DELETE)
    @DeleteMapping("/back/delete")
    public ResponseResult<Void> delete(@RequestBody List<Long> ids) {
        return linkService.deleteLink(ids);
    }
}

