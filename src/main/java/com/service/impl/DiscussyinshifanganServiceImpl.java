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


import com.dao.DiscussyinshifanganDao;
import com.entity.DiscussyinshifanganEntity;
import com.service.DiscussyinshifanganService;
import com.entity.vo.DiscussyinshifanganVO;
import com.entity.view.DiscussyinshifanganView;

@Service("discussyinshifanganService")
public class DiscussyinshifanganServiceImpl extends ServiceImpl<DiscussyinshifanganDao, DiscussyinshifanganEntity> implements DiscussyinshifanganService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussyinshifanganEntity> page = this.selectPage(
                new Query<DiscussyinshifanganEntity>(params).getPage(),
                new EntityWrapper<DiscussyinshifanganEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussyinshifanganEntity> wrapper) {
		  Page<DiscussyinshifanganView> page =new Query<DiscussyinshifanganView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussyinshifanganVO> selectListVO(Wrapper<DiscussyinshifanganEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussyinshifanganVO selectVO(Wrapper<DiscussyinshifanganEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussyinshifanganView> selectListView(Wrapper<DiscussyinshifanganEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussyinshifanganView selectView(Wrapper<DiscussyinshifanganEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
