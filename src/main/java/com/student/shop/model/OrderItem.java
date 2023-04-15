/**
 * 
 */
package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 订单关联明细
 * @author student
 * @date 2023-3-8
 * 
 */
@Setter
@Getter
@Entity
@Table(name = "t_orderitem")
public class OrderItem extends AbstractEntity {

	@OneToOne
	private Product product;

	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	@Column(columnDefinition="INT(11) NOT NULL COMMENT '订单数量'")
	private Integer quantity;

}
