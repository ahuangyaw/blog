package asia.huangzhitao.huangojbackendserviceclient.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新个人信息请求
 *
 * 
 */
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 用户头像
     */
    @TableField(value = "user_avatar")
    private String user_avatar;

    /**
     * 简介
     */
    @TableField(value = "user_profile")
    private String user_profile;

    private static final long serialVersionUID = 1L;
}