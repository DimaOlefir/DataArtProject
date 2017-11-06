import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'

import User from '@/components/User/User'

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
    	path: '/user',
    	name: 'User',
    	component: User
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
    }
  ]
})
