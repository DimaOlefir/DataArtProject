var app = new Vue({
    el: '#modal_signup',
    data: {
        name: "",
        surname: "",
        email: "",
        password: ""
    },
    methods: {
        signup: function (event) {
            event.preventDefault();
            reason = "";
            reason += this.validate();
            reason += this.validatePassword();
            reason += this.validateName();
            reason += this.validateSurame();

            if (reason.length > 0) {
                return false;
            } else {
                // this.$http.post('/someUrl', [body], [options]).then(successCallback, errorCallback);
                return false;
            }
        },
        validate: function () {
            var error = "";
            var temail = this.email.replace(/^\s+|\s+$/, '');
            var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/;
            var illegalChars = /[\(\)\<\>\,\;\:\\\"\}\?\{\%\[\]]/;

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
        validatePassword: function () {
            var error = "";
            var tpassword = this.password.replace(/^\s+|\s+$/, '');
            var passwordFilter = /^([a-zA-Z, 0-9]+|\d+)$/i;

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
        },
        validateName: function () {
            var error = "";
            var tname = this.name.replace(/^\s+|\s+$/, '');
            var nameFilter = /^([a-zA-Zа-яёA-ZЁ]+|\d+)$/i;

            if (this.name === "") {
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
        },
        validateSurame: function () {
            var error = "";
            var tsurname = this.surname.replace(/^\s+|\s+$/, '');
            var surnameFilter = /^([a-zA-Zа-яёA-ZЁ]+|\d+)$/i;

            if (this.surname === "") {
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
        },
        addError: function (el, errorEl, message) {
            $(el).addClass('error-color');
            $(errorEl).html(message);
        }
    }
});

//    Bootstrap Modal SignUp
$(document).ready(function(){
    $("#mySignUp").click(function(){
        $("#modal_signup").modal();
    });
});