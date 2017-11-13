var app = new Vue({
    el: '#myModal',
    data: {
        email: "",
        password: ""
    },
    methods: {
        login: function (event) {
            event.preventDefault();
            reason = "";
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
                $(".email").removeClass('error-color');
                $('.email-error').html("");
            }
            return error;
        },
        validatePassword: function () {
            var error = "";
            var tpassword = this.password.replace(/^\s+|\s+$/, '');
            var passwordFilter = /^([a-zA-Z, 0-9]+|\d+)$/i;

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
});

//    Bootstrap Modal LOGIN
$(document).ready(function(){
    $("#myLogin").click(function(){
        $("#myModal").modal();
    });
});