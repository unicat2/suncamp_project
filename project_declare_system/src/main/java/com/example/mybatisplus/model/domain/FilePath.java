package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FilePath对象", description="")
public class FilePath extends Model<FilePath> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "表单号")
            @TableId(value = "form_id")
    private Integer formId;

    @ApiModelProperty(value = "填写人手机号")
    private String phoneNumber;

            @ApiModelProperty(value = "路径")
    private String path;

            @ApiModelProperty(value = "是否删除(1未删除；0已删除)")
            @TableLogic
            private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.formId;
    }

}
