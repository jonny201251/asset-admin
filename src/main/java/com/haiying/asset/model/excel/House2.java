package com.haiying.asset.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class House2 {
    @ExcelProperty("资产编号")
    private String code;
    @ExcelProperty("资产名称")
    private String name;
    @ExcelProperty("取得日期")
    private String getDatee;
    @ExcelProperty("坐落位置")
    private String location;

    @ExcelProperty("房屋权属证明")
    private String own1;
    @ExcelProperty("权属人")
    private String own2;
    @ExcelProperty("发证日期")
    private String own33;

    @ExcelProperty("房屋所有权证号")
    private String own4;
    @ExcelProperty("权属面积")
    private Double own5;
    @ExcelProperty("权属性质")
    private String own6;

    @ExcelProperty("office1")
    private Double office1;
    @ExcelProperty("office2")
    private Double office2;
    @ExcelProperty("office3")
    private Double office3;
    @ExcelProperty("office4")
    private Double office4;

    @ExcelProperty("business1")
    private Double business1;
    @ExcelProperty("business2")
    private Double business2;
    @ExcelProperty("business3")
    private Double business3;
    @ExcelProperty("business4")
    private Double business4;

    @ExcelProperty("other1")
    private Double other1;
    @ExcelProperty("other2")
    private Double other2;
    @ExcelProperty("other3")
    private Double other3;
    @ExcelProperty("other4")
    private Double other4;


    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("原值")
    private Double startMoney;
    @ExcelProperty("累计折旧")
    private Double startLoseMoney;
}
