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
 * 运动计划
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
@TableName("yundongjihua")
public class YundongjihuaEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public YundongjihuaEntity() {
		
	}
	
	public YundongjihuaEntity(T t) {
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
	 * 运动名称
	 */
					
	private String yundongmingcheng;
	
	/**
	 * 运动类型
	 */
					
	private String yundongleixing;
	
	/**
	 * 图片
	 */
					
	private String tupian;
	
	/**
	 * 视频
	 */
					
	private String shipin;
	
	/**
	 * 运动强度
	 */
					
	private String yundongqiangdu;
	
	/**
	 * 运动周期
	 */
					
	private String yundongzhouqi;
	
	/**
	 * 运动备注
	 */
					
	private String yundongbeizhu;
	
	/**
	 * 运动目标
	 */
					
	private String yundongmubiao;
	
	/**
	 * 用户账号
	 */
					
	private String yonghuzhanghao;
	
	/**
	 * 用户姓名
	 */
					
	private String yonghuxingming;
	
	
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
	 * 设置：运动名称
	 */
	public void setYundongmingcheng(String yundongmingcheng) {
		this.yundongmingcheng = yundongmingcheng;
	}
	/**
	 * 获取：运动名称
	 */
	public String getYundongmingcheng() {
		return yundongmingcheng;
	}
	/**
	 * 设置：运动类型
	 */
	public void setYundongleixing(String yundongleixing) {
		this.yundongleixing = yundongleixing;
	}
	/**
	 * 获取：运动类型
	 */
	public String getYundongleixing() {
		return yundongleixing;
	}
	/**
	 * 设置：图片
	 */
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
	/**
	 * 设置：视频
	 */
	public void setShipin(String shipin) {
		this.shipin = shipin;
	}
	/**
	 * 获取：视频
	 */
	public String getShipin() {
		return shipin;
	}
	/**
	 * 设置：运动强度
	 */
	public void setYundongqiangdu(String yundongqiangdu) {
		this.yundongqiangdu = yundongqiangdu;
	}
	/**
	 * 获取：运动强度
	 */
	public String getYundongqiangdu() {
		return yundongqiangdu;
	}
	/**
	 * 设置：运动周期
	 */
	public void setYundongzhouqi(String yundongzhouqi) {
		this.yundongzhouqi = yundongzhouqi;
	}
	/**
	 * 获取：运动周期
	 */
	public String getYundongzhouqi() {
		return yundongzhouqi;
	}
	/**
	 * 设置：运动备注
	 */
	public void setYundongbeizhu(String yundongbeizhu) {
		this.yundongbeizhu = yundongbeizhu;
	}
	/**
	 * 获取：运动备注
	 */
	public String getYundongbeizhu() {
		return yundongbeizhu;
	}
	/**
	 * 设置：运动目标
	 */
	public void setYundongmubiao(String yundongmubiao) {
		this.yundongmubiao = yundongmubiao;
	}
	/**
	 * 获取：运动目标
	 */
	public String getYundongmubiao() {
		return yundongmubiao;
	}
	/**
	 * 设置：用户账号
	 */
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getYonghuxingming() {
		return yonghuxingming;
	}

}
