package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备调拨
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@Getter
@Setter
@TableName("asset_turn")
public class AssetTurn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private Integer categoryId;

    private String categoryName;

    private String name;

    private String oldLocation;

    private String newLocation;

    private Integer oldDeptId;

    private String oldDeptName;

    private Integer newDeptId;

    private String newDeptName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate turnDate;

    private String remark;

    private String displayName;

    private String loginName;

    private Integer deptId;
    private String deptName;

    private String guid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    private Integer processInstId;
    @TableField(exist = false)
    private ProcessInst processInst;

    private String descc;
}
