package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 办公营具计划
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
@Getter
@Setter
@TableName("office_tool_plan")
public class OfficeToolPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 新购，维修。。。
     */
    private String lifeCycle;

    /**
     * 名称
     */
    private String name;

    /**
     * 型号规格
     */
    private String modelSpec;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 价格
     */
    private Double price;

    private String displayName;

    private String loginName;

    private Integer startDeptId;

    private String startDeptName;

    private Integer endDeptId;

    private Integer endDeptName;

    private String level;

    private String remark;

    private Integer batchId;

    private String guid;

    private Integer year;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;
}
