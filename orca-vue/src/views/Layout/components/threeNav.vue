<template>
  <div class="nav flex">
    <!-- 新建start -->
    <div
      class="new nav-sub flex"
      style="position: relative"
      v-outside="handleOut"
    >
      <div class="wrap flex" @click="showDrop = !showDrop">
        <img
          src="@/assets/icons/document.png"
          alt=""
          style="width: 52px; height: 52px"
        />
        <div class="info flexpf">
          <p>新建</p>
          <span>新建文档开始编辑</span>
        </div>
        <img
          src="@/assets/icons/bottom-arrow2.png"
          alt=""
          style="width: 24px; height: 24px"
        />
      </div>
      <div class="newDrop" v-if="showDrop">
        <div class="title"><strong>新建</strong></div>
        <div class="new-by">
          <div
            class="docum flexpf"
            style="width: 58px; height: 76px"
            @click="$router.push('/newdocum')"
          >
            <img
              src="@/assets/icons/document1-active.png"
              alt=""
              style="width: 42px; height: 42px"
            />
            <span>文档</span>
          </div>
          <div class="folder flexpf" style="width: 58px; height: 76px">
            <img
              src="@/assets/icons/document1-active.png"
              alt=""
              style="width: 42px; height: 42px"
            />
            <span>文件夹</span>
          </div>
          <div
            class="import flexpf"
            style="width: 58px; height: 76px; margin-top: 5px"
          >
            <img
              src="@/assets/icons/import.png"
              alt=""
              style="width: 42px; height: 42px"
            />
            <span>文档</span>
          </div>
        </div>
      </div>
    </div>
    <!-- 新建end -->

    <!-- 导入start -->
    <div class="import nav-sub flex">
      <img
        src="@/assets/icons/import.png"
        alt=""
        style="width: 52px; height: 52px"
      />
      <div class="info flexpf">
        <p>导入</p>
        <span>导入文档进行操作</span>
      </div>
      <img
        src="@/assets/icons/bottom-arrow2.png"
        alt=""
        style="width: 24px; height: 24px"
      />
    </div>
    <!-- 导入end -->

    <!-- 模板start -->
    <div class="model nav-sub flex">
      <img
        src="@/assets/icons/model.png"
        alt=""
        style="width: 52px; height: 52px"
      />
      <div class="info flexpf">
        <p>模板</p>
        <span>选择模板快速创建</span>
      </div>
      <img
        src="@/assets/icons/bottom-arrow2.png"
        alt=""
        style="width: 24px; height: 24px"
      />
    </div>
    <!-- 模板end -->
  </div>
</template>

<script setup>
import { ref } from 'vue'
const showDrop = ref(false)
const handleOut = () => {
  showDrop.value = false
}
</script>

<style lang="scss" scoped>
.nav {
  .newDrop {
    position: absolute;
    bottom: -280px;
    left: 0;
    box-shadow: 0 0 3px 3px #efefef;
    width: 150px;
    height: 270px;
    z-index: 100;
    background-color: #fff;
    align-items: center;

    .title {
      padding-left: 15px;
      font-weight: 700;
      font-size: 18px;
    }

    .new-by {
      margin: 0 auto;
      width: 100%;
      padding-left: 20px;
      display: flex;
      // 换行
      flex-wrap: wrap;
    }

    span {
      height: 50px;
      line-height: 50px;
    }
  }

  .nav-sub {
    width: 25%;
    height: 85px;
    background-color: #efefef;
    border-radius: 12px;
    border: 1px solid #ddd;
    padding: 0 30px 0;
    line-height: 85px;
    margin-bottom: 48px;

    .info {
      width: 144px;
      height: 53px;

      p {
        display: block;
        line-height: 25px;
        height: 25px;
        margin: 0;
        font-weight: 700;
        font-size: 18px;
      }

      span {
        line-height: 25px;
        font-size: 14px;
      }
    }
  }
}
</style>
