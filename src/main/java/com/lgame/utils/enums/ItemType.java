package com.lgame.utils.enums;
/**
 * 使用道具物品分类
 * @author lvxiaohui
 *
 */
public enum ItemType {
	/**
	 * 未定义
	 */
	NULL(-1),
	/**
	 * 无使用效果
	 */
	noEffect(0),
	/**
	 * 礼包
	 */
	giftBag(1),
	/**
	 * 持续性效果
	 */
	lastingEffect(2),
	/**
	 * 君主BUFF
	 */
	characterBuff(3),
	/**
	 * 木材
	 */
	wood(4),
	/**
	 * 石料
	 */
	stons(5),
	/**
	 * 粮食
	 */
	food(6),
	/**
	 * 铁矿
	 */
	ironore(7),
	/**
	 * 铜币
	 */
	money(8),
	/**
	 * 人口
	 */
	population(9),
	/**
	 * 膏药
	 */
	plaster(10),
	/**
	 * 随机迁城
	 */
	randomMoveCity(11),
	/**
	 * 民心
	 */
	popular(12),
	/**
	 * 俸禄
	 */
	woods(13),
	/**
	 * 士兵
	 */
	soldier(14),
	/**
	 * 训练士兵
	 */
	trainSolider(16),
	/**
	 * 武将经验
	 */
	heroExp(17),
	/**
	 * 活动战场BUFF
	 */
	buffForBattleOrActivity(18),
	/**
	 * 金锭
	 */
	cash(19),
	/**
	 * 点卷
	 */
	ticket(20)
	;



	private final int value;
	public int getValue() {
		return value;
	}
	private ItemType(int value) {
		this.value = value;
	}

}
