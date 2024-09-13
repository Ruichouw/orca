<template>
  <div>发现页</div>

  <div>
    <input type="file" @change="handleFileChange" accept=".docx" />
    <div v-html="documentContent" v-if="documentContent"></div>
  </div>
</template>

<script>
import mammoth from 'mammoth'

export default {
  data() {
    return {
      documentContent: null
    }
  },
  methods: {
    async handleFileChange(event) {
      const file = event.target.files[0]
      console.log(file)
      if (!file) return

      const reader = new FileReader()
      reader.onload = async (e) => {
        try {
          const { value: html } = await mammoth.convertToHtml({
            arrayBuffer: e.target.result
          })
          this.documentContent = html
        } catch (error) {
          console.error('Error converting Word document:', error)
        }
      }
      reader.readAsArrayBuffer(file)
    }
  }
}
</script>

<style lang="scss" scoped>
/* 添加一些样式以改善显示 */
div {
  border: 1px solid #ccc;
  padding: 10px;
  margin-top: 10px;
}
</style>
