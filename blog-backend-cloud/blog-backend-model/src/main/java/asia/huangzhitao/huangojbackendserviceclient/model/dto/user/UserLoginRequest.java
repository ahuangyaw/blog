package asia.huangzhitao.huangojbackendserviceclient.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求
 *
 * 
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    @TableField(value = "user_account")
    private String user_account;

    @TableField(value = "user_password")
    private String user_password;
}
