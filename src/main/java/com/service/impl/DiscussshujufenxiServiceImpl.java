package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscussshujufenxiDao;
import com.entity.DiscussshujufenxiEntity;
import com.service.DiscussshujufenxiService;
import com.entity.vo.DiscussshujufenxiVO;
import com.entity.view.DiscussshujufenxiView;

@Service("discussshujufenxiService")
public class DiscussshujufenxiServiceImpl extends ServiceImpl<DiscussshujufenxiDao, DiscussshujufenxiEntity> implements DiscussshujufenxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussshujufenxiEntity> page = this.selectPage(
                new Query<DiscussshujufenxiEntity>(params).getPage(),
                new EntityWrapper<DiscussshujufenxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussshujufenxiEntity> wrapper) {
		  Page<DiscussshujufenxiView> page =new Query<DiscussshujufenxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussshujufenxiVO> selectListVO(Wrapper<DiscussshujufenxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussshujufenxiVO selectVO(Wrapper<DiscussshujufenxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussshujufenxiView> selectListView(Wrapper<DiscussshujufenxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussshujufenxiView selectView(Wrapper<DiscussshujufenxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
