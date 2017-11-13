<template>
  <div>
    <HeaderComponent />
    
    <MapComponent />

    <FooterComponent />
  </div>
</template>

<script>
import HeaderComponent from './Header/Header';
import MapComponent from './Map/Map';
import FooterComponent from './Footer/Footer';


export default {
  data () {
    return {
      
    }
  },
  components: {
    HeaderComponent, MapComponent, FooterComponent
  }
}
</script>

<style scoped>
  .navbar {
    margin: 0;
  }
</style>
