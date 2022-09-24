import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import BaiduMap from 'vue-baidu-map'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Video from 'video.js'
import 'video.js/dist/video-js.css'
import {postRequest} from "./utils/api";
import {postKeyValueRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import {initMenu} from "./utils/menus";
import 'font-awesome/css/font-awesome.min.css'
import 'leaflet/dist/leaflet.css'
Vue.prototype.postRequest = postRequest;
Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.getRequest = getRequest;

Vue.config.productionTip = false

Vue.use(ElementUI,{size:'small'});

router.beforeEach((to, from, next) => {
    if (to.path == '/') {
        next();
    }else {
        if (window.sessionStorage.getItem("user")) {
            initMenu(router, store);
            next();
        }else{
            next('/?redirect='+to.path);
        }
    }
})
Vue.use(BaiduMap,{
    /* Visit http://lbsyun.baidu.com/apiconsole/key for details about app key. */
    ak: 'nOM3ojG6ll8F0r60o8mAzNxP6AYiNK8r'
})

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
Vue.prototype.$video = Video