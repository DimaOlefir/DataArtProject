<template>
	<div class="container-fluid">
    <UserHeader></UserHeader>
		<div class="user-settings">
			<User></User>
			<div class="settings">
			  	<h2>Your personal information</h2>

				<form class="form-horizontal">
				   <div class="form-group">
				    <label for="firstName" class="col-sm-2 control-label">First name</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="firstName" placeholder="Your first name">
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="lastName" class="col-sm-2 control-label">Last name</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="lastName" placeholder="Your last name">
				    </div>
				  </div>

          <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Login</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="login" placeholder="Your login">
            </div>
          </div>

				  <div class="form-group">
				    <label for="password" class="col-sm-2 control-label">Password</label>
				    <div class="col-sm-8">
				      <input type="password" class="form-control" id="password" placeholder="Your password">
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="Sex" class="col-sm-2 control-label">Sex</label>
				    <div class="col-sm-8">
				    	<select class="form-control" id="sex">
				    	  <option>male</option>
				    	  <option>female</option>
				    	</select>
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="birthday" class="col-sm-2 control-label">Birthday</label>
				    <div class="col-sm-8">
				    	<input type="date" id="birthday" class="form-control" max="2100-01-01" min="1900-01-01" value="2017-01-01" />
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="City" class="col-sm-2 control-label">City</label>
				    <div class="col-sm-8">
				      <input type="email" class="form-control" id="City" placeholder="City">
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="phone" class="col-sm-2 control-label">Phone</label>
				    <div class="col-sm-8">
				    	<input type="tel" class="form-control" id="phone" pattern="\(\d\d\d\) ?\d\d\d-\d\d-\d\d" placeholder="(###) ###-##-##"/>
				    </div>
				  </div>

          <div class="form-group">
				    <div class="col-sm-1 col-sm-offset-9 sign-in">
				      <button type="submit" class="btn btn-primary">Sign in</button>
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
		}
	}
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
</style>
