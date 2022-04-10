package com.haiying.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.bean.WorkFlowBean;
import com.haiying.asset.mapper.ProcessInstMapper;
import com.haiying.asset.model.entity.ProcessInst;
import com.haiying.asset.model.entity.ProcessInstNode;
import com.haiying.asset.service.ProcessInstNodeService;
import com.haiying.asset.service.ProcessInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程实例 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-02-14
 */
@Service
public class ProcessInstServiceImpl extends ServiceImpl<ProcessInstMapper, ProcessInst> implements ProcessInstService {
    @Autowired
    ProcessInstNodeService processInstNodeService;
    @Autowired
    WorkFlowBean workFlowBean;

    @Override
    public void delete(ProcessInst processInst) {
        Integer processInstId = processInst.getId();
        this.removeById(processInstId);
        processInstNodeService.remove(new LambdaQueryWrapper<ProcessInstNode>().eq(ProcessInstNode::getProcessInstId, processInstId));
        workFlowBean.deleteProcessInstance(processInst.getActProcessInstanceId());
    }
}
