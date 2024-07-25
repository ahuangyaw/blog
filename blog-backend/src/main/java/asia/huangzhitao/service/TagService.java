package asia.huangzhitao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import asia.huangzhitao.domain.dto.SearchTagDTO;
import asia.huangzhitao.domain.dto.TagDTO;
import asia.huangzhitao.domain.entity.Tag;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.domain.vo.TagVO;

import java.util.List;


/**
 * (Tag)表服务接口
 *
 * @author tao
 * @since 2024-10-15 02:29:14
 */
public interface TagService extends IService<Tag> {
    /**
     * 查询所有标签
     * @return 标签列表
     */
    List<TagVO> listAllTag();

    /**
     * 添加标签
     * @param tagDTO 标签DTO
     * @return 是否成功
     */
    ResponseResult<Void> addTag(TagDTO tagDTO);

    /**
     * 搜索标签
     * @param searchTagDTO 搜索标签DTO
     * @return 标签列表
     */
    List<TagVO> searchTag(SearchTagDTO searchTagDTO);

    /**
     * 根据id查询
     * @param id id
     * @return 标签
     */
    TagVO getTagById(Long id);

    /**
     * 新增或修改标签
     * @param tagDTO 标签DTO
     * @return 是否成功
     */
    ResponseResult<Void> addOrUpdateTag(TagDTO tagDTO);

    /**
     * 根据id删除
     * @param ids id
     * @return 是否成功
     */
    ResponseResult<Void> deleteTagByIds(List<Long> ids);
}
