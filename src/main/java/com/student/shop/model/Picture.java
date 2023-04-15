package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author student
 * @version 2023/3/31
 */
@Setter
@Getter
@Entity
@DynamicUpdate
@Table(name = "t_picture")
public class Picture extends AbstractEntity {

    @Column(columnDefinition="VARCHAR(32) NOT NULL COMMENT '图片名称'")
    private String title;

    @Column(columnDefinition="VARCHAR(128) NOT NULL COMMENT '图片描述'")
    private String memo;

    @Column(columnDefinition="VARCHAR(64) NOT NULL COMMENT '图片地址'")
    private String url;

    @ManyToOne
    @JoinColumn
    private Admin updateAdmin;


}
