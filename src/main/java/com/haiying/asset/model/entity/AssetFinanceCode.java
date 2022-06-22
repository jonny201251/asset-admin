package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 资产的财务凭证号
 * </p>
 *
 * @author 作者
 * @since 2022-06-21
 */
@Getter
@Setter
@TableName("asset_finance_code")
public class AssetFinanceCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String code;

    private String guid;

    /**
     * 购置，报废，调拨-凭证号
     */
    private String type;

    /**
     * 凭证号
     */
    private String financeCode;


}
