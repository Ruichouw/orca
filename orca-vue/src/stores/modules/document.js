import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useDocumentStore = defineStore(
  'document',
  () => {
    // 定义接收导入文档传来的变量
    const docuContent = ref('')
    return {
      docuContent
    }
  },
  {
    persist: true
  }
)
