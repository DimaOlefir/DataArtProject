<template>
  <div id="app">
     <!--<img src="./assets/logo.png"> -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
  @font-face {
    font-family: FedraSansPro-DemiItalic;
    src: url('assets/fonts/FedraSansPro-DemiItalic.otf'); }

#app {
  font-family: FedraSansPro-DemiItalic, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
}
</style>
