package asia.huangzhitao.blogbackendserviceclient.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求
 *
 * 
 */
@Data
@TableName(value ="user")
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;
}
