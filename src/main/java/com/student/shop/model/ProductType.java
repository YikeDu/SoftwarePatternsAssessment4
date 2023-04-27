package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Yike Du
 * 
 */
@Setter
@Getter
@Entity
@Table(name = "tbl_producttype")
public class ProductType extends AbstractEntity {

	@Column(columnDefinition="VARCHAR(32) NOT NULL COMMENT '分类名称'")
	private String name;

}
