// 管理员个人信息组件
const ManagerProfileTemplate = `
<div>
    <el-row>
        <el-col>
            <el-form label-width="110px" style="width: 500px">
                <el-form-item label="管理员id：" prop="managerId">
                    <span>{{ manager.managerId }}</span>
                </el-form-item>
                <el-form-item label="类型：" prop="type">
                    <el-tag v-if="manager.type === 'NORMAL'" type="success">普通管理员</el-tag>
                    <el-tag v-else-if="manager.type === 'ADMIN'" type="warning">超级管理员</el-tag>
                </el-form-item>
                <el-form-item label="名称：" prop="name">
                    <span>{{ manager.name }}</span>
                </el-form-item>
                <el-form-item label="密钥：" prop="secretKey">
                    <template v-if="manager.secretKey">
                        <el-row>
                            <el-col :span="18">
                                <span v-if="manager.showingSecretKey">{{ manager.secretKey }}</span>
                                <span v-else>********************************</span>
                            </el-col>
                            <el-col :span="6">
                                <el-tooltip content="显示/隐藏" placement="top" :open-delay="1000" :hide-after="3000">
                                    <el-button @click="manager.showingSecretKey = !manager.showingSecretKey" type="success" icon="el-icon-view" size="mini" circle style="margin-left: 0"></el-button>
                                </el-tooltip>
                                <el-tooltip content="更换" placement="top" :open-delay="1000" :hide-after="3000">
                                    <el-button @click="refreshSecretKey(manager)" type="primary" icon="el-icon-refresh" size="mini" circle style="margin-left: 0"></el-button>
                                </el-tooltip>
                                <el-tooltip content="删除" placement="top" :open-delay="1000" :hide-after="3000">
                                    <el-button @click="deleteSecretKey(manager)" type="danger" icon="el-icon-delete" size="mini" circle style="margin-left: 0"></el-button>
                                </el-tooltip>
                            </el-col>
                        </el-row>
                    </template>
                    <template v-else>
                        <el-row>
                            <el-col :span="18">
                                <el-tag type="success">无</el-tag>
                            </el-col>
                            <el-col :span="6">
                                <el-tooltip content="创建" placement="top" :open-delay="1000" :hide-after="3000">
                                    <el-button @click="setNewSecretKey(manager)" type="success" icon="el-icon-plus" size="mini" circle style="margin-left: 0"></el-button>
                                </el-tooltip>
                            </el-col>
                        </el-row>
                    </template>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
    <el-row>
        <el-col>
            <el-button @click="modifyNameDialogVisible = true" type="primary" plain icon="el-icon-edit" size="small">修改名称</el-button>
            <el-button @click="modifyPasswordDialogVisible = true" type="primary" plain icon="el-icon-edit" size="small">修改密码</el-button>
        </el-col>
    </el-row>

    <el-dialog :visible.sync="modifyNameDialogVisible" :before-close="closeModifyNameDialog" title="修改名称" width="40%">
        <el-form ref="modifyNameForm" :model="modifyNameForm" label-width="20%">
            <el-form-item label="新名称" prop="newName" :rules="[{required:true, message:'请输入新名词', trigger:'blur'}]">
                <el-input v-model="modifyNameForm.newName" clearable placeholder="请输入新名词" style="width: 90%"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer">
            <el-button @click="closeModifyNameDialog">取消</el-button>
            <el-button type="primary" @click="modifyName">提交</el-button>
        </div>
    </el-dialog>

    <el-dialog :visible.sync="modifyPasswordDialogVisible" :before-close="closeModifyPasswordDialog" title="修改密码" width="40%">
        <el-form ref="modifyPasswordForm" :model="modifyPasswordForm" label-width="20%">
            <el-form-item label="原密码" prop="oldPassword" :rules="[{required:true, message:'请输入原密码', trigger:'blur'}]">
                <el-input v-model="modifyPasswordForm.oldPassword" type="password" clearable placeholder="请输入原密码" style="width: 90%"></el-input>
            </el-form-item>
        </el-form>
        <el-form ref="modifyPasswordForm" :model="modifyPasswordForm" label-width="20%">
            <el-form-item label="新密码" prop="newPassword" :rules="[{required:true, message:'请输入新密码', trigger:'blur'}]">
                <el-input v-model="modifyPasswordForm.newPassword" type="password" clearable placeholder="请输入新密码" style="width: 90%"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer">
            <el-button @click="closeModifyPasswordDialog">取消</el-button>
            <el-button type="primary" @click="modifyPassword">提交</el-button>
        </div>
    </el-dialog>
</div>
`;

