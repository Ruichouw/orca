<template>
  <el-container class="layout">
    <asidePanel></asidePanel>
    <el-container>
      <el-main>
        <rightHeader></rightHeader>
        <threeNav></threeNav>
        <router-view :key="$route.fullPath"></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import asidePanel from './components/asidePanel.vue'
import threeNav from './components/threeNav.vue'
import rightHeader from './components/rightHeader.vue'
</script>

<style lang="scss" scoped>
.layout {
  height: 100vh;

  background-color: #fff;

  .el-container {
    padding: 0 20px 0 10px;
  }
}
</style>
