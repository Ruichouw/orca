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
    <div class="import nav-sub flex" @click="visible = true">
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

    <el-dialog v-model="visible" :show-close="false" width="500">
      <template #header="{ close, titleId, titleClass }">
        <div class="my-header">
          <h4 :id="titleId" :class="titleClass">导入文档</h4>
          <el-button type="danger" @click="close">
            <el-icon class="el-icon--left">
              <CircleCloseFilled />
            </el-icon>
            Close
          </el-button>
        </div>
      </template>
      <input type="file" @change="handleFileChange" accept=".docx" />
      <el-button
        type="primary"
        @click="$router.push('/impodocum')"
        class="comfirm"
      >
        确定
      </el-button>
    </el-dialog>
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
// 新建的弹框逻辑
import { ref } from 'vue'
const showDrop = ref(false)
const handleOut = () => {
  showDrop.value = false
}

// 导入的弹框逻辑
import { CircleCloseFilled } from '@element-plus/icons-vue'
const visible = ref(false)

const documentContent = ref(null)

// 接收导入的文档并进行处理
import mammoth from 'mammoth'
import { useDocumentStore } from '@/stores'
import router from '@/router'
const documentStore = useDocumentStore()
const handleFileChange = (event) => {
  const file = event.target.files[0]
  console.log(file)
  if (!file) return

  const reader = new FileReader()
  reader.onload = async (e) => {
    try {
      const { value: html } = await mammoth.convertToHtml({
        arrayBuffer: e.target.result
      })
      documentContent.value = html

      documentStore.docuContent = html
      console.log(documentStore.docuContent)
    } catch (error) {
      console.error('Error converting Word document:', error)
    }
  }

  reader.readAsArrayBuffer(file)
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
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 16px;
}
.comfirm{
  width: 80px;
  position: absolute;
  right: 20px;
}
</style>
