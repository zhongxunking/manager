// 登录页面路径（由使用方设置）
let MANAGER_LOGIN_PATH = null;
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
// 是否为开发模式
const DEV_MODE = function () {
    let host = window.location.hostname;
    return host === 'localhost' || host === '127.0.0.1';
}();
// 导入依赖
if (DEV_MODE) {
    document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/vue.js"></script>');
} else {
    document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/vue.min.js"></script>');
}
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/vue-router.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + MANAGER_ROOT_PATH + '/manager/lib/element-ui/element-ui.css">');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/element-ui/element-ui.js"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/axios.js"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/lib/qs.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + MANAGER_ROOT_PATH + '/manager/lib/icon/iconfont.css">');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/common/common.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + MANAGER_ROOT_PATH + '/manager/common/common.css">');
// 导入管理员组件
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerLogin.js"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerInitAdmin.js"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerManagers.js"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerRelations.js"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerProfile.js"></script>');
document.write('<script src="' + MANAGER_ROOT_PATH + '/manager/components/ManagerMain.js"></script>');
