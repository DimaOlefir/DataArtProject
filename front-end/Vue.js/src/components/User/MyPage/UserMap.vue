<template>

  <div>
    <!--PRIVACY LEVEL-->
    <div class="dropdown privacy-level">
      <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Privacy level
        <span class="caret"></span></button>
      <form class="dropdown-menu">

        <div class="radio">
          <label><input type="radio" name="optradio" value="0" v-model="accessSelected">Private</label>
        </div>
        <div class="radio">
          <label><input type="radio" name="optradio" value="1" v-model="accessSelected">Only friends</label>
        </div>
        <div class="radio">
          <label><input type="radio" name="optradio" value="2" v-model="accessSelected">Public</label>
        </div>
      </form>
    </div>

    <label class="map-search">
      <!--Add place on the map <br>-->
      <GmapAutocomplete class="place" @place_changed="setPlace"></GmapAutocomplete>
      <!--<button @click="usePlace">Add marker</button>-->
      <button v-on:click="activateAddMarker">
        <i class="fa fa-map-marker" aria-hidden="true"></i>Add new marker
      </button>
    </label>
    <br/>


    <!--map for index page-->
    <GmapMap style="width: 100%; height: 100vh; margin-top: -1.5%" :zoom="3" :center="{lat:currentLocation.lat, lng:currentLocation.lng}" v-on:click="openModal">
      <!--v-on:click="openModal" data-toggle="modal"  data-target="#myModal">-->
      <!--<GmapMap :center="{lat:currentLocation.lat, lng:currentLocation.lng}" :zoom="3" :options="{disableDefaultUI:true}">-->
      <GmapMarker v-for="(marker, index) in markers"
                  :key="index"
                  :position="marker.position"
                  :label="marker.label"
                  v-on:click="getMarkerData(marker.id)"
      ></GmapMarker>
      <GmapMarker
        v-if="this.place"
        label="★"
        :position="{
          lat: this.place.geometry.location.lat(),
          lng: this.place.geometry.location.lng(),
        }"
      ></GmapMarker>
    </GmapMap>


    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog"
         v-bind:class="{'in': modal_active}" v-bind:style="{display: modal_display}">
      <!--<div class="modal fade" id="myModal" role="dialog">-->
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" v-on:click="closeModal">&times;</button>
            <form action="#" method="post" enctype="multipart/form-data">
              <h4>Add description</h4>
              <div class="input-group">
                <input id="address" type="text" v-model="name" class="form-control" name="name" placeholder="Place name" required>
              </div>
              <div class="input-group">
                <textarea type="text" v-model="description" class="form-control" id="description" placeholder="marker description" name="description"></textarea>
              </div>
              <div class="btn-group btn-group-justified">
                <a href="#" v-on:click="access=0" class="btn btn-primary">Private</a>
                <a href="#" v-on:click="access=1" class="btn btn-primary">All friends</a>
                <a href="#" v-on:click="access=2" class="btn btn-primary">Public</a>
              </div>
              <!--форма для загрузки картинок-->
              <div class="input-group download-photo">
                <input type="file" name="filename" value="Download"  placeholder="place name" class="btn btn-default" style="width: 100%"><br>
                <input type="submit" value="Download photo" class="btn btn-default down-photo" style="width:80%; margin-top:-20%;"><br>
              </div>

              <div class="input-group">
                <input id="lat" v-model="newMarkerLat" type="text" class="form-control" placeholder="lat" name="lat"  required>
              </div>
              <div class="input-group">
                <input id="lng" v-model="newMarkerLng" type="text" class="form-control" placeholder="lng" name="lng"  required>
              </div>
            </form>
            <div class="modal-save modal-footer">
              <button v-on:click="saveMarker" type="button" class="btn btn-default btn-modalSave">Save</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- MAIN (Center website) -->
    <div class="photo-block" v-bind:class="{ hidden : !clickedMarkerId}">
      <h3 class="text-left">Photo attached to this place</h3>
      <p class="text-left">You can leave a comment on this photo</p>
      <!-- Portfolio Gallery Grid -->
      <div class="row">
        <PhotosBlock  v-for="(photo, index) in photos"
                      v-bind:key="photo.id"
                      v-bind:photoLink="photo.photoLink">
        </PhotosBlock>
      </div>
    </div>

    <div class="container-fluid">
      <div class="messages" v-bind:class="{ hidden : !clickedMarkerId}">

        <div class="panel popup-messages-footer">
          <textarea id="status_message" v-model="newMessage" @keyup.enter="addMessage" placeholder="Add comments" class="text-input" rows="10" cols="40" name="message"></textarea>
          <button class="btn-primary" v-on:click="addMessage">Add comments</button>
          <!--<button class="btn btn-primary btn-clear" @click="clearList">Clear comments</button>-->
        </div>

        <section class="list">
          <ul class="list-item">
            <li is="Comments"
                v-for="(comment, index) in comments"
                v-bind:key="comment.id"
                v-bind:date="new Date(comment.dateTime).toLocaleString()"
                v-bind:time="new Date(comment.dateTime).toLocaleString()"
                v-bind:author="comment.creator.firstName + ' ' + comment.creator.lastName"
                v-bind:text="comment.text"
                v-on:remove="deleteComment(comment.id, index)">
            </li>

          </ul>
        </section>
      </div>
    </div>
  </div>

