<template>
  <span v-if="componentType === 'avue-card'">
    <avue-card :option="option"
               :data="data"
               @row-click="cardJumpLink"
    >
      <template #menu="scope">
        <span @click.stop="cardJumpLink(scope.row,scope.index)">{{ option.btnName || '点击跳转' }}</span>
      </template>
    </avue-card>
  </span>

  <span v-if="componentType === 'avue-tree'">
    <avue-tree ref="tree"
               :option="option"
               :data="data"
               v-model="form"
               @node-contextmenu="nodeContextmenu"
               @node-click="nodeClick">
    </avue-tree>
  </span>


  <span v-if="componentType === 'avue-mind'">
<template>
  <div class="mindMapDemo">
    <div id="mindMapContainer"></div>
    <div class="toolbar">
    	<button @click="changeLyaout">切换结构</button>
    </div>
  </div>
</template>
  </span>

</template>
<script>
import _remote from "@/api/crud/remoteApi.js";

export default {
  mounted() {
    let self = this;
    let query = this.$route.query;
    let domain = import.meta.env.VITE_API_URL;
    let configUrl = import.meta.env.VITE_APP_AVUE_API_PATH;
    let params = {
      "group": query.group,
    }
    _remote.post(domain + configUrl, params, (configObject) => {
      self.option = configObject.option;
      self.data = configObject.data;
      self.componentType = configObject.componentType;
    });

  },
  data() {
    return {
      componentType: "",
      option: {},
      data: []
    }
  },
  methods: {
    /**
     * 卡片点击事件
     * @param row
     * @param index
     */
    cardJumpLink(row, index) {
      this.$message.success('查看控制台')
      console.log(row, index)
      let path = row.path;
      window.open(path);
    },
  }
}
</script>
