<template>
  <div class="container-fluid">
    <UserHeader></UserHeader>
    <div class="users-messages-out">
      <User></User>
      <div class="messages-out">
        Out
      </div>
    </div>
  </div>
</template>

<script>
  import UserHeader from '../../../Header/UserHeader';
  import User from '../../User';
  export default {
    components: {
      User, UserHeader
    },
    data() {
      return {
        messagesOut: []
      }
    },
    methods: {
      getOutMessages: function() {
        this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/outmessage',
          JSON.stringify(data), {headers: {'Content-Type': 'application/json'}})
            .then(function(response) {
              console.log(response)
              // this.messagesIn = response.data;
            })
            .catch(function(error) {
              console.log('Error', error);
            })
      }
    }
    // created: function () {
    //   this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/outmessage')
    //     .then(function(response) {
    //       // console.log(response.data)
    //       this.messagesIn = response.data;
    //     })
    //     .catch(function(error) {
    //       console.log('Error', error);
    //     })
    // }
  }
</script>

<style scoped>
  .container-fluid {
    padding: 0;
  }
  .users-messages-out {
    display: -webkit-flex;
    display: -moz-flex;
    display: -ms-flex;
    display: -o-flex;
    display: flex;
    justify-content: space-around;
    background-color: #f0fff0;
    padding-top: 20px;
  }
  .messages-out {
    width: 80%;
    border: 1px solid #4267b2;
    border-radius: 5px;
    padding: 0 100px;
    background-color: #fff;
  }
</style>