</template>

<!--map for index page-->
<script>
  import * as VueGoogleMaps from 'vue2-google-maps'
  import Comments from './Comments.vue';
  import PhotosBlock from './PhotosBlock.vue';
  import Vue from 'vue';
  Vue.use(VueGoogleMaps, {
    load: {
      key: 'AIzaSyC55w5-BXC-4XaGpm8gkOtUxZViJf7R9Zg',
      libraries: 'places', // This is required if you use the Autocomplete plugin
    }
  });
  let nextMessageId = 1;
  export default {
    data() {
      return {
        markers: [],
        place: null,
        currentLocation : {lat: 46.480271, lng: 30.756080},
        newMarkerLat: null,
        newMarkerLng: null,
        addMarkerState: false,
        modal_active: false,
        modal_display: 'none',
        name:"",
        description:"",
        access:"2",
        loading:false,
        login_msg:false,
        accessSelected: 2,
        clickedMarkerId: null,
        newMessage: "",
        comments: [],
        photos:[]
      }
    },
    watch: {
      accessSelected: function(access)
      {
        console.log(access);
        access = +access;
        if (access === 0) {this.getmarkers('mymarkers')}
        else if (access === 1) {this.getmarkers('friendsmarkers')}
        else if (access === 2) {this.getmarkers('markers')}
      }
    },
    mounted : function() {
      this.geolocation();
      this.getmarkers('markers');
    },
    methods: {
      getmarkers(access) {
        this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/' + access, // запрос на чтение маркеров
          {headers: {'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')}})
          .then(function(response){
            let self = this;
            this.login_msg = false; //если логин или пароль неверно выпадает ошибка
            this.loading = true;
            console.log(response);
            self.markers = [];
            response.body.forEach(function(value, key) { // цикл для отображения маркеров на карте, все маркеры добавляются в массив this.markers
              value.position = {
                lat: value.lat,
                lng: value.lng
              };
              self.markers.push(value);
            });
          }, function (error) {
            this.loading = true; //если логин или пароль неверно то лоадер после выпадения ошибки исчезает
            this.markers = [];
            console.log(error);
          });
      },
      setPlace(place) {
        this.place = place
      },
      usePlace(place) {
        if (this.place) {
          this.markers.push({
            position: {
              lat: this.place.geometry.location.lat(),
              lng: this.place.geometry.location.lng()
            }
          });
          this.place = null;
        }
      },
      geolocation: function() {
        navigator.geolocation.getCurrentPosition((position) => {
          this.currentLocation = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
          this.markers.push({
            position: {
              lat: this.currentLocation.lat,
              lng: this.currentLocation.lng
            },
//            label: 'Hey! You are here!'
          });
        });
      },
