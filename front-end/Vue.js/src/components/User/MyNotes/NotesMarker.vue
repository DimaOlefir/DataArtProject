<template>
  <div class="media list-inline">
    <time>{{date}}</time>
    <div class="media-left">
      <img src="../../../assets/img/4_b.jpg" class="media-object" style="width:100px">
    </div>
    <div class="media-body">
      <h5 class="media-heading text-left">{{name}}</h5>
      <form>
        <div class="input-group">
          <textarea class="form-control" rows="10" cols="40" name="messages">{{description}}</textarea>
        </div>
      </form>
      <div class="btn-group pull-right">
        <!--<button type="button" class="btn btn-primary">Add comment</button>-->
        <button type="button" class="btn btn-primary" v-on:click="$emit('remove')">Delete mark</button>
      </div>
    </div>
    <hr>
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
  hr{
    border-top: 1px solid #4267b2;
  }
  time{
   color:silver;
 }
  .media-body h5{
    font-size: 18px;
    margin-left: 1%;
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
    height: 60px;
    resize: none;
  }
  .btn-group button:hover {
    background-color: cornflowerblue;
  }
  .btn-primary {
    margin-right: 10px;
  }
  .input-group {
    display: block;
  }

</style>
