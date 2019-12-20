package com.hstc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/9/25.
 */
@Data
@TableName(value="room_type")
public class Roomtype implements Serializable{

    @TableId(value="tid",type = IdType.AUTO)
    private Integer tid;
    private String tname;
    private String tphoto;
    private Float tprice;
    private Integer tlivenum;
    private Integer tbednum;
    private Integer troomnum;
    private Integer tablenum;
    private Integer tbooknum;
    private Integer tlivednum;
    private String tremark;
}
