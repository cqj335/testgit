package com.hstc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "ht_user")
public class User {
    @TableId(value = "uid",type = IdType.AUTO)
    private Integer uid;
    private String uname;
    private String upassword;
    private String phone;
    private String ustate;

}
