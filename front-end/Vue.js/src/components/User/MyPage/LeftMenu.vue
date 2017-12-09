<template>
  <div class="user-info">
    <div class="jumbotron">
      <span class="glyphicon glyphicon-user"></span>
      <h5>{{firstName}} {{lastName}}</h5>
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
    data() {
      return {
        firstName: "",
        lastName: "",
        id: "",
      }
    },
    watch: {
      access: function(access)
      {
        console.log(access);
      }
    },
    mounted: function () {
      this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/user',
        {headers: {'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('token')}})
        .then(function(response){
          this.login_msg = false; //если логин или пароль неверно выпадает ошибка
          this.loading = true;
          console.log(response);
          this.firstName = response.body.firstName;
          this.lastName = response.body.lastName;
          this.id = response.body.id;
        }, function (error) {
          this.loading = true; //если логин или пароль неверно то лоадер после выпадения ошибки исчезает
          console.log(error);
        });
    },
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
  .menu-list a{
    text-decoration: none;
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


