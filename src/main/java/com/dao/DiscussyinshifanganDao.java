package com.dao;

import com.entity.DiscussyinshifanganEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussyinshifanganVO;
import com.entity.view.DiscussyinshifanganView;


/**
 * 饮食方案评论表
 * 
 * @author 
 * @email 
 * @date 2025-02-19 14:59:19
 */
public interface DiscussyinshifanganDao extends BaseMapper<DiscussyinshifanganEntity> {
	
	List<DiscussyinshifanganVO> selectListVO(@Param("ew") Wrapper<DiscussyinshifanganEntity> wrapper);
	
	DiscussyinshifanganVO selectVO(@Param("ew") Wrapper<DiscussyinshifanganEntity> wrapper);
	
	List<DiscussyinshifanganView> selectListView(@Param("ew") Wrapper<DiscussyinshifanganEntity> wrapper);

	List<DiscussyinshifanganView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussyinshifanganEntity> wrapper);

	
	DiscussyinshifanganView selectView(@Param("ew") Wrapper<DiscussyinshifanganEntity> wrapper);
	

}
