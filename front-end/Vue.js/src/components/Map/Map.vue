<!-- <template>
  <div>
    <label>
      AutoComplete
      <GmapAutocomplete @place_changed="setPlace">
      </GmapAutocomplete>
      <button @click="usePlace">Add</button>
    </label>

    <gmap-map
      :center="center"
      @center_changed="updateCenter"
      :zoom="14"
      style="width: 100vw; height: 90vh"
    >
      <gmap-marker
        :key="index"
        v-for="(m, index) in markers"
        :position="m.position"
        :clickable="true"
        :draggable="true"
        @click="center=m.position"
      ></gmap-marker>
    </gmap-map>
  </div>
</template> -->


<template>
  <div>
    <label>
      Add Place
      <GmapAutocomplete @place_changed="setPlace">
      </GmapAutocomplete>
      <button @click="usePlace">Add</button>
    </label>
    <br/>

    <GmapMap style="width: 100%; height: 85vh" :zoom="14" :center="{lat: 46.480271, lng: 30.756080}">
      <GmapMarker v-for="(marker, index) in markers"
        :key="index"
        :position="marker.position"
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
  })
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
        place: null
      }
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
          })
          this.place = null;
        }
      }
    }
  }
  </script>

</script>

<style scoped>

</style>