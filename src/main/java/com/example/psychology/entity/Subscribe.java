package com.example.psychology.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/*
 * 预约
 */
@Data
@Entity
public class Subscribe {

    //订阅Id
    @Id
    private String id;
    //学生
    @ManyToOne
    private Student student;
    //老师
    @ManyToOne
    private Teacher teacher;
    //时间
    private Date createTime;
    //状态
    private Integer state;
}
