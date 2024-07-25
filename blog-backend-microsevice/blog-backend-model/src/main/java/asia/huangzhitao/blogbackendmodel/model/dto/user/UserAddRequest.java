package asia.huangzhitao.blogbackendserviceclient.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建请求
 *
 * 
 */
@Data
@TableName(value ="user")
public class UserAddRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户角色: user, admin
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}