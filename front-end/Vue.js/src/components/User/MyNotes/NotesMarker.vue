<template>
  <div class="media list-inline">
    <time class="text-right">{{date}}</time>
    <div class="media-left">
      <img src="../../../assets/img/4_b.jpg" class="media-object" style="width:100px">
    </div>
    <div class="media-body">
      <h5 class="media-heading text-left">{{name}}</h5>
      <form>
        <div class="input-group">
          <p class="form-control">{{description}}</p>
        </div>
      </form>
      <div class="btn-group pull-right">
        <!--<button type="button" class="btn btn-primary">Add comment</button>-->
        <button type="button" class="btn btn-primary" v-on:click="$emit('remove')">Delete mark</button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: ['date', 'name', 'description'],
  }
</script>

<style scoped>
  .container-fluid {
    padding: 0;
  }
  .user-photos {
    display: -webkit-flex;
    display: -moz-flex;
    display: -ms-flex;
    display: -o-flex;
    display: flex;
    justify-content: space-around;
    background-color: #f0fff0;
    padding-top: 20px;
  }
  .photos {
    width: 80%;
    border: 1px solid #4267b2;
    border-radius: 5px;
    padding: 0 100px;
    background-color: #fff;
  }
  h2 {
    margin-bottom: 50px;
  }
  .photo_list {
    margin-top:8%;
    margin-bottom: 50px;
    padding-bottom: 20px;
    border-bottom: 1px solid #4267b2;
  }
  .photo-list p {
    margin-bottom: 30px;
  }
  .media-body textarea {
    display: block;
    width:98%;
    margin: 1%;
    margin-bottom: 20px;
    border-radius: 0;
    border-color: #4267b2;
    height: 80px;
    resize: none;
  }
  .btn-primary {
    margin-right: 10px;
  }
  .input-group {
    display: block;
  }

</style>
