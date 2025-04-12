import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Forum from '../pages/forum/list'
import ForumAdd from '../pages/forum/add'
import ForumDetail from '../pages/forum/detail'
import MyForumList from '../pages/forum/myForumList'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import yinshifanganList from '../pages/yinshifangan/list'
import yinshifanganDetail from '../pages/yinshifangan/detail'
import yinshifanganAdd from '../pages/yinshifangan/add'
import shujufenxiList from '../pages/shujufenxi/list'
import shujufenxiDetail from '../pages/shujufenxi/detail'
import shujufenxiAdd from '../pages/shujufenxi/add'
import jiankangshujuList from '../pages/jiankangshuju/list'
import jiankangshujuDetail from '../pages/jiankangshuju/detail'
import jiankangshujuAdd from '../pages/jiankangshuju/add'
import yinshijihuaList from '../pages/yinshijihua/list'
import yinshijihuaDetail from '../pages/yinshijihua/detail'
import yinshijihuaAdd from '../pages/yinshijihua/add'
import yundongjihuaList from '../pages/yundongjihua/list'
import yundongjihuaDetail from '../pages/yundongjihua/detail'
import yundongjihuaAdd from '../pages/yundongjihua/add'
import forumtypeList from '../pages/forumtype/list'
import forumtypeDetail from '../pages/forumtype/detail'
import forumtypeAdd from '../pages/forumtype/add'
import forumreportList from '../pages/forumreport/list'
import forumreportDetail from '../pages/forumreport/detail'
import forumreportAdd from '../pages/forumreport/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import popupremindList from '../pages/popupremind/list'
import popupremindDetail from '../pages/popupremind/detail'
import popupremindAdd from '../pages/popupremind/add'
import discussyinshifanganList from '../pages/discussyinshifangan/list'
import discussyinshifanganDetail from '../pages/discussyinshifangan/detail'
import discussyinshifanganAdd from '../pages/discussyinshifangan/add'
import discussshujufenxiList from '../pages/discussshujufenxi/list'
import discussshujufenxiDetail from '../pages/discussshujufenxi/detail'
import discussshujufenxiAdd from '../pages/discussshujufenxi/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'forum',
					component: Forum
				},
				{
					path: 'forumAdd',
					component: ForumAdd
				},
				{
					path: 'forumDetail',
					component: ForumDetail
				},
				{
					path: 'myForumList',
					component: MyForumList
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'yinshifangan',
					component: yinshifanganList
				},
				{
					path: 'yinshifanganDetail',
					component: yinshifanganDetail
				},
				{
					path: 'yinshifanganAdd',
					component: yinshifanganAdd
				},
				{
					path: 'shujufenxi',
					component: shujufenxiList
				},
				{
					path: 'shujufenxiDetail',
					component: shujufenxiDetail
				},
				{
					path: 'shujufenxiAdd',
					component: shujufenxiAdd
				},
				{
					path: 'jiankangshuju',
					component: jiankangshujuList
				},
				{
					path: 'jiankangshujuDetail',
					component: jiankangshujuDetail
				},
				{
					path: 'jiankangshujuAdd',
					component: jiankangshujuAdd
				},
				{
					path: 'yinshijihua',
					component: yinshijihuaList
				},
				{
					path: 'yinshijihuaDetail',
					component: yinshijihuaDetail
				},
				{
					path: 'yinshijihuaAdd',
					component: yinshijihuaAdd
				},
				{
					path: 'yundongjihua',
					component: yundongjihuaList
				},
				{
					path: 'yundongjihuaDetail',
					component: yundongjihuaDetail
				},
				{
					path: 'yundongjihuaAdd',
					component: yundongjihuaAdd
				},
				{
					path: 'forumtype',
					component: forumtypeList
				},
				{
					path: 'forumtypeDetail',
					component: forumtypeDetail
				},
				{
					path: 'forumtypeAdd',
					component: forumtypeAdd
				},
				{
					path: 'forumreport',
					component: forumreportList
				},
				{
					path: 'forumreportDetail',
					component: forumreportDetail
				},
				{
					path: 'forumreportAdd',
					component: forumreportAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'popupremind',
					component: popupremindList
				},
				{
					path: 'popupremindDetail',
					component: popupremindDetail
				},
				{
					path: 'popupremindAdd',
					component: popupremindAdd
				},
				{
					path: 'discussyinshifangan',
					component: discussyinshifanganList
				},
				{
					path: 'discussyinshifanganDetail',
					component: discussyinshifanganDetail
				},
				{
					path: 'discussyinshifanganAdd',
					component: discussyinshifanganAdd
				},
				{
					path: 'discussshujufenxi',
					component: discussshujufenxiList
				},
				{
					path: 'discussshujufenxiDetail',
					component: discussshujufenxiDetail
				},
				{
					path: 'discussshujufenxiAdd',
					component: discussshujufenxiAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
