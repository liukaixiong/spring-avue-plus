<script lang="ts">
import JsonEditorVue from 'json-editor-vue3'

export default {
  name: 'app',
  components: {
    JsonEditorVue
  },
  data() {
    return {
      data: {
        "hello": "vue"
      }
    }
  },
  methods: {}
}
</script>

<template>
  <json-editor-vue class="editor" v-model="data" />
</template>

<style scoped>

</style>
