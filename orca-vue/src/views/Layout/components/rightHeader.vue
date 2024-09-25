<template>
  <el-header class="flex">
    <div class="left">首页</div>
    <div class="right">
      <el-button>
        <img
          src="@/assets/icons/search.png"
          alt=""
          style="width: 36px; height: 36px"
        />
      </el-button>
      <el-button>
        <img
          src="@/assets/icons/msg.png"
          alt=""
          style="width: 36px; height: 36px"
        />
      </el-button>
      <el-button>
        <img
          src="@/assets/icons/allcheck.png"
          alt=""
          style="width: 36px; height: 36px"
        />
      </el-button>
      <el-dropdown @command="handleCommand">
        <el-avatar
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          class="headshot"
        />
        <template #dropdown>
          <el-dropdown-menu
            class="flexpf"
            style="width: 307px; align-items: center"
          >
            <div class="top">
              <el-avatar
                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              />
            </div>
            <el-dropdown-item
              style="width: 247px; height: 42px; justify-content: center"
              command="change"
            >
              切换账号
            </el-dropdown-item>
            <el-dropdown-item
              style="width: 247px; height: 42px; justify-content: center"
              command="logout"
            >
              退出登录
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-header>
</template>

<script setup></script>

<style lang="scss" scoped>
.el-header {
  padding: 0 20px 0 10px;

  .left {
    width: 48px;
    height: 34px;
    font-size: 24px;
    font-weight: 700;
  }

  .el-button {
    border: 0;
    width: 36px;
    height: 36px;
  }

  .el-dropdown {
    .headshot {
      &:active,
      &:focus {
        outline: none;
      }
    }
  }
}
</style>
