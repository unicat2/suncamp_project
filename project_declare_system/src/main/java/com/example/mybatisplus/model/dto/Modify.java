package com.example.mybatisplus.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Author对象", description="")
public class Modify {
    private String phoneNumber;

    private String oldPass;

    private String newPass;
}
