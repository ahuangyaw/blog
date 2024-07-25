package asia.huangzhitao.blogbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类（项目启动入口）
 *
 * @author hwang
 *
 */

@SpringBootApplication()
@MapperScan("asia.huangzhitao.blogbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("asia.huangzhitao")
@EnableFeignClients(basePackages = "asia.huangzhitao.blogbackendserviceclient.service")
@EnableDiscoveryClient
public class HuangojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuangojBackendUserServiceApplication.class, args);
    }

}
