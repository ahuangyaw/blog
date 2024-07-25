package asia.huangzhitao.interceptor;

import com.rabbitmq.client.Channel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import asia.huangzhitao.constants.RabbitConst;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/16 20:51
 * 邮件队列监听器
 */
@Component
@Slf4j
@RabbitListener(queues = RabbitConst.MAIL_QUEUE)
public class EmailQueueListener {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    /**
     * 监听邮件队列
     */
    @RabbitHandler
    public void handlerMapMessage(Map<String, Object> data, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        String email = (String) data.get("email");
        String code = (String) data.get("code");
        String type = (String) data.get("type");
        SimpleMailMessage message = switch (type) {
            case "register" ->
                    createMessage("欢迎注册Blog", "您的邮件验证码为：" + code + "，有效时间5分钟，为了保障您的安全，请勿向他人泄露验证码信息。", email);
            case "reset" ->
                    createMessage("重置密码", "您的邮件验证码为：" + code + "，有效时间5分钟，为了保障您的安全，请勿向他人泄露验证码信息。", email);
            case "resetEmail" ->
                    createMessage("重置电子邮箱", "您的邮件验证码为：" + code + "，有效时间5分钟，为了保障您的安全，请勿向他人泄露验证码信息。", email);
            case "friendLinkApplication" ->
                    createMessage("友链申请通知", "有新的友链申请，请及时前往博客后台进行审核", email);
            case "friendLinkApplicationPass" ->
                    createMessage("友链审核通知", "您的友链已审核通过", email);
            default -> null;
        };
        if (Objects.isNull(message)) return;
        // 发送邮件
        try {
            mailSender.send(message);
            log.info(email + "：邮件发送成功");
            channel.basicAck(tag, false); // 成功则消费消息
        } catch (MailException e) {
            log.error(email + "：邮件发送失败", e);
            try {
                channel.basicNack(tag, false, true); // 邮件发送失败，不确认消息，并且重新入队
            } catch (IOException ex) {
                log.warn(email + ": 消息重新入队失败", ex);
            }
        } catch (IOException e) {
            log.error(email + "：消息确认失败", e);
        }
    }

    // 邮件信息
    private SimpleMailMessage createMessage(String title, String content, String email) {
        // 创建一个SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();

        // 设置邮件主题
        message.setSubject(title);

        // 设置邮件内容
        message.setText(content);

        // 设置收件人
        message.setTo(email);

        // 设置发件人
        message.setFrom(username);
        return message;
    }
}
