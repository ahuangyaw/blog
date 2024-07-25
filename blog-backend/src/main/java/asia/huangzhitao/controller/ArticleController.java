package asia.huangzhitao.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import asia.huangzhitao.annotation.AccessLimit;
import asia.huangzhitao.annotation.LogAnnotation;
import asia.huangzhitao.constants.LogConst;
import asia.huangzhitao.domain.dto.ArticleDTO;
import asia.huangzhitao.domain.dto.SearchArticleDTO;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.domain.vo.*;
import asia.huangzhitao.service.ArticleService;
import asia.huangzhitao.utils.ControllerUtils;

import java.util.List;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/15 2:57
 */
@RestController
@Tag(name = "文章相关接口")
@RequestMapping("/article")
@Validated
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Operation(summary = "获取所有的文章列表")
    @AccessLimit(seconds = 60, maxCount = 10)
    @Parameters({
            @Parameter(name = "pageNum", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页数量", required = true)
    })
    @GetMapping("/list")
    public ResponseResult<PageVO<List<ArticleVO>>> list(
            @NotNull Integer pageNum,
            @NotNull Integer pageSize
    ) {
        return ControllerUtils.messageHandler((() -> articleService.listAllArticle(pageNum, pageSize)));
    }

    @Operation(summary = "获取推荐的文章信息")
    @AccessLimit(seconds = 60, maxCount = 60)
    @GetMapping("/recommend")
    public ResponseResult<List<RecommendArticleVO>> recommend() {
        return ControllerUtils.messageHandler((() -> articleService.listRecommendArticle()));
    }

    @Operation(summary = "获取随机的文章信息")
    @AccessLimit(seconds = 60, maxCount = 60)
    @GetMapping("/random")
    public ResponseResult<List<RandomArticleVO>> random() {
        return ControllerUtils.messageHandler((() -> articleService.listRandomArticle()));
    }

    @Operation(summary = "获取文章详情")
    @Parameter(name = "id", description = "文章id", required = true)
    @AccessLimit(seconds = 60, maxCount = 60)
    @GetMapping("/detail/{id}")
    public ResponseResult<ArticleDetailVO> detail(@PathVariable("id") @NotNull Integer id) {
        return ControllerUtils.messageHandler((() -> articleService.getArticleDetail(id)));
    }

    @Operation(summary = "相关文章信息")
    @Parameters({
            @Parameter(name = "categoryId", description = "分类id", required = true),
            @Parameter(name = "articleId", description = "文章id", required = true)
    })
    @AccessLimit(seconds = 60, maxCount = 60)
    @GetMapping("/related/{categoryId}/{articleId}")
    public ResponseResult<List<RelatedArticleVO>> related(
            @PathVariable("categoryId") @NotNull Integer categoryId,
            @PathVariable("articleId") @NotNull Integer articleId
    ) {
        return ControllerUtils.messageHandler((() -> articleService.relatedArticleList(categoryId, articleId)));
    }

    @Operation(summary = "获取时间轴数据")
    @AccessLimit(seconds = 60, maxCount = 15)
    @GetMapping("/timeLine")
    public ResponseResult<List<TimeLineVO>> timeLine() {
        return ControllerUtils.messageHandler((articleService::listTimeLine));
    }

    @Operation(summary = "获取分类与标签下的文章")
    @Parameters({
            @Parameter(name = "typeId", description = "类型id", required = true),
            @Parameter(name = "type", description = "类型", required = true)
    })
    @AccessLimit(seconds = 60, maxCount = 60)
    @GetMapping("/where/list/{typeId}")
    public ResponseResult<List<CategoryArticleVO>> listCategoryArticle(
            @NotNull @PathVariable("typeId") Long typeId,
            @NotNull @RequestParam("type") Integer type
    ) {
        return ControllerUtils.messageHandler(() -> articleService.listCategoryArticle(type, typeId));
    }

    @Operation(summary = "文章访问量+1")
    @Parameter(name = "id", description = "文章id", required = true)
    @AccessLimit(seconds = 60, maxCount = 60)
    @GetMapping("/visit/{id}")
    public ResponseResult<Void> visit(@PathVariable("id") @NotNull Long id) {
        articleService.addVisitCount(id);
        return ControllerUtils.messageHandler(() -> null);
    }

    @PreAuthorize("hasAnyAuthority('blog:publish:article')")
    @Operation(summary = "上传文章封面")
    @Parameter(name = "articleCover", description = "文章封面")
    @LogAnnotation(module = "文章管理", operation = LogConst.UPLOAD_IMAGE)
    @AccessLimit(seconds = 60, maxCount = 5)
    @PostMapping("/upload/articleCover")
    public ResponseResult<String> uploadArticleCover(@RequestParam("articleCover") MultipartFile articleCover) {
        return articleService.uploadArticleCover(articleCover);
    }

    @PreAuthorize("hasAnyAuthority('blog:publish:article')")
    @Operation(summary = "发布文章")
    @Parameter(name = "articleDTO", description = "文章信息")
    @LogAnnotation(module = "文章管理", operation = LogConst.INSERT)
    @AccessLimit(seconds = 60, maxCount = 30)
    @PostMapping("/publish")
    public ResponseResult<Void> publish(@RequestBody @Valid ArticleDTO articleDTO) {
        return articleService.publish(articleDTO);
    }

    @PreAuthorize("hasAnyAuthority('blog:publish:article')")
    @Operation(summary = "删除文章封面")
    @Parameter(name = "articleCover", description = "文章封面")
    @LogAnnotation(module = "发布错误", operation = LogConst.DELETE)
    @AccessLimit(seconds = 60, maxCount = 30)
    @GetMapping("/delete/articleCover")
    public ResponseResult<Void> deleteArticleCover(@RequestParam("articleCoverUrl") String articleCoverUrl) {
        return articleService.deleteArticleCover(articleCoverUrl);
    }

    @PreAuthorize("hasAnyAuthority('blog:publish:article')")
    @Operation(summary = "上传文章图片")
    @Parameters({
            @Parameter(name = "articleImage", description = "文章图片"),
            @Parameter(name = "articleId", description = "文章id", required = true)
    })
    @LogAnnotation(module = "文章管理", operation = LogConst.UPLOAD_IMAGE)
    @AccessLimit(seconds = 60, maxCount = 30)
    @PostMapping("/upload/articleImage")
    public ResponseResult<String> uploadArticleImage(
            @RequestParam("articleImage") MultipartFile articleImage
    ) {
        return articleService.uploadArticleImage(articleImage);
    }

    @PreAuthorize("hasAnyAuthority('blog:article:list')")
    @Operation(summary = "获取所有的文章列表")
    @LogAnnotation(module = "文章管理", operation = LogConst.GET)
    @AccessLimit(seconds = 60, maxCount = 30)
    @GetMapping("/back/list")
    public ResponseResult<List<ArticleListVO>> listArticle() {
        return ControllerUtils.messageHandler(() -> articleService.listArticle());
    }

    @PreAuthorize("hasAnyAuthority('blog:article:search')")
    @Operation(summary = "搜索文章列表")
    @Parameters({
            @Parameter(name = "searchArticleDTO", description = "搜索文章信息", required = true)
    })
    @LogAnnotation(module = "文章管理", operation = LogConst.SEARCH)
    @AccessLimit(seconds = 60, maxCount = 30)
    @PostMapping("/back/search")
    public ResponseResult<List<ArticleListVO>> searchArticle(@RequestBody SearchArticleDTO searchArticleDTO) {
        return ControllerUtils.messageHandler(() -> articleService.searchArticle(searchArticleDTO));
    }

    @PreAuthorize("hasAnyAuthority('blog:article:update')")
    @Operation(summary = "修改文章状态")
    @Parameters({
            @Parameter(name = "id", description = "文章id", required = true),
            @Parameter(name = "status", description = "状态", required = true)
    })
    @LogAnnotation(module = "文章管理", operation = LogConst.UPDATE)
    @AccessLimit(seconds = 60, maxCount = 30)
    @PostMapping("/back/update/status")
    public ResponseResult<Void> updateArticleStatus(
            @RequestParam("id") @NotNull Long id,
            @RequestParam("status") @NotNull Integer status
    ) {
        return articleService.updateStatus(id, status);
    }

    @PreAuthorize("hasAnyAuthority('blog:article:update')")
    @Operation(summary = "修改文章是否顶置")
    @Parameters({
            @Parameter(name = "id", description = "文章id", required = true),
            @Parameter(name = "isTop", description = "是否顶置", required = true)
    })
    @LogAnnotation(module = "文章管理", operation = LogConst.UPDATE)
    @AccessLimit(seconds = 60, maxCount = 30)
    @PostMapping("/back/update/isTop")
    public ResponseResult<Void> updateArticleIsTop(
            @RequestParam("id") @NotNull Long id,
            @RequestParam("isTop") @NotNull Integer isTop
    ) {
        return articleService.updateIsTop(id, isTop);
    }

    @PreAuthorize("hasAnyAuthority('blog:article:echo')")
    @Operation(summary = "回显文章数据")
    @Parameters({
            @Parameter(name = "id", description = "文章id", required = true)
    })
    @LogAnnotation(module = "文章管理", operation = LogConst.GET)
    @AccessLimit(seconds = 60, maxCount = 30)
    @GetMapping("/back/echo/{id}")
    public ResponseResult<ArticleDTO> getArticleEcho(@PathVariable("id") Long id){
        return ControllerUtils.messageHandler(() -> articleService.getArticleDTO(id));
    }

    @PreAuthorize("hasAnyAuthority('blog:article:delete')")
    @Operation(summary = "删除文章")
    @Parameters({
            @Parameter(name = "id", description = "文章id", required = true)
    })
    @LogAnnotation(module = "文章管理", operation = LogConst.DELETE)
    @AccessLimit(seconds = 60, maxCount = 30)
    @DeleteMapping("/back/delete")
    public ResponseResult<Void> deleteArticle(@RequestBody List<Long> ids) {
        return articleService.deleteArticle(ids);
    }
}
