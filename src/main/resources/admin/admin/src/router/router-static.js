import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import discussyinshifangan from '@/views/modules/discussyinshifangan/list'
	import yinshifangan from '@/views/modules/yinshifangan/list'
	import jiankangshuju from '@/views/modules/jiankangshuju/list'
	import shujufenxi from '@/views/modules/shujufenxi/list'
	import forum from '@/views/modules/forum/list'
	import forumtype from '@/views/modules/forumtype/list'
	import popupremind from '@/views/modules/popupremind/list'
	import yonghu from '@/views/modules/yonghu/list'
	import forumreport from '@/views/modules/forumreport/list'
	import discussshujufenxi from '@/views/modules/discussshujufenxi/list'
	import messages from '@/views/modules/messages/list'
	import yinshijihua from '@/views/modules/yinshijihua/list'
	import config from '@/views/modules/config/list'
	import yundongjihua from '@/views/modules/yundongjihua/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '公告资讯',
		component: news
	}
	,{
		path: '/discussyinshifangan',
		name: '饮食方案评论',
		component: discussyinshifangan
	}
	,{
		path: '/yinshifangan',
		name: '饮食方案',
		component: yinshifangan
	}
	,{
		path: '/jiankangshuju',
		name: '健康数据',
		component: jiankangshuju
	}
	,{
		path: '/shujufenxi',
		name: '数据分析',
		component: shujufenxi
	}
	,{
		path: '/forum',
		name: '健康圈',
		component: forum
	}
	,{
		path: '/forumtype',
		name: '论坛分类',
		component: forumtype
	}
	,{
		path: '/popupremind',
		name: '弹窗提醒',
		component: popupremind
	}
	,{
		path: '/yonghu',
		name: '用户',
		component: yonghu
	}
	,{
		path: '/forumreport',
		name: '举报记录',
		component: forumreport
	}
	,{
		path: '/discussshujufenxi',
		name: '数据分析评论',
		component: discussshujufenxi
	}
	,{
		path: '/messages',
		name: '留言互动',
		component: messages
	}
	,{
		path: '/yinshijihua',
		name: '饮食计划',
		component: yinshijihua
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/yundongjihua',
		name: '运动计划',
		component: yundongjihua
	}
	,{
		path: '/newstype',
		name: '公告资讯分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
