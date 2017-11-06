<template>
  <gmap-map
    :center="center"
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
  export default {
    data () {
      return {
        center: {lat: 46.480271, lng: 30.756080},
        markers: [
          {
            position: {lat: 46.480271, lng: 30.756080}
          }, 
          // {
          //   position: {lat: 11.0, lng: 11.0}
          // }
        ]
      }
    },
    components: {
      
    }
  }
  </script>

</script>

<style scoped>

</style>