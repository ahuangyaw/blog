package asia.huangzhitao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import asia.huangzhitao.domain.dto.RoleDTO;
import asia.huangzhitao.domain.dto.RoleSearchDTO;
import asia.huangzhitao.domain.entity.Role;
import asia.huangzhitao.domain.entity.RoleMenu;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.domain.vo.RoleAllVO;
import asia.huangzhitao.domain.vo.RoleByIdVO;
import asia.huangzhitao.domain.vo.RoleVO;
import asia.huangzhitao.mapper.RoleMapper;
import asia.huangzhitao.mapper.RoleMenuMapper;
import asia.huangzhitao.service.RoleMenuService;
import asia.huangzhitao.service.RoleService;
import asia.huangzhitao.utils.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * (Role)表服务实现类
 *
 * @author tao
 * @since 2024-10-13 15:02:40
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public ResponseResult<List<RoleVO>> selectAll() {
        List<Role> roles = roleMapper.selectList(new LambdaQueryWrapper<Role>().eq(Role::getStatus, 0));
        List<RoleVO> vos = roles.stream().map(role -> role.asViewObject(RoleVO.class)).toList();
        if (!vos.isEmpty()) return ResponseResult.success(vos);
        return ResponseResult.failure();
    }

    @Override
    public List<RoleAllVO> selectRole(RoleSearchDTO roleSearchDTO) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(roleSearchDTO)){
            wrapper.like(Objects.nonNull(roleSearchDTO.getRoleName()), Role::getRoleName, roleSearchDTO.getRoleName())
                    .like(Objects.nonNull(roleSearchDTO.getRoleKey()), Role::getRoleKey, roleSearchDTO.getRoleKey())
                    .eq(Objects.nonNull(roleSearchDTO.getStatus()), Role::getStatus, roleSearchDTO.getStatus());
            if (roleSearchDTO.getCreateTimeStart() != null && roleSearchDTO.getCreateTimeEnd() != null) {
                wrapper.gt(Role::getCreateTime, roleSearchDTO.getCreateTimeStart()).and(a -> a.lt(Role::getCreateTime, roleSearchDTO.getCreateTimeEnd()));
            }
        }
        wrapper.orderByAsc(Role::getOrderNum);
        return roleMapper.selectList(wrapper).stream().map(role -> role.asViewObject(RoleAllVO.class)).toList();
    }

    @Override
    public ResponseResult<Void> updateStatus(Long id, Integer status) {
        if (roleMapper.updateById(new Role().setId(id).setStatus(status)) > 0) {
            return ResponseResult.success();
        }
        return ResponseResult.failure();
    }

    @Override
    public ResponseResult<RoleByIdVO> selectRoleById(Long id) {
        List<Long> menuIds = roleMenuMapper
                .selectList(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, id))
                .stream().map(RoleMenu::getMenuId).toList();
        Role role = roleMapper.selectById(id);
        if (role != null) {
            RoleByIdVO vo = role.asViewObject(RoleByIdVO.class, v -> v.setMenuIds(menuIds));
            return ResponseResult.success(vo);
        }
        return ResponseResult.failure();
    }

    @Transactional
    @Override
    public ResponseResult<Void> updateOrInsertRole(RoleDTO roleDTO) {
        Role role = roleDTO.asViewObject(Role.class);
        // 角色字符是否重复
        Role isRole = roleMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getRoleKey, role.getRoleKey().trim()));
        if (StringUtils.isNotNull(isRole) && !isRole.getId().equals(role.getId())) {
            return ResponseResult.failure("角色字符不可重复");
        }
        if (this.saveOrUpdate(role)) {
            // 添加与菜单的权限
            roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, role.getId()));
            List<RoleMenu> roleMenus = roleDTO.getMenuIds().stream().map(menuId -> new RoleMenu(role.getId(), menuId)).toList();
            roleMenuService.saveBatch(roleMenus);
        }
        return ResponseResult.success();
    }

    @Transactional
    @Override
    public ResponseResult<Void> deleteRole(List<Long> ids) {
        if (roleMapper.deleteBatchIds(ids) > 0) {
            roleMenuMapper.deleteBatchIds(ids);
            return ResponseResult.success();
        }
        return ResponseResult.failure();
    }
}

