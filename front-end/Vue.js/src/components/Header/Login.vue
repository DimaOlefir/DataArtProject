<template>
  <!-- Modal LogIn-->
  <div>
    <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header" style="padding:35px 50px;">
            <button type="button" class="close" data-dismiss="modal" ref="closeModalBtn">&times;</button>
            <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
          </div>
          <div class="modal-body" style="padding:40px 50px;">
            <form role="form" method="#" class="text-left">

              <div class="login-msg" v-bind:class="{hidden : login_msg}">
               <p class="text-center" style="color:red;">Your login or password incorrect</p>
              </div>
              <div class="form-group has-feedback">
                <label for="user_login"><span class="glyphicon glyphicon-user"></span> Login *</label>
                <input type="text" class="form-control" v-model="user_login" id="user_login" placeholder="Please enter your login" name="login">
                <div id="userlogin-error"></div>
              </div>
              <div class="form-group has-feedback">
                <label for="password"><span class="glyphicon glyphicon-eye-open"></span> Password *</label>
                <input type="password" id="password" class="form-control" v-model="password" placeholder="Please enter your password"  name="password">
                <div id="password-error"></div>
              </div>
              <div class="checkbox">
                <label><input type="checkbox" value="" checked>Remember me</label>
              </div>
              <button type="submit" class="btn btn-success btn-block" v-on:click="login"><span class="glyphicon glyphicon-off"></span> Login</button>
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
    data () {
      return {
        password: "",
        user_login: "",
        loading: true,
        login_msg: true,
      }
    },
    methods: {
      login: function (event) {
        event.preventDefault(); //чтобы после нажатия на sumbit, форма с ошибками автоматически не отправлялась
        let reason = "";
        reason += this. validateLogin();
        reason += this.validatePassword();

        if (reason.length > 0) {
          return false;
        } else {
          this.loading = false; //после нажатия на кнопку логин выпадает лоадер
          let data = {
            login : this.user_login,
            password : this.password
          };
          this.$http.post('https://rocky-retreat-50096.herokuapp.com/api/login',
            JSON.stringify(data), {headers: {'Content-Type': 'application/json'}})
            .then(function(response){
              console.log(response);

              if (response.status === 200 && 'token' in response.body) { //для хранения token в localstorage
                localStorage.setItem('token', response.body.token );
                this.$router.push('/mypage');
              }
              this.$refs.closeModalBtn.click(); //имитирует закрытие модального окна логина
            }, function (error) {
              this.login_msg = false; //если логин или пароль неверно выпадает ошибка
              this.loading = true; //если логин или пароль неверно то лоадер после выпадения ошибки исчезает
              console.log(error);
            });
          return false;
        }
      },
      validateLogin: function () {
        let error = "";
        let tlogin = this.user_login.replace(/^\s+|\s+$/, '');
        let loginFilter = /^([a-zA-Z, 0-9]+|\d+)$/i;

        if (this.user_login === "") {
          this.addError("#user_login","#userlogin-error", "Please enter your login.");
          error = "1";
        } else if (!loginFilter.test(tlogin)) { //test password for illegal characters
          this.addError("#user_login","#userlogin-error", "Please enter only latin letters and numbers.");
          error = "2";
        } else if (tlogin.length < 4) { //test password for illegal characters
          this.addError("#user_login","#userlogin-error", "Please enter more then 4 symbols.");
          error = "3";
        } else {
          $("#user_login").removeClass('error-color');
          $("#userlogin-error").html("");
        }
        return error;
      },
      validatePassword: function () {
        let error = "";
        let tpassword = this.password.replace(/^\s+|\s+$/, '');
        let passwordFilter = /^([a-zA-Z, 0-9]+)$/i;

        if (this.password === "") {
          this.addError("#password", "#password-error", "Please enter a password.");
          error = "2";
        } else if (!passwordFilter.test(tpassword)) { //test password for illegal characters
          this.addError("#password", "#password-error", "Please enter latin letters and numbers.");
          error = "3";
        } else if (tpassword.length < 6) { //test password for illegal characters
          this.addError("#password", "#password-error", "Please enter more then 6 symbols.");
          error = "3";
        } else {
          $("#password").removeClass('error-color');
          $("#password-error").html("");
        }
        return error;
      },
      addError: function (el, errorEl, message) {
        $(el).addClass('error-color');
        $(errorEl).html(message);
      }
    }
  }

  //    Bootstrap Modal LOGIN
  $(document).ready(function() {
    $("#myLogin").click(function () {
      $("#myModal").modal();
    });
  });
</script>

<style scoped>
  @font-face {
    font-family: FedraSansPro-DemiItalic;
    src: url('../../assets/fonts/FedraSansPro-DemiItalic.otf'); }

  /*modal window on page my_page */
  #myModal{
    margin-top: 2%;
    border-radius: 3px; }

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

  .dropdown form{
    margin-left: 25%; }

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
  #password-error,#userlogin-error{
    font-family: Helvetica, Arial, sans-serif;
    font-size: 10px;
    color:red;
  }
  .error-color {
    background-color: silver;
    opacity: 0.7;
  }
  .login-msg p {
    color:red;
    font-size: 20px;
    margin-bottom: 0;
  }
</style>
