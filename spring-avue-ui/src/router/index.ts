import {createRouter, createWebHistory} from 'vue-router'
import crudTest from '@/views/crud/component-demo/crud-test.vue'
import vueJsonTest from '@/views/crud/component-demo/vue-json-test.vue'
import serverCrud from '@/views/crud/server-crud.vue'
import jsonTest from '@/views/crud/component-demo/json-test.vue'
import dialogTest from '@/views/crud/component-demo/dialog-test.vue'
import avueComponent from '@/views/crud/avue-component.vue'
import loginComponent from '@/views/login.vue'
import jsonEdit from '@/views/crud/component-demo/json-editor-vue3.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'loginIndex',
            component: loginComponent
        },
        {
            path: '/login.html',
            name: 'login',
            component: loginComponent
        }
        , {
            path: '/json-test',
            name: 'home',
            component: jsonTest
        }
        , {
            path: '/server-crud',
            name: 'server-crud',
            component: serverCrud
        }
        , {
            path: '/crud-test',
            name: 'crud-test',
            component: crudTest
        }
        , {
            path: '/vue-json',
            name: 'vue-json',
            component: vueJsonTest
        }, {
            path: '/json-edit-demo',
            name: 'json-edit-demo',
            component: jsonEdit
        }, {
            path: '/dialog-test',
            name: 'json-edit-demo',
            component: dialogTest
        }, {
            path: '/avue-component',
            name: 'avue-component',
            component: avueComponent
        },
        {
            path: '/list',
            name: 'list',
            redirect: {path: '/avue-component', query: {group: 'avueUrlList'}}
        }
    ]
})

export default router
