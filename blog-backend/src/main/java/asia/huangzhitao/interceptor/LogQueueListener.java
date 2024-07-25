package asia.huangzhitao.interceptor;

import com.rabbitmq.client.Channel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import asia.huangzhitao.constants.RabbitConst;
import asia.huangzhitao.domain.entity.Log;
import asia.huangzhitao.domain.entity.LoginLog;
import asia.huangzhitao.mapper.LogMapper;
import asia.huangzhitao.mapper.LoginLogMapper;

import java.io.IOException;

/**
 * @author tao
 * <p>
 * 创建时间：2024/12/11 10:01
 * 日志队列
 */
@Component
@Slf4j
public class LogQueueListener {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Resource
    private LogMapper logMapper;

    /**
     * 监听登录日志队列
     */
    @RabbitListener(queues = RabbitConst.LOG_LOGIN_QUEUE,concurrency = "5-10")
    public void handlerLoginLog(LoginLog loginLog, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("监听登录日志队列,标识:{},数据：{}", tag, loginLog);
        if (loginLog.getBrowser().startsWith("Unknown")) {
            loginLog.setBrowser("未知");
        }
        if (loginLog.getOs().startsWith("Unknown")) {
            loginLog.setOs("未知");
        }
        if (loginLog.getType() == null) {
            loginLog.setType(2);
        }
        loginLogMapper.insert(loginLog);
        log.info("登录日志标识:{}，数据库添加成功", tag);
    }

    /**
     * 监听系统操作日志队列
     * 开启手动确认
     */
    @RabbitListener(queues = RabbitConst.LOG_SYSTEM_QUEUE,concurrency = "5-10")
    public void handlerSystemLog(Log logEntity) {
        log.info("--------------消费系统操作日志--------------");
        logMapper.insert(logEntity);
        log.info("--------------系统操作日志插入数据库成功--------------");
    }
}
