package asia.huangzhitao.huangojbackendserviceclient.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新请求
 *
 * 
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */

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

    /**
     * 用户角色：user/admin/ban
     */
    @TableField(value = "user_role")
    private String user_role;

    private static final long serialVersionUID = 1L;
}