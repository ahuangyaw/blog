package asia.huangzhitao.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tao
 * <p>
 * 创建时间：2024/12/18 11:40
 */
@Data
public class UserSearchDTO {
    //用户名
    private String username;
    //用户邮箱
    private String email;
    //是否禁用 0 否 1 是
    private Integer isDisable;
    // 创建时间开始
    private Date createTimeStart;
    // 创建时间结束
    private Date createTimeEnd;
}
