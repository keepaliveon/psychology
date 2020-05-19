package com.example.psychology.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 * 老师
 */
@Data
@Entity
public class Teacher {
    //工号
    @Id
    private String id;
    //老师姓名
    private String name;
    //密码
    private String password;
    //性别
    private String sex;
    //在线
    @Transient
    private boolean subscribed;
    @Transient
    private String subscribeId;
    //老师的预约
    @OneToMany(mappedBy = "teacher")
    private List<Subscribe> subscribeList = new ArrayList<>();
    //老师的消息
    @OneToMany(mappedBy = "teacher")
    private List<Message> messageList = new ArrayList<>();

}
