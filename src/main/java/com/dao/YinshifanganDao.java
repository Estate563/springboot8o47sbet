package com.dao;

import com.entity.YinshifanganEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YinshifanganVO;
import com.entity.view.YinshifanganView;


/**
 * 饮食方案
 * 
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
public interface YinshifanganDao extends BaseMapper<YinshifanganEntity> {
	
	List<YinshifanganVO> selectListVO(@Param("ew") Wrapper<YinshifanganEntity> wrapper);
	
	YinshifanganVO selectVO(@Param("ew") Wrapper<YinshifanganEntity> wrapper);
	
	List<YinshifanganView> selectListView(@Param("ew") Wrapper<YinshifanganEntity> wrapper);

	List<YinshifanganView> selectListView(Pagination page,@Param("ew") Wrapper<YinshifanganEntity> wrapper);

	
	YinshifanganView selectView(@Param("ew") Wrapper<YinshifanganEntity> wrapper);
	

}
