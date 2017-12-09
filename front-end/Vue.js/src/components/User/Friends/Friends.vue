<template>
  <div class="container-fluid">
    <UserHeader></UserHeader>
    <div class="user-friends">
      <User></User>
      <div class="friends">
        <form class="form-inline search">
          <div class="form-group">
            <input type="text" v-model="searchQuery" class="form-control" id="searchFriend" placeholder="Search Friends" size="70">
          </div>
        </form>

        <div v-if="filteredFriends.length > 0">
          <div class="friend" v-for="(friend, index) in filteredFriends">
            <span class="glyphicon glyphicon-user">{{friend.firstName}} {{friend.lastName}}</span>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#FriendsModal" data-whatever="@getbootstrap">Write letter</button>

            <button type="button" class="btn btn-danger" @click="deleteFriend(friend)">Delete Friend</button>

            <!-- <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="bottom" data-content="Delete friend" @click="deleteFriend(friend)">
              <span class="glyphicon glyphicon-option-horizontal">Delete Friend</span>
            </button> -->
          </div>
        </div>
        <!-- list with friends -->
        <div class="media friend_list" v-if="filteredFriends.length > 0">
          <div class="media list-inline" v-for="(friend, index) in filteredFriends">
            <a href="#" title="Delete friend" class="pull-right" data-toggle="popover" data-placement="bottom" data>
              <span class="glyphicon glyphicon-option-horizontal"></span>
            </a>
            <div class="media-left ava-friends">
              <img src="../../../assets/img/2_f.jpg" alt="Mountains">
            </div>
            <div class="media-body">
              <h5 class="media-heading text-left">{{friend.firstName}} {{friend.lastName}}</h5>
              <button type="button" class="btn btn-info btn-sm pull-left" data-toggle="modal" data-target="#myModal">Write letter</button>
            </div>
            <hr class="hr-line">
          </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
          <div class="modal-dialog modal-sm">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <form>
                  <h4>Write letter</h4>
                  <div class="input-group">
                    <textarea type="email" class="form-control" id="description" placeholder="marker description" name="email"></textarea>
                  </div>
                </form>
                <div class="modal-save modal-footer">
                  <button type="button" class="btn btn-default">Save</button>
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
  $(document).ready(function(){
    $('[data-toggle="popover"]').popover();
  });

  let nextFriendId = 1;
  export default {
    components: {
      User, UserHeader
    },
    data() {
      return {
        searchQuery: '',
        friends: [
          {id: nextFriendId++, firstName: 'Tanya', lastName: 'Osa'},
          {id: nextFriendId++, firstName: 'Anya', lastName: 'Korova'},
          {id: nextFriendId++, firstName: 'Slava', lastName: 'Superman'},
          {id: nextFriendId++, firstName: 'Roma', lastName: 'Backend'},
        ],
      }
    },
    created: function () {
      this.getFriends();
    },
    computed: {    //вычисляемая переменная поиск каждой введенной буквы
      filteredFriends: function () {
        let self = this;
        return this.friends.filter(function (friend) {
          let firstName = friend.firstName.toLowerCase();
          let lastName = friend.lastName.toLowerCase();
          let searchQuery = self.searchQuery.toLowerCase();
          return firstName.indexOf(searchQuery) !== -1 || lastName.indexOf(searchQuery) !== -1 ;
        });
      },
    },
    methods: {
      getFriends: function () {  //раскоментировать когда будет путь http...
//        this.$http.get('/api/friends').then(function (response) {
//          this.friends = response.body;
//        }, function (error) {
//          console.log(error);
//        });
        this.friends = [    //тестовые друзья
          {firstName: 'Tanya', lastName: 'Osa'},
          {firstName: 'Anya', lastName: 'Korova'},
          {firstName: 'Slava', lastName: 'Superman'},
          {firstName: 'Roma', lastName: 'Backend'},
        ];
      },
      deleteFriend: function(friend) {
        let index = this.friends.indexOf(friend);
        this.friends.splice(index, 1);
      }
    }
  }
</script>

<style scoped>
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
    width: 80%;
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

  .friend {
    display: -webkit-flex;
    display: -moz-flex;
    display: -ms-flex;
    display: -o-flex;
    display: flex;
    margin-bottom: 50px;
    padding-bottom: 50px;
    /*padding: 50px 0;*/
    border-bottom: 1px solid #4267b2;
  }
  .glyphicon-user {
    width: 60%;
    margin-bottom: -10px;
    font-size: 25px;
  }
  .btn-primary {
    margin-right: 10px;
  }
  .btn-default{
    background-color: #4267b2;
    width:100%;
  }
  .friend_list{
    padding: 5%;
  }
  .friend_list hr {
    border-top: 1px solid #4267b2;;
  }
  .ava-friends img{
    width: 60px;
    height: 60px;
    border-radius: 50%;
  }

  #myModal{
    margin-top: 2%;
    border-radius: 3px;
  }
  .modal-header{
    background-color: #4267b2;
    color:#ffffff;
  }
  .modal-header .close{
    color:#ffffff;
  }
  .input-group {
    margin: 3% 0;
    width: 100%;
    resize: none;
    border: 1px solid #4267b2;
  }
  .input-group textarea{
    height: 70px;
    resize: none;
  }
  .modal-save button{
    background-color: #4267b2;
    color:#ffffff;
    margin:-15px 0 -15px 0;
  }
  .modal-save button:hover{
    background-color: cornflowerblue;
    color:#ffffff; }

</style>
