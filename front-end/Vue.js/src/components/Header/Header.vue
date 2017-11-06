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
            <!-- <li data-toggle="modal" id="mySignUp"><a href="#"><span class="glyphicon glyphicon-user" ></span> Sign Up</a></li>
            <li data-toggle="modal"  id="myLogin"><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li> -->
            <li data-toggle="modal" id="mySignUp">
              <button type="button" class="btn btn-primary signUp" data-toggle="modal" data-target="#SignUp" data-whatever="@mdo">
                <span class="glyphicon glyphicon-user"></span> 
                Sign Up
              </button>

              <!-- SignUp Modal -->
              <div class="modal fade" id="SignUp" tabindex="-1" role="dialog" aria-labelledby="SignUpLabel">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header" style="padding:35px 50px;">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4><span class="glyphicon glyphicon-lock"></span> Sign Up</h4>
                    </div>
                    <div class="modal-body" style="padding:40px 50px;">
                        <form role="form">
                          <div class="form-group">
                            <label for="usrname"><span class="glyphicon glyphicon-user"></span> Name</label>
                            <input type="text" class="form-control"  placeholder="Your name">
                          </div>
                          <div class="form-group">
                            <label for="usrname"><span class="glyphicon glyphicon-user"></span> Surname</label>
                            <input type="text" class="form-control"  placeholder="Your surname">
                          </div>
                          <div class="form-group">
                            <label for="usrname"><span  class="glyphicon glyphicon-envelope"></span> Email</label>
                            <input type="text" class="form-control"  placeholder="Enter email">
                          </div>
                          <div class="form-group">
                            <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                            <input type="text" class="form-control"  placeholder="Enter password">
                          </div>
                          <div class="checkbox">
                            <label><input type="checkbox" value="" checked>Remember me</label>
                          </div>
                          <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Sign Up</button>
                        </form>
                    </div>
                    <!-- <div class="modal-footer">
                      <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Sign Up</button>
                    </div> -->
                  </div>
                </div>
              </div>
            </li>
            <li data-toggle="modal" id="myLogin">
              <button type="button" class="btn btn-primary login" data-toggle="modal" data-target="#Login" data-whatever="@mdo">
                <span class="glyphicon glyphicon-log-in"></span> 
                Login
              </button>

              <!-- Login Modal -->
              <div class="modal fade" id="Login" tabindex="-1" role="dialog" aria-labelledby="LoginLabel">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header" style="padding:35px 50px;">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
                    </div>
                    <div class="modal-body" style="padding:40px 50px;">
                      <form role="form">
                        <div class="form-group">
                          <label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
                          <input type="text" class="form-control" id="usrname" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                          <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                          <input type="text" class="form-control" id="psw" placeholder="Enter password">
                        </div>
                        <div class="checkbox">
                          <label><input type="checkbox" value="" checked>Remember me</label>
                        </div>
                        <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
                      </form>
                    </div>
                    <!-- <div class="modal-footer">
                      <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
                    </div> -->
                  </div>
                </div>
              </div>
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .navbar {
    margin-bottom: 0;
  }
  .signUp,
  .login {
    color: #fff;
    background-color: transparent;
    margin-top: 8px;
  }
  .login {
    margin-left: 10px;
  }
</style>
