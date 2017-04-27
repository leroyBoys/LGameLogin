package com.lgame.utils.enums;
/**
 * 邮件物品分类
 * @author lvxiaohui
 *
 */
public enum MailItemType {
	/**
	 * 未定义
	 */
	NULL(0),
	/**
	 * 装备
	 */
	equipment(1),
	/**
	 * （使用）道具
	 */
	item(2),
	/**
	 * 材料
	 */
	material(3),
	/**
	 * 任务
	 */
	quests(4)
	;



	private final int value;
	public int getValue() {
		return value;
	}
	private MailItemType(int value) {
		this.value = value;
	}

}