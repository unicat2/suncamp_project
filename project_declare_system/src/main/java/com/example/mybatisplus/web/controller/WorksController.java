package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.WorksService;
import com.example.mybatisplus.model.domain.Works;

import java.util.List;


@Controller
@RequestMapping("/works")
public class WorksController {

    private final Logger logger = LoggerFactory.getLogger( WorksController.class );

    @Autowired
    private WorksService worksService;

    /**
     * 描述：根据Id 查询
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Works  works =  worksService.getById(id);
        return JsonResponse.success(works);
    }


    /**
     * 描述：根据作品名称条件查询
     *
     */
    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
    public JsonResponse getByTitle(@PathVariable("title") String title)throws Exception {


        LambdaQueryWrapper<Works> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Works::getTitle,title);
        List<Works> works = worksService.list(wrapper);

        return JsonResponse.success(works);
    }

    /**
     * 描述：根据Id删除
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        worksService.removeById(id);
        return JsonResponse.success(null);
    }

    /**
     * 描述：根据Id更新
     *
     */


    @PostMapping(value = "/update")
    @ResponseBody
    public JsonResponse updateWorks(@RequestBody Works  works) throws Exception {
        worksService.saveOrUpdate(works);
        return JsonResponse.success("success");
    }


    /**
     * 描述:创建Works
     *
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Works  works) throws Exception {
        worksService.save(works);
        return JsonResponse.success(null);
    }


    // 分页查询 - mybatis-plus的方式
    @GetMapping("/page")
    public IPage<Works> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize
                                  ) {
        IPage<Works> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Works> queryWrapper = new QueryWrapper<>();

        return worksService.page(page, queryWrapper);
    }

}

