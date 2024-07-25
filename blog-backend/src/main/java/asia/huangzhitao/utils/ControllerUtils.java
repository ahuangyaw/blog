package asia.huangzhitao.utils;

import asia.huangzhitao.domain.response.ResponseResult;

import java.util.function.Supplier;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/30 9:52
 */
public class ControllerUtils {
    public static  <T> ResponseResult<T> messageHandler(Supplier<T> supplier) {
        return ResponseResult.success(supplier.get());
    }
}
