package org.comstudy.myapp.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name="BOARD", uniqueConstraints = {@UniqueConstraint(columnNames = {"SEQ"})})
@Data
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SEQ")
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Column(name = "CREATE_DATE")
	private Date createDate = new Date();
	private Long cnt = 0L;
}