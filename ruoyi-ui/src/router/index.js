import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import ParentView from '@/components/ParentView';
import Home from '../views/Home.vue'
import LayoutFront from "../layoutFront"
import LayoutWorker from "../layoutWorker"
import searchLayout from "../layoutFront/searchIndex"
/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect           // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    noCache: true                // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false            // 如果设置为false，则不会在breadcrumb面包屑中显示
  }
 */

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: (resolve) => require(['@/views/redirect'], resolve)
      }
    ]
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'home',
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/index'], resolve),
        name: '首页',
        meta: { title: '首页', icon: 'dashboard', noCache: true, affix: true }
      }
    ]
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/search',
    name: 'Search',
    redirect: 'search/list',
    hidden: true,
    component: searchLayout,
    children: [
      {
        path: '/search/list',
        component: (resolve) => require(['@/views/searchList'], resolve),
        name: 'searchList',
        meta: { title: '搜索结果' },
      },
      {
        path: '/search/details',
        component: (resolve) => require(['@/views/searchDetails'], resolve),
        name: 'details',
        meta: { title: '详情' },
      }
    ]
  },
  {
    path: '/system',
    name: 'System',
    redirect: 'caseList',
    hidden: true,
    component: LayoutFront,
    children: [
      {
        path: '/caseList',
        component: (resolve) => require(['@/views/caseList'], resolve),
        name: 'caseList',
        meta: { title: '教学演示流程管理' },
      },
      {
        path: '/createCase',
        component: (resolve) => require(['@/views/createCase'], resolve),
        name: 'createCase',
        meta: { title: '新增演示流程' }
      },
      {
        path: '/updateCase',
        component: (resolve) => require(['@/views/createCase'], resolve),
        name: 'updateCase',
        meta: { title: '修改演示流程' },
      },
      {
        path: '/templateList',
        component: (resolve) => require(['@/views/templateList'], resolve),
        name: 'templateList',
        meta: { title: '分区模板管理' }
      },
      {
        path: '/createTemplate',
        component: (resolve) => require(['@/views/createTemplate'], resolve),
        name: 'createTemplate',
        meta: { title: '新增模板' }
      },
      {
        path: '/updateTemplate',
        component: (resolve) => require(['@/views/createTemplate'], resolve),
        name: 'updateTemplate',
        meta: { title: '修改模板' },
      },
      {
        path: '/process',
        component: (resolve) => require(['@/views/process'], resolve),
        name: 'process',
        meta: { title: '开始流程' }
      },
    ]
  },
  {
    path: '/worker',
    component: LayoutWorker,
    redirect: 'worker/process',
    hidden: true,
    children: [
      // {
      //   path: '/worker/list',
      //   component: (resolve) => require(['@/views/workerPage'], resolve),
      //   name: 'workerList',
      //   meta: { title: '演示流程任务列表' },
      // },
      {
        path: '/worker/process',
        component: (resolve) => require(['@/views/workerPage/process'], resolve),
        name: 'workerProcess',
        meta: { title: '开始演示流程' },
      },
    ]
  },
  {
    path: '/gis',
    name: 'GIS',
    component: (resolve) => require(['@/views/gis'], resolve)
  },
  {
    path: '/gis/list',
    name: 'GISList',
    component: (resolve) => require(['@/views/gis/list'], resolve)
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  },
  {
    path: '/dict',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'type/data/:dictId(\\d+)',
        component: (resolve) => require(['@/views/system/dict/data'], resolve),
        name: 'Data',
        meta: { title: '字典数据', icon: '' }
      }
    ]
  },
  {
    path: '/job',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'log',
        component: (resolve) => require(['@/views/monitor/job/log'], resolve),
        name: 'JobLog',
        meta: { title: '调度日志' }
      }
    ]
  },
  {
    path: '/gen',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'edit/:tableId(\\d+)',
        component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
        name: 'GenEdit',
        meta: { title: '修改生成配置' }
      }
    ]
  }
]

export default new Router({
  mode: 'hash', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