//      Add marker
      activateAddMarker: function () {
        this.addMarkerState = true;
        if (this.place) {
          if(this.addMarkerState) {
            this.modal_active = true;
            this.modal_display = 'block';
            this.newMarkerLat = this.place.geometry.location.lat();
            this.newMarkerLng = this.place.geometry.location.lng();
          }
        }
      },
      closeModal: function () {
        this.modal_active = false;
        this.modal_display = 'none';
        this.addMarkerState = false;
        this.place = null;
      },
      openModal: function (event) {
        if(this.addMarkerState) {
          this.modal_active = true;
          this.modal_display = 'block';
          this.newMarkerLat = event.latLng.lat();
          this.newMarkerLng = event.latLng.lng();
        }
      },
      saveMarker: function (){
        this.loading = false; //после нажатия на кнопку логин выпадает лоадер
        let data = {
          name : this.name,
          description : this.description,
          access : +this.access,
          lat : +this.newMarkerLat,
          lng : +this.newMarkerLng,
          dateTime: new Date()
        };
        this.$http.post('https://rocky-retreat-50096.herokuapp.com/api/marker',
          JSON.stringify(data), {headers: {'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')}})
          .then(function(response){
            this.login_msg = false; //если логин или пароль неверно выпадает ошибка
            this.loading = true;
            console.log(response);
            let marker = response.body;
            marker.position = {
              lat: marker.lat,
              lng: marker.lng
            };
            this.markers.push(marker);
            this.closeModal();
          }, function (error) {
            this.loading = true; //если логин или пароль неверно то лоадер после выпадения ошибки исчезает
            console.log(error);
          });
        return false;
      },
      getMarkerData: function (id) {
        this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/marker/' + id + '/comments', // запрос на чтение маркеров
          {headers: {'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')}})
          .then(function(response){
            console.log(response);
            response.body.sort(function (element) {
              return element.dateTime;
            });
            this.comments = response.body;
            this.clickedMarkerId = id;
          }, function (error) {
            console.log(error);
          });

//        для отправки запроса по id маркера для загрузки фото
        this.$http.get('https://rocky-retreat-50096.herokuapp.com/api/marker/' + id + '/photos', // запрос на чтение маркеров
          {headers: {'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')}})
          .then(function(response){
            console.log(response);
            this.photos = response.body;
          }, function (error) {
            console.log(error);
            this.photos = [
              {id: 1, photoLink: "http://www.telegraph.co.uk/content/dam/Travel/Destinations/Europe/France/Paris/paris-attractions-xlarge.jpg"},
              {id: 2, photoLink: "../../../../assets/img/1_b.jpg"},
              {id: 3, photoLink: "../../../../assets/img/1_b.jpg"},
              {id: 4, photoLink: "../../../../assets/img/1_b.jpg"},
              {id: 5, photoLink: "../../../../assets/img/1_b.jpg"},
            ]
          });
      },
//      clearList: function () {    delete button all comments
//        this.comments = [];
//      },
      addMessage: function () {
        let comment = this.newMessage.trim();
        if (comment) {
          let data = {
            markerId: this.clickedMarkerId,
            dateTime: new Date(),
            text: comment
          };
          this.$http.post('https://rocky-retreat-50096.herokuapp.com/api/marker/comment',
            JSON.stringify(data), {headers: {'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + localStorage.getItem('token')}})
            .then(function(response){
              console.log(response);
            }, function (error) {
              this.loading = true; //если логин или пароль неверно то лоадер после выпадения ошибки исчезает
              console.log(error);
            });
          this.newMessage = "";
        }
        this.getMarkerData(this.clickedMarkerId);
      },
      deleteComment: function (id, index) {
        console.log(id, localStorage.getItem('token'));
        this.$http.delete('https://rocky-retreat-50096.herokuapp.com/api/marker/comment', // запрос на чтение маркеров
          {body: JSON.stringify({commentId: +id}), headers: {'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')}})
          .then(function(response){
            console.log(response);
            this.comments.splice(index, 1);
          }, function (error) {
            console.log(error);
          });
      },
    },
    components: {
      Comments, PhotosBlock
    }
  }
</script>



