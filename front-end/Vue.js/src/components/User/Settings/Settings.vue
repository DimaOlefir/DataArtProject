<template>
  <div class="container-fluid">
    <UserHeader></UserHeader>
    <div class="user-settings">
      <User></User>
      <div class="settings">
        <h2>Your personal information</h2>

        <form role="form" class="form-horizontal">

          <div class="settings-msg" v-bind:class="{hidden : settings_msg}">
            <p class="text-center success">You are successfully change settings</p>
          </div>
          <div class="settings-msgerror" v-bind:class="{hidden : settings_msgerror }">
            <p class="text-center success">An error has occurred</p>
          </div>

          <div class="form-group">
            <label for="username" class="col-sm-2 control-label">First name</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" v-model="firstName" id="username" placeholder="Your first name">
              <div  id="name-error"></div>
            </div>
          </div>

          <div class="form-group">
            <label for="surname" class="col-sm-2 control-label">Last name</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" v-model="lastName" id="surname" placeholder="Your last name">
              <div  id="surname-error"></div>
            </div>
          </div>

          <div class="form-group">
            <label for="login" class="col-sm-2 control-label">Login</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" v-model="login" id="login" placeholder="Your login">
              <div  id="login-error"></div>
            </div>
          </div>

          <!--<div class="form-group">-->
            <!--<label class="col-sm-2 control-label">Password</label>-->
            <!--<div class="col-sm-2">-->
              <!--<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Change password</button>-->
              <!--&lt;!&ndash;<input type="password" class="form-control" v-model="password" id="psw" placeholder="Your password">&ndash;&gt;-->
              <!--&lt;!&ndash;<div  class="password-error"></div>&ndash;&gt;-->
            <!--</div>-->
          <!--</div>-->

          <div class="form-group">
            <label class="col-sm-2 control-label">Email</label>
            <div class="col-sm-8">
              <input type="text" class="form-control email" v-model="email"  placeholder="Your email">
              <div  class="email-error"></div>
            </div>
          </div>

          <div class="form-group">
            <label for="Sex" class="col-sm-2 control-label">Sex</label>
            <div class="col-sm-8">
              <select class="form-control"  v-model="sex" id="sex">
                <option>MAN</option>
                <option>WOMAN</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label for="birthday" class="col-sm-2 control-label">Birthday</label>
            <div class="col-sm-8">
              <input type="date" id="birthday" class="form-control" v-model="birthday" max="2100-01-01" min="1900-01-01" value="2017-01-01" />
            </div>
          </div>

          <div class="form-group">
            <label for="about" class="col-sm-2 control-label">About</label>
            <div class="col-sm-8">
              <input type="email" class="form-control" v-model="about" id="about" placeholder="Please enter information about yourself">
              <div id="about-error"></div>
            </div>
          </div>

          <!--<div class="form-group">-->
          <!--<label for="phone" class="col-sm-2 control-label">Phone</label>-->
          <!--<div class="col-sm-8">-->
          <!--<input type="tel" class="form-control" id="phone" v-model="phone" pattern="\(\d\d\d\) ?\d\d\d-\d\d-\d\d" placeholder="(###) ###-##-##"/>-->
          <!--</div>-->
          <!--</div>-->

          <div class="form-group">
            <div class="col-sm-1 col-sm-offset-9 sign-in">
              <button type="submit" class="btn btn-primary" v-on:click="save_settings">Save</button>
            </div>
          </div>
        </form>

        <!-- Modal for password -->
        <div class="modal fade" id="myModal" role="dialog">
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Change password </h4>
                <h5 v-bind:class="{hidden : passwordChanged}">Your password successfully changed</h5>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <label>Existing password</label>
                  <input type="password" class="form-control" v-model="passwordOld"  placeholder="Your password">
                </div>
                <div class="form-group">
                  <label>New password</label>
                  <input type="password" class="form-control psw" v-model="passwordNew" placeholder="Your password">
                  <div  class="password-error"></div>
                </div>
              </div>
              <div class="modal-footer">
                <button class="btn btn-default" v-on:click="changePassword">Send</button>
              </div>
            </div>

          </div>
        </div>

      </div>
    </div>
    <!-- <FooterComponent /> -->
  </div>
</template>

