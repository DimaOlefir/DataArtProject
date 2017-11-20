<template>

  <div>
    <div class="add-marker">
      <button v-on:click="activateAddMarker">
        <i class="fa fa-map-marker" aria-hidden="true"></i>Add new marker
      </button>
    </div>
    <!--map for index page-->
    <GmapMap style="width: 100%; height: 100vh; border-radius:5%;" :zoom="3" :center="{lat:currentLocation.lat, lng:currentLocation.lng}"
             v-on:click="openModal">
             <!--v-on:click="openModal" data-toggle="modal"  data-target="#myModal">-->
      <!--<GmapMap :center="{lat:currentLocation.lat, lng:currentLocation.lng}" :zoom="3" :options="{disableDefaultUI:true}">-->
      <GmapMarker v-for="(marker, index) in markers"
                  :key="index"
                  :position="marker.position"
                  :label="marker.label"
      />
      <GmapMarker
        v-if="this.place"
        label="★"
        :position="{
          lat: this.place.geometry.location.lat(),
          lng: this.place.geometry.location.lng(),
        }"
      />
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
                <input id="address" type="text"  class="form-control" name="adress" placeholder="address" required>
              </div>
              <div class="input-group">
                <textarea type="email" class="form-control" id="description" placeholder="marker description" name="email"></textarea>
              </div>
              <div class="btn-group btn-group-justified">
                <a href="#" class="btn btn-primary">Privat</a>
                <a href="#" class="btn btn-primary">All friends</a>
                <a href="#" class="btn btn-primary">Public</a>
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
  </div>

</template>

<!--map for index page-->
<script>
  import * as VueGoogleMaps from 'vue2-google-maps'
  import Vue from 'vue';
  Vue.use(VueGoogleMaps, {
    load: {
      key: 'AIzaSyC55w5-BXC-4XaGpm8gkOtUxZViJf7R9Zg',
      libraries: 'places', // This is required if you use the Autocomplete plugin
    }
  });
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
        modal_display: 'none'
      }
    },
    mounted : function() {
      this.geolocation();
    },
    methods: {
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
      },
      closeModal: function () {
        this.modal_active = false;
        this.modal_display = 'none';
        this.addMarkerState = false;
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
        this.markers.push({
          position: {
            lat: this.newMarkerLat,
            lng: this.newMarkerLng
          },
        });
        this.closeModal();
      }
    }
  }
</script>



<style scoped>
  /*modal window on page my_page */
  .add-marker{
    position: absolute;
    z-index: 9;
    margin:0.7% 0 0 65%;
    opacity: 0.7;
  }
  .add-marker button{
    font-size: 14px;
    width:100%;
    height: 30px;
    border: 1px solid #ffffff;
    border-radius: 5px;
    box-shadow: 3px 2px 25px #b87b90;
  }
  .add-marker button:hover{
    background-color: cornflowerblue;
    opacity: 1;
  }
  .add-marker button i {
    color:red;
    font-size: 18px;
    padding-right: 4%;
  }
  #myModal{
    margin-top: 2%;
    border-radius: 3px; }

  .input-group{
    margin:3% 0;
    width:100%;
    border: 1px solid #4267b2; }
  .modal-save button{
    background-color: #4267b2;
    color:#ffffff;
    font-size: 14px;
    margin:-15px 0 -15px 0; }

  .modal-save button:hover{
    background-color: cornflowerblue;
    color:#ffffff; }

  .modal-header, h4, button, .close {
    background-color: #4267b2;
    color:white;
    text-align: center;
    font-size: 30px; }
  .download-photo input{
    color:#ffffff;
    background-color: #4267b2; ;
  }
  .download-photo input:hover{
    background-color: cornflowerblue;
  }

</style>
