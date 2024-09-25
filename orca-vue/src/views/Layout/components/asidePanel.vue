<template>
  <el-aside width="262px">
    <el-header>
      <div class="img"></div>
      <div class="title">ORCA</div>
    </el-header>
    <el-input placeholder="搜索">
      <template #prepend>
        <el-button :icon="Search"></el-button>
      </template>
    </el-input>
    <el-menu :default-active="$route.path" router>
      <el-menu-item index="/home">
        <img
          src="@/assets/icons/home-active.png"
          style="width: 24px; height: 24px"
          v-if="$route.path === '/home'"
        />
        <img
          src="@/assets/icons/home.png"
          style="width: 24px; height: 24px"
          v-else
        />
        <span> 首页 </span>
      </el-menu-item>
      <el-menu-item index="/documents">
        <img
          src="@/assets/icons/document1-active.png"
          style="width: 24px; height: 24px"
          v-if="$route.path === '/documents'"
        />
        <img
          src="@/assets/icons/document1.png"
          style="width: 24px; height: 24px"
          v-else
        />
        <span> 我的文档 </span>
        <img
          src="@/assets/icons/bottom-arrow2.png"
          alt=""
          style="width: 24px; height: 24px; position: absolute; right: 10px"
        />
      </el-menu-item>
      <el-menu-item index="/find">
        <img
          src="@/assets/icons/find-active.png"
          style="width: 24px; height: 24px"
          v-if="$route.path === '/find'"
        />
        <img
          src="@/assets/icons/find.png"
          style="width: 24px; height: 24px"
          v-else
        />
        <span> 发现 </span>
      </el-menu-item>
      <el-menu-item class="dele">
        <img src="@/assets/icons/dele.png" style="width: 24px; height: 24px" />
        <span>回收站</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script setup>
import { Search } from '@element-plus/icons-vue'
</script>

<style lang="scss" scoped>
.el-aside {
  position: relative;
  padding-left: 10px;
  text-align: center;
  background-color: #f7f7f7;

  .el-header {
    width: 242px;
    margin-bottom: 10px;

    .title {
      width: 79px;
      height: 69px;
      font-weight: 700;
      font-size: 24px;
      text-align: center;
      line-height: 69px;
    }
  }

  .el-input {
    width: 242px;
    height: 38px;
    margin-right: 10px;
  }

  .el-menu {
    margin-top: 10px;
    background-color: #f7f7f7;
    width: 242px;

    border: 0;

    .el-menu-item {
      height: 56px;
      line-height: 56px;

      span {
        margin-left: 15px;
      }
    }

    .el-menu-item.is-active {
      background-color: #e9f2f5;
    }

    .dele {
      position: fixed;
      bottom: 0;
      font-size: 16px;
      width: 242px;
    }
  }
}
</style>
