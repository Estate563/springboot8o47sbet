<template>
	<div>
	<!--  -->
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'Ξ'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/yinshijihua?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item3"><a href="javascript:void(0);">详情</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="detail-preview">
			<div class="attr">
				<div class="info">
					<div class="title-item">
						<div class="detail-title">
							{{detail.jihuamingcheng}}
						</div>
					</div>
					<div class="item">
						<div class="lable">早餐搭配</div>
						<div class="text "  >{{detail.zaocandapei}}</div>
					</div>
					<div class="item">
						<div class="lable">午餐搭配</div>
						<div class="text "  >{{detail.wucandapei}}</div>
					</div>
					<div class="item">
						<div class="lable">晚餐搭配</div>
						<div class="text "  >{{detail.wancandapei}}</div>
					</div>
					<div class="item">
						<div class="lable">加餐搭配</div>
						<div class="text "  >{{detail.jiacandapei}}</div>
					</div>
					<div class="item">
						<div class="lable">计划目标</div>
						<div class="text "  >{{detail.jihuamubiao}}</div>
					</div>
					<div class="item">
						<div class="lable">计划日期</div>
						<div class="text "  >{{detail.jihuariqi}}</div>
					</div>
					<div class="item">
						<div class="lable">用户账号</div>
						<div class="text "  >{{detail.yonghuzhanghao}}</div>
					</div>
					<div class="item">
						<div class="lable">用户姓名</div>
						<div class="text "  >{{detail.yonghuxingming}}</div>
					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('yinshijihua','修改')" @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('yinshijihua','删除')" @click="delClick">删除</el-button>
					</div>
				</div>
			</div>
		
			<div class="swiper3" v-if="detailBanner.length">
				<div class="big">
					<img id="big" :src="swiperBigUrl" class="image">
				</div>
				<div class="samll">
					<div class="swiper3-small-item" v-for="item in detailBanner" :key="item.id">
						<img v-if="item.substr(0,4)=='http'" :src="item" @click="swiperClick3(item)" class="image">
						<img v-else :src="baseUrl + item" @click="swiperClick3(baseUrl + item)" class="image">
					</div>
				</div>
			</div>


		

			<el-tabs class="detail-tabs" v-model="activeName" type="border-card" v-if="tabsNum>0" >
				<el-tab-pane label="计划详情" name="first">
					<div class="ql-snow ql-editor" v-html="detail.jihuaxiangqing"></div>
				</el-tab-pane>
			</el-tabs>

		</div>
		<div class="share_view">
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	export default {
		//数据集合
		data() {
			return {
				tablename: 'yinshijihua',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '饮食计划'
					}
				],
				title: '',
				detailBanner: [],
				userid: localStorage.getItem('frontUserid'),
				id: 0,
				detail: {},
				tabsNum: 1,
				activeName: 'first',
				total: 1,
				pageSize: 10,
				totalPage: 1,
				buynumber: 1,
				centerType: false,
				storeupType: false,
				shareUrl: location.href,
				swiperBigUrl: null,
			}
		},
		created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0) {
				this.centerType = true
			}
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			
			this.init();
		},
		mounted() {
		},
		//方法集合
		methods: {
			swiperClick3(src) {
				this.swiperBigUrl = src
			},
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						this.title = this.detail.jihuamingcheng;
						if(this.detail.fengmian) {
							this.detailBanner = this.detail.fengmian.split(",w").length>1?[this.detail.fengmian]:this.detail.fengmian.split(',');
						}
						this.$forceUpdate();
						if(localStorage.getItem('frontToken')){
						}

					}
					if (this.detailBanner.length) {
						if (this.detailBanner[0].substr(0,4)=='http') {
							this.swiperBigUrl = this.detailBanner[0]
						} else {
							this.swiperBigUrl = this.baseUrl + this.detailBanner[0]
						}
					}
				});
			},
			curChange(page) {
				this.getDiscussList(page);
			},
			prevClick(page) {
				this.getDiscussList(page);
			},
			nextClick(page) {
				this.getDiscussList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getDiscussList(1);
			},
			// 返回按钮
			backClick(){
				if(this.storeupType){
					history.back()
				}else{
					let params = {}
					if(this.centerType){
						params.centerType = 1
					}
					this.$router.push({path: '/index/yinshijihua', query: params});
				}
			},
			// 下载
			download(file ){
				if(!file) {
					this.$message({
						type: 'error',
						message: '文件不存在',
						duration: 1500,
					});
					return;
				}
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.baseUrl + '/file/download?fileName=' + arr, {
					headers: {
						token: localStorage.getItem("frontToken")
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] :'') + this.$config.name + '/file/download?fileName=' + arr, {
						headers: {
							token: localStorage.getItem("frontToken")
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},


			// 权限判断
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			// 修改
			editClick(){
				this.$router.push(`/index/yinshijihuaAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此饮食计划？') .then(_ => {
					this.$http.post('yinshijihua/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$message({
								type: 'success',
								message: '删除成功!',
								duration: 1500,
								onClose: () => {
									history.back()
								}
							});
						}
					});
				}).catch(_ => {});
			},
		},
		components: {
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.detail-preview {
		padding: 0 16%;
		margin: 10px auto;
		display: flex;
		width: 100%;
		position: relative;
		flex-wrap: wrap;
		.attr {
			padding: 0 10px;
			background: none;
			display: flex;
			width: 60%;
			position: relative;
			order: 2;
			.info {
				padding: 0;
				margin: 0 0 0 10px;
				background: #fff;
				flex: 1;
				.title-item {
					border: 1px solid #ED8E82;
					border-radius: 10px;
					padding: 5px 10px;
					margin: 0 0 10px 0;
					background: #fff;
					display: flex;
					justify-content: space-between;
					align-items: center;
					.detail-title {
						color: #000;
						font-size: 16px;
					}
				}
				.item {
					border: 1px solid #ED8E82;
					border-radius: 10px;
					padding: 8px 10px;
					margin: 0 0 10px 0;
					background: #fff;
					display: flex;
					justify-content: flex-start;
					align-items: center;
					.lable {
						padding: 0 10px;
						color: #000;
						white-space: nowrap;
						width: 130px;
						font-size: 14px;
						line-height: 24px;
						text-align: right;
						height: auto;
					}
					.text {
						padding: 0 10px;
						color: #000;
						word-break: break-all;
						flex: 1;
						font-size: 14px;
						line-height: 24px;
						height: auto;
					}
					.price {
						color: #f00;
					}
					.bold {
						font-weight: bold;
					}
					.link {
						cursor: pointer;
						text-decoration: underline;
					}
				}
				.btn_box {
					padding: 10px 0;
					display: flex;
					flex-wrap: wrap;
				}
				.editBtn {
					border: 0;
					cursor: pointer;
					border-radius: 0;
					padding: 0 20px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: #B177D5;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
				}
				.editBtn:hover {
					opacity: 0.7;
				}
				.delBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0 5px 0 0;
					color: #fff;
					font-size: 14px;
					line-height: 40px;
					border-radius: 0;
					outline: none;
					background: #80699A;
					width: auto;
					height: 40px;
					order: 1;
				}
				.delBtn:hover {
					opacity: 0.7;
				}
			}
		}
		.swiper3 {
			background: #fff;
			width: 40%;
			height: auto;
			order: 1;
			.big {
				margin: 0 0 20px;
				background: #fff;
				width: 100%;
				position: relative;
				height: 600px;
				img {
					object-fit: cover;
					display: block;
					width: 100%;
					height: 100%;
				}
			}
			.samll {
				padding: 0 5px;
				background: #fff;
				display: flex;
				width: 100%;
				height: 100px;
				.swiper3-small-item {
					margin: 0 5px;
					flex: 1;
					background: #fff;
					position: relative;
					height: 100%;
					img {
						object-fit: cover;
						display: block;
						width: 100%;
						height: 100%;
					}
				}
			}
		}
		.detail-tabs {
			border: 1px solid #EEA39A;
			border-radius: 10px;
			box-shadow: 0 0px 0px 0 rgba(0, 0, 0, .1);
			padding: 20px;
			margin: 10px 0;
			background: #FFF;
			width: 100%;
			order: 7;
			& /deep/ .el-tabs__header .el-tabs__nav-wrap {
				margin-bottom: 0;
			}
			/deep/ .el-tabs__header {
				border-radius: 10px;
				padding: 10px 20px;
				margin: 0;
				background: #F8E8F2;
				border-color: #EEA39A;
				border-width: 1px;
				border-style: solid;
			}
			
			/deep/ .el-tabs__header .el-tabs__item {
				border: 0;
				padding: 0 20px;
				margin: 0 5px;
				color: #000;
				font-weight: 500;
				display: inline-block;
				font-size: 14px;
				line-height: 40px;
				border-radius: 30px;
				background: transparent;
				position: relative;
				list-style: none;
				height: 40px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item:hover {
				border: 0;
				color: #fff;
				background: #EEA39A;
			}
			
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border: 0;
				color: #fff;
				background: #EEA39A;
			}
			
			/deep/ .el-tabs__content {
				padding: 15px 0;
			}
		}
	}
	.share_view{
		box-shadow: 0 1px 6px rgba(0,0,0,.3);
		z-index: 11;
		bottom: 20%;
		background: #fff;
		position: fixed;
		right: 0;
		.share:last-of-type{
			border:none;
		}
	}
</style>
