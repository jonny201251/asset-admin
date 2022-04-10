package com.haiying.asset.model.vo;

import com.haiying.asset.model.entity.ProcessDesign;
import com.haiying.asset.model.entity.ProcessDesignEdge;
import com.haiying.asset.model.entity.ProcessDesignTask;
import lombok.Data;

import java.util.List;

@Data
public class ProcessDesignVO {
    private ProcessDesign processDesign;
    private List<ProcessDesignTask> taskList;
    private List<ProcessDesignEdge> edgeList;
}
