<template>
	<div class="user-info">
		<div class="jumbotron">
			<span class="glyphicon glyphicon-user"></span>
			<h4>John Doe</h4>
		</div>
		<nav class="menu-list">
			<ul>
				<li>
					<a href="">
						<span class="glyphicon glyphicon-home"></span>
						My Page
					</a>
				</li>
				<li>
					<a href="">Messages</a>
				</li>
				<li>
					<a href="">Friends</a>
				</li>
				<li>
					<a href="">
						<span class="glyphicon glyphicon-camera"></span>
						Photos
					</a>
				</li>
				<li>
					<a href="">
						<span class="glyphicon glyphicon-cog"></span>
						Settings
					</a>
				</li>
			</ul>
		</nav>
	</div>
</template>

<script>
	export default {
		
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