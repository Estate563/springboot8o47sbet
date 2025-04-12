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

import com.entity.DiscussshujufenxiEntity;
import com.entity.view.DiscussshujufenxiView;

import com.service.DiscussshujufenxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 数据分析评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-19 14:59:19
 */
@RestController
@RequestMapping("/discussshujufenxi")
public class DiscussshujufenxiController {
    @Autowired
    private DiscussshujufenxiService discussshujufenxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussshujufenxiEntity discussshujufenxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussshujufenxiEntity> ew = new EntityWrapper<DiscussshujufenxiEntity>();


        //查询结果
		PageUtils page = discussshujufenxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshujufenxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussshujufenxiEntity discussshujufenxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussshujufenxiEntity> ew = new EntityWrapper<DiscussshujufenxiEntity>();

        //查询结果
		PageUtils page = discussshujufenxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshujufenxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussshujufenxiEntity discussshujufenxi){
       	EntityWrapper<DiscussshujufenxiEntity> ew = new EntityWrapper<DiscussshujufenxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussshujufenxi, "discussshujufenxi")); 
        return R.ok().put("data", discussshujufenxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussshujufenxiEntity discussshujufenxi){
        EntityWrapper< DiscussshujufenxiEntity> ew = new EntityWrapper< DiscussshujufenxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussshujufenxi, "discussshujufenxi")); 
		DiscussshujufenxiView discussshujufenxiView =  discussshujufenxiService.selectView(ew);
		return R.ok("查询数据分析评论表成功").put("data", discussshujufenxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussshujufenxiEntity discussshujufenxi = discussshujufenxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussshujufenxi,deSens);
        return R.ok().put("data", discussshujufenxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussshujufenxiEntity discussshujufenxi = discussshujufenxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussshujufenxi,deSens);
        return R.ok().put("data", discussshujufenxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussshujufenxiEntity discussshujufenxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussshujufenxi);
        discussshujufenxiService.insert(discussshujufenxi);
        return R.ok().put("data",discussshujufenxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussshujufenxiEntity discussshujufenxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussshujufenxi);
        discussshujufenxiService.insert(discussshujufenxi);
        return R.ok().put("data",discussshujufenxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussshujufenxiEntity discussshujufenxi = discussshujufenxiService.selectOne(new EntityWrapper<DiscussshujufenxiEntity>().eq("", username));
        return R.ok().put("data", discussshujufenxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussshujufenxiEntity discussshujufenxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussshujufenxi);
        //全部更新
        discussshujufenxiService.updateById(discussshujufenxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussshujufenxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussshujufenxiEntity discussshujufenxi, HttpServletRequest request,String pre){
        EntityWrapper<DiscussshujufenxiEntity> ew = new EntityWrapper<DiscussshujufenxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = discussshujufenxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshujufenxi), params), params));
        return R.ok().put("data", page);
    }








}
