<template>
  <header>
    <nav class="navbar navbar-inverse" >
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.html"><i class="fa fa-map" aria-hidden="true"></i>StepsMap</a>
        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav navbar-right">
            <li data-toggle="modal"  id="mySignUp"><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li data-toggle="modal"  id="myLogin"><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <SignUpComponent />
    <LoginComponent />


  </header>
</template>

<script>
  import LoginComponent from './Login.vue';
  import SignUpComponent from './SignUp.vue';

export default {
  components: {
    LoginComponent, SignUpComponent
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  @font-face {
    font-family: FedraSansPro-DemiItalic;
    src: url('../../assets/fonts/FedraSansPro-DemiItalic.otf'); }

  /*modal window on page my_page */
  /*header menu*/
  .navbar {
    font-family: FedraSansPro-DemiItalic, Helvetica, sans-serif;
    z-index: 1;
    background-color:  #4267b2;
    margin-bottom: 0;
    border-radius:0;
    border:none;
    font-size: 16px;
    box-shadow: 0 1px 15px #000000; }

  .fa-map, .glyphicon-log-out{
    margin:0 10px 0 20px;  }

  .navbar-inverse .navbar-nav>li>a, .navbar-inverse .navbar-brand {
    color:white; }

  .navbar-collapse ul li:hover {
    background-color: cornflowerblue; }

</style>
