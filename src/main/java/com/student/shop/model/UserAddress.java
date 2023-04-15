/**
 * 
 */
package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author student
 * @date 2023-3-8
 * 
 */
@Setter
@Getter
@Entity
@Table(name = "t_useraddress")
public class UserAddress extends AbstractEntity {

	@Column(columnDefinition="VARCHAR(64) DEFAULT NULL COMMENT '收货地址'")
	private String address;

	@Column(columnDefinition="VARCHAR(32) DEFAULT NULL COMMENT '手机号'")
	private String phone;

	@Column(columnDefinition="VARCHAR(16) DEFAULT NULL COMMENT '邮编'")
	private String zipcode;

	@Column(columnDefinition="VARCHAR(16) DEFAULT NULL COMMENT '收货人'")
	private String consignee;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

}
