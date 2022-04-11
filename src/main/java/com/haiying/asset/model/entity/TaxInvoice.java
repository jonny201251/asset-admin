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
 * 购置发票
 * </p>
 *
 * @author 作者
 * @since 2022-03-24
 */
@Getter
@Setter
@TableName("tax_invoice")
public class TaxInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer assetId;

    private String assetName;
    private String type;
    /**
     * 发票名称
     */
    private String name;

    /**
     * 发票代码
     */
    private String code;

    /**
     * 发票号码
     */
    private String code2;

    /**
     * 开票日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private Double money;
    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    private String displayName;

    private String loginName;

    private Integer deptId;

    private String deptName;

    private String status;

    private String guid;

    private Integer categoryId;
    private String categoryName;
}
