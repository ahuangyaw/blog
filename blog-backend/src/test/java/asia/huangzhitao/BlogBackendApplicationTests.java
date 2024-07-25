package asia.huangzhitao;

import asia.huangzhitao.domain.dto.UserRegisterDTO;
import asia.huangzhitao.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import asia.huangzhitao.utils.ParseEmailUtils;

@SpringBootTest
class BlogBackendApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ParseEmailUtils parseEmailUtils;

    @Resource
    private UserService userService;

    @Test
    void contextLoads() throws Exception {
        String username = "username";
        String password = "password";
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername(username);
        userRegisterDTO.setPassword(password);
        userRegisterDTO.setEmail("839967622@qq.com");


        userRegisterDTO.setCode();

        userService.userRegister()



    }

}
