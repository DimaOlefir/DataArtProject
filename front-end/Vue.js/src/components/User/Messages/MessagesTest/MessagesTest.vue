<template>
  <div class="container-fluid">
    <UserHeader></UserHeader>
    <div class="users-messages-test">
      <User></User>
      <div class="messages-test">
        <section class="list">
          <ul class="list-item">
            <li v-for="message in messages">
              <label>{{ message.text }}</label>
              <button class="delete" @click="removeMessage(message)">X</button>
            </li>
          </ul>
        </section>

        <section class="panel">
          <input v-model="newMessage" @keyup.enter="addMessage" placeholder="Add Message" autofocus class="text-input">
          <button @click="addMessage">Add Message</button>
          <button @click="clearList">Clear List</button>
        </section>
      </div>
    </div>
  </div>
</template>

<script>
  import UserHeader from '../../../Header/UserHeader';
  import User from '../../User';

  let nextMessageId = 1;

  export default {
    components: {
      User, UserHeader
    },
    data() {
      return {

        messagesTest: {},
        newMessage: "",
        messages: [
          {
            id: nextMessageId++,
            text: "This is an example message. Delete or add your own"
          }
        ]
      }

    },
    methods: {
      addMessage: function () {
        let message = this.newMessage.trim();
        if (message) {
          this.messages.push({id: nextMessageId++,text: message});

          let data = {
            message: this.newMessage
          };
          this.$http.post('https://rocky-retreat-50096.herokuapp.com/api/message',
            JSON.stringify(data),
            {headers: {'Content-Type': 'application/json'}})
              .then(function(response) {
                console.log(response.data)
                // this.messagesIn = response.data;
              })
              .catch(function(error) {
                console.log('Error', error);
              })


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
    },
    created: function () {
      let data = {};
      this.$http.post('https://rocky-retreat-50096.herokuapp.com/api/message',
        JSON.stringify(data),
        {headers: {'Content-Type': 'application/json'}})
          .then(function(response) {
            console.log(response.data)
            // this.messagesIn = response.data;
          })
          .catch(function(error) {
            console.log('Error', error);
          })
    }
  }
</script>

<style scoped>
  .container-fluid {
    padding: 0;
  }
  .users-messages-test {
    display: -webkit-flex;
    display: -moz-flex;
    display: -ms-flex;
    display: -o-flex;
    display: flex;
    justify-content: space-around;
    background-color: #f0fff0;
    padding-top: 20px;
  }
  .messages-test {
    width: 80%;
    border: 1px solid #4267b2;
    border-radius: 5px;
    padding: 0 100px;
    background-color: #fff;
  }
</style>
