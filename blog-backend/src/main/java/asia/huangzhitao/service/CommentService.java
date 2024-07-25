package asia.huangzhitao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import asia.huangzhitao.domain.dto.CommentIsCheckDTO;
import asia.huangzhitao.domain.dto.SearchCommentDTO;
import asia.huangzhitao.domain.dto.UserCommentDTO;
import asia.huangzhitao.domain.entity.Comment;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.domain.vo.ArticleCommentVO;
import asia.huangzhitao.domain.vo.CommentListVO;
import asia.huangzhitao.domain.vo.PageVO;

import java.util.List;


/**
 * (Comment)表服务接口
 *
 * @author tao
 * @since 2024-10-19 15:44:57
 */
public interface CommentService extends IService<Comment> {
    /**
     * 查询文章评论
     */
    PageVO<List<ArticleCommentVO>> getComment(Integer type, Integer typeId, Integer pageNum, Integer pageSize);

    /**
     * 添加评论
     */
    ResponseResult<Void> userComment(UserCommentDTO commentDTO);

    /**
     * 后台评论列表
     * @return 结果
     */
    List<CommentListVO> getBackCommentList(SearchCommentDTO searchDTO);

    /**
     * 是否通过评论
     * @param isCheckDTO 是否通过
     * @return 是否成功
     */
    ResponseResult<Void> isCheckComment(CommentIsCheckDTO isCheckDTO);

    /**
     * 删除评论
     * @param id id 列表
     * @return 是否成功
     */
    ResponseResult<Void> deleteComment(Long id);
}
