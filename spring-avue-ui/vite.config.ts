import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// import vueDevTools from 'vite-plugin-vue-devtools'
import { viteCommonjs } from '@originjs/vite-plugin-commonjs'

const isDevelopment = process.env.NODE_ENV === 'development';


// https://vitejs.dev/config/
export default defineConfig({
  base: '/avue',
  build: {
    sourcemap: isDevelopment,
  },
  plugins: [
    vue(),
      // vueDevTools(),
    viteCommonjs()
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    hmr: true
  }
})
