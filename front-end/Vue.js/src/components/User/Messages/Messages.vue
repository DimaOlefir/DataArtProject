<template>
	<div class="container-fluid">
    <UserHeader></UserHeader>
		<div class="user-messages">
	  		<User></User>
	  		<div class="messages">
	  		  <form class="form-inline search">
	  		    <div class="form-group">
	  		      <label for="searchMessage">Search</label>
	  		      <input type="text" class="form-control" id="searchMessage" placeholder="Search Messages" size="70">
	  		    </div>
	  		    <button type="submit" class="btn btn-primary">
				  <span class="glyphicon glyphicon-search"></span>
	  		    </button>
	  		  </form>

	  		  <section class="panel">
	  		    <input v-model="newMessage" @keyup.enter="addMessage" placeholder="Add Message" autofocus class="text-input">
	  		    <button @click="clearList">Clear List</button>
	  		  </section>

	  		  <section class="list">
	  		    <ul class="list-item">
	  		      <li v-for="task in tasks">
	  		        <label>{{ task.text }}</label>
	  		        <button class="delete" @click="removeTask(task)">X</button>
              </li>
	  		    </ul>
            <Pagination></Pagination>
          </section>

	  		</div>


	  	</div>
	  	<!-- <FooterComponent /> -->
	</div>

</template>


<script>
	import User from '../User';
  import UserHeader from '../../Header/UserHeader.vue';
  import Pagination from './Pagination.vue';
	let nextMessageId = 1;

	export default {
		components: {
			User, UserHeader,Pagination
		},
	  	data() {
	    	return {
	      		newMessage: "",
	      		tasks: [
			        {
			          id: nextMessageId++,
			          text: "This is an example task. Delete or add your own"
			        }
	      		]
	    	}
		},
    methods: {
        addMessage: function () {
		      	let task = this.newMessage.trim();
		      	if (task) {
			        this.tasks.push({id: nextMessageId++,text: task});
			        this.newMessage = "";
		      	}
		    },
      removeTask: function (task) {
		      	let index = this.tasks.indexOf(task);
		      	this.tasks.splice(index, 1);
		    },
      clearList: function () {
		      	this.tasks = [];
		    }
	  	}
	}
</script>

<style>
.container-fluid {
	padding: 0;
}
.user-messages {
	display: -webkit-flex;
	display: -moz-flex;
	display: -ms-flex;
	display: -o-flex;
	display: flex;
	justify-content: space-around;
	background-color: #f0fff0;
	padding-top: 20px;
}
ul, li {
	margin: 0;
	padding: 0;
	border: 0;
}

/* Global Styles */
.messages {
	width: 80%;
  border: 1px solid #4267b2;
	border-radius: 5px;
	padding: 0 100px;
	background-color: #fff;
}
.search {
	margin: 30px 0 50px 0;
}
.btn.btn-primary {
	height: 35px;
}
.panel, li {
	display: flex;
	align-items: center;
	justify-content: space-between;
	list-style-type: none;
	padding: 10px;
	border-bottom: 1px solid #efefef;
	background-color: #E7E8EB;
}
.text-input {
	border: 1px solid #dedede;
	padding-left: 10px;
	width: 70%;
	height: 35px;
	color: #555;
}
button {
	color: #555;
	background-color: #FFFFFF;
	border: 1px solid #bbb;
	outline: 0;
	width: 100px;
	height: 38px;
	cursor: pointer;
	font-size: 14px;
}

/* Task  area */
.list li {
	background-color: #3465A4;
}

.list li button {
	background-color: transparent;
	border: 1px solid #3465A4;
	color: #ddd;
	visibility: hidden;
	font-size: 20px;
	font-weight: bold;
}

.list li:hover > button {
	visibility: visible;
}

.list label {
	padding-right: 10px;
	display: inline-block;
	width: 70%;
	font-size: 18px;
	line-height: 24px;
	color: #fcfcfc;
	z-index: 2;
	overflow: hidden;
}
</style>







<!-- <template>
	<div class="messages">
		<h1>messages</h1>
		<form v-on:submit="addMessage" v-model="newMessageText">
			<input type="text" v-model="newMessage.title" placeholder="New Message">
			<input type="submit" value="Submit">
		</form>
		<ul>
			<li v-for="message in messages">
				<span>
					{{message.title}}
					<button v-on:click="deleteMessage(message)">X</button>
				</span>
			</li>
		</ul>
	</div>
</template>

<script>
	let nextMessageId = 1
	export default {
		name: 'messages',
		data() {
			return {
				newMessage: {},
				newMessageText: '',
				messages: [
					{
						id: nextMessageId++,
						title: 'jdoe@gmail.com'
					},
					{
						id: nextMessageId++,
						title: 'ssmith@gmail.com'
					},
					{
						id: nextMessageId++,
						title: 'tomwhite@gmail.com'
					}
				]
			}
		},
		methods: {
			addMessage: function(e) {
				e.preventDefault();
				// console.log('add');
				this.messages.push({
					id: nextMessageId++,
					title: this.newMessage.title
				});
				this.newMessageText = ''
			},
			deleteMessage: function(message) {
				this.messages.splice(this.messages.indexOf(message), 1);
			}
		}
	}
</script>

<style scoped>

</style> -->
