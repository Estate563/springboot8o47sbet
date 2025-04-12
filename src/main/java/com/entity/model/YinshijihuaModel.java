package com.entity.model;

import com.entity.YinshijihuaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 饮食计划
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
public class YinshijihuaModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 封面
	 */
	
	private String fengmian;
		
	/**
	 * 早餐搭配
	 */
	
	private String zaocandapei;
		
	/**
	 * 午餐搭配
	 */
	
	private String wucandapei;
		
	/**
	 * 晚餐搭配
	 */
	
	private String wancandapei;
		
	/**
	 * 加餐搭配
	 */
	
	private String jiacandapei;
		
	/**
	 * 计划目标
	 */
	
	private String jihuamubiao;
		
	/**
	 * 计划日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date jihuariqi;
		
	/**
	 * 计划详情
	 */
	
	private String jihuaxiangqing;
		
	/**
	 * 用户账号
	 */
	
	private String yonghuzhanghao;
		
	/**
	 * 用户姓名
	 */
	
	private String yonghuxingming;
				
	
	/**
	 * 设置：封面
	 */
	 
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
				
	
	/**
	 * 设置：早餐搭配
	 */
	 
	public void setZaocandapei(String zaocandapei) {
		this.zaocandapei = zaocandapei;
	}
	
	/**
	 * 获取：早餐搭配
	 */
	public String getZaocandapei() {
		return zaocandapei;
	}
				
	
	/**
	 * 设置：午餐搭配
	 */
	 
	public void setWucandapei(String wucandapei) {
		this.wucandapei = wucandapei;
	}
	
	/**
	 * 获取：午餐搭配
	 */
	public String getWucandapei() {
		return wucandapei;
	}
				
	
	/**
	 * 设置：晚餐搭配
	 */
	 
	public void setWancandapei(String wancandapei) {
		this.wancandapei = wancandapei;
	}
	
	/**
	 * 获取：晚餐搭配
	 */
	public String getWancandapei() {
		return wancandapei;
	}
				
	
	/**
	 * 设置：加餐搭配
	 */
	 
	public void setJiacandapei(String jiacandapei) {
		this.jiacandapei = jiacandapei;
	}
	
	/**
	 * 获取：加餐搭配
	 */
	public String getJiacandapei() {
		return jiacandapei;
	}
				
	
	/**
	 * 设置：计划目标
	 */
	 
	public void setJihuamubiao(String jihuamubiao) {
		this.jihuamubiao = jihuamubiao;
	}
	
	/**
	 * 获取：计划目标
	 */
	public String getJihuamubiao() {
		return jihuamubiao;
	}
				
	
	/**
	 * 设置：计划日期
	 */
	 
	public void setJihuariqi(Date jihuariqi) {
		this.jihuariqi = jihuariqi;
	}
	
	/**
	 * 获取：计划日期
	 */
	public Date getJihuariqi() {
		return jihuariqi;
	}
				
	
	/**
	 * 设置：计划详情
	 */
	 
	public void setJihuaxiangqing(String jihuaxiangqing) {
		this.jihuaxiangqing = jihuaxiangqing;
	}
	
	/**
	 * 获取：计划详情
	 */
	public String getJihuaxiangqing() {
		return jihuaxiangqing;
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
