package asia.huangzhitao.huangojbackendserviceclient.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import asia.huangzhitao.huangojbackendjudgeservice.common.PageRequest;


import java.io.Serializable;

/**
 * 用户查询请求
 *
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 开放平台id
     */
    @TableField(value = "open_id")
    private String union_id;

    /**
     * 公众号openId
     */
    @TableField(value = "mp_open_id")
    private String mp_open_id;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

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