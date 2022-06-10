package com.haiying.asset.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.common.utils.TreeUtil;
import com.haiying.asset.model.entity.Category;
import com.haiying.asset.model.entity.SysDept;
import com.haiying.asset.model.entity.SysUser;
import com.haiying.asset.model.vo.LabelValue;
import com.haiying.asset.model.vo.TreeSelect;
import com.haiying.asset.service.CategoryService;
import com.haiying.asset.service.SysDeptService;
import com.haiying.asset.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/preload")
@Wrapper
public class PreloadController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    SysDeptService sysDeptService;
    @Autowired
    SysUserService sysUserService;

    @GetMapping("get")
    public Map<String, Object> get() {
        Map<String, Object> map = new HashMap<>();
        //固定资产分类-全部
        List<Category> categoryList = categoryService.list();
        List<TreeSelect> categoryTreeSelect = TreeUtil.getTreeSelect(categoryList);
        map.put("categoryTreeSelect", categoryTreeSelect);
        //固定资产分类-设备
        List<Category> instrumentCategoryList = categoryService.list(new LambdaQueryWrapper<Category>().in(Category::getCodePrefix, Arrays.asList("KYQ", "KSB", "KQTSB")));
        List<TreeSelect> instrumentCategoryTreeSelect = TreeUtil.getTreeSelect(instrumentCategoryList);
        map.put("instrumentCategoryTreeSelect", instrumentCategoryTreeSelect);
        //固定资产分类-房屋
        List<Category> houseCategoryList = categoryService.list(new LambdaQueryWrapper<Category>().eq(Category::getUseYear, 35));
        List<TreeSelect> houseCategoryTreeSelect = TreeUtil.getTreeSelect(houseCategoryList);
        map.put("houseCategoryTreeSelect", houseCategoryTreeSelect);
        //固定资产分类-车辆
        List<Category> carCategoryList = categoryService.list(new LambdaQueryWrapper<Category>().eq(Category::getCodePrefix, "KCL"));
        List<TreeSelect> carCategoryTreeSelect = TreeUtil.getTreeSelect(carCategoryList);
        map.put("carCategoryTreeSelect", carCategoryTreeSelect);


        List<SysDept> deptList = sysDeptService.list();
        List<TreeSelect> deptTreeSelect = TreeUtil.getTreeSelect(deptList);
        map.put("deptTreeSelect", deptTreeSelect);
        //用户
        List<SysUser> sysUserList = sysUserService.list();
        List<LabelValue> userList = sysUserList.stream().map(item -> new LabelValue(item.getLoginName(), item.getLoginName())).collect(Collectors.toList());
        map.put("userList", userList);
        return map;
    }
}

