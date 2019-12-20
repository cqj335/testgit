package com.hstc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2019/10/8.
 */
@Data
@TableName(value = "income")
public class Income implements Serializable {
    @TableId(value = "Iid",type = IdType.AUTO)
    private int Iid;
    private String realname;
    private String idcard;
    private String phone;
    private Float tprice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leavedate;
    private String bianhao;
}
