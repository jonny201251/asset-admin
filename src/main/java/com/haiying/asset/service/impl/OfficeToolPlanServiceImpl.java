package com.haiying.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.bean.ButtonHandleBean;
import com.haiying.asset.mapper.OfficeToolPlanMapper;
import com.haiying.asset.model.entity.BatchCheck;
import com.haiying.asset.model.entity.OfficeToolPlan;
import com.haiying.asset.model.vo.OfficeToolPlanAfter;
import com.haiying.asset.service.BatchCheckService;
import com.haiying.asset.service.OfficeToolPlanService;
import com.haiying.asset.service.ProcessInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 办公营具计划 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
@Service
public class OfficeToolPlanServiceImpl extends ServiceImpl<OfficeToolPlanMapper, OfficeToolPlan> implements OfficeToolPlanService {
    @Autowired
    ButtonHandleBean buttonHandleBean;
    @Autowired
    BatchCheckService batchCheckService;
    @Autowired
    ProcessInstService processInstService;

    private void add(BatchCheck formValue, List<OfficeToolPlan> list) {
        formValue.setMenuName("办公营具计划");
        batchCheckService.save(formValue);
        list.forEach(item -> item.setBatchId(formValue.getId()));
        this.saveBatch(list);
    }

    private void edit(BatchCheck formValue, List<OfficeToolPlan> list) {
        Integer batchId = formValue.getId();
        this.remove(new LambdaQueryWrapper<OfficeToolPlan>().eq(OfficeToolPlan::getBatchId, batchId));
        list.forEach(item -> item.setBatchId(batchId));
        this.saveBatch(list);
    }

    private void delete(Integer batchId) {
        batchCheckService.removeById(batchId);
        this.remove(new LambdaQueryWrapper<OfficeToolPlan>().eq(OfficeToolPlan::getBatchId, batchId));
    }


    @Override
    public synchronized boolean btnHandle(OfficeToolPlanAfter after) {
        BatchCheck formValue = after.getFormValue();
        List<OfficeToolPlan> list = after.getList();
        String type = after.getType();
        String buttonName = after.getButtonName();
        String path = after.getPath();
        if (type.equals("add")) {
            if (buttonName.equals("草稿")) {
                add(formValue, list);
            } else {
                add(formValue, list);
                Integer processInstId = buttonHandleBean.addEdit(path, formValue, buttonName, formValue.getId(), formValue.getCheckName());
                //
                formValue.setProcessInstId(processInstId);
                batchCheckService.updateById(formValue);
            }
        } else if (type.equals("edit")) {
            if (buttonName.equals("草稿")) {
                edit(formValue, list);
            } else {
                edit(formValue, list);
                Integer processInstId = buttonHandleBean.addEdit(path, formValue, buttonName, formValue.getId(), formValue.getCheckName());
                //
                formValue.setProcessInstId(processInstId);
                batchCheckService.updateById(formValue);
            }
        } else if (type.equals("check") || type.equals("reject")) {
            String haveEditForm = after.getHaveEditForm();
            if (haveEditForm.equals("是")) {
                edit(formValue, list);
            }
            boolean flag = buttonHandleBean.checkReject(formValue.getProcessInstId(), formValue, buttonName,after.getComment());
            if (flag) {

            }
        } else if (type.equals("recall")) {
            buttonHandleBean.recall(formValue.getProcessInstId(), buttonName);
        } else if (type.equals("delete")) {
            delete(formValue.getId());
            buttonHandleBean.delete(formValue.getProcessInstId());
        }
        return true;
    }
}
