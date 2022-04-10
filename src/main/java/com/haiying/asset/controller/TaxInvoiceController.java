package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.SysUser;
import com.haiying.asset.model.entity.TaxInvoice;
import com.haiying.asset.model.vo.TaxInvoiceVO;
import com.haiying.asset.service.TaxInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 购置发票 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/taxInvoice")
@Wrapper
public class TaxInvoiceController {
    @Autowired
    TaxInvoiceService taxInvoiceService;
    @Autowired
    HttpSession httpSession;

    @PostMapping("list")
    public IPage<TaxInvoice> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<TaxInvoice> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object assetName = paramMap.get("type");
        Object code = paramMap.get("code");
        if (ObjectUtil.isNotEmpty(assetName)) {
            wrapper.like(TaxInvoice::getAssetName, assetName);
        }
        return taxInvoiceService.page(new Page<>(current, pageSize), wrapper);
    }

    @PostMapping("add")
    public boolean add(@RequestBody TaxInvoiceVO taxInvoiceVO) {
        SysUser user = (SysUser) httpSession.getAttribute("user");
        List<TaxInvoice> list = taxInvoiceVO.getList();
        for (TaxInvoice taxInvoice : list) {
            taxInvoice.setCreateDatetime(LocalDateTime.now());
            taxInvoice.setDisplayName(user.getDisplayName());
            taxInvoice.setLoginName(user.getLoginName());
            taxInvoice.setDeptId(user.getDeptId());
            taxInvoice.setDeptName(user.getDeptName());
        }
        return taxInvoiceService.saveBatch(list);
    }

    @GetMapping("get")
    public TaxInvoice get(String id) {
        return taxInvoiceService.getById(id);
    }

    @PostMapping("edit")
    public boolean edit(@RequestBody TaxInvoice taxInvoice) {
        return taxInvoiceService.updateById(taxInvoice);
    }

    @GetMapping("delete")
    public boolean delete(Integer[] arr) {
        List<Integer> idList = Stream.of(arr).collect(Collectors.toList());
        return taxInvoiceService.removeByIds(idList);
    }
}
