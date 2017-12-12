// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import vueResource from 'vue-resource'

import Map from './components/Map/Map';


import Messages from './components/User/Messages/Messages';

Vue.config.productionTip = false

Vue.use(vueResource)
Vue.http.options.root = 'https://rocky-retreat-50096.herokuapp.com/';
Vue.http.headers.common['Authorization'] = 'Basic YXBpOnBhc3N3b3Jk';

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  http: {
    root: '/root',
    headers: {
      Authorization: 'Basic YXBpOnBhc3N3b3Jk'
    }
  },
  template: '<App/>',
  components: { App }
});
