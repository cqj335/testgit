package com.hstc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2019/9/29.
 */
@Data
@TableName(value = "book")
public class Book implements Serializable{
    @TableId(value="bid",type = IdType.AUTO)
    private Integer bid;
    private String uname;
    private String tname;
    private String realname;
    private String idcard;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivedate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leavedate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creadate;
    private String bremark;
    private Float tprice;
    private String jiage;
    private String mingcheng;
    private String miaoshu;
    private String bianhao;
    private Integer roomnum;
}
