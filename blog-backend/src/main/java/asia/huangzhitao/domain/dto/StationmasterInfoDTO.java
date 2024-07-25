package asia.huangzhitao.domain.dto;

import lombok.Data;
import asia.huangzhitao.domain.BaseData;

/**
 * @author tao
 * <p>
 * 创建时间：2024/1/3 14:48
 */
@Data
public class StationmasterInfoDTO implements BaseData {
    //站长名称
    private String webmasterName;
    //站长文案
    private String webmasterCopy;
    //gitee链接
    private String giteeLink;
    //github链接
    private String githubLink;
}
