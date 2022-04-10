package com.haiying.asset.service.impl;

import com.haiying.asset.model.entity.Category;
import com.haiying.asset.mapper.CategoryMapper;
import com.haiying.asset.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 固定资产类别 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-09
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
