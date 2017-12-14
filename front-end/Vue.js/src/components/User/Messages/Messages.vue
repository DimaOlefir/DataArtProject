<template>
  <div class="container-fluid">
    <UserHeader></UserHeader>
    <div class="users-messages">
      <User></User>
      <div class="messages">
        <div class="messages-test">
          <section class="panel">
            <input v-model="userTo" placeholder="Add Message" autofocus class="text-input">
            <input v-model="newMessage" @keyup.enter="addMessage" placeholder="Add Message" autofocus class="text-input">
            <button @click="addMessage">Add Message</button>
          </section>
          <router-link to="/messagesTestIn">
            <a class="list-group-item">In Messages</a>
          </router-link>
          <router-link to="/messagesTestOut">
            <a class="list-group-item">Out Messages</a>
          </router-link>
        </div>
        <div class="messages">
          <ul>
            <li v-for="message in messages">
              <p>{{message.userToName}} {{message.userToSurname}} {{message.datetime}}</p>
              <p v-on:click="openMessage(message.id)">{{ message.subject }}</p>
            </li>
          </ul>
        </div>
        <!--<form class="form-inline search">-->
        <!--<div class="form-group">-->
        <!--<label for="searchMessage">Search</label>-->
        <!--<input type="text" class="form-control" id="searchMessage" placeholder="Search Messages" size="70">-->
        <!--</div>-->
        <!--<button type="submit" class="btn btn-primary">-->
        <!--<span class="glyphicon glyphicon-search"></span>-->
        <!--</button>-->
        <!--</form>-->
        <!--<UserInfo></UserInfo>-->

        <!-- <section class="list">
                <ul class="list-item">
                  <li v-for="message in messages">
                    <label>{{ message.text }}</label>
                    <button class="delete" @click="removeMessage(message)">X</button>
                  </li>
                </ul>
              </section>

        <section class="panel">
          <input v-model="newMessage" @keyup.enter="addMessage" placeholder="Add Message" autofocus class="text-input">
          <button @click="clearList">Clear List</button>
        </section> -->
      </div>
      <!-- <span class="glyphicon glyphicon-search"></span>
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
      </section> -->

    </div>
  </div>
</template>


<script>
  import User from '../User';
  import UserHeader from '../../Header/UserHeader.vue';
  import UserInfo from './UserInfo/UserInfo';
  import Pagination from './Pagination.vue';
  let nextMessageId = 1;

  export default {
    components: {
      User, UserHeader, UserInfo, Pagination
    },
    data() {
      return {
        newMessage: "",
//        messages: [],
        inmessages: [],
        outmessages: [],
//        messages: [
//          {
//            id: nextMessageId++,
//            text: "This is an example message. Delete or add your own"
//          }
//        ]
      }
    },
    created: function () {
      this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/inmessage', // запрос на чтение маркеров
        {headers: {'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('token')}})
        .then(function(response){
          console.log(response);
          this.inmessages = response.body;
        }, function (error) {
          this.inmessages = [];
          console.log(error);
        });
      this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/outmessage', // запрос на чтение маркеров
        {headers: {'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('token')}})
        .then(function(response){
          console.log(response);
          this.outmessages = response.body;
        }, function (error) {
          this.outmessages = [];
          console.log(error);
        });
    },
    computed: {    //вычисляемая переменная поиск каждой введенной буквы
      messages: function () {
        let self = this;
        let all_messages = this.inmessages.concat(this.outmessages);
        return all_messages.sort(function (element) {
          return element.datetime;
        });
      },
    },
    methods: {
      openMessage: function (id) {
        this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/message/' + id, // запрос на чтение маркеров
          {headers: {'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')}})
          .then(function(response){
            console.log(response);
            this.outmessages = response.body;
          }, function (error) {
            this.outmessages = [];
            console.log(error);
          });
      },
      addMessage: function () {
        let message = this.newMessage.trim();
        if (message) {
          this.messages.push({id: nextMessageId++,text: message});
          this.newMessage = "";
        }
      },
      removeMessage: function (message) {
        let index = this.messages.indexOf(message);
        this.messages.splice(index, 1);
      },
      clearList: function () {
        this.messages = [];
      }
    }
  }
</script>

<style>
  .container-fluid {
    padding: 0;
  }
  .users-messages {
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

  /* message  area */
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
