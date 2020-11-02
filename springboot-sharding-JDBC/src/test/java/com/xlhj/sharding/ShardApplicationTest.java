package com.xlhj.sharding;

import com.xlhj.sharding.entity.Course;
import com.xlhj.sharding.entity.SysDict;
import com.xlhj.sharding.entity.SysUser;
import com.xlhj.sharding.mapper.CourseMapper;
import com.xlhj.sharding.mapper.SysDictMapper;
import com.xlhj.sharding.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: lcj
 * @Date: 2020/11/2 10:53
 * @Description: 测试
 * @Version: 0.0.1
 */
@SpringBootTest
public class ShardApplicationTest {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysDictMapper dictMapper;

    /**
     * 测试分表
     */
    @Test
    public void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setName("java" + i);
            courseMapper.insert(course);
        }
    }

    /**
     * 测试水平分库
     */
    @Test
    public void addCourseDB() {
        Course course = new Course();
        course.setName("java");
        course.setUserId(101L);
        courseMapper.insert(course);
    }

    /**
     * 测试垂直分库
     */
    @Test
    public void addUserDB() {
        SysUser user = new SysUser();
        user.setName("zhangsan");
        userMapper.insert(user);
    }

    /**
     * 测试公共表
     */
    @Test
    public void addDict() {
        SysDict dict = new SysDict();
        dict.setDictCode("Y");
        dict.setDictValue("正常");
        dictMapper.insert(dict);
    }
}
