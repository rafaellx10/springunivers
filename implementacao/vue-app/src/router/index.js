import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/landing/Login'
import Landing from '@/components/landing/Landing'
import Home from '@/components/landing/Home'
import Main from '@/components/app/Main'
import Contatos from '@/components/app/Contatos'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name:"Landing",
      component: Landing,
      children: [
        { path: '/login',name:"Login", component: Login },
        { path: '/home',name:"Home", component: Home },
      ]
    },
    {
      path: '/app',
      name:"Main",
      component: Main,
      children: [
        { path: '/contatos',name:"Contatos", component: Contatos }
      ]
    }
  ]
})
