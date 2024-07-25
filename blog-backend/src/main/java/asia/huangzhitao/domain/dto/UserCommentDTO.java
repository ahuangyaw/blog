package asia.huangzhitao.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import asia.huangzhitao.domain.BaseData;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/25 14:00
 * 用户评论DTO
 */
@Data
public class UserCommentDTO implements BaseData {
    //评论类型 (1文章 2友链 3说说)
    @Schema(description = "评论类型 (1文章 2友链 3说说)")
    @NotNull
    private Integer type;
    //类型id
    @Schema(description = "类型id")
    @NotNull
    private Integer typeId;
    //父评论id
    @Schema(description = "父评论id")
    private Long parentId;
    //回复评论id
    @Schema(description = "回复评论id")
    private Long replyId;
    //评论的内容
    @Schema(description = "评论的内容")
    @NotNull
    private String commentContent;
    //回复用户的id
    @Schema(description = "回复用户的id")
    private Long replyUserId;
}
