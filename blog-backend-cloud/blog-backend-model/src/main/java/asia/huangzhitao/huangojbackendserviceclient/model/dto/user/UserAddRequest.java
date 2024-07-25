package asia.huangzhitao.huangojbackendserviceclient.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建请求
 *
 * 
 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 账号
     */
    @TableField(value = "user_account")
    private String user_account;

    /**
     * 用户头像
     */
    @TableField(value = "user_avatar")
    private String user_avatar;

    /**
     * 用户角色: user, admin
     */
    @TableField(value = "user_role")
    private String user_role;

    private static final long serialVersionUID = 1L;
}