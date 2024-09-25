<template>
  <el-container class="layout">
    <asidePanel></asidePanel>
    <el-container>
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
      <el-main>
        <threeNav></threeNav>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import asidePanel from './components/asidePanel.vue'
import threeNav from './components/threeNav.vue'
import rightHeader from './components/rightHeader.vue'
// import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const handleCommand = async (key) => {
  if (key === 'logout') {
    await ElMessageBox.confirm('你确认要退出吗?', '温馨提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    })
    router.push('/login')
  } else {
    router.push(`/user/${key}`)
  }
}
</script>

<style lang="scss" scoped>
.layout {
  height: 100vh;

  background-color: #fff;
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
  .el-container {
    padding: 0 20px 0 10px;
    .el-header {
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
  }
}
</style>
