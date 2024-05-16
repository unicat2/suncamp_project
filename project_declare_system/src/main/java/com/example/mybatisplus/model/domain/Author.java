package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.*;
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
@ApiModel(value="Author对象", description="")
public class Author extends Model<Author> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "表单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "表单类型")
    private String formType;

            @ApiModelProperty(value = "申报类别，0组织，1个人")
    private Integer writerType;

            @ApiModelProperty(value = "填写人手机号")
    private String writerPhoneNumber;

            @ApiModelProperty(value = "作者姓名")
    private String name;

            @ApiModelProperty(value = "身份证号码")
    private String personId;

            @ApiModelProperty(value = "性别,1男,0 女")
    private Integer sex;

            @ApiModelProperty(value = "手机号码")
    private String phone;

            @ApiModelProperty(value = "邮箱")
    private String email;

            @ApiModelProperty(value = "推荐组织")
    private String recOrg;

            @ApiModelProperty(value = "工作单位")
    private String workPlace;

            @ApiModelProperty(value = "联系省份")
    private String province;

            @ApiModelProperty(value = "联系城市")
    private String city;

            @ApiModelProperty(value = "联系区")
    private String area;

            @ApiModelProperty(value = "单位/组织类型")
    private String workType;

            @ApiModelProperty(value = "详细地址")
    private String assoDetailPlace;

            @ApiModelProperty(value = "紧急联系人/组织联系电话")
    private String secPhone;

            @ApiModelProperty(value = "是否删除(1未删除；0已删除)")
            @TableLogic
    private Integer deleted;

            @ApiModelProperty(value = "榜样名称")
    private String starName;

    @ApiModelProperty(value = "0未提交，1已提交")
    private Integer commitState;

    @ApiModelProperty(value = "证件类型")
    private String identityType;

    @TableField(exist = false)
    private Works works;


/*

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
*/



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
