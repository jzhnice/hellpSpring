package com.dzqc.hellospring.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
//Lombok 插件的注解 会自动帮用户生成get 方法 和set方法 无参构造器 全残构造器 重写toString方法
@Data
@TableName("t_coffee")
public class TCoffee {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer price;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}