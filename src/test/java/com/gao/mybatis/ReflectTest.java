package com.gao.mybatis;

import cn.hutool.json.JSONUtil;
import com.gao.mybatis.reflection.MetaObject;
import com.gao.mybatis.reflection.SystemMetaObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectTest {
    private Logger logger = LoggerFactory.getLogger(ReflectTest.class);
    
    @Test
    public void test() {
        try {
            // 获取要调用类的 class 对象
            Class<?> aClass = Class.forName("com.gao.mybatis.test.po.User");
            // 获取类的构造方法
            Constructor<?> constructor = aClass.getConstructor();
            // 使用构造方法创建实例
            Object newInstance = constructor.newInstance();
            // 获取方法
            Method setId = aClass.getMethod("setId", Long.class);
            // 调用方法
            setId.invoke(newInstance, 100L);
            Class<?> returnType = setId.getReturnType();
            System.out.println(returnType.getName());
            System.out.println(returnType.getTypeParameters());
            // 验证方法
            Method getId = aClass.getMethod("getId");
            Object invoke = getId.invoke(newInstance);
            System.out.println(invoke);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testField() {
        try {
            // 获取要调用类的 class 对象
            Class<?> aClass = Class.forName("com.gao.mybatis.test.po.User");
            // 获取类的构造方法
            Constructor<?> constructor = aClass.getConstructor();
            // 使用构造方法创建实例
            Object newInstance = constructor.newInstance();
            // 获取字段
            Field id = aClass.getField("userId");
            // 设置字段值
            id.set(newInstance, "1000");
            // 获取字段值
            Object value = id.get(newInstance);
            System.out.println(value);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    @Test
    public void test_reflection() {
        Teacher teacher = new Teacher();
        List<Teacher.Student> list = new ArrayList<>();
        list.add(new Teacher.Student());
        teacher.setName("小傅哥");
        teacher.setStudents(list);

        MetaObject metaObject = SystemMetaObject.forObject(teacher);

        logger.info("getGetterNames：{}", JSONUtil.toJsonStr(metaObject.getGetterNames()));
        logger.info("getSetterNames：{}", JSONUtil.toJsonStr(metaObject.getSetterNames()));
        logger.info("name的get方法返回值：{}", JSONUtil.toJsonStr(metaObject.getGetterType("name")));
        logger.info("students的set方法参数值：{}", JSONUtil.toJsonStr(metaObject.getGetterType("students")));
        logger.info("name的hasGetter：{}", metaObject.hasGetter("name"));
        logger.info("student.id（属性为对象）的hasGetter：{}", metaObject.hasGetter("student.id"));
        logger.info("获取name的属性值：{}", metaObject.getValue("name"));
        // 重新设置属性值
        metaObject.setValue("name", "小白");
        logger.info("设置name的属性值：{}", metaObject.getValue("name"));
        // 设置属性（集合）的元素值
        metaObject.setValue("students[0].id", "001");
        logger.info("获取students集合的第一个元素的属性值：{}", JSONUtil.toJsonStr(metaObject.getValue("students[0].id")));
        logger.info("对象的序列化：{}", JSONUtil.toJsonStr(teacher));
    }

    static class Teacher {

        private String name;

        private double price;

        private List<Student> students;

        private Student student;

        public static class Student {

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }
}
