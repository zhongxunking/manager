// 登录页面路径（由使用方设置）
let MANAGER_LOGIN_PATH = null;
// 当前管理员
let CURRENT_MANAGER = null;
// 获取当前管理员
const GET_CURRENT_MANAGER = function (callback) {
    if (CURRENT_MANAGER !== null) {
        if (callback) {
            callback(CURRENT_MANAGER);
        }
    }
    DO_GET_CURRENT_MANAGER(function (manager) {
        if (manager === null) {
            // 未登录，跳转到登录页面
            Vue.prototype.$alert('未登录或登录已超时，请进行登录', '警告', {
                callback: function () {
                    window.location.href = MANAGER_LOGIN_PATH;
                }
            });
            return;
        }
        CURRENT_MANAGER = manager;
        if (callback) {
            callback(CURRENT_MANAGER);
        }
    });
};
// 执行获取当前管理员
const DO_GET_CURRENT_MANAGER = function (callback) {
    axios.get(MANAGER_ROOT_PATH + '/manager/main/current')
        .then(function (result) {
            if (!result.success) {
                Vue.prototype.$message.error(result.message);
                return;
            }
            if (callback) {
                callback(result.manager);
            }
        });
};
// 获取管理员组件的route配置
const GET_MANAGER_MANAGERS_ROUTE = function () {
    return {
        path: '/managerManagers',
        component: ManagerManagers,
        meta: {
            title: '管理员',
            icon: 'manager-iconfont manager-icon-team'
        }
    };
};

// 计算根路径
const MANAGER_ROOT_PATH = function () {
    let path = document.currentScript ? document.currentScript.src : function () {
        let scripts = document.scripts;
        let last = scripts.length - 1;
        for (let i = last; i >= 0; i--) {
            if (scripts[i].readyState === 'interactive') {
                return scripts[i].src;
            }
        }
        return scripts[last].src;
    }();
    for (let i = 0; i < 2; i++) {
        path = path.substring(0, path.lastIndexOf('/'));
    }
    return path;
}();

MANAGER_VERSION = '1.4.6';
// 是否为开发模式
const DEV_MODE = function () {
    let host = window.location.hostname;
    return host === 'localhost' || host === '127.0.0.1';
}();
// 导入依赖
if (DEV_MODE) {
    document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/vue@2.6.10.js?version=' + MANAGER_VERSION + '"></script>');
} else {
    document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/vue@2.6.10.min.js?version=' + MANAGER_VERSION + '"></script>');
}
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/vue-router@3.0.7.js?version=' + MANAGER_VERSION + '"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + MANAGER_ROOT_PATH + '/manager/lib/element-ui/element-ui@2.10.1.css?version=' + MANAGER_VERSION + '">');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/element-ui/element-ui@2.10.1.js?version=' + MANAGER_VERSION + '"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/axios@0.19.0.js?version=' + MANAGER_VERSION + '"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/qs@6.7.0.js?version=' + MANAGER_VERSION + '"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + MANAGER_ROOT_PATH + '/manager/lib/icon/iconfont.css?version=' + MANAGER_VERSION + '">');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/common/common.js?version=' + MANAGER_VERSION + '"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + MANAGER_ROOT_PATH + '/manager/common/common.css?version=' + MANAGER_VERSION + '">');
// 导入管理员组件
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerLogin.js?version=' + MANAGER_VERSION + '"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerInitAdmin.js?version=' + MANAGER_VERSION + '"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerManagers.js?version=' + MANAGER_VERSION + '"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerProfile.js?version=' + MANAGER_VERSION + '"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerMain.js?version=' + MANAGER_VERSION + '"></script>');
