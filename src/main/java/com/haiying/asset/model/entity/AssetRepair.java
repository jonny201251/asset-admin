package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haiying.asset.model.vo.FileVO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 维保检申请
 * </p>
 *
 * @author 作者
 * @since 2022-03-31
 */
@Getter
@Setter
@TableName("asset_repair")
public class AssetRepair implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 计划类别
     */
    private String planType;

    /**
     * 维修，保养，检测
     */
    private String repairType;

    /**
     *  资产编号
     */
    private String code;

    private Integer categoryId;

    private String categoryName;

    private String name;

    private String modelSpec;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate useDate;

    private Integer useDeptId;

    private String useDeptName;

    private String location;

    /**
     * 维修预算金额
     */
    private Double money;

    private String remark;

    private String guid;

    private String displayName;

    private String loginName;

    private Integer deptId;

    private String deptName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    private Integer batchId;

    @TableField(exist = false)
    private List<FileVO> fileList;

}
