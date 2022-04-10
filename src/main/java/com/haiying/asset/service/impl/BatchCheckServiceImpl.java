package com.haiying.asset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.mapper.BatchCheckMapper;
import com.haiying.asset.model.entity.BatchCheck;
import com.haiying.asset.service.BatchCheckService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 批量审批 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
@Service
public class BatchCheckServiceImpl extends ServiceImpl<BatchCheckMapper, BatchCheck> implements BatchCheckService {
}
