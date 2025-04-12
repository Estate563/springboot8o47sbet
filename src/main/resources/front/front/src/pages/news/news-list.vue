<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'Ξ'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
	
		<div class="news-preview-pv">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="search-item">
					<el-input v-model="title" placeholder="标题"></el-input>
				</el-form-item>
				<el-button class="search-btn" type="primary" @click="getNewsList(1)">
					<span class="icon iconfont icon-chakan14"></span>
					搜索
				</el-button>
			</el-form>
			
			<!-- category -->
			<div class="category-list">
				<div class="item" @click="categoryClick(0)" :class="categoryIndex == 0 ? 'active' : ''">全部</div>
				<div v-for="(item,index) in categoryList" @click="categoryClick(index+1)" :key="index" class="item" :class="categoryIndex == index+1 ? 'active' : ''">{{item.typename}}</div>
			</div>
			<div v-if="newsList.length" class="list8 index-pv1">
				<div class="list-body-top">
					<div class="list-item1" @click="toNewsDetail(newsList[0])">
						<img :src="baseUrl + newsList[0].picture">
						<div class="infoBox">
							<div class="name">{{newsList[0].title}}</div>
							<div class="desc">{{newsList[0].introduction}}</div>
							<div class="time">{{newsList[0].addtime.split(' ')[0]}}</div>
						</div>
					</div>
					<div class="list-body-right" v-if="newsList.length > 1">
						<div class="list-item" v-for="item,index in newsList" v-if="index > 0 && index < 5" @click="toNewsDetail(item)">
							<div class="time_item">
								<div class="day">{{item.addtime.split(" ")[0].split("-")[2]}}</div>
								<div class="year">{{item.addtime.split(" ")[0].split("-")[0] + '-' + item.addtime.split(" ")[0].split("-")[1]}}</div>
							</div>
							<div class="infoBox">
								<div class="name">{{item.title}}</div>
								<div class="desc">{{item.introduction}}</div>
								<span class="icon iconfont icon-jiantou37"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="list-body" v-if="newsList.length > 4">
					<div class="list-item" v-for="item,index in newsList" v-if="index > 4" @click="toNewsDetail(item)">
						<div class="name">{{item.title}}</div>
						<div class="time">{{item.addtime.split(" ")[0]}}</div>
					</div>
				</div>
			</div>
		
			<el-pagination
				background
				id="pagination" class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				:page-sizes="pageSizes"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:layout='["total","prev","pager","next","sizes"].join()'
				:total="total"
				@current-change="curChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>

			<!-- 热门信息 -->
			<div class="hot">
				<div class="hot-title">热门信息</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toNewsDetail(item)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<!-- 最新动态 -->
			<div class="news">
				<div class="news-title">最新动态</div>
				<div class="news-list">
					<div class="news-item" v-for="item in recommendList" :key="item.id" @click="toNewsDetail(item)">
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
				baseUrl: this.$config.baseUrl,
				breadcrumbItem: [
				  {
					name: '公告资讯'
				  }
				],
				newsList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				layouts: '',
				title: '',
				categoryIndex: 0,
				categoryList: [],
				hotList: [],
				recommendList: [],
			}
		},
		created() {
			this.getCategoryList()
			
			this.getHotList()
			this.getRecommendList()
		},
		//方法集合
		methods: {
			getCategoryList(){
				this.$http.get('newstype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data.list;
						this.getNewsList(1);
					}
				});
			},
			categoryClick(index) {
				this.categoryIndex = index
				this.getNewsList()
			},
			getNewsList(page) {
				let params = {page, limit: this.pageSize,sort:'addtime',order:'desc'};
				let searchWhere = {};
				if(this.title != '') searchWhere.title = '%' + this.title + '%';
				if(this.categoryIndex!=0){
					searchWhere.typename = this.categoryList[this.categoryIndex - 1].typename
				}
				this.$http.get('news/list', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
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
			curChange(page) {
				this.getNewsList(page);
			},
			prevClick(page) {
				this.getNewsList(page);
			},
			nextClick(page) {
				this.getNewsList(page);
			},
			toNewsDetail(item) {
				this.$router.push({path: '/index/newsDetail', query: {id: item.id}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.news-preview-pv {
				padding: 0 16%;
				margin: 10px auto;
				background: none;
				width: 100%;
				position: relative;
				.list-form-pv {
						padding: 10px;
						background: none;
						display: flex;
						width: 100%;
						justify-content: center;
						align-items: center;
						flex-wrap: wrap;
						height: auto;
						.search-item {
								margin: 0 10px;
								.el-input {
										width: 100%;
									}
				.el-input /deep/ .el-input__inner {
										border: 1px solid #000;
										border-radius: 0;
										padding: 0 10px;
										margin: 0;
										outline: none;
										color: #333;
										width: 300px;
										font-size: 14px;
										line-height: 42px;
										height: 42px;
									}
			}
			.search-btn {
								cursor: pointer;
								border: 0;
								border-radius: 0;
								padding: 0px 25px;
								margin: 0 10px 0 0;
								outline: none;
								color: #fff;
								background: #ED8E82;
								width: auto;
								font-size: 14px;
								line-height: 42px;
								height: 42px;
								.icon {
										margin: 0 10px 0 0;
										color: #fff;
										font-size: 14px;
									}
			}
		}
		.category-list {
						border: 1px solid #ED8E82;
						border-radius: 10px;
						padding: 30px;
						box-shadow: inset 0 0 0px 15px #fff;
						background: #F8E8F2;
						display: flex;
						width: 100%;
						flex-wrap: wrap;
						height: auto;
						order: 2;
						.item {
								cursor: pointer;
								border-radius: 20px;
								padding: 0;
								margin: 0 10px 0 0;
								color: #000;
								background: none;
								width: 130px;
								font-size: 14px;
								line-height: 36px;
								text-align: center;
							}
			
			.item:hover {
								color: #fff;
								background: #ED8E82;
							}
			
			.item.active {
								color: #fff;
								background: #ED8E82;
							}
		}
		.list8 {
						padding: 10px 0 20px;
						background: #fff;
						width: 100%;
						height: auto;
						.list-body-top {
								display: flex;
								width: 100%;
								justify-content: space-between;
								height: auto;
								.list-item1 {
										border: 1px solid #EEA39A;
										border-radius: 10px;
										padding: 15px;
										width: 49%;
										position: relative;
										height: auto;
										img {
												border-radius: 10px;
												object-fit: cover;
												display: block;
												width: 100%;
												height: 400px;
											}
					.infoBox {
												border-radius: 0 0 10px 10px;
												padding: 10px;
												z-index: 9;
												left: 15px;
												bottom: 15px;
												background: rgba(0,0,0,.3);
												width: calc(100% - 30px);
												position: absolute;
												.name {
														overflow: hidden;
														color: #fff;
														white-space: nowrap;
														font-size: 16px;
														line-height: 2;
														text-overflow: ellipsis;
													}
						.desc {
														overflow: hidden;
														color: #fff;
														white-space: nowrap;
														font-size: 14px;
														line-height: 2;
														text-overflow: ellipsis;
													}
						.time {
														color: #fff;
														font-size: 14px;
														line-height: 2;
													}
					}
				}
				.list-body-right {
										padding: 0 0 0 20px;
										display: flex;
										width: 50%;
										flex-wrap: wrap;
										height: auto;
										.list-item {
												border: 1px solid #EEA39A;
												border-radius: 10px;
												padding: 0;
												display: flex;
												width: 100%;
												justify-content: center;
												align-items: center;
												height: 100px;
												.time_item {
														border: 1px solid #EEA39A;
														border-radius: 10px;
														padding: 10px 0;
														margin: 0 10px 0 0;
														flex-direction: column;
														background: #F8E8F2;
														display: flex;
														width: 100px;
														justify-content: center;
														align-items: center;
														height: 100%;
														.day {
																color: #EEA39A;
																font-size: 20px;
																line-height: 1.5;
															}
							.year {
																color: #EEA39A;
																font-size: 14px;
																line-height: 1.5;
															}
						}
						.infoBox {
														padding: 10px 0;
														flex: 1;
														display: flex;
														flex-wrap: wrap;
														height: 100%;
														.name {
																overflow: hidden;
																color: #000;
																white-space: nowrap;
																font-size: 16px;
																line-height: 2;
																text-overflow: ellipsis;
															}
							.desc {
																overflow: hidden;
																color: #717171;
																display: -webkit-box;
																font-size: 14px;
																line-height: 20px;
																text-overflow: ellipsis;
																-webkit-box-orient: vertical;
																-webkit-line-clamp: 2;
															}
							.icon {
																color: #999;
																display: none;
																font-size: 14px;
																line-height: 20px;
																float: right;
															}
						}
					}
				}
			}
			.list-body {
								border: 1px solid #EEA39A;
								border-radius: 10px;
								padding: 0 10px;
								margin: 20px 0 0 0;
								width: 100%;
								height: auto;
								.list-item {
										padding: 0 10px;
										display: flex;
										width: 100%;
										border-color: #EEA39A;
										border-width: 0 0 1px;
										justify-content: space-between;
										border-style: dashed;
										.name {
												overflow: hidden;
												color: #000;
												white-space: nowrap;
												width: 50%;
												font-size: 16px;
												line-height: 50px;
												text-overflow: ellipsis;
											}
					.time {
												color: #EEA39A;
												font-size: 14px;
												line-height: 50px;
											}
				}
			}
		}
		.hot {
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
						margin: 20px 0;
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
	
	.index-pv1 .animation-box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	
	.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1.2) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
	}
	
	.index-pv1 .animation-box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
	}
	
	.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(0.8) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
</style>
