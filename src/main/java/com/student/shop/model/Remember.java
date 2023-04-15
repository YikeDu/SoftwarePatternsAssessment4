package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Yike Du
 * @version 2023/3/13
 */
@Setter
@Getter
@Entity
@Table(name = "tbl_remember")
public class Remember extends AbstractEntity {

    @ManyToOne
    @JoinColumn
    private User user;

}
