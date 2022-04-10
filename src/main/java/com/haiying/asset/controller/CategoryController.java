package com.haiying.asset.controller;


import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.controller.base.BaseTreeController;
import com.haiying.asset.model.entity.Category;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 固定资产类别 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/category")
@Wrapper
public class CategoryController extends BaseTreeController<Category> {

}
