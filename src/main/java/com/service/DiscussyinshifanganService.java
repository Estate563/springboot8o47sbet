package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussyinshifanganEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussyinshifanganVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussyinshifanganView;


/**
 * 饮食方案评论表
 *
 * @author 
 * @email 
 * @date 2025-02-19 14:59:19
 */
public interface DiscussyinshifanganService extends IService<DiscussyinshifanganEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussyinshifanganVO> selectListVO(Wrapper<DiscussyinshifanganEntity> wrapper);
   	
   	DiscussyinshifanganVO selectVO(@Param("ew") Wrapper<DiscussyinshifanganEntity> wrapper);
   	
   	List<DiscussyinshifanganView> selectListView(Wrapper<DiscussyinshifanganEntity> wrapper);
   	
   	DiscussyinshifanganView selectView(@Param("ew") Wrapper<DiscussyinshifanganEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussyinshifanganEntity> wrapper);

   	

}

