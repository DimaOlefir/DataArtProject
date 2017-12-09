<template>
	<div class="container-fluid">
    <UserHeader></UserHeader>
		<div class="user-settings">
			<User></User>
			<div class="settings">
			  	<h2>Your personal information</h2>

				<form role="form" class="form-horizontal" >
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

				  <div class="form-group">
				    <label for="psw" class="col-sm-2 control-label">Password</label>
				    <div class="col-sm-8">
				      <input type="password" class="form-control" v-model="password" id="psw" placeholder="Your password">
              <div  class="password-error"></div>
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="Sex" class="col-sm-2 control-label">Sex</label>
				    <div class="col-sm-8">
				    	<select class="form-control"  v-model="sex" id="sex">
				    	  <option>male</option>
				    	  <option>female</option>
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
				    <label for="City" class="col-sm-2 control-label">City</label>
				    <div class="col-sm-8">
				      <input type="email" class="form-control " v-model="city" id="City" placeholder="City">
              <div id="city-error"></div>
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="phone" class="col-sm-2 control-label">Phone</label>
				    <div class="col-sm-8">
				    	<input type="tel" class="form-control" id="phone" v-model="phone" pattern="\(\d\d\d\) ?\d\d\d-\d\d-\d\d" placeholder="(###) ###-##-##"/>
				    </div>
				  </div>

          <div class="form-group">
				    <div class="col-sm-1 col-sm-offset-9 sign-in">
				      <button type="submit" class="btn btn-primary" v-on:click="save_settings">Save</button>
				    </div>
				  </div>
				</form>
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
            password: "",
            loading: true,
            login_msg: true,
            phone:"",
            sex:"",
            city:"",
            birthday:"",
            id: ""
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
          this.birthDate = response.body.birthDate;
          this.sex = response.body.sex;
          this.id = response.body.id;

        }, function (error) {
          this.loading = true; //если логин или пароль неверно то лоадер после выпадения ошибки исчезает
          console.log(error);
        });
    },
        methods: {
          save_settings: function (event) {
            event.preventDefault();
            let reason = "";
//            reason += this.validate();
//            reason += this.validatePassword();
            reason += this.validateName();
            reason += this.validateSurame();
            reason += this.validateLogin();
//            reason += this.validateCity();


            if (reason.length > 0) {

              console.log(3);
              return false;
            } else {
              this.loading = false; //после нажатия на кнопку логин выпадает лоадер
              console.log(1);
              let data = {
                firstName : this.firstName,
                about : this.firstName,
                lastName : this.lastName,
                login : this.login,
//                password : this.password,
                email : this.email,
//                phone : this.phone,
                sex: this.sex,
//                city: this.city,
                birthday: this.birthday,
//                id: this.id

              };
              this.$http.post('https://rocky-retreat-50096.herokuapp.com/api/user',
                JSON.stringify(data), {headers: {'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + localStorage.getItem('token')}})
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
          validateCity: function () {
            let error = "";
            let tcity = this.city.replace(/^\s+|\s+$/, '');
            let cityFilter = /^([a-zA-Zа-яёA-ZЁ]+)$/i;

            if (this.city === "") {
              this.addError("#City","#city-error", "Please enter your city.", );
              error = "1";
            } else if (!cityFilter.test(tcity)) { //test password for illegal characters
              this.addError("#City","#city-error", "Please enter only letters.");
              error = "2";
            } else if (tcity.length < 2) { //test password for illegal characters
              this.addError("#City", "#city-error", "Please enter more then 2 symbols.");
              error = "3";
            }else if (tcity.length > 30) { //test password for illegal characters
                this.addError("#City","#city-error", "Please enter less then 30 symbols.");
                error = "3";
            } else {
              $("#City").removeClass('error-color');
              $("#city-error").html("");
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
  .password-error, #login-error, #name-error, #city-error, #surname-error{
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
</style>
