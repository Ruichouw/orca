import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    //配置所需的插件的数组
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()]
    }),
    Components({
      resolvers: [ElementPlusResolver()]
    })
  ],
  server: {
    //运行服务的配置
    proxy: {
      //反向代理
      '/api': {
        target: 'https://api.longfish.site', // 目标地址http://frium.top:1111
        changeOrigin: true, // 是否换源， true 换源
        rewrite: (path) => path.replace(/^\/api/, '') // 替换
      }
    }
  },

  resolve: {
    //定义项目路径别名
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)) // @ 符号代表src目录
    }
  }
})
