package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YinshifanganEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YinshifanganVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YinshifanganView;


/**
 * 饮食方案
 *
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
public interface YinshifanganService extends IService<YinshifanganEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YinshifanganVO> selectListVO(Wrapper<YinshifanganEntity> wrapper);
   	
   	YinshifanganVO selectVO(@Param("ew") Wrapper<YinshifanganEntity> wrapper);
   	
   	List<YinshifanganView> selectListView(Wrapper<YinshifanganEntity> wrapper);
   	
   	YinshifanganView selectView(@Param("ew") Wrapper<YinshifanganEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YinshifanganEntity> wrapper);

   	

}

