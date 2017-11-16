import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'

import Users from '@/components/User/Users'

import MyPage from '@/components/User/MyPage/MyPage';

import Messages from '@/components/User/Messages/Messages';

import Friends from '@/components/User/Friends/Friends';

import Photos from '@/components/User/Photos/Photos';

import Settings from '@/components/User/Settings/Settings';



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
    	path: '/users',
    	name: 'Users',
    	component: Users
    },
    {
      path: '/messages',
      name: 'Messages',
      component: Messages
    },
    {
      path: '/friends',
      name: 'Friends',
      component: Friends
    },
    {
      path: '/photos',
      name: 'Photos',
      component: Photos
    },
    {
      path: '/settings',
      name: 'Settings',
      component: Settings
    },
    {
      path: '/mypage',
      name: 'MyPage',
      component: MyPage
    }
  ]
})
