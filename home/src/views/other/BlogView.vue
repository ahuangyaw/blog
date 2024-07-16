<template>
  <div id="blogView">
    <el-page-header @back="onBack">
      <template #content>
        <div class="flex items-center">

          <span class="text-large font-600 mr-3"> Title </span>
          <span
            class="text-sm mr-2"
            style="color: var(--el-text-color-regular)"
          >
            Sub title
          </span>
          <el-tag>Default</el-tag>
          <el-avatar
            class="mr-3"
            :size="32"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          />
        </div>
      </template>
      <template #extra>
        <div class="flex items-center">
          <el-button>Print</el-button>
          <el-button type="primary" class="ml-2">Edit</el-button>
          <el-avatar
            class="mr-3"
            :size="60"
            style="height: auto"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          />
        </div>
      </template>

      <el-descriptions :column="3" size="small" class="mt-4">
        <el-descriptions-item label="Telephone">
          18100000000
        </el-descriptions-item>
        <el-descriptions-item label="Place">Suzhou</el-descriptions-item>
        <el-descriptions-item label="Remarks">
          <el-tag size="small">School</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="Address">
          No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province
        </el-descriptions-item>
      </el-descriptions>
      <p class="mt-4 text-sm">
        1
      </p>
    </el-page-header>
  </div>
</template>
<script setup lang="ts">
import { useRouter } from "vue-router";
import { mainStore } from "@/store";
import { ElNotification as notify } from "element-plus";

const store = mainStore();
const router = useRouter();
const onBack = () => {
  // 清除浏览器缓存
  window.location.reload();
  router.back();
};

</script>
<style scoped>
#blogView {
}
</style>
