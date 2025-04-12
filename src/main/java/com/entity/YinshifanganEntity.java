package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 饮食方案
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
@TableName("yinshifangan")
public class YinshifanganEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public YinshifanganEntity() {
		
	}
	
	public YinshifanganEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 食谱名称
	 */
					
	private String shipumingcheng;
	
	/**
	 * 食谱分类
	 */
					
	private String shipufenlei;
	
	/**
	 * 食谱照片
	 */
					
	private String shipuzhaopian;
	
	/**
	 * 食谱用料
	 */
					
	private String shipuyongliao;
	
	/**
	 * 卡路里含量
	 */
					
	private String kalulihanliang;
	
	/**
	 * 分享时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date fenxiangshijian;
	
	/**
	 * 营养成分
	 */
					
	private String yingyangchengfen;
	
	/**
	 * 菜谱做法
	 */
					
	private String caipuzuofa;
	
	/**
	 * 食谱视频
	 */
					
	private String shipushipin;
	
	/**
	 * 食谱详情
	 */
					
	private String shipuxiangqing;
	
	/**
	 * 赞
	 */
					
	private Integer thumbsupnum;
	
	/**
	 * 踩
	 */
					
	private Integer crazilynum;
	
	/**
	 * 最近点击时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date clicktime;
	
	/**
	 * 评论数
	 */
					
	private Integer discussnum;
	
	/**
	 * 收藏数
	 */
					
	private Integer storeupnum;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：食谱名称
	 */
	public void setShipumingcheng(String shipumingcheng) {
		this.shipumingcheng = shipumingcheng;
	}
	/**
	 * 获取：食谱名称
	 */
	public String getShipumingcheng() {
		return shipumingcheng;
	}
	/**
	 * 设置：食谱分类
	 */
	public void setShipufenlei(String shipufenlei) {
		this.shipufenlei = shipufenlei;
	}
	/**
	 * 获取：食谱分类
	 */
	public String getShipufenlei() {
		return shipufenlei;
	}
	/**
	 * 设置：食谱照片
	 */
	public void setShipuzhaopian(String shipuzhaopian) {
		this.shipuzhaopian = shipuzhaopian;
	}
	/**
	 * 获取：食谱照片
	 */
	public String getShipuzhaopian() {
		return shipuzhaopian;
	}
	/**
	 * 设置：食谱用料
	 */
	public void setShipuyongliao(String shipuyongliao) {
		this.shipuyongliao = shipuyongliao;
	}
	/**
	 * 获取：食谱用料
	 */
	public String getShipuyongliao() {
		return shipuyongliao;
	}
	/**
	 * 设置：卡路里含量
	 */
	public void setKalulihanliang(String kalulihanliang) {
		this.kalulihanliang = kalulihanliang;
	}
	/**
	 * 获取：卡路里含量
	 */
	public String getKalulihanliang() {
		return kalulihanliang;
	}
	/**
	 * 设置：分享时间
	 */
	public void setFenxiangshijian(Date fenxiangshijian) {
		this.fenxiangshijian = fenxiangshijian;
	}
	/**
	 * 获取：分享时间
	 */
	public Date getFenxiangshijian() {
		return fenxiangshijian;
	}
	/**
	 * 设置：营养成分
	 */
	public void setYingyangchengfen(String yingyangchengfen) {
		this.yingyangchengfen = yingyangchengfen;
	}
	/**
	 * 获取：营养成分
	 */
	public String getYingyangchengfen() {
		return yingyangchengfen;
	}
	/**
	 * 设置：菜谱做法
	 */
	public void setCaipuzuofa(String caipuzuofa) {
		this.caipuzuofa = caipuzuofa;
	}
	/**
	 * 获取：菜谱做法
	 */
	public String getCaipuzuofa() {
		return caipuzuofa;
	}
	/**
	 * 设置：食谱视频
	 */
	public void setShipushipin(String shipushipin) {
		this.shipushipin = shipushipin;
	}
	/**
	 * 获取：食谱视频
	 */
	public String getShipushipin() {
		return shipushipin;
	}
	/**
	 * 设置：食谱详情
	 */
	public void setShipuxiangqing(String shipuxiangqing) {
		this.shipuxiangqing = shipuxiangqing;
	}
	/**
	 * 获取：食谱详情
	 */
	public String getShipuxiangqing() {
		return shipuxiangqing;
	}
	/**
	 * 设置：赞
	 */
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
	/**
	 * 设置：踩
	 */
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
	/**
	 * 设置：最近点击时间
	 */
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
	/**
	 * 设置：评论数
	 */
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
	/**
	 * 设置：收藏数
	 */
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}

}
