/**
 * 
 */
package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 支付方式
 * 
 * @author Student
 * @email student@test.com
 * @date 2023-3-18
 * 
 */
@Setter
@Getter
@Entity
@Table(name = "t_payment")
public class Payment extends AbstractEntity {

	@Column(columnDefinition="VARCHAR(20) NOT NULL COMMENT '付款人名字'")
	private String name;

}
