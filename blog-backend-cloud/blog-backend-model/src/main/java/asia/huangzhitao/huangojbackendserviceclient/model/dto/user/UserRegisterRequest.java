package asia.huangzhitao.huangojbackendserviceclient.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * 
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    @TableField(value = "user_account")
    private String user_account;

    @TableField(value = "user_password")
    private String user_password;

    private String check_password;
}
