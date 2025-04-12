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


import com.dao.YinshifanganDao;
import com.entity.YinshifanganEntity;
import com.service.YinshifanganService;
import com.entity.vo.YinshifanganVO;
import com.entity.view.YinshifanganView;

@Service("yinshifanganService")
public class YinshifanganServiceImpl extends ServiceImpl<YinshifanganDao, YinshifanganEntity> implements YinshifanganService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YinshifanganEntity> page = this.selectPage(
                new Query<YinshifanganEntity>(params).getPage(),
                new EntityWrapper<YinshifanganEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YinshifanganEntity> wrapper) {
		  Page<YinshifanganView> page =new Query<YinshifanganView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YinshifanganVO> selectListVO(Wrapper<YinshifanganEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YinshifanganVO selectVO(Wrapper<YinshifanganEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YinshifanganView> selectListView(Wrapper<YinshifanganEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YinshifanganView selectView(Wrapper<YinshifanganEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
