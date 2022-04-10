package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 固定资产类别
 * </p>
 *
 * @author 作者
 * @since 2022-03-09
 */
@Getter
@Setter
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pid;

    private String name;

    private Integer useYear;

    private String codePrefix;

    private Double sort;

    private String remark;

    @TableField(exist = false)
    private List<Category> children;
}
