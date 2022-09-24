<template>
    <div>
        <div style="display: flex;justify-content: space-between">
            <el-button icon="el-icon-plus" type="primary" @click="showAddMapsView">添加地图</el-button>
            <el-button icon="el-icon-refresh" type="success" @click="initMaps"></el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table :data="maps" border stripe>
                <el-table-column type="selection" align="left" width="55"></el-table-column>
                <el-table-column prop="id" label="id" fixed width="40" align="left"></el-table-column>
                <el-table-column prop="preview" label="预览" width="100" align="left">
                    <template slot-scope="scope">
                        <img :src="scope.row.preview" min-width="60px" height="60px"/>
                    </template>
                </el-table-column>
                <el-table-column prop="name" label="名称" width="140" align="left"></el-table-column>
                <el-table-column prop="createDate" label="创建日期" width="150" align="left"></el-table-column>
                <el-table-column prop="detailInfo" label="详细信息" width="900" align="left"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button @click="showEditMapView(scope.row)" width="60" style="padding: 3px" size="mini">编辑</el-button>
                        <el-button @click="deleteMap(scope.row)" style="padding: 3px" size="mini" type="danger">删除</el-button>
                        <el-button @click="showMapView(scope.row)" style="padding: 3px" icon="el-icon-location" size="mini">查看地图</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog
                :title="dialogTitle"
                :visible.sync="dialogVisible"
                width="50%">
            <div>
                <el-form :model="elformMap" :rules="rules" ref="MapForm" label-position="labelPosition">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="id:" prop="id">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="elformMap.id"
                                          placeholder="请输入图片id"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="预览:" prop="preview">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="elformMap.preview"
                                          placeholder="请输入图片地址"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="名称:" prop="name">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="elformMap.name"
                                          placeholder="请输入图片名称"></el-input>
                            </el-form-item>
                        </el-col>

                        <el-col :span="6">
                            <el-form-item label="创建日期:" prop="createDate">
                                <el-date-picker
                                        v-model="elformMap.createDate"
                                        size="mini"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        style="width: 130px;"
                                        placeholder="创建日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="21" >
                            <el-form-item label="详细信息:" prop="detailInfo">
                                <div class="text-area">
                                <el-input type="textarea" size="mini"  :rows="5" prefix-icon="el-icon-edit"
                                          v-model="elformMap.detailInfo" placeholder="请输入详细信息"></el-input>
                                </div>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="doAddMap">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
                :title="地图"
                :visible.sync="dialogVisibleToMap"
                width="100%">
            <div>
                <baidu-map class="map" center="北京"></baidu-map>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisibleToMap = false">关 闭</el-button>
            </span>
        </el-dialog>
    </div>
</template> `

<script>
    export default {
        name: "MapManage",
        data() {
            return {
                dialogVisible: false,
                dialogTitle: '添加地图',
                dialogVisibleToMap: false,
                maps: [],
                temp: 0,
                center:{lng:0,lat:0},
                zoom:3,
                map: {
                    id: 0,
                    preview: 0,
                    name: 0,
                    createDate: 0,
                    detailInfo: 0,
                    state: 0,
                    type: 0,
                    userGroup: 0,
                    tag: 0
                },
                elformMap:
                    {
                        id: 0,
                        preview: 0,
                        name: 0,
                        createDate: 0,
                        detailInfo: 0,
                        state: 0,
                        type: 0,
                        userGroup: 0,
                        tag: 0
                    },
                rules:
                    {
                        id: [{required: true, message: '请输入地图id', trigger: 'blur'}],
                        preview: [{required: true, message: '请输入地图地址', trigger: 'blur'}],
                        name: [{required: true, message: '请输入地图名称', trigger: 'blur'}],
                        createDate: [{required: true, message: '请输入地图创建日期', trigger: 'blur'}],
                        detailInfo: [{required: true, message: '请输入地图详细信息', trigger: 'blur'}],
                    }
            }
        },
        mounted() {
            this.initMaps();
        },
        methods: {
            showEditMapView(data) {
                this.dialogTitle = '编辑';
                this.elformMap = data;
                this.temp = 1;
                this.dialogVisible = true;
            },
            deleteMap(data) {
                this.$confirm('此操作将删除【' + data.name + '】地图，是否继续？', '提示', {
                    cancelButtonText: '取消',
                    confirmButtonText: '确定'
                }).then(() => {
                    this.deleteRequest("/map/manage/" + data.id).then(resp => {
                        if (resp) {
                            this.initMaps();
                        }
                    })
                }).catch(() => {
                    this.$message.info("取消删除!");
                })
            },
            showAddMapsView() {
                //数据初始化
                this.emptyEmp();
                this.temp = 0;
                this.dialogTitle = '添加地图';
                this.dialogVisible = true;
            },
            initMaps() {
                this.getRequest("/map/manage/").then(resp => {
                    if (resp) {
                        this.maps = resp;
                    }
                })
            },
            emptyEmp() {
                this.elformMap = {
                    id: "",
                    preview: "",
                    name: "",
                    createDate: "",
                    detailInfo: "",
                    state: "",
                    type: "",
                    userGroup: "",
                    tag: ""
                };
            },
            showMapView(data)
            {
                this.dialogVisibleToMap = true;
            },
            doAddMap() {
                if (this.temp == 1) {
                    this.$refs['MapForm'].validate(valid => {
                        if (valid) {
                            this.putRequest("/map/manage/", this.elformMap).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initMaps();
                                }
                            })
                        }
                    });
                } else {
                    this.$refs['MapForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/map/manage/", this.elformMap).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initMaps();
                                }
                            })
                        }
                    });
                }
            },
        }
    }
</script>

<style scoped>
    .map {
        width: 100%;
        height: 500px;
    }
    .text-area textarea{
        width: 100%;
        height:100%;
    }
</style>