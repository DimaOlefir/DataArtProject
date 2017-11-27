<template>
  <div class="container-fluid">
      <div class="messages">

        <div class="panel popup-messages-footer">
          <textarea id="status_message" v-model="newMessage" @keyup.enter="addMessage" placeholder="Add comments" class="text-input" rows="10" cols="40" name="message"></textarea>
          <button class="btn btn-primary btn-clear" @click="clearList">Clear comments</button>
        </div>

        <section class="list">
          <ul class="list-item">
            <li v-for="task in tasks">
              <div class="chat-box-single-line">
                <abbr class="timestamp">October 8th, 2015</abbr>
              </div>

              <div class="direct-chat-info clearfix pull-right">
                <span class="direct-chat-timestamp pull-right">3.36 PM</span>
              </div>

              <div class="direct-chat-msg doted-border">
                <img alt="message user image" src="../../../assets/img/1_f.png" class="pull-left">
                <div class="direct-chat-info clearfix">
                  <span class="direct-chat-name pull-left">Osahan</span>
                  <label class="direct-chat-text">{{ task.text }}</label>
                  <button class="delete pull-right" @click="removeTask(task)">Delete</button>
                </div>

              </div>
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
  .panel {
    display: flex;
    align-items: center;
    justify-content: space-between;
    list-style-type: none;
    padding-top: 10px;
    background-color: #EBE9E9;
  }
  .panel textarea{
    border: 1px solid #3465A4;
    border-bottom: 1px solid #3465A4;
    height: 40px ;
    padding: 5px ;
     width: 85% ;
  }
  .delete {
    background-color: #3465A4;
    border: 2px solid #3465A4;
    border-radius: 5px;
    color: #ffffff;
    width: 6%;
    height: 100%;
    margin: 0 3% 0 0;
    font-size: 14px;
  }
  .delete:hover{
    background-color: cornflowerblue;
    border: 2px solid cornflowerblue;
  }
  .list label {
    margin:6px;
    padding-left: 5px;
    display: inline-block;
    width: 90%;
    font-size: 14px;
    font-weight: 200;
    line-height: 20px;
    color: #2c3e50;
    text-align: left;
  }
  .list-item{
    padding: 0;
    list-style: none;
  }
  .list-item li img{
    width:50px;
    height: 50px;
    border-radius: 50%;
  }
  .direct-chat-name{
    margin-left: 3%;
    color: cornflowerblue;
  }
  .direct-chat-timestamp{
    margin: 0 30px 0 0;
  }
  .chat-box-single-line {
    border-bottom: 1px solid #3465A4;
    height: 12px;
    margin: 7px 0 20px;
    position: relative;
    text-align: center;
  }
  abbr.timestamp {
    background: #3465A4 none repeat scroll 0 0;
    color: #fff;
    padding: 0 11px;
  }
  .direct-chat-text::after, .direct-chat-text::before {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: transparent #d2d6de transparent transparent;
    border-image: none;
    border-style: solid;
    border-width: medium;
    content: " ";
    height: 0;
    pointer-events: none;
    position: absolute;
    right: 100%;
    top: 15px;
    width: 0;
  }
  .direct-chat-text::after {
    border-width: 5px;
    margin-top: -5px;
  }
  .direct-chat-text {
    background: #d2d6de none repeat scroll 0 0;
    border: 1px solid #d2d6de;
    border-radius: 5px;
    color: #444;
    margin: 5px 0 0 50px;
    padding: 5px 10px;
    position: relative;
  }

</style>
