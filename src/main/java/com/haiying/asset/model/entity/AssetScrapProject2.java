package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 办公营具和设备仪器仪表-报废项目2
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@Getter
@Setter
@TableName("asset_scrap_project2")
public class AssetScrapProject2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer project1Id;

    /**
     * 项目
     */
    private String projectName;

    /**
     * 检查项目及要求
     */
    private String projectStandard;

    private String result;

    /**
     * 项目类型
     */
    private String type;


}
