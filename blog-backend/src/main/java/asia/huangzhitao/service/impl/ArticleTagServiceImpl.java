package asia.huangzhitao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import asia.huangzhitao.domain.entity.ArticleTag;
import asia.huangzhitao.mapper.ArticleTagMapper;
import asia.huangzhitao.service.ArticleTagService;

/**
 * (ArticleTag)表服务实现类
 *
 * @author tao
 * @since 2024-10-15 02:29:13
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
