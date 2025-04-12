package com.entity.vo;

import com.entity.JiankangshujuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 健康数据
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
public class JiankangshujuVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 用户姓名
	 */
	
	private String yonghuxingming;
		
	/**
	 * 年龄
	 */
	
	private String nianling;
		
	/**
	 * 身高(cm)
	 */
	
	private Double shengao;
		
	/**
	 * 体重(kg)
	 */
	
	private Double tizhong;
		
	/**
	 * 心率(次/分钟)
	 */
	
	private Double xinlv;
		
	/**
	 * 血压(mmHg)
	 */
	
	private Double xueya;
		
	/**
	 * 血糖(mmHg)
	 */
	
	private Double xuetang;
		
	/**
	 * 运动量
	 */
	
	private String yundongliang;
		
	/**
	 * 饮食量
	 */
	
	private String yinshiliang;
				
	
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
				
	
	/**
	 * 设置：年龄
	 */
	 
	public void setNianling(String nianling) {
		this.nianling = nianling;
	}
	
	/**
	 * 获取：年龄
	 */
	public String getNianling() {
		return nianling;
	}
				
	
	/**
	 * 设置：身高(cm)
	 */
	 
	public void setShengao(Double shengao) {
		this.shengao = shengao;
	}
	
	/**
	 * 获取：身高(cm)
	 */
	public Double getShengao() {
		return shengao;
	}
				
	
	/**
	 * 设置：体重(kg)
	 */
	 
	public void setTizhong(Double tizhong) {
		this.tizhong = tizhong;
	}
	
	/**
	 * 获取：体重(kg)
	 */
	public Double getTizhong() {
		return tizhong;
	}
				
	
	/**
	 * 设置：心率(次/分钟)
	 */
	 
	public void setXinlv(Double xinlv) {
		this.xinlv = xinlv;
	}
	
	/**
	 * 获取：心率(次/分钟)
	 */
	public Double getXinlv() {
		return xinlv;
	}
				
	
	/**
	 * 设置：血压(mmHg)
	 */
	 
	public void setXueya(Double xueya) {
		this.xueya = xueya;
	}
	
	/**
	 * 获取：血压(mmHg)
	 */
	public Double getXueya() {
		return xueya;
	}
				
	
	/**
	 * 设置：血糖(mmHg)
	 */
	 
	public void setXuetang(Double xuetang) {
		this.xuetang = xuetang;
	}
	
	/**
	 * 获取：血糖(mmHg)
	 */
	public Double getXuetang() {
		return xuetang;
	}
				
	
	/**
	 * 设置：运动量
	 */
	 
	public void setYundongliang(String yundongliang) {
		this.yundongliang = yundongliang;
	}
	
	/**
	 * 获取：运动量
	 */
	public String getYundongliang() {
		return yundongliang;
	}
				
	
	/**
	 * 设置：饮食量
	 */
	 
	public void setYinshiliang(String yinshiliang) {
		this.yinshiliang = yinshiliang;
	}
	
	/**
	 * 获取：饮食量
	 */
	public String getYinshiliang() {
		return yinshiliang;
	}
			
}
