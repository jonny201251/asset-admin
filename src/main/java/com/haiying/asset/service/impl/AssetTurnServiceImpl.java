package com.haiying.asset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.bean.ButtonHandleBean;
import com.haiying.asset.mapper.AssetTurnMapper;
import com.haiying.asset.model.entity.AssetTurn;
import com.haiying.asset.model.entity.SysDept;
import com.haiying.asset.model.vo.AssetTurnAfter;
import com.haiying.asset.service.AssetTurnService;
import com.haiying.asset.service.ProcessInstService;
import com.haiying.asset.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备调拨 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@Service
public class AssetTurnServiceImpl extends ServiceImpl<AssetTurnMapper, AssetTurn> implements AssetTurnService {
    @Autowired
    ButtonHandleBean buttonHandleBean;
    @Autowired
    ProcessInstService processInstService;
    @Autowired
    SysDeptService sysDeptService;

    private void add(AssetTurn formValue) {
        SysDept dept = sysDeptService.getById(formValue.getNewDeptId());
        formValue.setNewDeptName(dept.getName());
        String descc = formValue.getDescc();
        descc = descc.replaceAll("xx", formValue.getOldDeptName());
        descc = descc.replaceAll("yy", formValue.getName());
        descc = descc.replaceAll("zz", formValue.getNewDeptName());
        descc = descc.replaceAll("qq", formValue.getTurnDate().toString());
        formValue.setDescc(descc);
        this.save(formValue);
    }

    private void edit(AssetTurn formValue) {
        SysDept dept = sysDeptService.getById(formValue.getNewDeptId());
        formValue.setNewDeptName(dept.getName());
        String descc = formValue.getDescc();
        descc = descc.replaceAll("xx", formValue.getOldDeptName());
        descc = descc.replaceAll("yy", formValue.getName());
        descc = descc.replaceAll("zz", formValue.getNewDeptName());
        descc = descc.replaceAll("qq", formValue.getTurnDate().toString());
        formValue.setDescc(descc);
        this.updateById(formValue);
    }

    private void delete(Integer id) {
        this.removeById(id);
    }


    @Override
    public synchronized boolean btnHandle(AssetTurnAfter after) {
        AssetTurn formValue = after.getFormValue();
        String type = after.getType();
        String buttonName = after.getButtonName();
        String path = after.getPath();
        if (type.equals("add")) {
            if (buttonName.equals("草稿")) {
                add(formValue);
            } else {
                add(formValue);
                Integer processInstId = buttonHandleBean.addEdit(path, formValue, buttonName, formValue.getId(), formValue.getName());
                //
                formValue.setProcessInstId(processInstId);
                this.updateById(formValue);
            }
        } else if (type.equals("edit")) {
            if (buttonName.equals("草稿")) {
                edit(formValue);
            } else {
                edit(formValue);
                Integer processInstId = buttonHandleBean.addEdit(path, formValue, buttonName, formValue.getId(), formValue.getName());
                //
                formValue.setProcessInstId(processInstId);
                this.updateById(formValue);
            }
        } else if (type.equals("check") || type.equals("reject")) {
            String haveEditForm = after.getHaveEditForm();
            if (haveEditForm.equals("是")) {
                edit(formValue);
            }
            boolean flag = buttonHandleBean.checkReject(formValue.getProcessInstId(), formValue, buttonName, after.getComment());
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
