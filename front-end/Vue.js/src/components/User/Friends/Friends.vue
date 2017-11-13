<template>
	<div class="container-fluid">
		<HeaderComponent />
		<div class="user-friends">
			<User />
			<div class="friends">
				<form class="form-inline search">
				  <div class="form-group">
				    <label for="searchFriend">Search</label>
				    <input type="text" class="form-control" id="searchFriend" placeholder="Search Friends" size="70">
				  </div>
				  <button type="submit" class="btn btn-primary btn-search">
					<span class="glyphicon glyphicon-search"></span>
				  </button>
				</form>

				<div class="friend-list">
					<span class="glyphicon glyphicon-user">Igor</span>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#FriendsModal" data-whatever="@getbootstrap">Write letter</button>

					<button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="bottom" data-content="Delete friend">
					  <span class="glyphicon glyphicon-option-horizontal"></span>
					</button>

					<div class="modal fade" id="FriendsModal" tabindex="-1" role="dialog" aria-labelledby="FriendsModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="FriendsModalLabel">Write Letter</h4>
					      </div>
					      <div class="modal-body">
					        <form>
					          <div class="form-group">
					            <label for="message-text" class="control-label">Message:</label>
					            <textarea class="form-control" id="message-text"></textarea>
					          </div>
					        </form>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        <button type="button" class="btn btn-primary">Send message</button>
					      </div>
					    </div>
					  </div>
					</div>
				</div>

				<div class="friend-list">
					<span class="glyphicon glyphicon-user">Lena</span>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#FriendsModal" data-whatever="@getbootstrap">Write letter</button>

					<button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="bottom" data-content="Delete friend">
					  <span class="glyphicon glyphicon-option-horizontal"></span>
					</button>

					<div class="modal fade" id="FriendsModal" tabindex="-1" role="dialog" aria-labelledby="FriendsModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="FriendsModalLabel">Write Letter</h4>
					      </div>
					      <div class="modal-body">
					        <form>
					          <div class="form-group">
					            <label for="message-text" class="control-label">Message:</label>
					            <textarea class="form-control" id="message-text"></textarea>
					          </div>
					        </form>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        <button type="button" class="btn btn-primary">Send message</button>
					      </div>
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
	import HeaderComponent from '../../Header/Header';
	import FooterComponent from '../../Footer/Footer';
	$(document).ready(function(){
	    $('[data-toggle="popover"]').popover();
	});
	export default {
		components: {
			User, HeaderComponent, FooterComponent
		}
	}
</script>

<style scoped>
	template {

	}
	.container-fluid {
		padding: 0;
	}
	.user-friends {
		display: -webkit-flex;
		display: -moz-flex;
		display: -ms-flex;
		display: -o-flex;
		display: flex;
		justify-content: space-around;
		background-color: #f0fff0;
		padding-top: 20px;
	}
	.friends {
		width: 70vw;
		border: 1px solid #4267b2;
		border-radius: 5px;
		padding: 0 100px;
		background-color: #fff;
	}
	.search {
		margin: 30px 0 50px 0;
	}
	.form-inline {
		margin-bottom: 50px;
	}
	.btn.btn-search {
		padding: 6px 45px;
	}
	.friend-list {
		margin-bottom: 50px;
		padding-bottom: 50px;
		/*padding: 50px 0;*/
		border-bottom: 1px solid #4267b2;
	}
	.glyphicon-user {
		margin-bottom: -10px;
		font-size: 25px;
	}
</style>