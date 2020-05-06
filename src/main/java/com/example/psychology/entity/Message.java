package com.example.psychology.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

/*
 * 消息
 */
@Data
@Entity
public class Message {
    //消息Id
    @Id
    private String id;
    //学生
    @ManyToOne
    private Student student;
    //老师
    @ManyToOne
    private Teacher teacher;
    //消息体
    private String content;
    //时间
    private Date createTime;
    //上一条消息
    @OneToOne
    private Message preMessage;
    //状态
    private Integer state;
}
