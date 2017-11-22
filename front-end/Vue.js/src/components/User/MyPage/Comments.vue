<template>
  <div class="container-fluid">
      <div class="messages">

        <section class="panel">
          <input v-model="newMessage" @keyup.enter="addMessage" placeholder="Add comments" class="text-input">
          <button class="btn btn-primary btn-clear" @click="clearList">Clear comments</button>
        </section>

        <section class="list">
          <ul class="list-item">

            <li v-for="task in tasks">
              <div class="jumbotron">
              <span class="glyphicon glyphicon-user"></span>
              <!--<h5>John Doe</h5>-->
            </div>
              <label>{{ task.text }}</label>
              <button class="delete" @click="removeTask(task)">X</button>
            </li>
          </ul>
        </section>
      </div>
  </div>

</template>


<script>
  let nextMessageId = 1;
  export default {
    data() {
      return {
        newMessage: "",
        tasks: [
          {
            id: nextMessageId++,
            text: "This is an example task. Delete or add your own"
          }
        ]
      }
    },
    methods: {
      addMessage: function () {
        let task = this.newMessage.trim();
        if (task) {
          this.tasks.unshift({id: nextMessageId++,text: task});
          this.newMessage = "";
        }
      },
      removeTask: function (task) {
        let index = this.tasks.indexOf(task);
        this.tasks.splice(index, 1);
      },
      clearList: function () {
        this.tasks = [];
      }
    }
  }
</script>

<style scoped>
  .messages {
    font-family: FedraSansPro-DemiItalic, Helvetica, sans-serif;
    width: 100%;
    border: 1px solid #efefef;
    padding:0 5% 0 5% ;
    background-color: #EBE9E9;
  }
  .jumbotron {
    margin: -1px 4% 0 0;
    border-radius: 0;
    padding: 0 ;
    background-color: #EBE9E9;

  }
  .jumbotron span {
    margin-right: 4%;
    padding: 18%;
    color: red;
    font-size: 30px;
    border-radius: 50%;
    background-color:#ffffff;
  }
  .btn-clear {
    width: 120px;
    height: 34px;
    border: 1px solid #3465A4 ;
    margin-bottom: 1%;
  }
  .btn-clear:hover {
    background-color: cornflowerblue;
  }
  .panel, li {
    display: flex;
    align-items: center;
    justify-content: space-between;
    list-style-type: none;
    margin-top: 10px;
    border-bottom: 1px solid #efefef;
    background-color: #EBE9E9;
  }
  .text-input {
    border: 1px solid #dedede;
    padding-left: 10px;
    width: 80%;
    height: 35px;
    color: #555;
    margin: 2% 4% 3% 9%;
  }
  /* Task  area */
  .list li {
    background-color: #ffffff;
    margin-top: 5%;
  }
  .list li button {
    background-color: #3465A4;
    border: 4px solid #3465A4;
    color: #ffffff;
    width: 5%;
    height: 100%;
    visibility: hidden;
    font-size: 16px;
    font-weight: bold;
  }
  .list li:hover > button {
    visibility: visible;
  }
  .list label {
    margin:6px;
    padding-left: 0;
    display: inline-block;
    width: 100%;
    font-size: 14px;
    font-weight: 200;
    line-height: 20px;
    color: #2c3e50;
    text-align: left;
  }
  .list-item{
    padding: 0;
  }
</style>
