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
        <div class="photo navbar-right">
          <h3>Your photo on the marks</h3>

          <!-- list with photo -->
          <div class="photo_list">
            <div class="media list-inline">
              <a href="#" title="Hide comments" class="pull-right" data-toggle="popover" data-placement="bottom" data>
                <span class="glyphicon glyphicon-option-horizontal"></span>
              </a>
              <div class="media-left">
                <img src="../../../assets/img/4_b.jpg" class="media-object" style="width:100px">
              </div>
              <div class="media-body">
                <h5 class="media-heading text-left">Eiffel Tower in Paris on the street Radicul 16</h5>
                <form>
                  <div class="input-group">
                    <textarea type="email" class="form-control"  placeholder="add comment" name="email"></textarea>
                  </div>
                </form>
                <div class="btn-group pull-right">
                  <button type="button" class="btn btn-primary">Add comment</button>
                  <button type="button" class="btn btn-primary">Delete</button>
                </div>
              </div>
            </div>
          </div>

          <!-- list with photo -->
          <div class="photo_list">
            <div class="media list-inline">
              <a href="#" title="Hide comments" class="pull-right" data-toggle="popover" data-placement="bottom" data>
                <span class="glyphicon glyphicon-option-horizontal"></span>
              </a>
              <div class="media-left">
                <img src="../../../assets/img/4_b.jpg" class="media-object" style="width:100px">
              </div>
              <div class="media-body">
                <h5 class="media-heading text-left">Eiffel Tower in Paris on the street Radicul 16</h5>
                <form>
                  <div class="input-group">
                    <textarea type="email" class="form-control"  placeholder="add comment" name="email"></textarea>
                  </div>
                </form>
                <div class="btn-group pull-right">
                  <button type="button" class="btn btn-primary">Add comment</button>
                  <button type="button" class="btn btn-primary">Delete</button>
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
  import UserHeader from '../../Header/UserHeader.vue';
  export default {
		data() {
//			return {
////				title: 'Your photo on the marks',
////				photoItem: [
////					{
////						title: 'Eiffel Tower in Paris on the street Radicul 16'
////					},
////					{
////						title: 'House with my dream on the Rococo street'
////					}
////				]
//			}
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
	.photo_list {
    margin-top:8%;
		margin-bottom: 50px;
		padding-bottom: 20px;
		border-bottom: 1px solid #4267b2;
	}
	.photo-list p {
		margin-bottom: 30px;
	}
	.media-body textarea {
    display: block;
    width:98%;
    margin: 1%;
		margin-bottom: 20px;
    border-radius: 0;
    border-color: #4267b2;
    height: 80px;
    resize: none;
	}
  .btn-primary {
    margin-right: 10px;
  }
  .input-group {
    display: block;
  }

</style>
