package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 资产附件
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
@Getter
@Setter
@TableName("asset_file")
public class AssetFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //对应类的名称
    private String type;
    //具体设备表的id
    private Integer assetId;

    private String name;

    private String url;


}
