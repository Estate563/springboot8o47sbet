package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussshujufenxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussshujufenxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussshujufenxiView;


/**
 * 数据分析评论表
 *
 * @author 
 * @email 
 * @date 2025-02-19 14:59:19
 */
public interface DiscussshujufenxiService extends IService<DiscussshujufenxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussshujufenxiVO> selectListVO(Wrapper<DiscussshujufenxiEntity> wrapper);
   	
   	DiscussshujufenxiVO selectVO(@Param("ew") Wrapper<DiscussshujufenxiEntity> wrapper);
   	
   	List<DiscussshujufenxiView> selectListView(Wrapper<DiscussshujufenxiEntity> wrapper);
   	
   	DiscussshujufenxiView selectView(@Param("ew") Wrapper<DiscussshujufenxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussshujufenxiEntity> wrapper);

   	

}

