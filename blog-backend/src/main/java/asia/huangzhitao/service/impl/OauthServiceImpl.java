package asia.huangzhitao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import asia.huangzhitao.domain.entity.User;
import asia.huangzhitao.mapper.UserMapper;
import asia.huangzhitao.service.OauthService;
import asia.huangzhitao.service.UserService;
import asia.huangzhitao.utils.AddressUtils;
import asia.huangzhitao.utils.IpUtils;

import java.util.Date;

/**
 * @author tao
 * <p>
 * 创建时间：2024/12/21 17:09
 */
@Slf4j
@Service
public class OauthServiceImpl implements OauthService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public String handleLogin(AuthResponse authResponse, HttpServletRequest request,Integer type) {
        if (authResponse.getCode() == 2000) {
            AuthUser authUser = (AuthUser)authResponse.getData();
            // 第三方登录默认密码
            String enPassword = passwordEncoder.encode(authUser.getToken().getAccessToken());
            if (userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getId,authUser.getUuid()).eq(User::getRegisterType,type)) == 0) {
                // 已注册
                String ipAddr = IpUtils.getIpAddr(request);
                String realAddressByIP = AddressUtils.getRealAddressByIP(ipAddr);

                User user = User.builder()
                        .id(Long.valueOf(authUser.getUuid()))
                        .username(authUser.getUsername())
                        .avatar(authUser.getAvatar())
                        .nickname(authUser.getNickname())
                        .password(enPassword)
                        .email(authUser.getEmail())
                        .registerType(type)
                        .registerIp(ipAddr)
                        .registerAddress(realAddressByIP)
                        .loginType(type)
                        .loginAddress(realAddressByIP)
                        .loginIp(ipAddr)
                        .loginTime(new Date())
                        .build();
                userService.save(user);
            }
            User user = User.builder().id(Long.valueOf(authUser.getUuid())).password(enPassword).build();
            userMapper.updateById(user);
            switch (type){
                case 1:
                    return "?login_type=gitee&access_token="+authUser.getToken().getAccessToken()+"&user_name="+authUser.getUsername();
                case 2:
                    return "?login_type=github&access_token="+authUser.getToken().getAccessToken()+"&user_name="+authUser.getUsername();
            }
        }else{
            return authResponse.getMsg();
        }
        return null;
    }
}