const ManagerProfile = {
    template: ManagerProfileTemplate,
    props: ['manager'],
    data: function () {
        return {
            modifyNameDialogVisible: false,
            modifyNameForm: {
                newName: null
            },
            modifyPasswordDialogVisible: false,
            modifyPasswordForm: {
                oldPassword: null,
                newPassword: null
            }
        };
    },
    created: function () {
        Vue.set(this.manager, 'showingSecretKey', false);
    },
    methods: {
        closeModifyNameDialog: function () {
            this.modifyNameDialogVisible = false;
            this.modifyNameForm.newName = null;
        },
        modifyName: function () {
            const theThis = this;
            this.$refs.modifyNameForm.validate(function (valid) {
                if (!valid) {
                    return;
                }
                axios.post(MANAGER_ROOT_PATH + '/manager/manage/modifyName', {
                    managerId: theThis.manager.managerId,
                    newName: theThis.modifyNameForm.newName
                }).then(function (result) {
                    if (!result.success) {
                        Vue.prototype.$message.error(result.message);
                        return;
                    }
                    Vue.prototype.$message.success(result.message);
                    theThis.closeModifyNameDialog();
                    window.location.reload()
                });
            });
        },
        closeModifyPasswordDialog: function () {
            this.modifyPasswordDialogVisible = false;
            this.modifyPasswordForm.oldPassword = null;
            this.modifyPasswordForm.newPassword = null;
        },
        modifyPassword: function () {
            const theThis = this;
            this.$refs.modifyPasswordForm.validate(function (valid) {
                if (!valid) {
                    return;
                }
                axios.post(MANAGER_ROOT_PATH + '/manager/manage/modifyPassword', {
                    managerId: theThis.manager.managerId,
                    oldPassword: theThis.modifyPasswordForm.oldPassword,
                    newPassword: theThis.modifyPasswordForm.newPassword
                }).then(function (result) {
                    if (!result.success) {
                        Vue.prototype.$message.error(result.message);
                        return;
                    }
                    Vue.prototype.$message.success(result.message);
                    theThis.closeModifyPasswordDialog();
                    window.location.reload()
                });
            });
        },
        refreshSecretKey: function (manager) {
            const theThis = this;
            Vue.prototype.$confirm('确定更换密钥？', '警告', {type: 'warning'})
                .then(function () {
                    theThis.setNewSecretKey(manager);
                });
        },
        setNewSecretKey: function (manager) {
            const theThis = this;
            axios.get(MANAGER_ROOT_PATH + '/manager/main/randomCode', {params: {}})
                .then(function (result) {
                    if (!result.success) {
                        Vue.prototype.$message.error(result.message);
                        return;
                    }
                    theThis.modifySecretKey(manager.managerId, result.randomCode, function () {
                        manager.secretKey = result.randomCode;
                        manager.showingSecretKey = true;
                    });
                });
        },
        deleteSecretKey: function (manager) {
            const theThis = this;
            Vue.prototype.$confirm('确定删除密钥？', '警告', {type: 'warning'})
                .then(function () {
                    theThis.modifySecretKey(manager.managerId, null, function () {
                        manager.secretKey = null;
                    });
                });
        },
        modifySecretKey: function (managerId, secretKey, callback) {
            axios.post(MANAGER_ROOT_PATH + '/manager/manage/modifySecretKey', {
                managerId: managerId,
                secretKey: secretKey
            }).then(function (result) {
                if (!result.success) {
                    Vue.prototype.$message.error(result.message);
                    return;
                }
                Vue.prototype.$message.success(result.message);
                callback();
            });
        }
    }
};

Vue.component('manager-profile', ManagerProfile);
