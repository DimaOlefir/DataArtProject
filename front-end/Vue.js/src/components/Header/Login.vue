<template>
  <!-- Modal LogIn-->
  <div>
    <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header" style="padding:35px 50px;">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
          </div>
          <div class="modal-body" style="padding:40px 50px;">
            <form role="form" method="post" class="text-left">
              <div class="form-group has-feedback">
                <label for="email"><span class="glyphicon glyphicon-user"></span> Email *</label>
                <input type="email" class="email form-control" v-model="email" id="email" name="email" placeholder="Enter email">
                <div class="email-error"></div>
              </div>
              <div class="form-group has-feedback">
                <label for="password"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                <input type="password" id="password" class="form-control" v-model="password"  placeholder="Enter password" required name="password" pattern="[A-Za-z, 0-9]{6,}">
                <div id="password-error"></div>
              </div>
              <div class="checkbox">
                <label><input type="checkbox" value="" checked>Remember me</label>
              </div>
              <button type="submit" class="btn btn-success btn-block" v-on:click="login"><span class="glyphicon glyphicon-off"></span> Login</button>
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
        email: "",
        password: ""
      }
    },
    methods: {
      login: function (event) {
        event.preventDefault();
        let reason = "";
        reason += this.validateEmail();
        reason += this.validatePassword();

        if (reason.length > 0) {
          return false;
        } else {
          // this.$http.post('/someUrl', [body], [options]).then(successCallback, errorCallback);
          return false;
        }
      },
      validateEmail: function () {
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
          $(".email").removeClass('error-color');
          $('.email-error').html("");
        }
        return error;
      },
      validatePassword: function () {
        let error = "";
        let tpassword = this.password.replace(/^\s+|\s+$/, '');
        let passwordFilter = /^([a-zA-Z, 0-9]+|\d+)$/i;

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

<style >
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

  .modal-header, h4, button, .close {
    background-color: #4267b2;
    color:white !important;
    text-align: center;
    font-size: 30px; }

  .btn-success{
    background-color: #4267b2;
    border-color: #4267b2;}

  .btn-success:hover {
    background-color: cornflowerblue; }

  .password-error,.email-error,#password-error,#surname-error,#name-error{
    font-family: Helvetica, Arial, sans-serif;
    font-size: 10px;
    color:red;
  }
  .error-color {
    background-color: silver;
    opacity: 0.7;
  }
</style>
