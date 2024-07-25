package asia.huangzhitao.annotation;

import java.lang.annotation.*;

/**
 * @author tao
 * <p>
 * 创建时间：2024/12/11 22:50
 * 系统日志记录
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";
    String operation() default "";
}
