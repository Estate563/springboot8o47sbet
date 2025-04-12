package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YundongjihuaEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YundongjihuaVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YundongjihuaView;


/**
 * 运动计划
 *
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
public interface YundongjihuaService extends IService<YundongjihuaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YundongjihuaVO> selectListVO(Wrapper<YundongjihuaEntity> wrapper);
   	
   	YundongjihuaVO selectVO(@Param("ew") Wrapper<YundongjihuaEntity> wrapper);
   	
   	List<YundongjihuaView> selectListView(Wrapper<YundongjihuaEntity> wrapper);
   	
   	YundongjihuaView selectView(@Param("ew") Wrapper<YundongjihuaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YundongjihuaEntity> wrapper);

   	

}

