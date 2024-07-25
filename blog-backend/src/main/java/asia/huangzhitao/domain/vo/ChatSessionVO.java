package asia.huangzhitao.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author tao
 * <p>
 * 创建时间：2024/11/12 16:02
 */
@Data
@Builder
@Schema(name = "ChatSessionVO", description = "会话VO")
public class ChatSessionVO {
    @Schema(description = "会话id")
    private Long id;
    @Schema(description = "会话标题")
    private String conversationTitle;
    @Schema(description = "会话创建时间")
    private Date createTime;
}
