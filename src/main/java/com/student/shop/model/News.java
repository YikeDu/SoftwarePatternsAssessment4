/**
 * 
 */
package com.student.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Yike Du
 * @email student@test.com
 * @date 2023-3-18
 * 
 */
@Setter
@Getter
@Entity
@Table(name = "tbl_news")
public class News extends AbstractEntity {

	@Column(columnDefinition="VARCHAR(64) NOT NULL COMMENT '标题'")
	private String title;

	@Column(columnDefinition="VARCHAR(512) NOT NULL COMMENT '内容'")
	private String content;

	@ManyToOne
	@JoinColumn(columnDefinition="COMMENT '管理员'")
    private Admin  inputUser;

}
