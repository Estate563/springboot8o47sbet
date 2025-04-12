<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="120px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="食谱名称" prop="shipumingcheng" >
					<el-input v-model="ruleForm.shipumingcheng" placeholder="食谱名称" clearable  :readonly="ro.shipumingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="食谱名称" prop="shipumingcheng" >
					<el-input v-model="ruleForm.shipumingcheng" placeholder="食谱名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="食谱分类" prop="shipufenlei" >
					<el-select :disabled="ro.shipufenlei" v-model="ruleForm.shipufenlei" placeholder="请选择食谱分类" >
						<el-option
							v-for="(item,index) in shipufenleiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="食谱分类" prop="shipufenlei" >
					<el-input v-model="ruleForm.shipufenlei"
						placeholder="食谱分类" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.shipuzhaopian" label="食谱照片" prop="shipuzhaopian" >
					<file-upload
						tip="点击上传食谱照片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.shipuzhaopian?ruleForm.shipuzhaopian:''"
						@change="shipuzhaopianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.shipuzhaopian" label="食谱照片" prop="shipuzhaopian" >
					<img v-if="ruleForm.shipuzhaopian.substring(0,4)=='http'&&ruleForm.shipuzhaopian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.shipuzhaopian" width="100" height="100">
					<img v-else-if="ruleForm.shipuzhaopian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.shipuzhaopian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.shipuzhaopian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="食谱用料" prop="shipuyongliao" >
					<el-input v-model="ruleForm.shipuyongliao" placeholder="食谱用料" clearable  :readonly="ro.shipuyongliao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="食谱用料" prop="shipuyongliao" >
					<el-input v-model="ruleForm.shipuyongliao" placeholder="食谱用料" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="卡路里含量" prop="kalulihanliang" >
					<el-input v-model="ruleForm.kalulihanliang" placeholder="卡路里含量" clearable  :readonly="ro.kalulihanliang"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="卡路里含量" prop="kalulihanliang" >
					<el-input v-model="ruleForm.kalulihanliang" placeholder="卡路里含量" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="分享时间" prop="fenxiangshijian" >
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.fenxiangshijian" 
						type="date"
						:readonly="ro.fenxiangshijian"
						placeholder="分享时间"
					></el-date-picker> 
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.fenxiangshijian" label="分享时间" prop="fenxiangshijian" >
					<el-input v-model="ruleForm.fenxiangshijian" placeholder="分享时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info'&& !ro.shipushipin" label="食谱视频" prop="shipushipin" >
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
				<el-form-item v-else-if="ruleForm.shipushipin" label="食谱视频" prop="shipushipin" >
					<el-button class="viewBtn" type="text" size="small" @click="download($base.url+ruleForm.shipushipin)">
						<span class="icon iconfont icon-chakan14"></span>
						预览
					</el-button>
				</el-form-item>
				<el-form-item v-else-if="!ruleForm.shipushipin" label="食谱视频" prop="shipushipin" >
					<el-button class="unBtn" type="text" size="small">
						<span class="icon iconfont icon-xihuan"></span>
						暂无
					</el-button>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="营养成分" prop="yingyangchengfen" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="营养成分"
					v-model="ruleForm.yingyangchengfen" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.yingyangchengfen" label="营养成分" prop="yingyangchengfen" >
				<span class="text">{{ruleForm.yingyangchengfen}}</span>
			</el-form-item>
			<el-form-item class="textarea" v-if="type!='info'" label="菜谱做法" prop="caipuzuofa" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="菜谱做法"
					v-model="ruleForm.caipuzuofa" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.caipuzuofa" label="菜谱做法" prop="caipuzuofa" >
				<span class="text">{{ruleForm.caipuzuofa}}</span>
			</el-form-item>
			<el-form-item v-if="type!='info'"  label="食谱详情" prop="shipuxiangqing" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.shipuxiangqing" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.shipuxiangqing" label="食谱详情" prop="shipuxiangqing" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.shipuxiangqing"></span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
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
					clicktime: '',
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
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					crazilynum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					clicktime: [
					],
					discussnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
			this.ruleForm.fenxiangshijian = this.getCurDate()
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
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
							this.ruleForm.shipuzhaopian = obj[o];
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
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.shipufenleiOptions = "家常菜,快手菜,减脂餐,特色主食,菜式菜系,蔬菜,肉类大全,食疗养身,烘焙,其他".split(',')
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `yinshifangan/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.shipuxiangqing = this.ruleForm.shipuxiangqing.replace(reg,'../../../springboot8o47sbet/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.shipuzhaopian!=null) {
						this.ruleForm.shipuzhaopian = this.ruleForm.shipuzhaopian.replace(new RegExp(this.$base.url,"g"),"");
					}
					if(this.ruleForm.shipushipin!=null) {
						this.ruleForm.shipushipin = this.ruleForm.shipushipin.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `yinshifangan/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.yinshifanganCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
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
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.yinshifanganCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			shipuzhaopianUploadChange(fileUrls) {
				this.ruleForm.shipuzhaopian = fileUrls;
			},
			shipushipinUploadChange(fileUrls) {
				this.ruleForm.shipushipin = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 20px 0;
	}
	.add-update-preview {
		padding: 0px;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 49%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #9E9E9E;
		font-weight: 600;
		width: 120px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 120px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #333;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 16px;
		line-height: 40px;
		min-width: 50%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #2F3644;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: auto;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #2F3644;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: auto;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #2F3644;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		color: #666;
		background: none;
		width: auto;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #2F3644;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: none;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 0px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: none;
		width: auto;
		font-size: 16px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px solid #2F3644;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px solid #2F3644;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px solid #2F3644;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #2F3644;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 14px;
		min-width: 400px;
		height: 120px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0;
				cursor: not-allowed;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: none;
				width: auto;
				font-size: 14px;
				min-width: 400px;
				height: auto;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 1px solid #000000;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #000;
			background: #fff;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 1px solid #000000;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #000;
			background: #fff;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 1px solid #000000;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #000;
			background: #fff;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 1px solid #000000;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #000;
			background: #fff;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 1px solid #000000;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #000;
			background: #fff;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
