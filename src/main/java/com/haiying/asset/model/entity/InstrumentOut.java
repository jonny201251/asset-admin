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
 * 设备出库
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@Getter
@Setter
@TableName("instrument_out")
public class InstrumentOut implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资产编号
     */
    private String code;

    private Integer categoryId;
    private String categoryName;

    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;

    /**
     * 启用日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate useDate;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 资产原值=入库中的单价*数量+运输费+安装费
     */
    private Double startMoney;

    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    private String displayName;

    private String loginName;

    private Integer deptId;

    private String deptName;

    private Integer useDeptId;

    private String useDeptName;

    private String useDisplayName;

    private String useLoginName;

    private String location;

    private String guid;
    private String status;
}
