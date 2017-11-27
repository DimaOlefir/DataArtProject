<template>
	<div class="container-fluid">
    <UserHeader></UserHeader>
		<div class="user-page">
      <LeftMenu></LeftMenu>
			<div class="my-page">
        <UserMap></UserMap>
        <PhotosBlock></PhotosBlock>
        <Comments></Comments>
			</div>
		</div>
	</div>
</template>

<script>
  import UserHeader from '../../Header/UserHeader.vue';
  import LeftMenu from './LeftMenu.vue';
  import UserMap from './UserMap.vue';
	import PhotosBlock from './PhotosBlock.vue';
	import Comments from './Comments.vue';
	export default {
	 	data() {
	 		return {
      }
	 	},
    beforeCreate: function () {     //для того чтобы незареганый пассажир не зашел на эту страницу
      if (!localStorage.getItem('token')) {
        this.$router.push('/')
      }
    },
	 	components: {
      UserHeader, LeftMenu, UserMap, PhotosBlock, Comments
	 	}
	}
</script>

<style scoped>
  @font-face {
    font-family: FedraSansPro-DemiItalic;
    src: url('../../../assets/fonts/FedraSansPro-DemiItalic.otf'); }

  .container-fluid {
		padding: 0;
	}
	.user-page {
		display: -webkit-flex;
		display: -moz-flex;
		display: -ms-flex;
		display: -o-flex;
		display: flex;
		justify-content: space-around;
		background-color: #f0fff0;
		padding-top: 20px;
	}
	.my-page {
		width: 80%;
	}
</style>
