<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'Ξ'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div v-if="centerType" class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="list-preview">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="list-item">
					<div class="lable">运动名称：</div>
					<el-input v-model="formSearch.yundongmingcheng" placeholder="运动名称" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item">
					<div class="lable">运动类型：</div>
					<el-input v-model="formSearch.yundongleixing" placeholder="运动类型" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-button class="list-search-btn" v-if=" true " type="primary" @click="getList(1, curFenlei)">
					<i class="el-icon-search"></i>
					查询
				</el-button>
				<el-button class="list-add-btn" v-if="btnAuth('yundongjihua','新增')" type="primary" @click="add('/index/yundongjihuaAdd')">
					<i class="el-icon-circle-plus-outline"></i>
					添加
				</el-button>
			</el-form>
			<div class="list">
				<div class="list5">
					<div v-for="(item,index) in dataList" :key="index" class="list-item" @click.stop="toDetail(item)">
						<div class="imgbox">
							<img @click.stop="imgPreView(item.tupian)" v-if="item.tupian && item.tupian.substr(0,4)=='http'&&item.tupian.split(',w').length>1" :src="item.tupian" class="image" />
							<img @click.stop="imgPreView(item.tupian.split(',')[0])" v-else-if="item.tupian && item.tupian.substr(0,4)=='http'" :src="item.tupian.split(',')[0]" class="image" />
							<img @click.stop="imgPreView(baseUrl + (item.tupian?item.tupian.split(',')[0]:''))" v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" class="image" />
						</div>
						<div class="infoBox">
							<div class="name">{{item.yundongmingcheng}}</div>
							<div class="name">运动类型:{{item.yundongleixing}}</div>
							<div class="bottomInfo">
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{item.addtime.split(' ')[0]}}</span>
								</div>
								<div class="publisher_item">
									<span class="icon iconfont icon-geren16"></span>
									<span class="label">发布人：</span>
									<span class="text">{{item.yonghuzhanghao}}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

	
			<el-pagination
				background
				id="pagination"
				class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:layout='["total","prev","pager","next","sizes"].join()'
				:total="total"
				:page-sizes="pageSizes"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>
		</div>
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
				layouts: '',
				swiperIndex: -1,
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '运动计划'
					}
				],
				formSearch: {
					yundongmingcheng: '',
					yundongleixing: '',
				},
				fenlei: [],
				dataList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				curFenlei: '全部',
				isPlain: false,
				indexQueryCondition: '',
				timeRange: [],
				centerType:false,
				previewImg: '',
				previewVisible: false,
				sortType: 'id',
				sortOrder: 'desc',
			}
		},
		async created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0){
				this.centerType = true
			}
			this.baseUrl = this.$config.baseUrl;
			await this.getFenlei();
			this.getList(1, '全部');
		},
		watch:{
			$route(newValue){
				this.getList(1, newValue.query.homeFenlei);
			}
		},
		//方法集合
		methods: {
			add(path) {
				let query = {}
				if(this.centerType){
					query.centerType = 1
				}
				this.$router.push({path: path,query:query});
			},
			async getFenlei() {
			},
			getList(page, fenlei, ref = '') {
				let params = {
					page,
					limit: this.pageSize,
				};
				let searchWhere = {};
				if (this.formSearch.yundongmingcheng != '') searchWhere.yundongmingcheng = '%' + this.formSearch.yundongmingcheng + '%';
				if (this.formSearch.yundongleixing != '') searchWhere.yundongleixing = '%' + this.formSearch.yundongleixing + '%';
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`yundongjihua/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.dataList = res.data.data.list;
						this.total = Number(res.data.data.total);
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			curChange(page) {
				this.getList(page);
			},
			prevClick(page) {
				this.getList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getList(1);
			},
			nextClick(page) {
				this.getList(page);
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			toDetail(item) {
				let params = {
					id: item.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/yundongjihuaDetail', query: params});
			},
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			backClick() {
				this.$router.push({path: '/index/center'});
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview {
		padding: 0 16%;
		margin: 10px auto;
		background: none;
		display: flex;
		width: 100%;
		position: relative;
		flex-wrap: wrap;
		.list-form-pv {
			padding: 10px;
			background: none;
			display: flex;
			width: 100%;
			align-items: center;
			flex-wrap: wrap;
			height: auto;
			order: 1;
			.list-item {
				margin: 0 10px 10px 0;
				/deep/.el-form-item__content {
					display: flex;
				}
				.lable {
					padding: 0 10px;
					color: #000;
					display: inline-block;
					width: auto;
					line-height: 42px;
				}
				.el-input {
					width: auto;
				}
				.datetimerange {
					border: 1px solid #000;
					border-radius: 0;
					padding: 3px 10px;
					outline: none;
					background: #fff;
					width: 100%;
					justify-content: center;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #000;
					border-radius: 0;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #000;
					width: 100%;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-select {
					width: auto;
				}
				.el-select /deep/ .el-input__inner {
				}
				.el-date-editor {
					width: auto;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #000;
					border-radius: 0;
					padding: 0 30px;
					margin: 0;
					outline: none;
					color: #000;
					width: 100%;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
			}
			.list-search-btn {
				cursor: pointer;
				border: 0;
				border-radius: 0;
				padding: 0px 15px;
				margin: 0 10px 10px 0;
				outline: none;
				color: #fff;
				background: #ED8E82;
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					font-size: 14px;
				}
			}
			.list-add-btn {
				cursor: pointer;
				border: 0;
				border-radius: 0;
				padding: 0px 15px;
				margin: 0 10px 10px 0;
				outline: none;
				color: #666;
				background: #FADCE9;
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				i {
					margin: 0 10px 0 0;
					color: #666;
					font-size: 14px;
				}
			}
		}
		.list {
			margin: 0 0 10px;
			background: #fff;
			width: 100%;
			order: 4;
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
				
			.index-pv1 .animation-box:hover {
				transform: rotate(0) scale(1.2) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
				
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0) scale(0.8) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list5 {
				column-gap: 15px;
				margin: 20px auto;
				column-count: 4;
				width: 100%;
				.list-item {
					border: 1px solid #eee;
					padding: 10px;
					margin: 0 0 20px;
					overflow: hidden;
					background: #fff;
					display: block;
					position: relative;
					.imgbox {
						overflow: hidden;
						width: 100%;
						.image {
							filter: saturate(150%);
							transform: rotate(0deg);
							display: block;
							width: 100%;
							opacity: 0.9;
						}
					}
					.infoBox {
						padding: 10px 10px;
						left: 0px;
						bottom: 0;
						background: rgba(255, 255, 255, .8);
						display: flex;
						width: 100%;
						font-size: 14px;
						position: inherit;
						flex-wrap: wrap;
						transition: all 0.5s;
						.name {
							color: #555;
							width: 100%;
							font-size: 16px;
							text-align: center;
						}
						.price {
							border-radius: 30px;
							padding: 10px 30px;
							margin: 10px auto 0;
							color: #fff;
							background: #ED8E82;
							font-size: 14px;
							order: 1;
							.price_text {
								color: #fff;
								font-size: 18px;
							}
						}
						.bottomInfo {
							display: flex;
							flex-wrap: wrap;
							.time_item {
								padding: 0;
								width: 100%;
								text-align: center;
								order: 5;
								.icon {
									margin: 0 5px 0 0;
									color: #ED8E82;
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
									color: #ED8E82;
									font-size: 14px;
									line-height: 25px;
								}
							}
							.publisher_item {
								padding: 0;
								width: 100%;
								text-align: center;
								order: 4;
								.icon {
									margin: 0 5px 0 0;
									color: #ED8E82;
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
									color: #ED8E82;
									font-size: 14px;
									line-height: 25px;
								}
							}
							.like_item {
								padding: 0;
								width: 100%;
								text-align: center;
								order: 1;
								.icon {
									margin: 0 5px 0 0;
									color: #ED8E82;
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
									color: #ED8E82;
									font-size: 14px;
									line-height: 25px;
								}
							}
							.collect_item {
								padding: 0;
								width: 100%;
								text-align: center;
								order: 2;
								.icon {
									margin: 0 5px 0 0;
									color: #ED8E82;
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
									color: #ED8E82;
									font-size: 14px;
									line-height: 25px;
								}
							}
							.view_item {
								padding: 0;
								width: 100%;
								text-align: center;
								order: 3;
								.icon {
									margin: 0 5px 0 0;
									color: #ED8E82;
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
									color: #ED8E82;
									font-size: 14px;
									line-height: 25px;
								}
							}
						}
					}
				}
				.list-item:hover {
					border: 1px solid #ED8E82;
					background: #ED8E82;
					.imgbox {
						.image {
							transform: scale(1.05);
							ilter: saturate(100%);
							opacity: 1;
							transition: all 200ms linear;
						}
					}
					.infoBox {
						bottom: 0px;
						background: #fff;
						font-size: 14px;
						.name {
							color: #000;
							font-size: 16px;
						}
						.price {
							color: #fff;
							.price_text {
								font-weight: bold;
							}
						}
						.bottomInfo {
							display: flex;
							flex-wrap: wrap;
							.time_item {
								.icon {
									color: #ED8E82;
								}
								.label {
									color: #000;
								}
								.text {
									color: #ED8E82;
								}
							}
							.publisher_item {
								.icon {
									color: #ED8E82;
								}
								.label {
									color: #000;
								}
								.text {
									color: #ED8E82;
								}
							}
							.like_item {
								.icon {
									color: #ED8E82;
								}
								.label {
									color: #000;
								}
								.text {
									color: #ED8E82;
								}
							}
							.collect_item {
								.icon {
									color: #ED8E82;
								}
								.label {
									color: #000;
								}
								.text {
									color: #ED8E82;
								}
							}
							.view_item {
								.icon {
									color: #ED8E82;
								}
								.label {
									color: #000;
								}
								.text {
									color: #ED8E82;
								}
							}
						}
					}
				}
			}
		}
	}
</style>
