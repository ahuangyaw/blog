package asia.huangzhitao.service;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/22 15:17
 */
public interface RedisService {

    void articleCountClear();

    void articleVisitCount();

    void clearLimitCache();
}
