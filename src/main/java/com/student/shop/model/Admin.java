/**
 * 
 */
package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 管理员
 * @author student
 * @date 2023-3-8
 * 
 */
@Setter
@Getter
@Entity
@Table(name = "t_admin")
public class Admin extends AbstractEntity {

	@Column(columnDefinition="VARCHAR(16) NOT NULL COMMENT '用户名'")
	private String username;//账户名

	@Column(columnDefinition="VARCHAR(32) NOT NULL COMMENT '密码'")
	private String password;//密码

}
