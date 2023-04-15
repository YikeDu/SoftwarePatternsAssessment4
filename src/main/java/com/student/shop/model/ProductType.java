package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 商品类型
 * 
 * @author Student
 * @email student@test.com
 * @date 2023-3-9
 * 
 */
@Setter
@Getter
@Entity
@Table(name = "t_producttype")
public class ProductType extends AbstractEntity {

	@Column(columnDefinition="VARCHAR(32) NOT NULL COMMENT '分类名称'")
	private String name;

}
