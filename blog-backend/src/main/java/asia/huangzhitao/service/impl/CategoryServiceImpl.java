package asia.huangzhitao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import asia.huangzhitao.constants.FunctionConst;
import asia.huangzhitao.domain.dto.CategoryDTO;
import asia.huangzhitao.domain.dto.SearchCategoryDTO;
import asia.huangzhitao.domain.entity.Article;
import asia.huangzhitao.domain.entity.Category;
import asia.huangzhitao.domain.response.ResponseResult;
import asia.huangzhitao.domain.vo.CategoryVO;
import asia.huangzhitao.mapper.ArticleMapper;
import asia.huangzhitao.mapper.CategoryMapper;
import asia.huangzhitao.service.CategoryService;
import asia.huangzhitao.utils.StringUtils;

import java.util.List;

/**
 * (Category)表服务实现类
 *
 * @author tao
 * @since 2024-10-15 02:29:14
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> listAllCategory() {
        List<Category> categories = this.query().list();

        return categories.stream().map(category -> category.asViewObject(CategoryVO.class, item -> {
            item.setArticleCount(articleMapper.selectCount(new LambdaQueryWrapper<Article>().eq(Article::getCategoryId, category.getId())));
        })).toList();
    }

    @Override
    public ResponseResult<Void> addCategory(CategoryDTO categoryDTO) {
        categoryDTO.setId(null);
        if (this.save(categoryDTO.asViewObject(Category.class))) return ResponseResult.success();
        return ResponseResult.failure();
    }

    @Override
    public List<CategoryVO> searchCategory(SearchCategoryDTO searchCategoryDTO) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(searchCategoryDTO.getCategoryName()), Category::getCategoryName, searchCategoryDTO.getCategoryName());
        if (StringUtils.isNotNull(searchCategoryDTO.getStartTime()) && StringUtils.isNotNull(searchCategoryDTO.getEndTime()))
            queryWrapper.between(Category::getCreateTime, searchCategoryDTO.getStartTime(), searchCategoryDTO.getEndTime());

        return categoryMapper.selectList(queryWrapper)
                .stream()
                .map(category ->
                        category.asViewObject(CategoryVO.class, item ->
                                item.setArticleCount(articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                                        .eq(Article::getCategoryId, category.getId())))))
                .toList();
    }

    @Override
    public CategoryVO getCategoryById(Long id) {
        return categoryMapper.selectById(id).asViewObject(CategoryVO.class);
    }

    @Transactional
    @Override
    public ResponseResult<Void> addOrUpdateCategory(CategoryDTO categoryDTO) {
        if (this.saveOrUpdate(categoryDTO.asViewObject(Category.class))) return ResponseResult.success();
        return ResponseResult.failure();
    }

    @Transactional
    @Override
    public ResponseResult<Void> deleteCategoryByIds(List<Long> ids) {
        // 是否有剩下文章
        Long count = articleMapper.selectCount(new LambdaQueryWrapper<Article>().in(Article::getCategoryId, ids));
        if (count > 0) return ResponseResult.failure(FunctionConst.CATEGORY_EXIST_ARTICLE);
        // 执行删除
        if (this.removeByIds(ids)) return ResponseResult.success();
        return ResponseResult.failure();
    }
}
