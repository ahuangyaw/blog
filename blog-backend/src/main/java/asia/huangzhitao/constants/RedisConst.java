package asia.huangzhitao.constants;

/**
 * @author tao
 * <p>
 * 创建时间：2024/10/11 8:59
 */
public class RedisConst {

    /**
     * jwt 黑名单（退出登录的用户jwt加入黑名单）
     */
    public static final String JWT_WHITE_LIST = "jwt:white:list:";

    /**
     * 邮箱验证码
     */
    public static final String VERIFY_CODE = "verifyCode:";

    /**
     * 邮箱验证码过期时间
     */
    public static final Integer VERIFY_CODE_EXPIRATION = 5;

    /**
     * 分隔符
     */
    public static final String SEPARATOR = ":";

    /**
     * 注册
     */
    public static final String REGISTER = "register";

    /**
     * 重置密码
     */
    public static final String RESET = "reset";

    /**
     * 重置邮箱
     */
    public static final String RESET_EMAIL = "resetEmail";

    /**
     * 文章收藏数
     */
    public static final String ARTICLE_FAVORITE_COUNT = "article:count:favorite:";
    /**
     * 文章点赞数
     */
    public static final String ARTICLE_LIKE_COUNT = "article:count:like:";
    /**
     * 文章评论数
     */
    public static final String ARTICLE_COMMENT_COUNT = "article:count:comment:";

    /**
     * 文章访问数
     */
    public static final String ARTICLE_VISIT_COUNT = "article:count:visit:";
}
