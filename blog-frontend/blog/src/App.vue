<template>
  <div>
    <router-view></router-view>
  </div>
  <!-- 全局loading -->
  <loading />
  <!-- <Music /> -->
</template>

<script setup lang="ts">
import {useDark, useToggle} from "@vueuse/core";
import useWebsiteStore from "@/store/modules/website.ts";
import loading from "@/components/Loading/index.vue";
import Music from "@/views/Music/index.vue";
const useWebsite = useWebsiteStore()

onMounted(() => {
  useWebsite.getInfo()
})

//  深色切换
useDark({
  selector: 'html',
  attribute: 'class',
  valueLight: 'light',
  valueDark: 'dark'
})

useDark({
  onChanged(dark) {
    useToggle(dark)
  }
})
</script>

<style scoped lang="scss">

</style>
