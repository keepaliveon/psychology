package com.example.psychology.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/*
 * 学生
 */
@Data
@Entity
public class Student {
    //学号
    @Id
    private String id;
    //姓名
    private String name;
    //性别
    private String sex;
    //专业
    private String speciality;
    //学院
    private String department;
    //档案记录
    private String document;
    //密码
    private String password;
    //学生的预约
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Subscribe> subscribeList = new ArrayList<>();
    //学生的消息
    @OneToMany(mappedBy = "student")
    private List<Message> messageList = new ArrayList<>();

}
