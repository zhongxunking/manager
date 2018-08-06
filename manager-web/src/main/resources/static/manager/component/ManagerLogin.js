// 管理员登录组件
const ManagerLoginTemplate = `
<div style="position:absolute; left:0; top:0; width:100%;height:100%;background-color: #304156">
    <el-form ref="loginForm" :model="loginForm" style="width: 350px;padding: 35px;margin: 120px auto">
        <div v-html="titleHtml" style="text-align: center;color: #eee;margin-bottom: 40px;"></div>
        <el-form-item prop="managerId" :rules="[{ required:true, message:'请输入管理员id', trigger:'blur'}]">
            <el-input v-model="loginForm.managerId" prefix-icon="manager-iconfont manager-icon-user" clearable placeholder="请输入管理员id"></el-input>
        </el-form-item>
        <el-form-item prop="password" :rules="[{required:true, message:'请输入密码', trigger:'blur'}]">
            <el-input type="password" v-model="loginForm.password" prefix-icon="manager-iconfont manager-icon-lock" clearable placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="login" style="width: 100%">登录</el-button>
        </el-form-item>
    </el-form>
</div>
`;

const ManagerLogin = {
    template: ManagerLoginTemplate,
    props: ['titleHtml', 'initAdminPath', 'mainPath'],
    data: function () {
        return {
            loginForm: {
                managerId: null,
                password: null
            }
        };
    },
    created: function () {
        const theThis = this;
        // 判断用户是否已登录
        axios.get(managerRootPath + '/manager/main/current')
            .then(function (result) {
                if (!result.success) {
                    Vue.prototype.$message.error(result.message);
                    return;
                }
                if (result.manager !== null) {
                    window.location.href = theThis.mainPath;
                }
            });

        // 判断是否需要初始化超级管理员
        axios.get(managerRootPath + '/manager/init/canInitAdmin')
            .then(function (result) {
                if (!result.success) {
                    return;
                }
                Vue.prototype.$alert('第一次使用，需初始化超级管理员，现跳转到初始化页面', '提示', {
                    callback: function () {
                        window.location.href = theThis.initAdminPath;
                    }
                });
            });
    },
    methods: {
        login: function () {
            const theThis = this;
            this.$refs.loginForm.validate(function (valid) {
                if (!valid) {
                    return;
                }
                axios.post(managerRootPath + '/manager/main/login', theThis.loginForm)
                    .then(function (result) {
                        if (result.success) {
                            window.location.href = theThis.mainPath;
                        } else {
                            Vue.prototype.$message.error(result.message);
                        }
                    });
            });
        }
    }
};
