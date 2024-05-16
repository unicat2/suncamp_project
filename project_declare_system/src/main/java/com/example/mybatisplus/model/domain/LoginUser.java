package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value="LoginUser对象", description="")
public class LoginUser extends Model<LoginUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "phone_number")
    private String phoneNumber;

    private String name;

    private String password;

    private String personId;

    @TableLogic
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.phoneNumber;
    }

}
