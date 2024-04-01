export default [
  { path: '/', name: '主页', icon: 'smile', component: './Index' },

  { path: '/interface_info/:id', name: '查看接口', icon: 'smile', component: './InterfaceInfo',hideInMenu:true },

  {
    path: '/user',
    layout: false,
    routes: [
      { name: '登录', path: '/user/login', component: './user/Login' },
      { component: './404' },
    ],
  },

  {
    path: '/admin',
    name: '管理页',
    // ant design的权限控制，写在这里只有管理员能访问
    icon: 'crown',
    access: 'canAdmin',
    routes: [
      { name: '接口管理', icon: 'table', path: '/admin/interface_info', component: './Admin/InterfaceInfo' },
      { name: '接口分析', icon: 'analysis', path: '/admin/interface_analysis', component: './Admin/InterfaceAnalysis' },
      { name: '用户管理', icon: 'table', path: '/admin/user_info', component: './Admin/UserInfo' },

      { component: './404' },
    ],
  },


  // { path: '/', redirect: '/welcome' },
  // { path: '/', redirect: '/' },
  { path: '*', layout: false, component: './404' },
  // { path: '*', redirect: '/index' },
];
