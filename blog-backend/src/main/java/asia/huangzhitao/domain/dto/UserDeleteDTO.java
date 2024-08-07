package asia.huangzhitao.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author tao
 * <p>
 * 创建时间：2024/12/19 10:07
 */
@Data
public class UserDeleteDTO {

    /**
     * 用户id列表
     */
    @NotNull
    List<Long> Ids;
}
