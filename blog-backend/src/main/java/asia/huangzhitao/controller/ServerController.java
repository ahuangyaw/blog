package asia.huangzhitao.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import asia.huangzhitao.annotation.AccessLimit;
import asia.huangzhitao.domain.entity.Server;
import asia.huangzhitao.domain.response.ResponseResult;

/**
 * @author tao
 * <p>
 * 创建时间：2024/12/14 15:12
 */
@RestController
@Tag(name = "服务监控")
@RequestMapping("/monitor/server")
public class ServerController
{
    @PreAuthorize("hasAnyAuthority('monitor:server:list')")
    @AccessLimit(seconds = 60, maxCount = 30)
    @Operation(summary = "获取服务监控数据")
    @GetMapping()
    public ResponseResult<Server> getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return ResponseResult.success(server);
    }
}
