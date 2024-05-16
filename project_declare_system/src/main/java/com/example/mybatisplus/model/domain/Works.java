package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Works对象", description="")
public class Works extends Model<Works> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "作品表ID")
    private Integer id;

    @ApiModelProperty(value = "填写人手机号")
    private String phoneNumber;

            @ApiModelProperty(value = "推荐类别")
    private String type;

            @ApiModelProperty(value = "作品标题")
    private String title;

            @ApiModelProperty(value = "作品链接")
    private String link;

            @ApiModelProperty(value = "首发平台")
    private String platform;

            @ApiModelProperty(value = "作者简介")
    private String authorIntroduce;

            @ApiModelProperty(value = "作品简介")
    private String workIntroduce;

            @ApiModelProperty(value = "社会效果")
    private String socialResult;

            @ApiModelProperty(value = "单位意见")
    private String workView;

    /*@ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
*/



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
