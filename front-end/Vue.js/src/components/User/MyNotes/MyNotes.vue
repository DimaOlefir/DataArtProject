<template>
	<div class="container-fluid">
    <UserHeader></UserHeader>
		<div class="user-photos">
      <User></User>
			<div class="photos">
				<h2>{{title}}</h2>
				<div class="photo-list" v-for="item in photoItem">
					<p class="text-left">{{item.title}}</p>
					<textarea class="form-control" rows="3"></textarea>
					<div class="comments-btn">
						<button class="btn btn-primary">Add Comment</button>
						<button class="btn btn-danger">Delete</button>
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
		data() {
			return {
				title: 'Your photo on the marks',
				photoItem: [
					{
						title: 'Eiffel Tower in Paris on the street Radicul 16'
					},
					{
						title: 'House with my dream on the Rococo street'
					}
				]
			}
		},
		components: {
		    User, UserHeader
		}
	}
</script>

<style scoped>
	.container-fluid {
		padding: 0;
	}
	.user-photos {
		display: -webkit-flex;
		display: -moz-flex;
		display: -ms-flex;
		display: -o-flex;
		display: flex;
		justify-content: space-around;
		background-color: #f0fff0;
		padding-top: 20px;
	}
	.photos {
		width: 80%;
		border: 1px solid #4267b2;
		border-radius: 5px;
		padding: 0 100px;
		background-color: #fff;
	}
	h2 {
		margin-bottom: 50px;
	}
	.photo-list {
		margin-bottom: 50px;
		padding-bottom: 20px;
		border-bottom: 1px solid #4267b2;
	}
	.photo-list p {
		margin-bottom: 30px;
	}
	.photo-list textarea {
		margin-bottom: 40px;
	}
	textarea {
		border-color: #4267b2;
	}
	.comments-btn {
		display: -webkit-flex;
		display: -moz-flex;
		display: -ms-flex;
		display: -o-flex;
		display: flex;
		justify-content: flex-end;
	}
  .btn-primary {
    margin-right: 10px;
  }
</style>
