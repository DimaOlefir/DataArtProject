<template>
  <div class="container-fluid">
    <UserHeader></UserHeader>
    <div class="user-messages">
      <User></User>
      <div class="messages">
        <ul>
          <li v-for="message in messages">
            {{ message.subject }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
  import User from '../../User';
  import UserHeader from '../../../Header/UserHeader';
  export default {
    components: {
      UserHeader, User
    },
    data() {
      return {
        messages: [],
        inmessages: [],
        outmessages: [],
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
  }
</script>

<style scoped>
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
    list-style: none;
  }
  li {
    margin-bottom: 10px;
    padding: 10px;
    background-color: #efefef;
  }
  .messages {
    width: 80%;
    border: 1px solid #4267b2;
    border-radius: 5px;
    padding: 0 100px;
    background-color: #fff;
  }
</style>
