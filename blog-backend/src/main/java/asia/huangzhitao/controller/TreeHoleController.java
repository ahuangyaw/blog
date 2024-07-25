package asia.huangzhitao.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import asia.huangzhitao.annotation.AccessLimit;
import asia.huangzhitao.annotation.LogAnnotation;
import asia.huangzhitao.constants.LogConst;
import asia.huangzhitao.domain.dto.SearchTreeHoleDTO;
import asia.huangzhitao.domain.dto.TreeHoleIsCheckDTO;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.domain.vo.TreeHoleListVO;
import asia.huangzhitao.domain.vo.TreeHoleVO;
import asia.huangzhitao.service.TreeHoleService;
import asia.huangzhitao.utils.ControllerUtils;

import java.util.List;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/30 11:20
 */
@RestController
@Tag(name = "树洞相关接口")
@RequestMapping("/treeHole")
@Validated
public class TreeHoleController {

    @Resource
    private TreeHoleService treeHoleService;

    @Operation(summary = "添加树洞")
    @AccessLimit(seconds = 60, maxCount = 60)
    @PostMapping("/auth/addTreeHole")
    public ResponseResult<Void> addTreeHole(@Valid @NotNull @RequestBody String content) {
        return treeHoleService.addTreeHole(JSON.parseObject(content).getString("content"));
    }

    @Operation(summary = "查看树洞")
    @AccessLimit(seconds = 60, maxCount = 60)
    @GetMapping("/getTreeHoleList")
    public ResponseResult<List<TreeHoleVO>> getTreeHoleList() {
        return ControllerUtils.messageHandler(() -> treeHoleService.getTreeHole());
    }

    @PreAuthorize("hasAnyAuthority('blog:treeHole:list')")
    @Operation(summary = "后台树洞列表")
    @AccessLimit(seconds = 60, maxCount = 30)
    @LogAnnotation(module="树洞管理",operation= LogConst.GET)
    @GetMapping("/back/list")
    public ResponseResult<List<TreeHoleListVO>> backList() {
        return ControllerUtils.messageHandler(() -> treeHoleService.getBackTreeHoleList(null));
    }

    @PreAuthorize("hasAnyAuthority('blog:treeHole:search')")
    @Operation(summary = "搜索后台树洞列表")
    @AccessLimit(seconds = 60, maxCount = 30)
    @LogAnnotation(module="树洞管理",operation= LogConst.SEARCH)
    @PostMapping("/back/search")
    public ResponseResult<List<TreeHoleListVO>> backList(@RequestBody SearchTreeHoleDTO searchDTO) {
        return ControllerUtils.messageHandler(() -> treeHoleService.getBackTreeHoleList(searchDTO));
    }

    @PreAuthorize("hasAnyAuthority('blog:treeHole:isCheck')")
    @Operation(summary = "修改树洞是否通过")
    @AccessLimit(seconds = 60, maxCount = 30)
    @LogAnnotation(module="树洞管理",operation= LogConst.UPDATE)
    @PostMapping("/back/isCheck")
    public ResponseResult<Void> isCheck(@RequestBody @Valid TreeHoleIsCheckDTO treeHoleIsCheckDTO) {
        return treeHoleService.isCheckTreeHole(treeHoleIsCheckDTO);
    }

    @PreAuthorize("hasAnyAuthority('blog:treeHole:delete')")
    @Operation(summary = "删除树洞")
    @AccessLimit(seconds = 60, maxCount = 30)
    @LogAnnotation(module="树洞管理",operation= LogConst.DELETE)
    @DeleteMapping("/back/delete")
    public ResponseResult<Void> delete(@RequestBody List<Long> ids) {
        return treeHoleService.deleteTreeHole(ids);
    }

}
