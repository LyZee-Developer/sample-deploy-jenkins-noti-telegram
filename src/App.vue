

<template>
  <div>
     <h1>Ly Zee Devops</h1>
     <div>fail or success</div>
  </div>
</template>
<script setup>
import { ref } from 'vue';
import HelloWorld from './components/HelloWorld.vue'
var test = ref("")
</script>
<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
