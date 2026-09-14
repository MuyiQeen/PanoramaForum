import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

import path from 'path'

const __dirname = path.dirname(fileURLToPath(import.meta.url))

const alias = {
    '@': path.resolve(__dirname, './src'),
    '@auth': path.resolve(__dirname, './src/modules/auth'),
    '@admin': path.resolve(__dirname, './src/modules/admin'),
    '@user': path.resolve(__dirname, './src/modules/user'),
}

export default defineConfig({
    plugins: [
        vue(),
        vueDevTools(),
        {
            name: 'multi-entry-rewrite',
            configureServer(server) {
                server.middlewares.use((req, res, next) => {
                    const url = req.url
                    if (!url) return next()

                    if (/\.(js|css|png|jpg|jpeg|gif|svg|ico|woff|woff2|ttf|eot|map)$/i.test(url)) {
                        return next()
                    }

                    if (url.startsWith('/@vite/') || url.startsWith('/@id/') || url.startsWith('/node_modules/')) {
                        return next()
                    }

                    const routeMap: Record<string, string> = {
                        '/': '/src/modules/user/index.html',        // 默认入口
                        '/admin': '/src/modules/admin/index.html',
                        '/user': '/src/modules/user/index.html',
                        '/sso': '/src/modules/auth/index.html',
                    }

                    if (routeMap[url]) {
                        req.url = routeMap[url]
                        return next()
                    }

                    for (const [prefix, target] of Object.entries(routeMap)) {
                        if (prefix !== '/' && url.startsWith(prefix + '/')) {
                            req.url = target
                            return next()
                        }
                    }

                    next()
                })
            }
        }
    ],
    resolve: {
        alias: alias
    },
    server: {
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8080',
                changeOrigin: true,
            }
        }
    },
    build: {
        rolldownOptions: {
            input: {
                admin: path.resolve(__dirname, 'src/modules/admin/index.html'),
                auth: path.resolve(__dirname, 'src/modules/auth/index.html'),
                user: path.resolve(__dirname, 'src/modules/user/index.html'),
            }
        }
    }
})