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


import com.dao.YinshijihuaDao;
import com.entity.YinshijihuaEntity;
import com.service.YinshijihuaService;
import com.entity.vo.YinshijihuaVO;
import com.entity.view.YinshijihuaView;

@Service("yinshijihuaService")
public class YinshijihuaServiceImpl extends ServiceImpl<YinshijihuaDao, YinshijihuaEntity> implements YinshijihuaService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YinshijihuaEntity> page = this.selectPage(
                new Query<YinshijihuaEntity>(params).getPage(),
                new EntityWrapper<YinshijihuaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YinshijihuaEntity> wrapper) {
		  Page<YinshijihuaView> page =new Query<YinshijihuaView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YinshijihuaVO> selectListVO(Wrapper<YinshijihuaEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YinshijihuaVO selectVO(Wrapper<YinshijihuaEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YinshijihuaView> selectListView(Wrapper<YinshijihuaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YinshijihuaView selectView(Wrapper<YinshijihuaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
