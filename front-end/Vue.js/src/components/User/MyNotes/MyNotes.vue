<template>
  <div class="container-fluid">
    <UserHeader></UserHeader>
    <div class="user-marks">
      <User></User>
      <div class="marks">
        <form class="form-inline search">
          <div class="form-group">
            <input type="text" v-model="searchQuery" class="form-control" id="searchFriend" placeholder="Search marks name" size="70">
          </div>
        </form>

        <h3 class="text-center">All your marks</h3>
        <div class="notes navbar-right">
          <!-- list with photo -->
          <div class="photo_list">
            <NotesMarker v-for="(marker, index) in filteredMarkers"
                         v-bind:key="marker.id"
                         v-bind:date="new Date(marker.dateTime).toLocaleString()"
                         v-bind:name="marker.name"
                         v-bind:description="marker.description"
                         v-on:remove="deleteMarker(marker.id, index)"
            ></NotesMarker>

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
  import NotesMarker from './NotesMarker.vue';
  export default {
    data() {
      return {
        markers: [],
        searchQuery: '',
      }
    },
    beforeCreate: function () {     //для того чтобы незареганый пассажир не зашел на эту страницу
      if (!localStorage.getItem('token')) {
        this.$router.push('/')
      }
    },
    computed: {    //вычисляемая переменная поиск каждой введенной буквы
      filteredMarkers: function () {
        let self = this;
        return this.markers.filter(function (marker) {
          let filterName = marker.name.toLowerCase();
          let searchQuery = self.searchQuery.toLowerCase();
          return filterName.indexOf(searchQuery) !== -1;
        });
      },
    },
    mounted : function() {
      this.getmarkers('mymarkers');
    },
    methods: {
      getmarkers(access) {
        this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/' + access, // запрос на чтение маркеров
          {headers: {'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')}})
          .then(function(response){
            console.log(response);
            let self = this;
            this.login_msg = false; //если логин или пароль неверно выпадает ошибка
            this.loading = true;
            self.markers = [];
            response.body.forEach(function(value, key) { // цикл для отображения маркеров на карте, все маркеры добавляются в массив this.markers
              value.position = {
                lat: value.lat,
                lng: value.lng
              };
              self.markers.push(value);
            });
            self.markers.sort(function (element) {
              return element.dateTime;
            });
          }, function (error) {
            this.loading = true; //если логин или пароль неверно то лоадер после выпадения ошибки исчезает
            this.markers = [];
            console.log(error);
          });
      },
      deleteMarker(id, index){
        this.$http.delete('https://rocky-retreat-50096.herokuapp.com/api/marker/' + id, // запрос на чтение маркеров
          {headers: {'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')}})
          .then(function(response){
            console.log(response);
            this.markers.splice(index, 1);
          }, function (error) {
            console.log(error);
          });
      }
    },
    components: {
      User, UserHeader, NotesMarker
    }
  }
</script>

<style scoped>
  .container-fluid {
    padding: 0;
  }
  .user-marks {
    display: -webkit-flex;
    display: -moz-flex;
    display: -ms-flex;
    display: -o-flex;
    display: flex;
    justify-content: space-around;
    background-color: #f0fff0;
    padding-top: 20px;
  }
  .marks {
    width: 80%;
    border: 1px solid #4267b2;
    border-radius: 5px;
    padding: 0 100px;
    background-color: #fff;
  }
  .search {
    margin: 30px 0 50px 0;
  }
  h2 {
    margin-bottom: 50px;
  }
  .photo_list {
    margin-top:8%;
    margin-bottom: 50px;
    padding-bottom: 20px;
  }
  .photo_list hr{
    border-top: 1px solid #4267b2;
  }
  .photo_list p {
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
