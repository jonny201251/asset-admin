package com.haiying.asset.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class ChargeDeptLeaderVO {
    private String loginName;
    private List<Integer> deptIdList;
}
