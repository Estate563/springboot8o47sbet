<template>
	<div class="main-containers">
		<div class="body-containers">
			<div class="top-container">
				<!-- info -->
				<div class="top_title">
					<span @click="goMenu('/index/home')">基于SpringBoot+Vue个人健康管理系统的设计与实现</span>
				</div>
			

				<img class="top_avatar1" v-if="headportrait&&Token" :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
				<div class="top_nickname1" v-if="Token">{{username}}</div>
				<el-button v-if="Token && notAdmin" class="btn-user" @click="goMenu('/index/center')">
					<span class="icon iconfont icon-wuliu8"></span>
					个人中心
				</el-button>
				<el-button v-if="!Token" class="btn-login" @click="toLogin">
					<span class="icon iconfont icon-wuliu8"></span>
					登录
				</el-button>
				<el-button v-if="Token" class="btn-register" @click="logout">
					<span class="icon iconfont icon-wuliu8"></span>
					退出
				</el-button>
			</div>


			<div class="menu-preview">
				<el-scrollbar wrap-class="scrollbar-wrapper-horizontal">
					<el-menu class="el-menu-horizontal-demo" :default-active="activeMenu" :unique-opened="true" mode="horizontal" :router="true" @select="handleSelect">
						<div class="userinfo">
						  <el-image v-if="headportrait&&Token" :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')" fit="cover"></el-image>
						  <div class="nickname">{{username}}</div>
						</div>
						<el-menu-item class="home" index="/index/home" @click.native="goMenu('/index/home')">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<span>系统首页</span>
						</el-menu-item>
						<el-menu-item class="item" v-for="(menu, index) in menuList" :index="menu.url" :key="index" @click.native="goMenu(menu.url)">
							<i class="icons" :class="iconArr[index]"></i>
							<span>{{menu.name}}</span>
						</el-menu-item>
						<el-menu-item class="user" index="/index/center" v-if="Token && notAdmin" @click.native="goMenu('/index/center')">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<span>个人中心</span>
						</el-menu-item>
					</el-menu>
				</el-scrollbar>
			</div>

			<div class="banner-preview" v-if="carouselChange()">
				<el-carousel trigger="click" indicator-position="inside" arrow="always" type="default" direction="horizontal" height="460px" :autoplay="false" :interval="3000" :loop="true">
					<el-carousel-item v-for="item in carouselList" :key="item.id">
						<el-image v-if="preHttp(item.value)" @click="carouselClick(item.url)" :src="item.value" fit="cover"></el-image>
						<el-image v-else @click="carouselClick(item.url)" :src="baseUrl + item.value" fit="cover"></el-image>
						<div class="banner-hidden">
						</div>
					</el-carousel-item>
				</el-carousel>
			</div>
			<router-view id="scrollView"></router-view>
			
			<div class="bottom-preview">
				<div class="footer"><div v-html="bottomContent"></div></div>
			</div>
		</div>
		
		<el-dialog :title="remindForm.title" :append-to-body="true" :visible.sync="remindVisibie" width="60%">
			<div class="ql-snow ql-editor" v-html="remindForm.content"></div>
		</el-dialog>
	</div>
</template>

<script>
	import Vue from 'vue'
	import Swiper from "swiper";
	import axios from 'axios'
