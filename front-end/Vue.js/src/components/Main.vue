<template>
  <div>
    <HeaderNav />
    
    <Map />
  </div>
</template>

<script>
import HeaderNav from './Header/Header';
import Map from './Map/Map';


export default {
  data () {
    return {
      
    }
  },
  components: {
    HeaderNav, Map
  }
}
</script>

<style scoped>
  .navbar {
    margin: 0;
  }
</style>
