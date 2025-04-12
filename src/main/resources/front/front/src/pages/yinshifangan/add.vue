<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="80px"
			>
			<el-form-item class="add-item" label="食谱名称" prop="shipumingcheng">
				<el-input v-model="ruleForm.shipumingcheng" 
					placeholder="食谱名称" clearable :disabled=" false  ||ro.shipumingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="食谱分类" prop="shipufenlei">
				<el-select v-model="ruleForm.shipufenlei" placeholder="请选择食谱分类" :disabled=" false  ||ro.shipufenlei" >
					<el-option
						v-for="(item,index) in shipufenleiOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="食谱照片" v-if="type!='cross' || (type=='cross' && !ro.shipuzhaopian)" prop="shipuzhaopian">
				<file-upload
					tip="点击上传食谱照片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.shipuzhaopian?ruleForm.shipuzhaopian:''"
					@change="shipuzhaopianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="食谱照片" prop="shipuzhaopian">
				<img v-if="ruleForm.shipuzhaopian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.shipuzhaopian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.shipuzhaopian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="食谱用料" prop="shipuyongliao">
				<el-input v-model="ruleForm.shipuyongliao" 
					placeholder="食谱用料" clearable :disabled=" false  ||ro.shipuyongliao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="卡路里含量" prop="kalulihanliang">
				<el-input v-model="ruleForm.kalulihanliang" 
					placeholder="卡路里含量" clearable :disabled=" false  ||ro.kalulihanliang"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="分享时间" prop="fenxiangshijian">
				<el-date-picker
					:disabled=" false  ||ro.fenxiangshijian"
					format="yyyy 年 MM 月 dd 日"
					value-format="yyyy-MM-dd"
					v-model="ruleForm.fenxiangshijian" 
					type="date"
					placeholder="分享时间">
				</el-date-picker> 
			</el-form-item>
			<el-form-item class="add-item" label="食谱视频" v-if="type!='cross' || (type=='cross' && !ro.shipushipin)" prop="shipushipin">
				<file-upload
					tip="点击上传食谱视频"
					action="file/upload"
					:limit="1"
					:type="2"
					:multiple="true"
					:fileUrls="ruleForm.shipushipin?ruleForm.shipushipin:''"
					@change="shipushipinUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="食谱视频" prop="shipushipin">
				<div class="viewBtn" v-if="ruleForm.shipushipin" @click="download(baseUrl + ruleForm.shipushipin)" type="success">预览</div>
				<div class="unviewBtn" v-else disabled>暂无</div>
			</el-form-item>
			<el-form-item class="add-item" label="营养成分" prop="yingyangchengfen">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="营养成分"
					v-model="ruleForm.yingyangchengfen">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="菜谱做法" prop="caipuzuofa">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="菜谱做法"
					v-model="ruleForm.caipuzuofa">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="食谱详情" prop="shipuxiangqing">
				<editor 
					v-model="ruleForm.shipuxiangqing" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont icon-kaitongfuwu"></span>
					<span class="text">提交</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu1"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
				ro:{
					shipumingcheng : false,
					shipufenlei : false,
					shipuzhaopian : false,
					shipuyongliao : false,
					kalulihanliang : false,
					fenxiangshijian : false,
					yingyangchengfen : false,
					caipuzuofa : false,
					shipushipin : false,
					shipuxiangqing : false,
					thumbsupnum : false,
					crazilynum : false,
					clicktime : false,
					discussnum : false,
					storeupnum : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					shipumingcheng: '',
					shipufenlei: '',
					shipuzhaopian: '',
					shipuyongliao: '',
					kalulihanliang: '',
					fenxiangshijian: '',
					yingyangchengfen: '',
					caipuzuofa: '',
					shipushipin: '',
					shipuxiangqing: '',
					thumbsupnum: '',
					crazilynum: '',
					clicktime: '',
					discussnum: '',
					storeupnum: '',
				},
				shipufenleiOptions: [],


				rules: {
					shipumingcheng: [
					],
					shipufenlei: [
					],
					shipuzhaopian: [
					],
					shipuyongliao: [
					],
					kalulihanliang: [
					],
					fenxiangshijian: [
					],
					yingyangchengfen: [
					],
					caipuzuofa: [
					],
					shipushipin: [
					],
					shipuxiangqing: [
					],
					thumbsupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					crazilynum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					clicktime: [
					],
					discussnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
				},
				centerType: false,
			};
		},
		computed: {



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
			this.ruleForm.fenxiangshijian = this.getCurDate()
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o=='shipumingcheng'){
							this.ruleForm.shipumingcheng = obj[o];
							this.ro.shipumingcheng = true;
							continue;
						}
						if(o=='shipufenlei'){
							this.ruleForm.shipufenlei = obj[o];
							this.ro.shipufenlei = true;
							continue;
						}
						if(o=='shipuzhaopian'){
							this.ruleForm.shipuzhaopian = obj[o].split(",")[0];
							this.ro.shipuzhaopian = true;
							continue;
						}
						if(o=='shipuyongliao'){
							this.ruleForm.shipuyongliao = obj[o];
							this.ro.shipuyongliao = true;
							continue;
						}
						if(o=='kalulihanliang'){
							this.ruleForm.kalulihanliang = obj[o];
							this.ro.kalulihanliang = true;
							continue;
						}
						if(o=='fenxiangshijian'){
							this.ruleForm.fenxiangshijian = obj[o];
							this.ro.fenxiangshijian = true;
							continue;
						}
						if(o=='yingyangchengfen'){
							this.ruleForm.yingyangchengfen = obj[o];
							this.ro.yingyangchengfen = true;
							continue;
						}
						if(o=='caipuzuofa'){
							this.ruleForm.caipuzuofa = obj[o];
							this.ro.caipuzuofa = true;
							continue;
						}
						if(o=='shipushipin'){
							this.ruleForm.shipushipin = obj[o];
							this.ro.shipushipin = true;
							continue;
						}
						if(o=='shipuxiangqing'){
							this.ruleForm.shipuxiangqing = obj[o];
							this.ro.shipuxiangqing = true;
							continue;
						}
						if(o=='thumbsupnum'){
							this.ruleForm.thumbsupnum = obj[o];
							this.ro.thumbsupnum = true;
							continue;
						}
						if(o=='crazilynum'){
							this.ruleForm.crazilynum = obj[o];
							this.ro.crazilynum = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
					}
				});
				this.shipufenleiOptions = "家常菜,快手菜,减脂餐,特色主食,菜式菜系,蔬菜,肉类大全,食疗养身,烘焙,其他".split(',')

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`yinshifangan/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}


						await this.$http.post(`yinshifangan/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.$router.go(-1);
			},
			shipuzhaopianUploadChange(fileUrls) {
				this.ruleForm.shipuzhaopian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
			shipushipinUploadChange(fileUrls) {
				this.ruleForm.shipushipin = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 20px 16%;
		margin: 10px auto;
		background: #fff;
		width: 100%;
		position: relative;
		.add-update-form {
			border: 1px solid #EEA39A;
			border-radius: 10px;
			padding: 15px;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				padding: 10px;
				margin: 0 0 10px;
				background: none;
				display: inline-block;
				width: 49%;
				/deep/ .el-form-item__label {
					padding: 0 10px 0 0;
					color: #4B3E39;
					font-weight: 500;
					width: 80px;
					font-size: 14px;
					line-height: 40px;
					text-align: right;
				}
				/deep/ .el-form-item__content {
					margin-left: 80px;
				}
				.el-input {
					width: 100%;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #000;
					border-radius: 10px;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 1px solid #000;
					cursor: not-allowed;
					border-radius: 10px;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					background: #ddd;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 1px solid #000;
					border-radius: 10px;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 1px solid #000;
					cursor: not-allowed;
					border-radius: 10px;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					background: #ddd;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
					border: 1px solid #000;
					border-radius: 10px;
					padding: 0 10px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 1px solid #000;
					cursor: not-allowed;
					border-radius: 10px;
					padding: 0 10px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					background: #ddd;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #000;
					border-radius: 10px;
					padding: 0 10px 0 30px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 1px solid #000;
					cursor: not-allow;
					border-radius: 10px;
					padding: 0 10px 0 30px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					background: #ddd;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				/deep/ .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				/deep/ .upload .upload-img {
					border: 1px dashed #EEA39A;
					cursor: pointer;
					border-radius: 10px;
					color: #EEA39A;
					background: #F8E8F2;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px dashed #EEA39A;
					cursor: pointer;
					border-radius: 10px;
					color: #EEA39A;
					background: #F8E8F2;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px dashed #EEA39A;
					cursor: pointer;
					border-radius: 10px;
					color: #EEA39A;
					background: #F8E8F2;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				/deep/ .el-upload__tip {
					color: #838fa1;
					display: none;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 1px solid #000;
					border-radius: 10px;
					padding: 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 100%;
					font-size: 14px;
					height: 120px;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 1px solid #000;
					cursor: not-allowed;
					border-radius: 10px;
					padding: 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #ddd;
					width: 100%;
					font-size: 14px;
					height: 120px;
				}
				/deep/ .el-input__inner::placeholder {
					color: #999;
					font-size: 14px;
				}
				/deep/ textarea::placeholder {
					color: #999;
					font-size: 14px;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: 0 0 0px rgba(75,223,201,.5);
					margin: 0;
					width: 100%;
					border-color: #ccc;
					border-width: 0;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					border-radius: 10px;
					width: 100px;
					height: 100px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 35px;
					border-radius: 4px;
					outline: none;
					background: #EEA39A;
					width: auto;
					height: 35px;
				}
				.viewBtn:hover {
					opacity: 0.7;
				}
				.unviewBtn {
					border: 0;
					cursor: not-allowed;
					padding: 0 10px;
					margin: 0;
					color: #6E6E6E;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: #E8E8E8;
					width: auto;
					height: 30px;
				}
				.unviewBtn:hover {
					opacity: 1;
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 0;
				.submitBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 15px;
					margin: 0 20px 0 0;
					outline: none;
					background: #EEA39A;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
					.icon {
						color: rgba(255, 255, 255, 1);
						display: none;
					}
					.text {
						color: rgba(255, 255, 255, 1);
					}
				}
				.submitBtn:hover {
					opacity: 0.7;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 15px;
					margin: 0 20px 0 0;
					outline: none;
					background: #E8E8E8;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
					.icon {
						color: #fff;
						display: none;
					}
					.text {
						color: #6E6E6E;
					}
				}
				.closeBtn:hover {
					opacity: 0.7;
					.icon {
						color: #fff;
					}
					.text {
						color: #6E6E6E;
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
