<template>
      <div class="column">
        <div class="content">
          <img v-bind:src="photoLink" alt="Mountains">
        </div>
      </div>
</template>

<script>
  export default {
    props: ['photoLink']
  }
</script>

<style scoped>
  /* Center website */
  .photo-block {
    font-family: FedraSansPro-DemiItalic, Helvetica, sans-serif;
    max-width: 100%;
    margin:5% 0 0 0;
    padding: 1%;
    background-color: #EBE9E9;
  }
  .photo-block h3{
    padding-top: 0;
  }
  .row {
    margin: 8px -16px;
  }
  /* Add padding BETWEEN each column */
  .row,
  .row > .column {
    padding: 8px;
  }
  /* Create four equal columns that floats next to each other */
  .column {
    float: left;
    width: 20%;
  }
  .content{
    box-shadow: 3px 2px 25px #b87b90;
  }
  .content img{
    width: 100%;
    height: 150px;

  }
  .content img:hover {
    transform: scale(1.9);
     -webkit-transition: transform 0.3s ease-in-out;
  }

  /* Clear floats after rows */
  .row:after {
    content: "";
    display: table;
    clear: both;
  }
  /* Content */
  .content {
    background-color: white;
    padding: 10px;
  }
  /* Responsive layout - makes a two column-layout instead of four columns */
  @media (max-width: 900px) {
    .column {
      width: 50%;
    }
  }
  /* Responsive layout - makes the two columns stack on top of each other instead of next to each other */
  @media (max-width: 600px) {
    .column {
      width: 100%;
    }
  }
</style>
