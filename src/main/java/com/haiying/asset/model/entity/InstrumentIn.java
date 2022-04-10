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
 * 设备入库
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@Getter
@Setter
@TableName("instrument_in")
public class InstrumentIn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *  资产编号
     */
    private String code;

    private Integer categoryId;
    private String categoryName;

    private String name;

    /**
     * 型号规格
     */
    private String modelSpec;

    /**
     * 生产厂商
     */
    private String factory;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 资金来源
     */
    private String moneyFrom;

    /**
     * 购置日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private Integer number;

    private Double price;

    /**
     * 运输费
     */
    private Double moveMoney;

    /**
     * 安装费
     */
    private Double installMoney;

    /**
     * 资产原值=入库中的单价*数量+运输费+安装费
     */
    private Double startMoney;

    /**
     * 资料份数
     */
    private Integer fileNumber;

    /**
     * 使用年限
     */
    private Integer useYear;

    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    private String displayName;

    private String loginName;

    private Integer deptId;

    private String deptName;

    /**
     * 取得方式，购置，无偿转入
     */
    private String getStyle;

    @TableField(exist = false)
    private List<FileVO> fileList;

    private String guid;
    private String status;
}
