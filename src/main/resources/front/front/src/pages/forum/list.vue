<template>
	<div class="forum-preview">
		<div class="forum-title">
			<div>健康圈</div>
		</div>
		<el-form :inline="true" :model="formSearch" class="list-form-pv">
			<el-form-item class="search-item">
				<el-input v-model="title" placeholder="标题"></el-input>
			</el-form-item>
			<div class="search-btn-item">
				<el-button class="searchBtn" type="primary" @click="getForumList(1)">
					<span class="icon iconfont icon-chakan14"></span>
					查询
				</el-button>
				<el-button class="pubBtn" type="primary" @click="toForumAdd">
					<span class="icon iconfont icon-tianjia14"></span>
					发布帖子
				</el-button>
			</div>
		</el-form>
		<div class="category-list">
			<div class="item" @click="categoryClick(0)" :class="categoryIndex == 0 ? 'active' : ''">全部</div>
			<div v-for="(item,index) in categoryList" @click="categoryClick(index+1)" :key="index" class="item" :class="categoryIndex == index+1 ? 'active' : ''">{{item.typename}}</div>
		</div>
		<div class="z-box">
			<div class="section-content" v-for="item in forumList" :key="item.id" @click.stop="toForumDetail(item)">
				<img class="item-img" :src="$config.baseUrl + item.cover.split(',')[0]" @click.stop="imgPreView($config.baseUrl + item.cover.split(',')[0])" />
				<div class="item-title">{{item.title}}</div>
				<div class="item-user">发布人：{{item.isanon==1&&item.userid!=userid?'匿名':item.username}}</div>
				<div class="item-time">{{item.addtime}}</div>
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
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				formSearch: {},
				title: '',
				layouts: '',
				forumList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				previewImg: '',
				previewVisible: false,
				userid: localStorage.getItem('frontUserid'),
				categoryIndex: 0,
				categoryList: [],
			}
		},
		created() {
			this.getCategoryList()
		},
		//方法集合
		methods: {
			getCategoryList(){
				this.$http.get('forumtype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data.list
						this.getForumList(1);
					}
				});
			},
			categoryClick(index) {
				this.categoryIndex = index
				this.getForumList()
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			getForumList(page) {
				let params = {page, limit: this.pageSize, isdone: '开放', sort: 'istop,toptime', order: 'desc,desc',delflag: 0};
				let searchWhere = {};
				if(this.title != '') searchWhere.title = '%' + this.title + '%';
				if(this.categoryIndex!=0){
					searchWhere.typename = this.categoryList[this.categoryIndex - 1].typename
				}
				this.$http.get('forum/flist', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.forumList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			curChange(page) {
				this.getForumList(page);
			},
			prevClick(page) {
				this.getForumList(page);
			},
			nextClick(page) {
				this.getForumList(page);
			},
			toForumAdd() {
				this.$router.push('/index/forumAdd');
			},
			toForumDetail(item) {
				this.$router.push({path: '/index/forumDetail', query: {id: item.id}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.forum-preview {
				padding: 20px 16%;
				margin: 20px auto;
				background: rgb(255, 255, 255);
				width: 100%;
				position: relative;
				.forum-title {
						width: 100%;
						div {
								margin: 0 auto 40px;
								color: #000;
								background: url(http://codegen.caihongy.cn/20250204/dea0da8558ec43668514abc9cb06b051.png) center center/100% 100%;
								font-weight: bold;
								display: block;
								width: 400px;
								font-size: 26px;
								line-height: 70px;
								text-align: center;
								height: 70px;
							}
		}
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
								/deep/ .el-form-item__content {
										display: flex;
										align-items: center;
									}
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
			.search-btn-item {
								display: flex;
								.searchBtn {
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
											}
				}
				
				.searchBtn:hover {
										opacity: 0.7;
									}
				
				.pubBtn {
										cursor: pointer;
										border: 0;
										border-radius: 0;
										padding: 0px 25px;
										margin: 0 10px 10px 0;
										outline: none;
										color: #666;
										background: #FADCE9;
										width: auto;
										font-size: 14px;
										line-height: 42px;
										height: 42px;
										.icon {
												margin: 0 10px 0 0;
												color: #666;
												font-size: 14px;
											}
				}
				
				.pubBtn:hover {
										opacity: 0.7;
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
								cursor: pointer;
								border-radius: 20px;
								padding: 0;
								margin: 0 10px 0 0;
								color: #fff;
								background: #ED8E82;
								width: 130px;
								font-size: 14px;
								line-height: 36px;
								text-align: center;
							}
			
			.item.active {
								cursor: pointer;
								border-radius: 20px;
								padding: 0;
								margin: 0 10px 0 0;
								color: #fff;
								background: #ED8E82;
								width: 130px;
								font-size: 14px;
								line-height: 36px;
								text-align: center;
							}
		}
		.z-box {
						padding: 20px;
						width: 100%;
						.section-content {
								cursor: pointer;
								border-radius: 10px;
								padding: 20px;
								margin: 0 0 10px;
								background: #fff;
								display: flex;
								width: 100%;
								border-color: #EEA39A;
								border-width: 1px;
								align-items: center;
								border-style: solid;
								flex-wrap: wrap;
								.item-img {
										border-radius: 0px;
										margin: 0 10px 0 0;
										width: 50px;
										transition: all .4s;
										height: 50px;
									}
				.item-title {
										color: #666;
										width: calc(100% - 60px);
										font-size: 14px;
									}
				.item-user {
										color: #666;
										flex: 1;
										font-size: 14px;
										line-height: 40px;
									}
				.item-time {
										color: #666;
										flex: 1;
										font-size: 14px;
										line-height: 40px;
									}
			}
			.section-content:hover {
								background: #F8E8F2;
								.item-title {
										color: #111;
									}
				.item-user {
										color: #111;
									}
				.item-time {
										color: #111;
									}
			}
		}
	}
</style>
