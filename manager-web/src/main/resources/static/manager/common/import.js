!function () {
    // 计算路径
    let managerPath = function () {
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
    // 导入js、css
    document.write('<script src="' + managerPath + '/lib/vue.js"></script>');
    document.write('<script src="' + managerPath + '/lib/vue-router.js"></script>');
    document.write('<link rel="stylesheet" type="text/css" href="' + managerPath + '/lib/element-ui/element-ui.css">');
    document.write('<script src="' + managerPath + '/lib/element-ui/element-ui.js"></script>');
    document.write('<script src="' + managerPath + '/lib/axios.js"></script>');
    document.write('<script src="' + managerPath + '/lib/qs.js"></script>');
    document.write('<link rel="stylesheet" type="text/css" href="' + managerPath + '/lib/icon/iconfont.css">');
    document.write('<script src="' + managerPath + '/common/common.js"></script>');
}();
