<template>
	<div class="container-fluid">
    <UserHeader></UserHeader>
		<div class="user-settings">
			<User></User>
			<div class="settings">
			  	<h2>Your personal information</h2>

				<form class="form-horizontal">
				   <div class="form-group">
				    <label for="firstName" class="col-sm-offset-1 col-sm-1 control-label">First Name</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="firstName" placeholder="Your First Name">
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="lastName" class="col-sm-offset-1 col-sm-1 control-label">Last Name</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="lastName" placeholder="Your Last Name">
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="password" class="col-sm-offset-1 col-sm-1 control-label">Password</label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" id="password" placeholder="Your Password">
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="Sex" class="col-sm-offset-1 col-sm-1 control-label">Sex</label>
				    <div class="col-sm-9">
				    	<select class="form-control" id="sex">
				    	  <option>male</option>
				    	  <option>female</option>
				    	</select>
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="birthday" class="col-sm-offset-1 col-sm-1 control-label">Name</label>
				    <div class="col-sm-9">
				    	<input type="date" id="birthday" class="form-control" max="2100-01-01" min="1900-01-01" value="2017-01-01" />
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="City" class="col-sm-offset-1 col-sm-1 control-label">City</label>
				    <div class="col-sm-9">
				      <input type="email" class="form-control" id="City" placeholder="City">
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="phone" class="col-sm-offset-1 col-sm-1 control-label">Phone</label>
				    <div class="col-sm-9">
				    	<input type="tel" class="form-control" id="phone" pattern="\(\d\d\d\) ?\d\d\d-\d\d-\d\d" placeholder="(###) ###-##-##"/>
				    </div>
				  </div>

          <div class="form-group">
				    <div class="col-sm-offset-10 col-sm-1">
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
</style>
