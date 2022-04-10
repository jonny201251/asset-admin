package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 设备部门内的移动
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@Getter
@Setter
@TableName("asset_move")
public class AssetMove implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private Integer categoryId;

    private String categoryName;

    private String name;

    private Integer useDeptId;

    private String useDeptName;

    /**
     * 移动日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate moveDate;

    private String oldDisplayName;

    private String oldLoginName;

    private String newDisplayName;

    private String newLoginName;

    private String displayName;

    private String loginName;

    private Integer deptId;
    private String deptName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    private String guid;

    private String remark;


}
