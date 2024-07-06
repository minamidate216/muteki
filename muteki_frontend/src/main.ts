import './assets/main.css'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import { initializeApi } from './api/job-api-accessor'
//elementPlus icon
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

initializeApi()
const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(ElementPlus)
app.use(router)

app.mount('#app')