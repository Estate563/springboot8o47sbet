package com.dao;

import com.entity.DiscussshujufenxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussshujufenxiVO;
import com.entity.view.DiscussshujufenxiView;


/**
 * 数据分析评论表
 * 
 * @author 
 * @email 
 * @date 2025-02-19 14:59:19
 */
public interface DiscussshujufenxiDao extends BaseMapper<DiscussshujufenxiEntity> {
	
	List<DiscussshujufenxiVO> selectListVO(@Param("ew") Wrapper<DiscussshujufenxiEntity> wrapper);
	
	DiscussshujufenxiVO selectVO(@Param("ew") Wrapper<DiscussshujufenxiEntity> wrapper);
	
	List<DiscussshujufenxiView> selectListView(@Param("ew") Wrapper<DiscussshujufenxiEntity> wrapper);

	List<DiscussshujufenxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussshujufenxiEntity> wrapper);

	
	DiscussshujufenxiView selectView(@Param("ew") Wrapper<DiscussshujufenxiEntity> wrapper);
	

}