<style scoped>

  .privacy-level {
    position: absolute;
    margin: 1% 60%;
    z-index: 9;
  }
  .dropdown form{
    margin-left:2%;
    padding: 4%}

  .privacy-level button{
    padding: 2.5% 10% 2.5% 12%;
    font-size: 14px;
    opacity: 0.7;
    border: 1px solid #ffffff;
  }
  /*modal window on page my_page */
  .map-search{
    position: absolute;
    margin:1% 0 0 -30%;
    opacity: 0.8;
    z-index: 9;
  }
  .map-search button{
    font-size: 14px;
    width:130px;
    height: 28px;
    border: 1px solid #ffffff;
    border-radius: 5px;
    opacity: 0.9;
  }
  .privacy-level button:hover,
  .map-search button:hover{
    background-color: cornflowerblue;
    opacity: 1;
  }
  .map-search .place{
    width: 360px;
  }
  .fa-map-marker{
    color:red;
    font-size: 18px;
    padding-right: 4%;
  }
  #myModal{
    margin-top: 2%;
    border-radius: 3px;
  }
  .input-group{
    margin:3% 0;
    width:100%;
    border: 1px solid #4267b2;
  }
  .modal-save button{
    background-color: #4267b2;
    color:#ffffff;
    font-size: 14px;
    margin:-15px 0 -15px 0;
  }
  .modal-save button:hover{
    background-color: cornflowerblue;
    color:#ffffff;
  }
  .modal-header, h4, button, .close {
    background-color: #4267b2;
    color:white;
    text-align: center;
    font-size: 30px;
  }
  .download-photo input{
    color:#ffffff;
    background-color: #4267b2; ;
  }
  .download-photo input:hover{
    background-color: cornflowerblue;
  }
  .modal-backdrop fade in{
    display: none;
  }

  .messages {
    font-family: FedraSansPro-DemiItalic, Helvetica, sans-serif;
    width: 100%;
    border: 1px solid #efefef;
    padding:0 5% 0 5% ;
    background-color: #EBE9E9;
  }
  .panel {
    display: flex;
    align-items: center;
    justify-content: space-between;
    list-style-type: none;
    padding-top: 10px;
    background-color: #EBE9E9;
  }
  .panel textarea{
    border: 1px solid #3465A4;
    border-bottom: 1px solid #3465A4;
    height: 40px ;
    padding: 5px ;
    width: 85% ;
  }
  .delete {
    background-color: #3465A4;
    border: 2px solid #3465A4;
    border-radius: 5px;
    color: #ffffff;
    width: 6%;
    height: 100%;
    margin: 0 3% 0 0;
    font-size: 14px;
  }
  .delete:hover{
    background-color: cornflowerblue;
    border: 2px solid cornflowerblue;
  }
  .list label {
    margin:6px;
    padding-left: 5px;
    display: inline-block;
    width: 90%;
    font-size: 14px;
    font-weight: 200;
    line-height: 20px;
    color: #2c3e50;
    text-align: left;
  }
  .list-item{
    padding: 0;
    list-style: none;
  }
  .list-item li img{
    width:50px;
    height: 50px;
    border-radius: 50%;
  }
  .direct-chat-name{
    margin-left: 3%;
    color: cornflowerblue;
  }
  .direct-chat-timestamp{
    margin: 0 30px 0 0;
  }
  .chat-box-single-line {
    border-bottom: 1px solid #3465A4;
    height: 12px;
    margin: 7px 0 20px;
    position: relative;
    text-align: center;
  }
  abbr.timestamp {
    background: #3465A4 none repeat scroll 0 0;
    color: #fff;
    padding: 0 11px;
  }
  .direct-chat-text::after, .direct-chat-text::before {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: transparent #d2d6de transparent transparent;
    border-image: none;
    border-style: solid;
    border-width: medium;
    content: " ";
    height: 0;
    pointer-events: none;
    position: absolute;
    right: 100%;
    top: 15px;
    width: 0;
  }
  .direct-chat-text::after {
    border-width: 5px;
    margin-top: -5px;
  }
  .direct-chat-text {
    background: #d2d6de none repeat scroll 0 0;
    border: 1px solid #d2d6de;
    border-radius: 5px;
    color: #444;
    margin: 5px 0 0 50px;
    padding: 5px 10px;
    position: relative;
  }


</style>