export default {
	data() {
		return {
			activeIndex: '0',
			baseUrl: '',
			carouselList: [],
			carouselForm: {
				inHome: true,
				inOther: true,
			},
			menuList: [],
			headportrait: localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'',
			Token: localStorage.getItem('frontToken'),
			username: localStorage.getItem('username'),
			notAdmin: localStorage.getItem('frontSessionTable')!='"users"',
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],
			bottomContent: '',
			remindVisibie: false,
			remindForm: {},
			timesAll: null,
			timesOnce: null,
			showType4: -1,
		}
	},
	async created() {
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		this.getCarousel();
		if(localStorage.getItem('frontToken') && localStorage.getItem('frontToken')!=null) {
			this.getSession()
			this.getRecommendAll()
			this.getRecommendOnce()
		}
	},
	mounted() {
		this.activeIndex = localStorage.getItem('keyPath') || '0';



	},
	destroyed() {
		clearTimeout(this.timesAll)
		clearTimeout(this.timesOnce)
	},
	computed: {
		activeMenu() {
			const route = this.$route
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		},
	},
	watch: {
		$route(newValue) {
			let that = this
			let url = window.location.href
			let arr = url.split('#')
			for (let x in this.menuList) {
				if (newValue.path == this.menuList[x].url) {
					this.activeIndex = x
				}
			}
			this.Token = localStorage.getItem('frontToken')
			if(arr[1]!='/index/home'){
				var element = document.getElementById('scrollView');
				var distance = element.offsetTop;
				window.scrollTo( 0, distance )
			}else{
				window.scrollTo( 0, 0 )
			}
		},
		headportrait(){
			this.$forceUpdate()
		},
	},
	methods: {
		preHttp(str) {
			return str && str.substr(0,4)=='http';
		},
		compareDate(s1, s2) {
			return ((new Date(s1.replace(/-/g, "\/"))) > (new Date(s2.replace(/-/g, "\/"))));
		},
		async getRecommendAll() {
			let that = this
			this.$http.get('popupremind/list',{params: {type:'全局'}}).then(async res => {
				if (res.data && res.data.code == 0) {
					for (let x in res.data.data.list) {
						if (this.compareDate(this.getCurDateTime(), res.data.data.list[x]
							.remindtime)) {
							await this.$http.get('storeup/page',{params:{refid: res.data.data.list[x].id,tablename: 'popupremind',type: '61'}}).then(obj => {
								if (obj.data && obj.data.code == 0) {
									if (!obj.data.data.list.length) {
										res.data.data.list[x].content = res.data.data.list[x].content.replace(/img src/gi,"img style=\"width:100%;\" src");
										this.$notify({
											title: res.data.data.list[x].title,
											dangerouslyUseHTMLString: true,
											message: res.data.data.list[x].brief,
											duration: 0,
											position: 'bottom-right',
											onClick() {
												that.remindForm = res.data.data.list[x]
												that.remindVisibie = true
											}
										})
										this.$http.post('storeup/add',{
											userid: Number(localStorage.getItem('frontUserid')),
											refid: res.data.data.list[x].id,
											tablename: 'popupremind',
											name: res.data.data.list[x].title,
											type: '61',
										})
									}
								}
							})
						}
					}
					this.timesAll = setTimeout(() => {
						this.getRecommendAll()
					}, 10000)
				}
			})
		},
		async getRecommendOnce() {
			let that = this
			this.$http.get('popupremind/page',{params: {type:'个人'}}).then(async res => {
				if (res.data && res.data.code == 0) {
					for (let x in res.data.data.list) {
						if (this.compareDate(this.getCurDateTime(), res.data.data.list[x]
							.remindtime)) {
							await this.$http.get('storeup/page',{params:{refid: res.data.data.list[x].id,tablename: 'popupremind',type: '61'}}).then(obj => {
								if (obj.data && obj.data.code == 0) {
									if (!obj.data.data.list.length) {
										res.data.data.list[x].content = res.data.data.list[x].content.replace(/img src/gi,"img style=\"width:100%;\" src");
										this.$notify({
											title: res.data.data.list[x].title,
											dangerouslyUseHTMLString: true,
											message: res.data.data.list[x].brief,
											duration: 0,
											position: 'top-right',
											onClick() {
												that.remindForm = res.data.data.list[x]
												that.remindVisibie = true
											}
										})
										this.$http.post('storeup/add',{
											userid: Number(localStorage.getItem('frontUserid')),
											refid: res.data.data.list[x].id,
											tablename: 'popupremind',
											name: res.data.data.list[x].title,
											type: '61',
										})
									}
								}
							})
						}
					}
					this.timesOnce = setTimeout(() => {
						this.getRecommendOnce()
					}, 10000)
				}
			})
		},

		async getSession() {
			await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
				if (res.data.code == 0) {
					localStorage.setItem('sessionForm',JSON.stringify(res.data.data))
					localStorage.setItem('frontUserid', res.data.data.id);
					if(res.data.data.vip) {
						localStorage.setItem('vip', res.data.data.vip);
					}
					if(res.data.data.touxiang) {
						this.headportrait = res.data.data.touxiang
						localStorage.setItem('frontHeadportrait', res.data.data.touxiang);
					} else if(res.data.data.headportrait) {
						this.headportrait = res.data.data.headportrait
						localStorage.setItem('frontHeadportrait', res.data.data.headportrait);
					}
				}
			});
		},
		handleSelect(keyPath) {
			if (keyPath) {
				localStorage.setItem('keyPath', keyPath)
			}
		},
		toLogin() {
		  this.$router.push('/login');
		},
		logout() {
			localStorage.clear();
			Vue.http.headers.common['Token'] = "";
			this.$router.push('/index/home');
			this.activeIndex = '0'
			localStorage.setItem('keyPath', this.activeIndex)
			this.Token = ''
			this.$forceUpdate()
			this.$message({
				message: '登出成功',
				type: 'success',
				duration: 1000,
			});
		},
		getCarousel() {
			this.$http.get('config/list', {params: { page: 1, limit: 3 }}).then(res => {
				if (res.data.code == 0) {
					this.carouselList = res.data.data.list;
				}
			});
		},
		// 轮播图跳转
		carouselClick(url) {
			if (url) {
				if (url.indexOf('https') != -1) {
					window.open(url)
				} else {
					this.$router.push(url)
				}
			}
		},
		carouselChange(){
			let url = window.location.href
			let arr = url.split('#')
			return (this.carouselForm.inHome&&arr[1]=='/index/home')||(this.carouselForm.inOther&&arr[1]!='/index/home')
		},
		goBackend() {
			localStorage.setItem('Token', localStorage.getItem('frontToken'));
			localStorage.setItem('role', localStorage.getItem('frontRole'));
			localStorage.setItem('sessionTable', localStorage.getItem('frontSessionTable'));
			localStorage.setItem('headportrait', localStorage.getItem('frontHeadportrait'));
			localStorage.setItem('userid', localStorage.getItem('frontUserid'));
			window.location.href = `${this.$config.baseUrl}admin/dist/index.html`
			
		},
		menuShowClick4(index){
			this.showType4 = index
		},
		goMenu(path) {
			this.$router.push(path);
		},
	}
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.main-containers {
		.body-containers {
			padding: 60px 0 0;
			margin: 0;
			background: #fff;
			min-height: 100vh;
			position: relative;
			.top-container {
				box-shadow: 0 0px 0px rgba(64, 158, 255, .3);
				padding: 0 20px;
				z-index: 1002;
				top: 0;
				left: 0;
				background: #ED8E82;
				display: flex;
				width: 100%;
				justify-content: flex-start;
				align-items: center;
				position: absolute;
				height: 60px;
				.top_title {
					display: block;
					span {
						padding: 0 0 0 12px;
						color: #ED8E82;
						left: 20px;
						bottom: -70px;
						font-weight: bold;
						font-size: 18px;
						line-height: 70px;
						position: absolute;
						float: left;
					}
				}
				.top_avatar1 {
					border-radius: 50%;
					margin: 0 0 0 10px;
					width: 40px;
					height: 40px;
				}
				.top_nickname1 {
					padding: 0 12px;
					color: #fff;
					font-size: 15px;
					line-height: 32px;
					height: 32px;
				}
				.btn-user {
					border: 0;
					padding: 0 8px;
					margin: 0 10px;
					color: #333;
					background: #efefef;
					display: none;
					width: auto;
					font-size: 14px;
					line-height: 32px;
					height: 32px;
					.icon {
						color: inherit;
						font-size: 14px;
					}
				}
				.btn-user:hover {
					color: #fff;
					background: red;
				}
				.btn-login {
					border: 0;
					padding: 0 8px;
					margin: 0 10px;
					color: #fff;
					background: none;
					width: auto;
					font-size: 15px;
					line-height: 32px;
					height: 32px;
					.icon {
						color: inherit;
						display: none;
						font-size: inherit;
					}
				}
				.btn-login:hover {
					opacity: 0.7;
				}
				.btn-register {
					border: 0;
					padding: 0 8px;
					margin: 0 10px;
					color: #fff;
					background: none;
					width: auto;
					font-size: 15px;
					line-height: 32px;
					height: 32px;
					.icon {
						color: inherit;
						display: none;
						font-size: 14px;
					}
				}
				.btn-register:hover {
					opacity: 0.7;
				}
			}
			.menu-preview {
				.el-scrollbar {
					height: 100%;
			  
					& /deep/ .scrollbar-wrapper-vertical {
						overflow-x: hidden;
					}
			  
					& /deep/ .scrollbar-wrapper-horizontal {
						overflow-y: hidden;
			  
						.el-scrollbar__view {
							white-space: nowrap;
						}
					}
				}
				padding: 0 30px 0 20px;
				background: #FFD2CC;
				width: 100%;
				border-color: #efefef;
				border-width: 0;
				border-style: solid;
				height: auto;
				.el-menu-horizontal-demo {
					border: 0;
					padding: 0;
					margin: 0 20px 0 auto;
					background: none;
					display: flex;
					width: 75%;
					justify-content: flex-end;
					align-items: center;
					position: relative;
					list-style: none;
					height: 70px;
					.userinfo {
						padding: 6px 10px 0;
						display: none;
						width: 84px;
						height: auto;
						.el-image {
							border-radius: 20px;
							object-fit: cover;
							display: block;
							width: 100%;
							height: 32px;
						}
						.nickname {
							color: #333;
							font-size: 12px;
							line-height: 1.5;
							text-align: center;
						}
					}
					// 首页
					.el-menu-item.home {
						cursor: pointer;
						border: 0;
						padding: 0 20px;
						color: #ED8E82;
						white-space: nowrap;
						display: flex;
						font-size: 14px;
						line-height: 40px;
						background: none;
						align-items: center;
						position: relative;
						list-style: none;
						height: 40px;
					}
					.el-menu-item.home .icon {
						padding: 0;
						margin: 0;
						color: inherit;
						font-size: 14px;
						line-height: 40px;
						height: 40px;
					}
					
					.el-menu-item.home:hover {
						border-radius: 25px;
						color: #ED8E82;
						background: #fff;
					}
					
					.el-menu-item.home.is-active {
						border-radius: 25px;
						color: #ED8E82;
						background: #fff;
					}
					// 其他盒子
					.el-menu-item.item {
						cursor: pointer;
						border: 0;
						padding: 0 10px;
						color: #ED8E82;
						white-space: nowrap;
						display: flex;
						font-size: 14px;
						line-height: 40px;
						border-radius: 25px;
						background: none;
						align-items: center;
						position: relative;
						list-style: none;
						height: 40px;
					}
					.el-menu-item.item .icons {
						padding: 0;
						margin: 0;
						color: inherit;
						font-size: 14px;
						line-height: 40px;
						height: 40px;
					}
					
					.el-menu-item.item:hover {
						border-radius: 25px;
						color: #ED8E82;
						background: #fff;
					}
					
					.el-menu-item.item.is-active {
						border-radius: 25px;
						color: #ED8E82;
						background: #fff;
					}
					.el-menu-item.user {
						cursor: pointer;
						border: 0;
						padding: 0 10px;
						color: #ED8E82;
						white-space: nowrap;
						display: flex;
						font-size: 14px;
						line-height: 40px;
						border-radius: 25px;
						background: none;
						align-items: center;
						position: relative;
						list-style: none;
						height: 40px;
						order: 5;
					}
					.el-menu-item.user .icon {
						padding: 0;
						margin: 0;
						color: inherit;
						font-size: 14px;
						line-height: 40px;
						height: 40px;
					}
					
					.el-menu-item.user:hover {
						border-radius: 25px;
						color: #ED8E82;
						background: #fff;
					}
					
					.el-menu-item.user.is-active {
						border-radius: 25px;
						color: #ED8E82;
						background: #fff;
					}
				}
			}
			.banner-preview {
				width: 100%;
				height: auto;
				.el-carousel {
					margin: 0 auto;
					width: 100%;
					/deep/ .el-carousel__item {
						border-radius: 0;
						width: 100%;
						height: 100%;
						@keyframes wave1 {from { left: -236px } to { left: -1233px }}
						@keyframes wave2 {from { left: 0 } to { left: -1009px }}
						.el-image {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					/deep/ .el-carousel__container .el-carousel__arrow--left {
						width: 36px;
						font-size: 12px;
						height: 36px;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--left:hover {
						background: #ED8E82;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--right {
						width: 36px;
						font-size: 12px;
						height: 36px;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--right:hover {
						background: #ED8E82;
					}
					/deep/ .el-carousel__indicators {
						padding: 0;
						margin: 0;
						z-index: 2;
						position: absolute;
						list-style: none;
						li {
							padding: 0;
							margin: 0 4px;
							background: #ED8E82;
							display: inline-block;
							width: 12px;
							opacity: 0.4;
							transition: 0.3s;
							height: 12px;
						}
						li:hover {
							padding: 0;
							margin: 0 4px;
							background: #ED8E82;
							display: inline-block;
							width: 24px;
							opacity: 0.7;
							height: 12px;
						}
						li.is-active {
							padding: 0;
							margin: 0 4px;
							background: #ED8E82;
							display: inline-block;
							width: 24px;
							opacity: 1;
							height: 12px;
						}
					}
					/deep/ .el-carousel__indicator button {
						width: 0;
						height: 0;
						display: none;
					}
				}
			}
			.bottom-preview {
				width: 100%;
				height: auto;
				.footer {
					padding: 20px;
					overflow: hidden;
					background: #000;
					width: 100%;
					height: auto;
				}
			}
		}
	}
</style>
