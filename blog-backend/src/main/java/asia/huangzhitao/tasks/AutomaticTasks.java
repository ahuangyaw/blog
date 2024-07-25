package asia.huangzhitao.tasks;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import asia.huangzhitao.service.RedisService;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/22 15:16
 * 说明：自动任务，初始化
 */
@Slf4j
@Component
public class AutomaticTasks implements ApplicationRunner {

    @Resource
    private RedisService redisService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("--------执行初始化任务--------");
        redisService.articleCountClear();
        redisService.articleVisitCount();
        redisService.clearLimitCache();
    }
}
