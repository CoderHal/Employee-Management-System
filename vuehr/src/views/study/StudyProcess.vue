<template>
    <div>
        <div style="display: flex;justify-content: space-between">
            <el-button icon="el-icon-plus" type="primary" @click="showAddProcessesView">添加心得</el-button>
            <el-button icon="el-icon-refresh" type="success" @click="initProcesses"></el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table :data="processes" border stripe>
                <el-table-column type="selection" align="left" width="55"></el-table-column>
                <el-table-column prop="id" label="id" fixed width="40" align="left"></el-table-column>
                <el-table-column prop="createDate" label="创建日期" width="150" align="left"></el-table-column>
                <el-table-column prop="title" label="标题" width="140" align="left"></el-table-column>
                <el-table-column prop="content" label="详细信息" width="900" align="left"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button @click="showEditProcessView(scope.row)" width="60" style="padding: 3px" size="mini">编辑</el-button>
                        <el-button @click="deleteProcess(scope.row)" style="padding: 3px" size="mini" type="danger">删除</el-button>
                        <el-button @click="showProcessView(scope.row)" style="padding: 3px" icon="el-icon-search" size="mini">查看详细日志</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog
                center :title="dialogTitle"
                :visible.sync="dialogVisible"
                width="50%"
        >
            <div>
                <el-form :model="elformProcess" label-position="labelPosition" :rules="rules" ref="ProcessForm" label-width="87px">
                    <el-row>
                        <el-col :span="6" :offset="1">
                            <el-form-item label="id:" prop="id">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="elformProcess.id"
                                          placeholder="请输入id"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="标题:" prop="title">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="elformProcess.title"
                                          placeholder="新的标题"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="创建日期:" prop="createDate">
                                <el-date-picker
                                        v-model="elformProcess.createDate"
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
                        <el-col :span="20">
                            <div>
                                <el-form-item title="详细内容:" prop="content">
                                    <div class="text-area">
                                    <textarea size="mini"  prefix-icon="el-icon-edit" :rows="20"
                                              v-model="elformProcess.content" placeholder="请输入详细内容"></textarea>
                                    </div>
                                </el-form-item>
                            </div>

                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="doAddProcess">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
                :title="心得体会"
                :visible.sync="dialogVisibleToProcess"
                width="50%"
                height="800px"
                >
            <div>

                <el-form :model="elformProcess" :rules="rules" ref="ProcessForm"  >
                    <el-form-item  prop="title" align="center">
                        <el-input size="mini" style="width: 250px" prefix-icon="el-icon-caret-right" v-model="elformProcess.title"
                                  placeholder="新的标题"></el-input>
                    </el-form-item>
                <el-form-item label="Content:"  prop="content">
                    <div class="text-area">
                    <textarea size="mini"  prefix-icon="el-icon-edit" :rows="20"
                              v-model="elformProcess.content" placeholder="请输入详细内容" ></textarea>
                    </div>
                </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisibleToMap = false">关 闭</el-button>
            </span>
        </el-dialog>
    </div>
</template> `

<script>
    export default {
        name: "StudyProcess",
        data() {
            return {
                dialogVisible: false,
                dialogTitle: '添加学习心得',
                dialogVisibleToProcess: false,
                processes: [],
                temp: 0,
                center:{lng:0,lat:0},
                zoom:3,
                process: {
                    id: 0,
                    createDate: 0,
                    title: 0,
                    content: 0,
                },
                elformProcess:
                    {
                        id: 0,
                        createDate: 0,
                        title: 0,
                        content: 0,
                    },
                rules:
                    {
                        id: [{required: true, message: '请输入id', trigger: 'blur'}],
                        createDate: [{required: true, message: '请输入心得创建日期', trigger: 'blur'}],
                        title: [{required: true, message: '请输入标题', trigger: 'blur'}],
                        content: [{required: true, message: '请输入心得体会', trigger: 'blur'}],
                    }
            }
        },
        mounted() {
            this.initProcesses();
        },
        methods: {
            showEditProcessView(data) {
                this.dialogTitle = '编辑';
                this.elformProcess = data;
                this.temp = 1;
                this.dialogVisible = true;
            },
            showProcessView(data) {

                this.elformProcess = data;
                this.temp = 1;
                this.dialogVisibleToProcess = true;
            },
            deleteProcess(data) {
                this.$confirm('此操作将删除【' + data.name + '】心得，是否继续？', '提示', {
                    cancelButtonText: '取消',
                    confirmButtonText: '确定'
                }).then(() => {
                    this.deleteRequest("/study/process/" + data.id).then(resp => {
                        if (resp) {
                            this.initProcesses();
                        }
                    })
                }).catch(() => {
                    this.$message.info("取消删除!");
                })
            },
            showAddProcessesView() {
                //数据初始化
                this.emptyEmp();
                this.temp = 0;
                this.dialogTitle = '添加心得';
                this.dialogVisible = true;
            },
            initProcesses() {
                this.getRequest("/study/process/").then(resp => {
                    if (resp) {
                        this.processes = resp;
                    }
                })
            },
            emptyEmp() {
                this.elformProcess = {
                    id: "",
                    createDate: "",
                    title: "",
                    content: ""
                };
            },
            showView(data)
            {
                this.dialogVisibleToProcess = true;
            },
            doAddProcess() {
                if (this.temp == 1) {
                    this.$refs['ProcessForm'].validate(valid => {
                        if (valid) {
                            this.putRequest("/study/process/", this.elformProcess).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initProcesses();
                                }
                            })
                        }
                    });
                } else {
                    this.$refs['ProcessForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/study/process/", this.elformProcess).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initProcesses();
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