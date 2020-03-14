package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.query.TeacherQuery;

import java.io.Serializable;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zwl
 * @since 2020-03-14
 */
public interface TeacherService extends IService<Teacher> {
    boolean removeById(Serializable id);

    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);


}
