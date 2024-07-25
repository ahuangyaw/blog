package asia.huangzhitao.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author tao
 * <p>
 * 创建时间：2024/11/30 17:20
 */
@Data
public class UpdateRoleStatusDTO {
    @NotNull
    private Long id;
    @Min(value = 0)
    @Max(value = 1)
    private Integer status;
}
