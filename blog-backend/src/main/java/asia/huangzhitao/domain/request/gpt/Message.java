package asia.huangzhitao.domain.request.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tao
 * <p>
 * 创建时间：2024/11/10 14:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    String role;
    String content;
}
