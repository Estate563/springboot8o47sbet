<template>
	<div class="news-detail-box">
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="news-detail-view">
			<div class="title-box">
				<div>公告资讯</div>
			</div>
			<div class="news-detail">
				<div class="detail-title">{{detail.title}}</div>
				<div class="infoBox">
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{detail.addtime.split(' ')[0]}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{detail.name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{detail.thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{detail.storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{detail.clicknum}}</span>
					</div>
				</div>
				<div class="operate">
					<div class="zan-btn" @click="zanClick" >
						<span class="icon iconfont" :class="zanType?'icon-guanzhu-zhihui':'icon-guanzhu-zhihui'" ></span>
						<span class="text" >{{zanType?'取消点赞':'点赞'}}</span>
					</div>
					<div class="collect-btn" @click="collectClick" >
						<span class="icon iconfont" :class="collectType?'icon-guanzhu-zhihui':'icon-guanzhu-zhihui'" ></span>
						<span class="text" >{{collectType?'取消收藏':'收藏'}}</span>
					</div>
				</div>
				
				<div class="content-detail ql-snow ql-editor" v-html="detail.content"></div>
			</div>
			<!-- option -->
			<div class="option-box">
				<div class="prev-btn" @click="prepDetailClick">
					<span class="text">上一篇：prev</span>
					<span class="icon iconfont icon-jiantou08"></span>
				</div>
				<div class="next-btn" @click="nextDetailClick">
					<span class="text">下一篇：next</span>
					<span class="icon iconfont icon-jiantou09"></span>
				</div>
			</div>

			<!-- 热门文章 -->
			<div class="hot">
				<div class="hot-title">热门信息</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toDetail(item.id)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<!-- 推荐文章 -->
			<div class="news">
				<div class="news-title">推荐文章</div>
				<div class="news-list">
					<div class="news-item" v-for="item in recommendList" :key="item.id" @click="toDetail(item.id)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="news-name">{{ item.title }}</div>
						<div class="news-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				id: 0,
				detail: {},
				zanType: false,
				zanForm: {},
				collectType:false,
				collectForm: {},
				baseUrl:'',
				hotList: [],
				recommendList: [],
				categoryList: [],
				currentIndex: 0,
				allList: [],
				storeupType: false,
			}
		},
		created() {
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			this.id = this.$route.query.id
			this.baseUrl = this.$config.baseUrl;
			this.getCategoryList()
			this.getDetail()
			this.getNewsList()
			this.getHotList()
			this.getRecommendList()
		},
		watch:{
			$route(newValue) {
				this.id = this.$route.query.id
			    this.getDetail()
			}
		},
		methods: {
			backClick() {
				if(this.storeupType){
					history.back()
				}else {
					this.$router.push({path: '/index/news'});
				}
			},
			getNewsList() {
				let params = {page:1, limit: 100,sort:'addtime',order:'desc'};
				this.$http.get('news/list', {params: params}).then(res => {
					if (res.data.code == 0) {
						for(let x in res.data.data.list){
							if(res.data.data.list[x].id == this.id){
								this.currentIndex = Number(x)
								break
							}
						}
						this.allList = res.data.data.list
					}
				});
			},
			// 上一篇
			prepDetailClick(){
				if(this.currentIndex == 0){
					this.$message.error('已经是第一篇了')
					return false
				}
				this.currentIndex--
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			// 下一篇
			nextDetailClick(){
				if(this.currentIndex == this.allList.length - 1){
					this.$message.error('已经是最后一篇了')
					return false
				}
				this.currentIndex++
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			getCategoryList(){
				this.$http.get('newstype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data;
					}
				});
			},
			// 获取热门列表
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
			// 获取推荐列表
			getRecommendList(){
				let url = 'news/autoSort'
				if(localStorage.getItem('frontToken')){
					url = 'news/autoSort2'
				}
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get(url, {params: params}).then(res => {
					if (res.data.code == 0) {
						this.recommendList = res.data.data.list;
					}
				});
			},
			// 跳转详情
			toDetail(id){
				this.$router.push({path: '/index/newsDetail', query: {id: id}});
			},
			getDetail(){
				this.$http.get(`news/detail/${this.id}`,{}).then(res=>{
					if(res.data&&res.data.code==0){
						this.detail = res.data.data
						window.scrollTo(0, 100)
						this.getZan()
						this.getCollect()
					}
				})
			},
			getZan() {
				this.$http.get('storeup/list', {
					params: {
						page: 1,
						limit: 1,
						type: '21',
						userid: Number(localStorage.getItem('frontUserid')),
						tablename: 'news',
						refid: this.id
					}
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if(res.data.data.list.length){
							this.zanType = true
							this.zanForm = res.data.data.list[0]
						}else{
							this.zanType = false
						}
					}
				})
			},
			getCollect(){
				this.$http.get('storeup/list', {
					params: {
						page: 1,
						limit: 1,
						type: '1',
						userid: Number(localStorage.getItem('frontUserid')),
						tablename: 'news',
						refid: this.id
					}
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if(res.data.data.list.length){
							this.collectType = true
							this.collectForm = res.data.data.list[0]
						}else{
							this.collectType = false
						}
					}
				})
			},
			zanClick() {
				if(this.zanType){
					this.$http.post('storeup/delete', [this.zanForm.id]).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('取消成功')
							this.detail.thumbsupnum--
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getZan()
						}
					})
				}else{
					let data = {
						name: this.detail.title,
						picture: this.detail.picture,
						refid: this.detail.id,
						type: '21',
						tablename: 'news',
						userid: Number(localStorage.getItem('frontUserid')),
					}
					this.$http.post('storeup/add', data).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('点赞成功')
							this.detail.thumbsupnum++
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getZan()
						}
					})
				}
			},
			collectClick(){
				if(this.collectType){
					this.$http.post('storeup/delete', [this.collectForm.id]).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('取消成功')
							this.detail.storeupnum--
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getCollect()
						}
					})
				}else{
					let data = {
						name: this.detail.title,
						picture: this.detail.picture,
						refid: this.detail.id,
						type: '1',
						tablename: 'news',
						userid: Number(localStorage.getItem('frontUserid')),
					}
					this.$http.post('storeup/add', data).then(res => {
						if (res.data && res.data.code == 0) {
							this.detail.storeupnum++
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.$message.success('收藏成功')
							this.getCollect()
						}
					})
				}
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	
	.news-detail-box {
				padding: 0 16%;
				margin: 10px auto;
				background: #fff;
				width: 100%;
				position: relative;
				.news-detail-view {
						border: 1px solid #EEA39A;
						border-radius: 10px;
						padding: 20px;
						width: 100%;
						.title-box {
								width: 100%;
								height: auto;
								div {
										border: 1px solid #EEA39A;
										border-radius: 5px;
										margin: 0;
										color: #000;
										background: #F8E8F2;
										font-size: 20px;
										line-height: 54px;
										text-align: center;
									}
			}
			.news-detail {
								padding: 20px 0;
								width: 100%;
								position: relative;
								.detail-title {
										color: #000;
										font-size: 18px;
										line-height: 40px;
										text-align: center;
									}
				.infoBox {
										padding: 10px 0;
										display: flex;
										width: 100%;
										border-color: #C4C4C4;
										border-width: 0 0 1px;
										justify-content: center;
										align-items: center;
										border-style: solid;
										.time_item {
												padding: 0 10px;
												.icon {
														margin: 0 5px 0 0;
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
						.label {
														color: #666;
														display: none;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
					}
					.publisher_item {
												padding: 0 10px;
												.icon {
														margin: 0 5px 0 0;
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
						.label {
														color: #666;
														display: none;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
					}
					.like_item {
												padding: 0 10px;
												.icon {
														margin: 0 5px 0 0;
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
						.label {
														color: #666;
														display: none;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
					}
					.collect_item {
												padding: 0 10px;
												.icon {
														margin: 0 5px 0 0;
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
						.label {
														color: #666;
														display: none;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
					}
					.view_item {
												padding: 0 10px;
												.icon {
														margin: 0 5px 0 0;
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
						.label {
														color: #666;
														display: none;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #EEA39A;
														font-size: 14px;
														line-height: 25px;
													}
					}
				}
				.operate {
										padding: 10px 20px;
										margin: 0 auto;
										display: flex;
										width: 80%;
										justify-content: space-between;
										align-items: center;
										.zan-btn {
												border: 1px solid #EEA39A;
												cursor: pointer;
												border-radius: 10px;
												padding: 0 35px;
												background: #F8E8F2;
												display: flex;
												justify-content: center;
												align-items: center;
												.icon {
														margin: 0 5px 0 0;
														color: #EEA39A;
														font-size: 14px;
														line-height: 40px;
													}
						.text {
														color: #EEA39A;
														font-size: 14px;
														line-height: 40px;
													}
					}
					.zan-btn:hover {
												opacity: 0.7;
												.icon {
														color: #EEA39A;
													}
						.text {
														color: #EEA39A;
													}
					}
					.collect-btn {
												border: 1px solid #EEA39A;
												cursor: pointer;
												border-radius: 10px;
												padding: 0 35px;
												background: #F8E8F2;
												display: flex;
												justify-content: center;
												align-items: center;
												.icon {
														margin: 0 5px 0 0;
														color: #EEA39A;
														font-size: 14px;
														line-height: 40px;
													}
						.text {
														color: #EEA39A;
														font-size: 14px;
														line-height: 40px;
													}
					}
					.collect-btn:hover {
												opacity: 0.7;
												.icon {
														color: #EEA39A;
													}
						.text {
														color: #EEA39A;
													}
					}
				}
				.content-detail {
										width: 100%;
										height: auto;
									}
			}
			.option-box {
								padding: 10px 20px;
								margin: 0 auto;
								display: flex;
								width: 80%;
								justify-content: space-between;
								align-items: center;
								.prev-btn {
										border: 1px solid #EEA39A;
										cursor: pointer;
										border-radius: 10px;
										padding: 0 35px;
										background: #F8E8F2;
										display: flex;
										justify-content: center;
										align-items: center;
										.text {
												color: #EEA39A;
												font-size: 14px;
												line-height: 40px;
											}
					.icon {
												color: #EEA39A;
												display: none;
												font-size: 14px;
												line-height: 40px;
											}
				}
				.prev-btn:hover {
										opacity: 0.7;
										.text {
												color: #EEA39A;
											}
					.icon {
												color: #EEA39A;
											}
				}
				.next-btn {
										border: 1px solid #EEA39A;
										cursor: pointer;
										border-radius: 10px;
										padding: 0 35px;
										background: #F8E8F2;
										display: flex;
										justify-content: center;
										align-items: center;
										.text {
												color: #EEA39A;
												font-size: 14px;
												line-height: 40px;
											}
					.icon {
												color: #666;
												display: none;
												font-size: 14px;
												line-height: 40px;
											}
				}
				.next-btn:hover {
										opacity: 0.7;
										.text {
												color: #EEA39A;
											}
					.icon {
												color: #ff0;
											}
				}
			}
			.hot {
								margin: 20px 0;
								display: flex;
								width: 100%;
								flex-wrap: wrap;
								height: auto;
								order: 5;
								.hot-title {
										margin: 0 auto 40px;
										color: #000;
										background: url(http://codegen.caihongy.cn/20250204/dea0da8558ec43668514abc9cb06b051.png) center center/100% 100%;
										font-weight: bold;
										display: inline-block;
										width: 400px;
										font-size: 26px;
										line-height: 70px;
										text-align: center;
										height: 70px;
									}
				.hot-list {
										padding: 0 0 5px 0;
										background: #fff;
										display: flex;
										width: 100%;
										flex-wrap: wrap;
										height: auto;
										.hot-item {
												border: 1px solid #ED8E82;
												border-radius: 10px;
												padding: 10px;
												margin: 0 1%;
												background: #fff;
												width: 23%;
												height: auto;
												img {
														border-radius: 10px;
														object-fit: cover;
														display: block;
														width: 100%;
														height: 160px;
													}
						.hot-name {
														padding: 0;
														margin: 10px 0;
														color: #000;
														background: #F8E8F2;
														width: 100%;
														font-size: 14px;
														line-height: 2;
														text-align: center;
													}
						.hot-time {
														padding: 5px 0;
														color: #ED8E82;
														font-size: 12px;
														line-height: 12px;
														text-align: right;
													}
					}
				}
			}
			.news {
								margin: 20px 0 0;
								display: flex;
								width: 100%;
								flex-wrap: wrap;
								height: auto;
								order: 5;
								.news-title {
										margin: 0 auto 40px;
										color: #000;
										background: url(http://codegen.caihongy.cn/20250204/dea0da8558ec43668514abc9cb06b051.png) center center/100% 100%;
										font-weight: bold;
										display: inline-block;
										width: 400px;
										font-size: 26px;
										line-height: 70px;
										text-align: center;
										height: 70px;
									}
				.news-list {
										padding: 0 0 5px 0;
										background: #fff;
										display: flex;
										width: 100%;
										flex-wrap: wrap;
										height: auto;
										.news-item {
												border: 1px solid #ED8E82;
												border-radius: 10px;
												padding: 10px;
												margin: 0 1%;
												background: #fff;
												width: 23%;
												height: auto;
												img {
														border-radius: 10px;
														object-fit: cover;
														display: block;
														width: 100%;
														height: 160px;
													}
						.news-name {
														padding: 0;
														margin: 10px 0;
														color: #000;
														background: #F8E8F2;
														width: 100%;
														font-size: 14px;
														line-height: 2;
														text-align: center;
													}
						.news-time {
														padding: 5px 0;
														color: #ED8E82;
														font-size: 12px;
														line-height: 12px;
														text-align: right;
													}
					}
				}
			}
		}
	}
</style>