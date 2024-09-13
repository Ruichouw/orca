import { createApp } from 'vue'
import pinia from './stores/index'
import App from './App.vue'
// 导入根组件
import router from './router'
import '@/styles/common.scss'
import { lazyPlugin, outsidePlugin } from '@/directives/index'
// import { componentPlugin } from '@/components/index.js'
const app = createApp(App)
// 创建Vue 应用实例
app.use(router)
app.use(pinia)
app.use(lazyPlugin)
app.use(outsidePlugin)
// app.use()允许你安装 Vue 插件。这些插件可以是路由、状态管理、UI 组件库、工具库等

// app.use(componentPlugin)
app.mount('#app')
/*用于将 Vue 应用实例挂载到 DOM 元素上的方法
 这里的 #app 是一个 CSS 选择器，它选择了 HTML 文档中 id 属性为 app 的元素。*/
