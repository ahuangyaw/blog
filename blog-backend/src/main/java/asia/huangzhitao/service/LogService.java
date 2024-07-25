package asia.huangzhitao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import asia.huangzhitao.domain.dto.LogDTO;
import asia.huangzhitao.domain.dto.LogDeleteDTO;
import asia.huangzhitao.domain.entity.Log;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.domain.vo.PageVO;


/**
 * (Log)表服务接口
 *
 * @author tao
 * @since 2024-12-12 09:12:32
 */
public interface LogService extends IService<Log> {
    /**
     * 搜索/显示操作日志
     *
     * @param LogDTO  查询条件
     * @param aLong
     * @param current
     * @return 数据列表
     */
    PageVO searchLog(LogDTO LogDTO, Long aLong, Long current);

    /**
     *  删除/清空操作日志
     * @param logDeleteDTO id集合
     * @return  响应结果
     */
    ResponseResult<Void> deleteLog(LogDeleteDTO logDeleteDTO);

}
