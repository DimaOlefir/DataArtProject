<template>
  <div class="container-fluid">
    <UserHeader></UserHeader>
    <div class="user-messages">
      <!-- <User></User> какого не работает? -->
      <div class="messages">
        <ul>
          <li v-for="message in messages">
            {{ message.body }}
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
        messages: {}
      }
    },
    created: function () {
      this.$http.get('https://jsonplaceholder.typicode.com/posts')
        .then(function(response) {
          // console.log(response)
          this.messages = response.data;
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
  .user-messages {
    display: -webkit-flex;
    display: -moz-flex;
    display: -ms-flex;
    display: -o-flex;
    display: flex;
  }
</style>
