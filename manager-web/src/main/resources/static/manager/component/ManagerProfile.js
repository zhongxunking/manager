// 管理员个人信息组件
const ManagerProfileTemplate = `
<div>
    <el-row>
        <el-col :span="6">
            <el-form label-width="110px">
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
            </el-form>
        </el-col>
    </el-row>
    <el-row>
        <el-col :span="6">
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
    props: ['loginPath'],
    data: function () {
        return {
            manager: {},
            modifyNameDialogVisible: false,
            modifyNameForm: {
                newName: null
            },
            modifyPasswordDialogVisible: false,
            modifyPasswordForm: {
                newPassword: null
            }
        };
    },
    created: function () {
        this.findCurrentManager();
    },
    methods: {
        findCurrentManager: function () {
            const theThis = this;
            // 获取当前管理员
            axios.get(managerRootPath + '/manager/main/current')
                .then(function (result) {
                    if (!result.success) {
                        Vue.prototype.$message.error(result.message);
                        return;
                    }
                    if (result.manager === null) {
                        // 未登录，则跳转到登录页面
                        Vue.prototype.$alert('未登录或登录已超时，请进行登录', '警告', {
                            callback: function () {
                                window.location.href = theThis.loginPath;
                            }
                        });
                        return;
                    }
                    theThis.manager = Object.assign({}, result.manager);
                });
        },
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
                axios.post(managerRootPath + '/manager/manage/modifyName', {
                    managerId: theThis.manager.managerId,
                    newName: theThis.modifyNameForm.newName
                }).then(function (result) {
                    if (!result.success) {
                        Vue.prototype.$message.error(result.message);
                        return;
                    }
                    Vue.prototype.$message.success(result.message);
                    theThis.closeModifyNameDialog();
                    theThis.findCurrentManager();
                });
            });
        },
        closeModifyPasswordDialog: function () {
            this.modifyPasswordDialogVisible = false;
            this.modifyPasswordForm.newPassword = null;
        },
        modifyPassword: function () {
            const theThis = this;
            this.$refs.modifyPasswordForm.validate(function (valid) {
                if (!valid) {
                    return;
                }
                axios.post(managerRootPath + '/manager/manage/modifyPassword', {
                    managerId: theThis.manager.managerId,
                    newPassword: theThis.modifyPasswordForm.newPassword
                }).then(function (result) {
                    if (!result.success) {
                        Vue.prototype.$message.error(result.message);
                        return;
                    }
                    Vue.prototype.$message.success(result.message);
                    theThis.closeModifyPasswordDialog();
                    theThis.findCurrentManager();
                });
            });
        }
    }
};