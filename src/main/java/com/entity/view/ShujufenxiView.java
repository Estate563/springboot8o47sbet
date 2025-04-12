package com.entity.view;

import com.entity.ShujufenxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 数据分析
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
@TableName("shujufenxi")
public class ShujufenxiView  extends ShujufenxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShujufenxiView(){
	}
 
 	public ShujufenxiView(ShujufenxiEntity shujufenxiEntity){
 	try {
			BeanUtils.copyProperties(this, shujufenxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
