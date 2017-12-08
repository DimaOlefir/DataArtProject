<template>
  <ul>
    <li v-for="user in users">
      <p>
        {{ user.name }}
        <img src="https://placehold.it/50x50/0f0" v-bind:alt="user.name" class="img-responsive">
      </p>
      <router-link to="/usermessages">
        <a>Click me to see our messages</a>
      </router-link>
    </li>
  </ul>
</template>

<script>
  export default {
    data() {
      return {
        users: []
      }

    },
    created: function () {
      this.$http.get('https://jsonplaceholder.typicode.com/users')
        .then(function(response) {
          // console.log(response.data)
          this.users = response.data;
        })
        .catch(function(error) {
          console.log('Error', error);
        })
    }
  }
</script>

<style scoped>
  p {
    margin-bottom: 20px;
  }
  img {
    /*width: 50px;*/
    border-radius: 50%;
    margin-top: 20px;
  }
</style>
