package com.example.mybatisplus.mapper;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Works;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.service.WorksService;
import com.example.mybatisplus.web.controller.WorksController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


public interface WorksMapper extends BaseMapper<Works> {


}
