<template>
	<div class="container-fluid">
		<HeaderComponent />
		<div class="user-page">
			<User />
			<div class="my-page">
				<MapComponent />
			</div>
		</div>
	</div>
</template>

<script>
	import HeaderComponent from '../../Header/Header';
	import User from '../User';
	import MapComponent from '../../Map/Map';
	export default {
	 	data() {
	 		return {

	 		}
	 	},
	 	components: {
	 		HeaderComponent, User, MapComponent
	 	}
	}
</script>

<style scoped>
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
		width: 70vw;
	}
</style>