package asia.huangzhitao.domain.vo;

import lombok.Data;

/**
 * (Permission)表实体类
 *
 * @author tao
 * @since 2024-12-05 19:55:11
 */
@Data
public class PermissionVO {
    //权限表id
    private Integer id;
    //描述
    private String permissionDesc;
    //权限字符
    private String permissionKey;
    // 菜单名称
    private String menuName;
}

