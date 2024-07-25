package asia.huangzhitao.blogbackendgetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
// 排除掉数据源自动配置
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HuangojBackendGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuangojBackendGatewayApplication.class, args);
    }

}
