package asia.huangzhitao.domain.request.oauth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tao
 * <p>
 * 创建时间：2024/12/21 15:50
 */
@Data
@Component
@ConfigurationProperties(value = "oauth.github")
public class GithubBody {
    private String clientId; //客户端id
    private String redirectUri; //登陆后后的回调地址
    private String clientSecret; //密钥
}
