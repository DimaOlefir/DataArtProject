<template>
  <div class="user-info">
    <div class="jumbotron">
      <span class="glyphicon glyphicon-user"></span>
      <h5>John Doe</h5>
    </div>
    <div class="menu-list text-left">
      <router-link  to="/mypage">
        <a class="list-group-item" href="#"><i class="fa fa-home fa-1x fa-fw" aria-hidden="true"></i>My page</a>
      </router-link>
      <router-link  to="/messages">
        <a class="list-group-item" href="#"><i class="fa fa-comments fa-1x fa-fw" aria-hidden="true"></i>Messages</a>
      </router-link>
      <router-link  to="/friends">
        <a class="list-group-item" href="#"><i class="fa fa fa-users fa-1x fa-fw " aria-hidden="true"></i>&nbsp; Friends</a>
      </router-link>
      <router-link  to="/mynotes">
        <a class="list-group-item" href="#"><i class="fa fa-picture-o fa-1x fa-fw" aria-hidden="true"></i>My notes</a>
      </router-link>
      <router-link  to="/settings">
        <a class="list-group-item" href="#"><i class="fa fa-cog fa-spin fa-1x fa-fw"></i>Settings</a>
      </router-link>
    </div>
    <h5 class="footer_logo">SiteMap@2017</h5>
  </div>
</template>

<script>
	export default {

	}
</script>

<style scoped>
  .user-info {
    font-family: FedraSansPro-DemiItalic, Helvetica, sans-serif;
    width: 16vw;
    display: -webkit-flex;
    display: -moz-flex;
    display: -ms-flex;
    display: -o-flex;
    display: flex;
    flex-direction: column;
  }
  .jumbotron {
    margin: 0;
    border-bottom-left-radius:0 ;
    border-bottom-right-radius:0;
  }
  .jumbotron span {
    padding: 12%;
    color: red;
    font-size: 50px;
    border-radius: 50%;
    background-color: #ffffff;
  }
  .menu-list a{
    text-decoration: none;
  }
  .menu-list a:first-child{
    border-radius:0;
  }
  .menu-list a:last-child{
    border-bottom-right-radius:5px;
    border-bottom-left-radius:5px;
  }
  .menu-list ul {
    margin: 0;
    padding: 0;
    list-style: none;
  }
  .list-group-item {
    position: relative;
    display: block;
    padding: 10px 15px;
    margin-bottom: -1px;
    background-color: #4267b2;
    border: 1px solid #ddd;  }

  .menu-list a:hover{
    background-color: cornflowerblue;
    color:#fff; }

  .menu-list a:focus{
    background-color: cornflowerblue;
    color:#fff; }

  a.list-group-item, button.list-group-item {
    text-decoration: none;
    color: #FFFFFF;}

  .menu-list a i,.menu-list a span {
    margin-right: 4%;
  }
  .dropdown{
    margin-top: 10%;
  }
  .dropdown button{
    background-color: #4267b2;
    border: 0;
    padding: 3%;
  }
  .dropdown button:hover{
    background-color: cornflowerblue;
  }
  .dropdown form{
    margin-left:25%;
    padding: 4%}

  .dropdown-menu  {
    min-width:0;
    border:0;}

  .footer_logo{
    color: grey; }
</style>




<!-- <template>
  <div class="user-info">
    <div class="jumbotron">
      <span class="glyphicon glyphicon-user"></span>
      <h4>John Doe</h4>
    </div>
    <nav class="menu-list">
      <ul>
        <li>
          <router-link to="/user">
            <span class="glyphicon glyphicon-home"></span>
            My Page
          </router-link>
        </li>
        <li>
          <router-link to="/messages">Messages</router-link>
        </li>
        <li>
          <router-link to="/friends">Friends</router-link>
        </li>
        <li>
          <router-link to="/photos">
            <span class="glyphicon glyphicon-camera"></span>
            Photos
          </router-link>
        </li>
        <li>
          <router-link to="/settings">
            <span class="glyphicon glyphicon-cog"></span>
            Settings
          </router-link>
        </li>
      </ul>

      <router-view></router-view>
    </nav>
  </div>
</template>

<script>
  import Vue from 'vue';
  import vueRouter from 'vue-router';

  import Main from '../Main';

  import User from './User';
  import Friends from './Friends/Friends';
  import Messages from './Messages/Messages';
  import Photos from './Photos/Photos';
  import Settings from './Settings/Settings';

  Vue.use(vueRouter)

  const router = new vueRouter({
    mode: 'history',
    base: __dirname,
    // routes: [
    //  {path: '/', name: 'Main', component: Main},
    //  {path: '/user', name: 'User', component: User},
    //  {path: '/messages', name: 'Messages', component: Messages},
    //  {path: '/friends', name: 'Friends', component: Friends},
    //  {path: '/photos', name: 'Photos', component: Photos},
    //  {path: '/settings', name: 'Settings', component: Settings}
    // ]
  })

  export default {
    routes: [
      {path: '/user', name: 'User', component: User},
      {path: '/messages', name: 'Messages', component: Messages},
      {path: '/friends', name: 'Friends', component: Friends},
      {path: '/photos', name: 'Photos', component: Photos},
      {path: '/settings', name: 'Settings', component: Settings}
    ]
  }
</script>

<style scoped>
  .user-info {
    /*margin-left: 20px;*/
    width: 16vw;
    display: -webkit-flex;
    display: -moz-flex;
    display: -ms-flex;
    display: -o-flex;
    display: flex;
    flex-direction: column;
    /*justify-content: flex-start;*/
  }
  .jumbotron {
    margin: 0;
  }
  .jumbotron span {
      padding: 5%;
      color: red;
      font-size: 50px;
      border-radius: 50%;
      background-color: #ffffff;
  }
  .menu-list ul {
    margin: 0;
    padding: 0;
    list-style: none;
  }
  .menu-list ul li {
    background-color: #4267b2;
    margin-bottom: 1px;
  }
  .menu-list ul li:first-child {
    border-radius: 5px 5px 0 0;
  }
  .menu-list ul li:last-child {
    border-radius: 0 0 5px 5px;
  }
  .menu-list ul li a {
    padding: 10px 15px;
    color: #fff;
    text-decoration: none;
    display: block;
  }
</style> -->
