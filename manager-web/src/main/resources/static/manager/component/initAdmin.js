// 初始化超级管理员组件
const initAdminComponentTemplate = `
<div style="position:absolute; left:0; top:0; width:100%;height:100%;background-color: #304156">
    <el-form ref="initAdminForm" :model="initAdminForm" style="width: 350px;padding: 35px;margin: 120px auto">
        <div style="text-align: center;color: #eee;margin-bottom: 40px;">
            <span style="font-size: xx-large;">初始化超级管理员</span>
        </div>
        <el-form-item prop="managerId" :rules="[{ required:true, message:'请输入管理员id', trigger:'blur'}]">
            <el-input v-model="initAdminForm.managerId" prefix-icon="manager-iconfont manager-icon-user" clearable placeholder="请输入管理员id"></el-input>
        </el-form-item>
        <el-form-item prop="name" :rules="[{ required:true, message:'请输入名称', trigger:'blur'}]">
            <el-input v-model="initAdminForm.name" prefix-icon="manager-iconfont manager-icon-smile" clearable placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item prop="password" :rules="[{required:true, message:'请输入密码', trigger:'blur'}]">
            <el-input type="password" v-model="initAdminForm.password" prefix-icon="manager-iconfont manager-icon-lock" clearable placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="initAdmin" style="width: 100%">初始化</el-button>
        </el-form-item>
    </el-form>
</div>
`;

const initAdminComponent = {
    template: initAdminComponentTemplate,
    props: ['loginPath'],
    data: function () {
        return {
            initAdminForm: {
                managerId: null,
                name: null,
                password: null
            }
        };
    },
    created: function () {
        const theThis = this;
        // 判断是否需要初始化超级管理员
        axios.get(managerRootPath + '/manager/init/canInitAdmin')
            .then(function (result) {
                if (result.success) {
                    return;
                }
                Vue.prototype.$alert('超级管理员已初始化，不能重复初始化，现跳转到登录页面', '警告', {
                    callback: function () {
                        window.location.href = theThis.loginPath;
                    }
                });
            });
    },
    methods: {
        initAdmin: function () {
            const theThis = this;
            this.$refs.initAdminForm.validate(function (valid) {
                if (!valid) {
                    return;
                }
                axios.post(managerRootPath + '/manager/init/initAdmin', theThis.initAdminForm)
                    .then(function (result) {
                        if (!result.success) {
                            Vue.prototype.$message.error(result.message);
                            return;
                        }
                        Vue.prototype.$alert('超级管理员已初始化完成，现跳转回登录页面进行登录', '提示', {
                            callback: function () {
                                window.location.href = theThis.loginPath;
                            }
                        });
                    });
            });
        }
    }
};