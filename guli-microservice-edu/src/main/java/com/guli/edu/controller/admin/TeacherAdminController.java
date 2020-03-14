package com.guli.edu.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 后台控制器
 * </p>
 *
 * @author zwl
 * @since 2020-03-14
 */
@RestController
@RequestMapping("admin/edu/teacher")
@Api("讲师管理")
public class TeacherAdminController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("讲师列表")
    @GetMapping
    public R list(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);

    }

    @ApiOperation(value="根据id删除讲师")
    @GetMapping("{id}")
    public R removeById(
            @ApiParam(name = "id",value = "讲师id",required = true)
            @PathVariable String id
    ){
        boolean b = teacherService.removeById(id);
        if(b){
            return R.ok().message("删除成功");
        }else{
            return R.setResult(ResultCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page",value = "当前页码",required = true)
            @PathVariable Long page ,

            @ApiParam(name="limit",value = "每页记录数",required = true)
            @PathVariable Long limit,

            @ApiParam(name="teacherQuery",value = "讲师查询对象",required = true)
            TeacherQuery teacherQuery
    ){

        Page<Teacher> pageParam = new Page<>(page,limit);
        teacherService.pageQuery(pageParam,teacherQuery);

        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("rows",records).data("total",total);
    }


}

