<template>
  <div>
    <!-- Modal Sign Up -->
    <div class="modal fade" id="modal_signup" role="dialog">
      <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4><span class="glyphicon glyphicon-lock"></span> Sign Up</h4>
          </div>
          <div class="modal-body" style="padding:40px 50px;">
            <!--<form role="form" action="https://rocky-retreat-50096.herokuapp.com/api/test" method="get" class="text-left">-->
            <form role="form" action="#" method="#" class="text-left">

              <div class="login-msg" v-bind:class="{hidden : login_msg}">
                <p class="text-center success">You are successfully signed up</p>
                <p class="text-center">Now you can login using login and password</p>
              </div>
              <div class="form-group">
                <label for="username"><span class="glyphicon glyphicon-user"></span> Name *</label>
                <input type="text" v-model="firstName" class="form-control" id="username" placeholder="Your name" name="firstName">
                <div  id="name-error"></div>
              </div>
              <div class="form-group">
                <label for="surname"><span class="glyphicon glyphicon-user"></span> Surname *</label>
                <input type="text" v-model="lastName" class="form-control" id="surname" placeholder="Your surname" name="lastName">
                <div id="surname-error"></div>
              </div>
              <div class="form-group">
                <label for="login"><span class="glyphicon glyphicon-user"></span> Login *</label>
                <input type="text" class="login form-control" v-model="login" id="login" placeholder="Your login" name="login">
                <div id="login-error"></div>
              </div>
              <div class="form-group">
                <label for="email"><span  class="glyphicon glyphicon-envelope"></span> Email *</label>
                <input class="email form-control" type="text" v-model="email" id="email"  placeholder="Enter email" name="email">
                <div  class="email-error"></div>
              </div>
              <div class="form-group">
                <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                <input type="password" v-model="password" id="psw" class="form-control"  placeholder="Enter password" name="password">
                <div class="password-error"></div>
              </div>
              <div class="checkbox">
                <label><input type="checkbox" value="" checked>Remember me</label>
              </div>
              <button class="btn btn-success btn-block"  v-on:click="signup"><span class="glyphicon glyphicon-off"></span> Sign Up</button>
              <div class="loader text-center" v-bind:class="{ hidden : loading}">
                <i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
                <span class="sr-only">Loading...</span>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        firstName: "",
        lastName: "",
        login: "",
        email: "",
        password: "",
        loading: true,
        login_msg: true,
      }
    },
    methods: {
      signup: function (event) {
        event.preventDefault();
        let reason = "";
        reason += this.validate();
        reason += this.validatePassword();
        reason += this.validateName();
        reason += this.validateSurame();
        reason += this.validateLogin();

        if (reason.length > 0) {

          console.log(3);
          return false;
        } else {
          this.loading = false; //после нажатия на кнопку логин выпадает лоадер
          console.log(1);
         let data = {
           firstName : this.firstName,
           lastName : this.lastName,
           login : this.login,
           password : this.password,
           email : this.email
         };
           this.$http.post('https://rocky-retreat-50096.herokuapp.com/api/register',
             JSON.stringify(data), {headers: {'Content-Type': 'application/json'}})
             .then(function(response){
               this.login_msg = false; //если логин или пароль неверно выпадает ошибка
               this.loading = true;
             console.log(response);
           }, function (error) {
               this.loading = true; //если логин или пароль неверно то лоадер после выпадения ошибки исчезает
             console.log(error);
           });
          return false;
        }
      },
      validate: function () {
        let error = "";
        let temail = this.email.replace(/^\s+|\s+$/, '');
        let emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/;
        let illegalChars = /[\(\)\<\>\,\;\:\\\"\}\?\{\%\[\]]/;

        if (this.email === "") {
          this.addError(".email", '.email-error', "Please enter an email address.");
          error = "2";
        } else if (!emailFilter.test(temail)) { //test email for illegal characters
          this.addError(".email", '.email-error', "Please enter a valid email address.");
          error = "3";
        } else if (this.email.match(illegalChars)) {
          this.addError(".email", '.email-error', "Email contains invalid characters.");
          error = "4";
        } else {
          $(".email").removeClass('.error-color');
          $('.email-error').html("");
        }
        return error;
      },
      validateLogin: function () {
        let error = "";
        let tlogin = this.login.replace(/^\s+|\s+$/, '');
        let loginFilter = /^([a-zA-Z, 0-9]+|\d+)$/i;

        if (this.login === "") {
          this.addError("#login","#login-error", "Please enter your login.");
          error = "1";
        } else if (!loginFilter.test(tlogin)) { //test password for illegal characters
          this.addError("#login","#login-error", "Please enter only latin letters and numbers.");
          error = "2";
        } else if (tlogin.length < 4) { //test password for illegal characters
          this.addError("#login","#login-error", "Please enter more then 4 symbols.");
          error = "3";
        } else {
          $("#login").removeClass('error-color');
          $("#login-error").html("");
        }
        return error;
      },
      validatePassword: function () {
        let error = "";
        let tpassword = this.password.replace(/^\s+|\s+$/, '');
        let passwordFilter = /^([a-zA-Z, 0-9]+|\d+)$/i;

        if (this.password === "") {
          this.addError("#psw", ".password-error", "Please enter a password.");
          error = "1";
        } else if (!passwordFilter.test(tpassword)) { //test password for illegal characters
          this.addError("#psw", ".password-error", "Please enter only latin letters and numbers.");
          error = "2";
        } else if (tpassword.length < 6) { //test password for illegal characters
          this.addError("#psw", ".password-error", "Please enter more then 6 symbols.");
          error = "3";
        } else {
          $("#psw").removeClass('error-color');
          $(".password-error").html("");
        }
        return error;
      },
      validateName: function () {
        let error = "";
        let tname = this.firstName.replace(/^\s+|\s+$/, '');
        let nameFilter = /^([a-zA-Zа-яёA-ZЁ]+)$/i;

        if (this.firstName === "") {
          this.addError("#username","#name-error", "Please enter your name.");
          error = "1";
        } else if (!nameFilter.test(tname)) { //test password for illegal characters
          this.addError("#username","#name-error", "Please enter only letters.");
          error = "2";
        } else if (tname.length < 2) { //test password for illegal characters
          this.addError("#username","#name-error", "Please enter more then 2 symbols.");
          error = "3";
        } else {
          $("#username").removeClass('error-color');
          $("#name-error").html("");
        }
        return error;
      },
      validateSurame: function () {
        let error = "";
        let tsurname = this.lastName.replace(/^\s+|\s+$/, '');
        let surnameFilter = /^([a-zA-Zа-яёA-ZЁ]+)$/i;

        if (this.lastName === "") {
          this.addError("#surname","#surname-error", "Please enter your surname.");
          error = "1";
        } else if (!surnameFilter.test(tsurname)) { //test password for illegal characters
          this.addError("#surname","#surname-error", "Please enter only letters.");
          error = "2";
        } else if (tsurname.length < 2) { //test password for illegal characters
          this.addError("#surname","#surname-error", "Please enter more then 2 symbols.");
          error = "3";
        } else {
          $("#surname").removeClass('error-color');
          $("#surname-error").html("");
        }
        return error;
      },
      addError: function (el, errorEl, message) {
        $(el).addClass('error-color');
        $(errorEl).html(message);
      }
    }
  }

  //    Bootstrap Modal SignUp
  $(document).ready(function(){
    $("#mySignUp").click(function(){
      $("#modal_signup").modal();
    });
  });
</script>

<style scoped>
  @font-face {
    font-family: FedraSansPro-DemiItalic;
    src: url('../../assets/fonts/FedraSansPro-DemiItalic.otf'); }

  /*modal window on page my_page */
  .modal-header{
    padding:10px;
  }
  .modal-save button{
    background-color: #4267b2;
    color:#000;
    margin:-15px 0 -15px 0; }

  .modal-save button:hover{
    background-color: cornflowerblue;
    color:#ffffff; }

  .open>.dropdown-toggle .btn-primary:focus{
    background-color: cornflowerblue;
    color:#fff;
    border-color: #ffffff;}

  .modal-header, h4, .close {
    background-color: #4267b2;
    color:white !important;
    text-align: center;
    font-size: 30px; }

  .btn-success{
    background-color: #4267b2;
    border-color: #4267b2;}

  .btn-success:hover {
    background-color: cornflowerblue; }

  .password-error,.email-error,#login-error,#surname-error,#name-error{
    font-family: Helvetica, Arial, sans-serif;
    font-size: 10px;
    color:red;
  }
  .error-color {
    background-color: silver;
    opacity: 0.7;
  }
  .login-msg p:first-child {
    color:red;
    font-size: 20px;
    margin-bottom: 0;
  }
</style>
