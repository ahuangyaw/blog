package asia.huangzhitao.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import asia.huangzhitao.domain.BaseData;

@Data
public class UserUpdateDTO implements BaseData {
    //用户昵称
    @NotNull
    private String nickname;
    //用户性别
    @NotNull
    private Integer gender;
    //用户头像
    @NotNull
    private String avatar;
    //个人简介
    @NotNull
    private String intro;
}
