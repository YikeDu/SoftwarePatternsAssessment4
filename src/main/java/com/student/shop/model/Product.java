package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * @author Student
 * @email student@test.com
 * @date 2023-3-9
 */
@Setter
@Getter
@Entity
@DynamicUpdate
@Table(name = "t_product")
public class Product extends AbstractEntity {

    @Column(columnDefinition="VARCHAR(64) NOT NULL COMMENT 'Product Name'")
    private String title;

    @Column(columnDefinition="DOUBLE NOT NULL COMMENT 'Product Price'")
    private Integer point;

    @ManyToOne
    @JoinColumn
    private Picture masterPic;//主图

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Picture> slavePic;//关联图

    @Lob
    @Column(columnDefinition = "TEXT COMMENT '商品描述'")
    private String note;

    @Column(columnDefinition="VARCHAR(32) NOT NULL COMMENT 'Product Code'")
    private String code;

    @Column(columnDefinition="VARCHAR(32) NOT NULL COMMENT 'Product Model'")
    private String model;

    @Column(columnDefinition="INT(11) NOT NULL COMMENT '库存数量'")
    private Long stock;

    @ManyToOne
    @JoinColumn
    private Admin inputUser;

}
