<template>
	<div class="home-preview">




		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">公告资讯</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<div v-if="newsList.length" class="list list16 index-pv1">
				<div class="list-body" v-for="item,index in [1,2,3]" :key="index">
					<template v-for="(item1,index1) in newsList">
						<div class="list-item" v-if="index1 >= index*6 && index1 < (index+1)*6" @click="toDetail('newsDetail', item1)">
							<div class="time">{{item1.addtime.split(" ")[0].split("-")[1] + '-' + item1.addtime.split(" ")[0].split("-")[2]}}</div>
							<div class="name">{{item1.title}}</div>
						</div>
					</template>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendyinshifangan" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">饮食方案推荐</span>
				<span class="recommend_subhead">{{'yinshifangan'.toUpperCase()}} RECOMMEND</span>
			</div>
			<!-- 样式一 -->
			<div class="list list1 index-pv1">
				<div v-for="(item,index) in yinshifanganRecommend" :key="index" @click="toDetail('yinshifanganDetail', item)" class="list-item animation-box">
					<img v-if="preHttp(item.shipuzhaopian)" :src="item.shipuzhaopian.split(',')[0]" alt="" />
					<img v-else :src="baseUrl + (item.shipuzhaopian?item.shipuzhaopian.split(',')[0]:'')" alt="" />
					<div class="name line1">{{item.shipumingcheng}}</div>
					<div class="name line1">{{item.shipufenlei}}</div>
				
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{item.addtime.split(' ')[0]}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{item.thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{item.storeupnum}}</span>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('yinshifangan')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendshujufenxi" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">数据分析推荐</span>
				<span class="recommend_subhead">{{'shujufenxi'.toUpperCase()}} RECOMMEND</span>
			</div>
			<!-- 样式一 -->
			<div class="list list1 index-pv1">
				<div v-for="(item,index) in shujufenxiRecommend" :key="index" @click="toDetail('shujufenxiDetail', item)" class="list-item animation-box">
					<img v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
					<img v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
				
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{item.addtime.split(' ')[0]}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{item.thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{item.storeupnum}}</span>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('shujufenxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
		<!-- 特价商品 -->
		<div id="animate_listyinshifangan" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">饮食方案展示</span>
				<span class="list_subhead">{{'yinshifangan'.toUpperCase()}} SHOW</span>
			</div>
			<div v-if="yinshifanganList.length" class="list list11 index-pv1">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in yinshifanganList" :key="index" @click="toDetail('yinshifanganDetail', item)">
						<img :name="item.id" v-if="preHttp(item.shipuzhaopian)" :src="item.shipuzhaopian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.shipuzhaopian?item.shipuzhaopian.split(',')[0]:'')" alt="" />
						<div class="name">{{item.shipumingcheng}}</div>
						<div class="name">{{item.shipufenlei}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('yinshifangan')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
		<!-- 特价商品 -->
		<div id="animate_listshujufenxi" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">数据分析展示</span>
				<span class="list_subhead">{{'shujufenxi'.toUpperCase()}} SHOW</span>
			</div>
			<div v-if="shujufenxiList.length" class="list list11 index-pv1">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in shujufenxiList" :key="index" @click="toDetail('shujufenxiDetail', item)">
						<img :name="item.id" v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('shujufenxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],
				yinshifanganRecommend: [],
				shujufenxiRecommend: [],

				yinshifanganList: [],
				shujufenxiList: [],




			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},

			listIndexClick11(index, name) {
				this['listIndex11' + name] = index[this['listColumn11' + name]]
				this.getList()
			},

			handleScroll() {
				let arr = [
					{id:'about',css:'animate__bounce'},
					{id:'system',css:'animate__bounce'},
					{id:'animate_recommendyinshifangan',css:'animate__bounce'},
					{id:'animate_listyinshifangan',css:'animate__'},
					{id:'animate_recommendshujufenxi',css:'animate__bounce'},
					{id:'animate_listshujufenxi',css:'animate__'},
					{id:'animate_newsnews',css:'animate__bounce'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 18,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "yinshifangan/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "yinshifangan/autoSort2";
				}
				data = {
					page: 1,
					limit: 8,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.yinshifanganRecommend = res.data.data.list;
					}
				});
				autoSortUrl = "shujufenxi/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "shujufenxi/autoSort2";
				}
				data = {
					page: 1,
					limit: 8,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.shujufenxiRecommend = res.data.data.list;
					}
				});
			
				data = {
					page: 1,
					limit: 6,
				}

				this.$http.get('yinshifangan/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.yinshifanganList = res.data.data.list;
					}
				});
				data = {
					page: 1,
					limit: 6,
				}

				this.$http.get('shujufenxi/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.shujufenxiList = res.data.data.list;
					}
				});
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0px auto;
		flex-direction: column;
		display: flex;
		width: 100%;
		.news {
			padding: 40px 16%;
			margin: 0;
			flex-direction: column;
			background: #fff;
			display: flex;
			width: 100%;
			order: 4;
			.news_title_box {
				margin: 10px auto;
				background: none;
				width: auto;
				line-height: 54px;
				text-align: center;
				.news_title {
					color: #000;
					background: url(http://codegen.caihongy.cn/20250204/dea0da8558ec43668514abc9cb06b051.png) center center/100% 100%;
					font-weight: bold;
					display: inline-block;
					width: 400px;
					font-size: 26px;
					line-height: 70px;
					height: 70px;
				}
				.news_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list16 {
				padding: 10px;
				background: none;
				display: flex;
				width: 100%;
				justify-content: space-between;
				height: auto;
				order: 2;
				.list-body {
					padding: 10px;
					background: #F8F6F7;
					width: 32%;
					height: auto;
					.list-item {
						display: flex;
						width: 100%;
						border-color: #efefef;
						border-width: 0;
						justify-content: space-between;
						border-style: dashed;
						height: auto;
						.time {
							color: #ED8E82;
							font-weight: 500;
							font-size: 16px;
							line-height: 40px;
							order: 1;
						}
						.name {
							padding: 0 0 0 10px;
							color: #000;
							font-size: 16px;
							line-height: 40px;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				margin: 10px auto;
				background: none;
				display: block;
				width: 80px;
				line-height: 1;
				text-align: center;
				order: 1;
				.text {
					color: #ED8E82;
					font-weight: bold;
					font-size: 16px;
				}
				.icon {
					color: #ED8E82;
					display: none;
					font-size: 15px;
				}
			}
		}
		.recommend {
			padding: 40px 16%;
			margin: 0;
			flex-direction: column;
			background: #fff;
			display: flex;
			width: 100%;
			order: 1;
			.recommend_title_box {
				margin: 10px auto;
				background: none;
				width: auto;
				line-height: 54px;
				text-align: center;
				.recommend_title {
					color: #000;
					background: url(http://codegen.caihongy.cn/20250204/dea0da8558ec43668514abc9cb06b051.png) center center/100% 100%;
					font-weight: bold;
					display: inline-block;
					width: 400px;
					font-size: 26px;
					line-height: 70px;
					height: 70px;
				}
				.recommend_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list1 {
				padding: 0 10px;
				background: #fff;
				display: flex;
				width: 100%;
				flex-wrap: wrap;
				height: auto;
				order: 2;
				.list-item {
					border: 2px solid rgb(238, 238, 238);
					margin: 10px;
					background: #fff;
					display: flex;
					width: 23%;
					position: relative;
					flex-wrap: wrap;
					height: auto;
					img {
						object-fit: cover;
						display: block;
						width: 100%;
						height: 210px;
					}
					.name {
						padding: 10px 0 0;
						color: #000;
						width: 100%;
						font-size: 16px;
						line-height: 1.5;
						text-align: center;
					}
					.price {
						border-radius: 30px;
						padding: 5px 40px;
						margin: 10px auto;
						color: #fff;
						background: #ED8E82;
						width: auto;
						font-size: 20px;
						line-height: 1.5;
						order: 6;
					}
					.time_item {
						padding: 0 10px;
						width: 100%;
						text-align: center;
						order: 5;
						.icon {
							margin: 0 5px 0 0;
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
					}
					.publisher_item {
						padding: 0 10px;
						width: 100%;
						text-align: center;
						order: 4;
						.icon {
							margin: 0 5px 0 0;
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
					}
					.like_item {
						padding: 0 10px;
						width: 100%;
						text-align: center;
						order: 1;
						.icon {
							margin: 0 5px 0 0;
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
					}
					.collect_item {
						padding: 0 10px;
						width: 100%;
						text-align: center;
						order: 2;
						.icon {
							margin: 0 5px 0 0;
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
					}
					.view_item {
						padding: 0 10px;
						width: 100%;
						text-align: center;
						order: 3;
						.icon {
							margin: 0 5px 0 0;
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				margin: 10px auto;
				background: none;
				display: block;
				width: 80px;
				line-height: 1;
				text-align: center;
				order: 1;
				.text {
					color: #ED8E82;
					font-weight: bold;
					font-size: 16px;
				}
				.icon {
					color: #ED8E82;
					display: none;
					font-size: 15px;
				}
			}
		}
		.lists {
			margin: 0;
			background: #fff;
			width: 100%;
			position: relative;
			order: 2;
			.list_title_box {
				margin: 10px auto;
				background: url(http://codegen.caihongy.cn/20250204/4d94318ba6084d73a80788fd58b211c5.png) center center/100% 100%;
				display: flex;
				width: 100%;
				line-height: 54px;
				justify-content: center;
				align-items: center;
				text-align: center;
				height: 210px;
				.list_title {
					margin: 0 0 40px;
					color: #000;
					background: url(http://codegen.caihongy.cn/20250204/dea0da8558ec43668514abc9cb06b051.png) center center/100% 100%;
					font-weight: bold;
					display: inline-block;
					width: 400px;
					font-size: 26px;
					line-height: 70px;
					height: 70px;
				}
				.list_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list11 {
				padding: 10px 16%;
				background: #fff;
				display: flex;
				width: 100%;
				justify-content: space-between;
				height: auto;
				.tab_view {
					margin: 0 20px 0 0;
					flex-direction: column;
					display: flex;
					width: 160px;
					height: auto;
					.tab {
						cursor: pointer;
						padding: 0 0 10px;
						margin: 0;
						width: 100%;
						border-color: #efefef;
						border-width: 0;
						border-style: solid;
						height: auto;
						.icon {
							margin: 0 3px 0 0;
							color: #C4C4C4;
							font-size: 10px;
							line-height: 40px;
						}
						img {
							margin: 0 5px 0 0;
							object-fit: cover;
							display: inline-block;
							width: 40px;
							height: 40px;
						}
						.text {
							color: #333;
							font-size: 15px;
							line-height: 40px;
						}
					}
					.more_btn {
						cursor: pointer;
						padding: 0 10px;
						margin: 0;
						display: none;
						width: 100%;
						border-color: #efefef;
						border-width: 0;
						border-style: solid;
						height: auto;
						.icon {
							margin: 0 3px 0 0;
							color: #C4C4C4;
							font-size: 10px;
							line-height: 40px;
						}
						.text {
							color: #333;
							display: inline-block;
							font-size: 16px;
							line-height: 44px;
						}
					}
				}
				.list-body {
					flex: 1;
					background: #fff;
					display: flex;
					justify-content: flex-start;
					flex-wrap: wrap;
					height: auto;
					.list-item {
						margin: 0 1% 5px;
						width: 18%;
						position: relative;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100%;
							height: 210px;
						}
						.name {
							padding: 0 10px;
							color: #333;
							width: 100%;
							font-size: 16px;
							line-height: 30px;
							text-align: center;
						}
						.price {
							padding: 0 10px;
							color: #ED8E82;
							font-size: 14px;
							line-height: 30px;
							text-align: center;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				margin: 0 0 0 -40px;
				top: 160px;
				left: 50%;
				background: none;
				display: block;
				width: 80px;
				line-height: 1;
				position: absolute;
				text-align: center;
				order: 1;
				.text {
					color: #ED8E82;
					font-weight: bold;
					font-size: 16px;
				}
				.icon {
					color: #ED8E82;
					display: none;
					font-size: 15px;
				}
			}
		}
	}
</style>
