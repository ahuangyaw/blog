package asia.huangzhitao.utils;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/17 9:24
 * 时间工具类
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentTime() {
       return LocalDateTime.now().format(dateTimeFormatter);
    }
}
