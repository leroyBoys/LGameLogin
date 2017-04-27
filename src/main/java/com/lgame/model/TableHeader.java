package com.lgame.model;

/**
 * ��ͷ��Ϣ
 * @author lxh
 *
 */
public class TableHeader {
	private String name;
	private String comment;
	private int rows;
	public String getName() {
		return name;
	}
	public String getComment() {
		return comment;
	}
	public int getRows() {
		return rows;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
}