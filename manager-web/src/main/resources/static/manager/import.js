// 计算根路径
const managerRootPath = function () {
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
// 导入依赖
document.write('<script src="' + managerRootPath + '/manager/lib/vue.js"></script>');
document.write('<script src="' + managerRootPath + '/manager/lib/vue-router.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + managerRootPath + '/manager/lib/element-ui/element-ui.css">');
document.write('<script src="' + managerRootPath + '/manager/lib/element-ui/element-ui.js"></script>');
document.write('<script src="' + managerRootPath + '/manager/lib/axios.js"></script>');
document.write('<script src="' + managerRootPath + '/manager/lib/qs.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + managerRootPath + '/manager/lib/icon/iconfont.css">');
document.write('<script src="' + managerRootPath + '/manager/common/common.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + managerRootPath + '/manager/common/common.css">');
// 管理员组件
document.write('<script src="' + managerRootPath + '/manager/component/ManagerLogin.js"></script>');
document.write('<script src="' + managerRootPath + '/manager/component/ManagerInitAdmin.js"></script>');
document.write('<script src="' + managerRootPath + '/manager/component/ManagerProfile.js"></script>');
document.write('<script src="' + managerRootPath + '/manager/component/ManagerMain.js"></script>');
