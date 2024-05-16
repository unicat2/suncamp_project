package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Works;
import com.example.mybatisplus.mapper.WorksMapper;
import com.example.mybatisplus.service.WorksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class WorksServiceImpl extends ServiceImpl<WorksMapper, Works> implements WorksService {


}
