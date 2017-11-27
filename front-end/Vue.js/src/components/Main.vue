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
  @font-face {
    font-family: FedraSansPro-DemiItalic;
    src: url('../assets/fonts/FedraSansPro-DemiItalic.otf'); }

</style>
