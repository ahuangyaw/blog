package asia.huangzhitao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import asia.huangzhitao.domain.dto.LinkDTO;
import asia.huangzhitao.domain.dto.LinkIsCheckDTO;
import asia.huangzhitao.domain.dto.SearchLinkDTO;
import asia.huangzhitao.domain.entity.Link;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.domain.vo.LinkListVO;
import asia.huangzhitao.domain.vo.LinkVO;

import java.util.List;


/**
 * (Link)表服务接口
 *
 * @author tao
 * @since 2024-11-14 08:43:35
 */
public interface LinkService extends IService<Link> {

    /**
     * 申请友链
     * @param linkDTO 友链信息
     * @return 是否成功
     */
    ResponseResult<Void> applyLink(LinkDTO linkDTO);

    /**
     * 查询通过审核的友链
     */
    List<LinkVO> getLinkList();


    /**
     * 后台友链列表
     * @return 结果
     */
    List<LinkListVO> getBackLinkList(SearchLinkDTO searchDTO);

    /**
     * 是否通过友链
     * @param isCheckDTO 是否通过
     * @return 是否成功
     */
    ResponseResult<Void> isCheckLink(LinkIsCheckDTO isCheckDTO);

    /**
     * 删除友链
     * @param ids id 列表
     * @return 是否成功
     */
    ResponseResult<Void> deleteLink(List<Long> ids);
}
