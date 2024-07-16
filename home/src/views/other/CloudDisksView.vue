<template>
  <div id="cloudDisksView">
    <span @click="onclick">back</span>

  </div>
</template>
<script setup lang="ts">
import { useRouter } from "vue-router";
import { mainStore } from "@/store";

const store = mainStore();
const router = useRouter();
const onclick = () => {
  // 清除浏览器缓存
  window.location.reload();
  router.back();
}
</script>
<style scoped>
#cloudDisksView {
}
</style>