<script>
  import User from '../User';
  import UserHeader from '../../Header/UserHeader.vue';
  export default {
    components: {
      User, UserHeader
    },
    data() {
      return {
        firstName: "",
        lastName: "",
        login: "",
        email: "",
        passwordNew: "",
        passwordOld:"",
//            loading: true,
//            login_msg: true,
//            phone:"",
        sex:"",
        about:"",
        birthday:"",
        passwordChanged: true,
        settings_msg: true,
        settings_msgerror: true
      }
    },
    beforeCreate: function () {     //для того чтобы незареганый пассажир не зашел на эту страницу
      if (!localStorage.getItem('token')) {
        this.$router.push('/')
      }
    },
    created: function () {
      this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/user',
        {headers: {'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('token')}})
        .then(function(response){
          this.login_msg = false; //если логин или пароль неверно выпадает ошибка
          this.loading = true;
          console.log(response);
          this.firstName = response.body.firstName;
          this.lastName = response.body.lastName;
          this.login = response.body.login;
          this.email = response.body.email;
          let date = new Date(response.body.birthDate);
          this.birthday = date.getFullYear() + '-' + ("0" + (date.getMonth() + 1)).slice(-2) + '-' + ("0" + date.getDate()).slice(-2);
          this.sex = response.body.sex;
          this.about = response.body.about;

        }, function (error) {
          this.loading = true; //если логин или пароль неверно то лоадер после выпадения ошибки исчезает
          console.log(error);
        });
    },

    methods: {
      save_settings: function (event) {
        event.preventDefault();
        let reason = "";
            reason += this.validate();
//            reason += this.validatePassword();
        reason += this.validateName();
        reason += this.validateSurame();
        reason += this.validateLogin();
        reason += this.validateAbout();

        if (reason.length > 0) {
          return false;
        } else {
          this.loading = false; //после нажатия на кнопку логин выпадает лоадер
          console.log(this.birthday);
          let data = {
            firstName : this.firstName,
            lastName : this.lastName,
            login : this.login,
//                password : this.password,
            email : this.email,
//                phone : this.phone,
            sex: this.sex,
//                city: this.city,
            birthDate: this.birthday,
            about : this.about,
//                id: this.id

          };
          this.$http.post('https://rocky-retreat-50096.herokuapp.com/api/user',
            JSON.stringify(data), {headers: {'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + localStorage.getItem('token')}})
            .then(function(response){
              this.settings_msg = false;
              if (data.login) { // разлогинить пользователя если менялся логин
                localStorage.removeItem("token");
                location.replace('/');
              }
              console.log(response);
            }, function (error) {
              this.settings_msgerror = false;
              console.log(error);
            });
          return false;
        }
      },
      changePassword: function (event) {
//            event.preventDefault();
        console.log(1);
        let reason = "";
//            reason += this.validatePassword();
        if (reason.length > 0) {
          console.log(3);
          return false;
        } else {
          let data = {
            passwordOld : this.passwordOld,
            passwordNew : this.passwordNew
          };

          console.log(JSON.stringify(data));
          console.log(localStorage.getItem('token'));

          this.$http.post('https://rocky-retreat-50096.herokuapp.com/api/user/password',
            {body: JSON.stringify(data), headers: {'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + localStorage.getItem('token')}})
            .then(function(response){
              this.passwordChanged = false;
            }, function (error) {
              console.log(error);
            });
//              localStorage.removeItem("token");
//              location.replace('/');
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
          this.addError("#login", "#login-error", "Please enter more then 4 symbols.");
          error = "3";
        }else if (tlogin.length > 30) { //test password for illegal characters
          this.addError("#login","#login-error", "Please enter less then 30 symbols.");
          error = "4";
        } else {
          $("#login").removeClass('error-color');
          $("#login-error").html("");
        }
        return error;
      },
      validatePassword: function () {
        let error = "";
        let tpassword = this.passwordNew.replace(/^\s+|\s+$/, '');
        let passwordFilter = /^([a-zA-Z, 0-9]+|\d+)$/i;

        if (this.passwordNew === "") {
          this.addError(".psw", ".password-error", "Please enter a password.");
          error = "1";
        } else if (!passwordFilter.test(tpassword)) { //test password for illegal characters
          this.addError(".psw", ".password-error", "Please enter only latin letters and numbers.");
          error = "2";
        } else if (tpassword.length < 6) { //test password for illegal characters
          this.addError(".psw", ".password-error", "Please enter more then 6 symbols.");
          error = "3";
        } else {
          $(".psw").removeClass('error-color');
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
          this.addError("#username", "#name-error", "Please enter more then 2 symbols.");
          error = "3";
        }else if (tname.length > 30) { //test password for illegal characters
          this.addError("#username","#name-error", "Please enter less then 30 symbols.");
          error = "4";
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
          this.addError("#surname", "#surname-error", "Please enter more then 2 symbols.");
          error = "3";
        }else if (tsurname.length > 30) { //test password for illegal characters
          this.addError("#surname", "#surname-error", "Please enter less then 30 symbols.");
          error = "3";
        } else {
          $("#surname").removeClass('error-color');
          $("#surname-error").html("");
        }
        return error;
      },
      validateAbout: function () {
        let error = "";
        let tabout = this.about.replace(/^\s+|\s+$/, '');
        if (tabout) {
          let aboutFilter = /^([a-zA-Zа-яёA-ZЁ]+)$/i;

//            if (this.about === "") {
//              this.addError("#about","#about-error", "Please enter your city.", );
//              error = "1";
//            } else
//              if (!aboutFilter.test(tabout)) { //test password for illegal characters
//                this.addError("#about","#about-error", "Please enter only letters.");
//                error = "2";
//              } else
          if (tabout.length < 2) { //test password for illegal characters
            this.addError("#about", "#about-error", "Please enter more then 2 symbols.");
            error = "3";
          }else if (tabout.length > 100) { //test password for illegal characters
            this.addError("#about","#about-error", "Please enter less then 100 symbols.");
            error = "3";
          } else {
            $("#about").removeClass('error-color');
            $("#about-error").html("");
          }
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
  .container-fluid {
    padding: 0;
  }
  .user-settings {
    display: -webkit-flex;
    display: -moz-flex;
    display: -ms-flex;
    display: -o-flex;
    display: flex;
    justify-content: space-around;
    background-color: #f0fff0;
    padding-top: 20px;
  }
  .settings {
    width: 80%;
    border: 1px solid #4267b2;
    border-radius: 5px;
    padding: 0;
    background-color: #fff;
  }
  h2 {
    margin-bottom: 50px;
  }
  .sign-in button:hover{
    background-color: cornflowerblue;
  }
  .password-error, #login-error, #name-error, .email-error, #surname-error{
    color:red;
    font-size: 12px;
    text-align: left;
  }
   .error-color{
    border-color:red;
    -moz-box-shadow: 0 0 12px #dececf;
    -webkit-box-shadow: 0 0 12px #dececf;
    box-shadow: 0 0 12px #dececf;
  }
  .success{
    color:red;
  }
</style>
