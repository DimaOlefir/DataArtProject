<template>
  <div >
    <!--map for index page-->

    <GmapMap style="width: 100%; height: 100vh" :zoom="3" :center="{lat:currentLocation.lat, lng:currentLocation.lng}">
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
      // OR: libraries: 'places,drawing'
      // OR: libraries: 'places,drawing,visualization'
      // (as you require)
    }
  });
  // export default {
  //   data () {
  //     return {
  //       center: {lat: 46.480271, lng: 30.756080},
  //       markers: [
  //         {
  //           position: {lat: 46.480271, lng: 30.756080}
  //         },
  //         // {
  //         //   position: {lat: 11.0, lng: 11.0}
  //         // }
  //       ]
  //     }
  //   },

  //   components: {

  //   }
  // }

  export default {
    data() {
      return {
        markers: [],
        place: null,
        currentLocation : {lat: 46.480271, lng: 30.756080},
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
      }
    }
  }
  </script>



<style scoped>

</style>
