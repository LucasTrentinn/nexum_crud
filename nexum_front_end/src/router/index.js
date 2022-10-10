import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CreateView from '../views/CreateView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/adicionarPessoa',
      name: 'create',
      component: CreateView
    },
    {
      path: '/atualizarPessoa/:id',
      name: 'update',
      component: CreateView
    }
  ]
})

export default router
