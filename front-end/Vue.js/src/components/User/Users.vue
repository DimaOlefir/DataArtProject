<template>
	<ul>
		<li v-for="user in users">
			<span>{{user.name}}: {{user.email}}</span>
		</li>
	</ul>
</template>

<script>
	export default {
		data() {
			return {
				users: {}
			}
		},
		created: function() {
			this.$http.get('https://jsonplaceholder.typicode.com/users')
				.then(function(response) {
					console.log(response.data);
					this.users = response.data
				});
		}
	}
</script>

<style></style>
