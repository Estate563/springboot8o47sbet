package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.YinshijihuaEntity;
import com.entity.view.YinshijihuaView;

import com.service.YinshijihuaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 饮食计划
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-19 14:59:18
 */
@RestController
@RequestMapping("/yinshijihua")
public class YinshijihuaController {
    @Autowired
    private YinshijihuaService yinshijihuaService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YinshijihuaEntity yinshijihua,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date jihuariqistart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date jihuariqiend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			yinshijihua.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<YinshijihuaEntity> ew = new EntityWrapper<YinshijihuaEntity>();
        if(jihuariqistart!=null) ew.ge("jihuariqi", jihuariqistart);
        if(jihuariqiend!=null) ew.le("jihuariqi", jihuariqiend);


        //查询结果
		PageUtils page = yinshijihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yinshijihua), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YinshijihuaEntity yinshijihua, 
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date jihuariqistart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date jihuariqiend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YinshijihuaEntity> ew = new EntityWrapper<YinshijihuaEntity>();
        if(jihuariqistart!=null) ew.ge("jihuariqi", jihuariqistart);
        if(jihuariqiend!=null) ew.le("jihuariqi", jihuariqiend);

        //查询结果
		PageUtils page = yinshijihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yinshijihua), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YinshijihuaEntity yinshijihua){
       	EntityWrapper<YinshijihuaEntity> ew = new EntityWrapper<YinshijihuaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yinshijihua, "yinshijihua")); 
        return R.ok().put("data", yinshijihuaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YinshijihuaEntity yinshijihua){
        EntityWrapper< YinshijihuaEntity> ew = new EntityWrapper< YinshijihuaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yinshijihua, "yinshijihua")); 
		YinshijihuaView yinshijihuaView =  yinshijihuaService.selectView(ew);
		return R.ok("查询饮食计划成功").put("data", yinshijihuaView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YinshijihuaEntity yinshijihua = yinshijihuaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yinshijihua,deSens);
        return R.ok().put("data", yinshijihua);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YinshijihuaEntity yinshijihua = yinshijihuaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yinshijihua,deSens);
        return R.ok().put("data", yinshijihua);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YinshijihuaEntity yinshijihua, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yinshijihua);
        yinshijihuaService.insert(yinshijihua);
        return R.ok().put("data",yinshijihua.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YinshijihuaEntity yinshijihua, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yinshijihua);
        yinshijihuaService.insert(yinshijihua);
        return R.ok().put("data",yinshijihua.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YinshijihuaEntity yinshijihua, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yinshijihua);
        //全部更新
        yinshijihuaService.updateById(yinshijihua);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yinshijihuaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
