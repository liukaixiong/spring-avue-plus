import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// import './assets/styles/element-variables.scss'
import {createApp} from 'vue'
import {createPinia} from 'pinia'
import axios from 'axios'
import App from './App.vue'
import router from './router'
import Avue from '@smallwei/avue'
import '@smallwei/avue/lib/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'


// npm install bin-code-editor --save --legacy-peer-deps

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(ElementPlus, {
    locale: zhCn
})
app.use(Avue, {axios})
app.mount('#app')
