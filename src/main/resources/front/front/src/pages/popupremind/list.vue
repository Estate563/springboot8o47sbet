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
					<div class="lable">标题：</div>
					<el-input v-model="formSearch.title" placeholder="标题" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item">
					<div class="lable">类型：</div>
					<el-input v-model="formSearch.type" placeholder="类型" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item">
					<div class="lable">提醒时间：</div>
					<el-date-picker
						class="datetimerange"
						v-model="timeRange"
						type="datetimerange"
						range-separator="至"
						start-placeholder="提醒时间起始"
						end-placeholder="提醒时间结束"
						value-format="yyyy-MM-dd HH:mm:ss">
					</el-date-picker>
				</el-form-item>
				<el-button class="list-search-btn" v-if=" true " type="primary" @click="getList(1, curFenlei)">
					<i class="el-icon-search"></i>
					查询
				</el-button>
				<el-button class="list-add-btn" v-if="btnAuth('popupremind','新增')" type="primary" @click="add('/index/popupremindAdd')">
					<i class="el-icon-circle-plus-outline"></i>
					添加
				</el-button>
			</el-form>
			<div class="list">
				<el-table class="tables" :stripe='false'
					:style='{"width":"100%","padding":"0","borderColor":"#eee","borderStyle":"solid","borderWidth":"1px 0 0 1px","background":"#fff"}' 
					:border='true'
					v-if="btnAuth('popupremind','查看')" :data="dataList">
					<el-table-column :resizable='true' :sortable='false' prop="title" label="标题">
						<template slot-scope="scope">
							{{scope.row.title}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false' prop="type" label="类型">
						<template slot-scope="scope">
							{{scope.row.type}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false' prop="remindtime" label="提醒时间">
						<template slot-scope="scope">
							{{scope.row.remindtime}}
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if="btnAuth('popupremind','查看')" type="success" size="mini"
								@click="toDetail(scope.row)">
								查看
							</el-button>
							<el-button class="edit" v-if="btnAuth('popupremind','修改') " type="primary" size="mini"
								@click="editClick(scope.row)">
								修改
							</el-button>
							<el-button class="del" v-if="btnAuth('popupremind','删除') " type="danger" size="mini"
								@click="delClick(scope.row )">
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
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
						name: '弹窗提醒'
					}
				],
				formSearch: {
					title: '',
					type: '',
					remindtime: '',
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
				typeOptions: [],
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
			this.typeOptions = '个人,全局'.split(',');
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
				if (this.formSearch.title != '') searchWhere.title = '%' + this.formSearch.title + '%';
				if (this.formSearch.type != '') searchWhere.type = this.formSearch.type;
				if (this.timeRange.length > 0) {
					searchWhere.remindtimestart = this.timeRange[0];
					searchWhere.remindtimeend = this.timeRange[1];
				}
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`popupremind/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(res => {
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
			sortClick(type){
				if(this.sortType==type){
					if(this.sortOrder == 'desc'){
						this.sortOrder = 'asc'
					}else{
						this.sortOrder = 'desc'
					}
				}else{
					this.sortType = type
					this.sortOrder = 'desc'
				}
				this.getList(1, '全部')
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
				this.$router.push({path: '/index/popupremindDetail', query: params});
			},
			editClick(row) {
				this.$router.push(`/index/popupremindAdd?type=edit&&id=${row.id}`);
			},
			delClick(row) {
				this.$confirm('是否删除此弹窗提醒？')
					.then(_ => {
						this.$http.post('popupremind/delete', [row.id]).then(res => {
							if (res.data.code == 0) {
								this.$message({
									type: 'success',
									message: '删除成功!',
									duration: 1500,
									onClose: () => {
										this.getList(1, '全部')
									}
								});
							}
						});
					}).catch(_ => {});
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
			.el-table /deep/ .el-table__header-wrapper thead {
				color: #999;
				font-weight: 500;
				width: 100%;
			}
			
			.el-table /deep/ .el-table__header-wrapper thead tr {
				background: #fff;
			}
			
			.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 12px 0;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: left;
			}
			
			.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 10px;
				word-wrap: normal;
				word-break: break-all;
				white-space: normal;
				font-weight: bold;
				display: inline-block;
				vertical-align: middle;
				width: 100%;
				line-height: 24px;
				position: relative;
				text-overflow: ellipsis;
			}
			
			
			.el-table /deep/ .el-table__body-wrapper tbody {
				width: 100%;
			}
			
			.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: #fff;
			}
			
			.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 12px 0;
				color: #999;
				background: #fff;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: left;
			}
			
			
			.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 12px 0;
				color: #fff;
				background: rgba(64, 158, 255, .5);
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: left;
			}
			
			.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 12px 0;
				color: #999;
				background: #fff;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: left;
			}
			
			.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 10px;
				overflow: hidden;
				word-break: break-all;
				white-space: normal;
				line-height: 24px;
				text-overflow: ellipsis;
			}
		}
	}
</style>
