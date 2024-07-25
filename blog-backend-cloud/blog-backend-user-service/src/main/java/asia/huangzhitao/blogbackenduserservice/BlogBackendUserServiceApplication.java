package asia.huangzhitao.blogbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
/**
 * 主类（项目启动入口）
 *
 * @author hwang
 *
 */

@MapperScan("asia.huangzhitao.blogbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("asia.huangzhitao")
//@EnableFeignClients(basePackages = "asia.huangzhitao.blogbackenduserservice.service")
//@EnableDiscoveryClient
public class BlogBackendUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogBackendUserServiceApplication.class, args);
    }

}
