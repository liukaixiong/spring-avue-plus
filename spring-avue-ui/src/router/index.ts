import {createRouter, createWebHistory} from 'vue-router'
import crudTest from '@/views/crud/component-demo/crud-test.vue'
import vueJsonTest from '@/views/crud/component-demo/vue-json-test.vue'
import serverCrud from '@/views/crud/server-crud.vue'
import jsonTest from '@/views/crud/component-demo/json-test.vue'
import jsonEdit from '@/views/crud/component-demo/json-editor-vue3.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'server-crud-home',
            component: serverCrud
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
        }
    ]
})

export default router
