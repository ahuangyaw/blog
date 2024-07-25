package asia.huangzhitao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import asia.huangzhitao.domain.entity.RoleMenu;
import asia.huangzhitao.mapper.RoleMenuMapper;
import asia.huangzhitao.service.RoleMenuService;

/**
 * (RoleMenu)表服务实现类
 *
 * @author tao
 * @since 2024-11-28 10:23:17
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
