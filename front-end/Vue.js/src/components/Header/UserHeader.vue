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
          <a class="navbar-brand" href="#"><i class="fa fa-map" aria-hidden="true"></i>StepsMap</a>
        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav navbar-right">
            <li>
              <router-link  to="/">
             <span class="glyphicon glyphicon-log-out"></span>Logout
              </router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>

  export default {

  }
</script>


<style scoped>

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
    padding-right: 30px;
    color:white; }

  .navbar-collapse ul li{
    padding:0;
    background-color:  #4267b2;
    border-bottom: none;

  }

  .navbar-collapse ul li:hover {
    background-color: cornflowerblue; }

</style>
