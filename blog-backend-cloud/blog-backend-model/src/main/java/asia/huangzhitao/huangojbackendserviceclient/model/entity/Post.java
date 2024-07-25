package asia.huangzhitao.huangojbackendserviceclient.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 帖子
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private String tags;

    /**
     * 文章缩略图
     */
    private String article_cover;

    /**
     * 是否置顶 (0否 1是）
     */
    private Integer is_top;

    /**
     * 文章状态 (1公开 2私密 3草稿)
     */
    private Integer status;

    /**
     * 访问量
     */
    private Long visit_count;

    /**
     * 点赞数
     */
    private Integer thumb_num;

    /**
     * 收藏数
     */
    private Integer favour_num;

    /**
     * 创建用户 id
     */
    private Long user_id;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 是否删除
     */
    private Integer is_delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}