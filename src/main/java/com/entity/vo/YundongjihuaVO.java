package com.entity.vo;

import com.entity.YundongjihuaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 运动计划
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
public class YundongjihuaVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
