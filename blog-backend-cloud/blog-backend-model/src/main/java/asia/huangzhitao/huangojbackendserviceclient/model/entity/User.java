package asia.huangzhitao.huangojbackendserviceclient.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "user_account")
    private String user_account;

    /**
     * 密码
     */
    @TableField(value = "user_password")
    private String user_password;

    /**
     * 微信开放平台id
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
     * 用户性别(0,未定义,1,男,2女)
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 用户头像
     */
    @TableField(value = "user_avatar")
    private String user_avatar;

    /**
     * 用户简介
     */
    @TableField(value = "user_profile")
    private String user_profile;

    /**
     * 用户角色：user/admin/ban
     */
    @TableField(value = "user_role")
    private String user_role;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer is_delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}