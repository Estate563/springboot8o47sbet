package com.entity.model;

import com.entity.ShujufenxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 数据分析
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
public class ShujufenxiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 封面
	 */
	
	private String fengmian;
		
	/**
	 * 蛋白质
	 */
	
	private Double danbaizhi;
		
	/**
	 * 碳水化合物
	 */
	
	private Double tanshuihuahewu;
		
	/**
	 * 脂肪
	 */
	
	private Double zhifang;
		
	/**
	 * 维生素
	 */
	
	private Double weishengsu;
		
	/**
	 * 营养分析
	 */
	
	private String yingyangfenxi;
		
	/**
	 * 营养建议
	 */
	
	private String yingyangjianyi;
		
	/**
	 * 分析时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fenxishijian;
		
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
	 * 评分
	 */
	
	private Double totalscore;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
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
	 * 设置：蛋白质
	 */
	 
	public void setDanbaizhi(Double danbaizhi) {
		this.danbaizhi = danbaizhi;
	}
	
	/**
	 * 获取：蛋白质
	 */
	public Double getDanbaizhi() {
		return danbaizhi;
	}
				
	
	/**
	 * 设置：碳水化合物
	 */
	 
	public void setTanshuihuahewu(Double tanshuihuahewu) {
		this.tanshuihuahewu = tanshuihuahewu;
	}
	
	/**
	 * 获取：碳水化合物
	 */
	public Double getTanshuihuahewu() {
		return tanshuihuahewu;
	}
				
	
	/**
	 * 设置：脂肪
	 */
	 
	public void setZhifang(Double zhifang) {
		this.zhifang = zhifang;
	}
	
	/**
	 * 获取：脂肪
	 */
	public Double getZhifang() {
		return zhifang;
	}
				
	
	/**
	 * 设置：维生素
	 */
	 
	public void setWeishengsu(Double weishengsu) {
		this.weishengsu = weishengsu;
	}
	
	/**
	 * 获取：维生素
	 */
	public Double getWeishengsu() {
		return weishengsu;
	}
				
	
	/**
	 * 设置：营养分析
	 */
	 
	public void setYingyangfenxi(String yingyangfenxi) {
		this.yingyangfenxi = yingyangfenxi;
	}
	
	/**
	 * 获取：营养分析
	 */
	public String getYingyangfenxi() {
		return yingyangfenxi;
	}
				
	
	/**
	 * 设置：营养建议
	 */
	 
	public void setYingyangjianyi(String yingyangjianyi) {
		this.yingyangjianyi = yingyangjianyi;
	}
	
	/**
	 * 获取：营养建议
	 */
	public String getYingyangjianyi() {
		return yingyangjianyi;
	}
				
	
	/**
	 * 设置：分析时间
	 */
	 
	public void setFenxishijian(Date fenxishijian) {
		this.fenxishijian = fenxishijian;
	}
	
	/**
	 * 获取：分析时间
	 */
	public Date getFenxishijian() {
		return fenxishijian;
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
	 * 设置：评分
	 */
	 
	public void setTotalscore(Double totalscore) {
		this.totalscore = totalscore;
	}
	
	/**
	 * 获取：评分
	 */
	public Double getTotalscore() {
		return totalscore;
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
