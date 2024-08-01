<template>
  <span v-if="componentType === 'avue-card'">
    <avue-card :option="option"
               :data="data"
               @row-click="tip"
               @row-add="tip">
      <template #menu="scope">
        <span @click.stop="tip(scope.row,scope.index)">操作1</span>
        <span @click.stop="tip(scope.row,scope.index)">操作2</span>
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


  <span v-if="componentType === 'avue-tree'">
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
    let query = this.$route.query;
    let domain = "http://localhost:8765";
    let self = this;
    let configUrl = "/avue/crud";
    let params = {
      "group": query.group,
    }
    _remote.post(domain + configUrl, params, (configObject) => {
      self.option = configObject.option;
      self.data = configObject.data;
      self.componentType = configObject.componentType;
      debugger;
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
    tip(row, index) {
      this.$message.success('查看控制台')
      console.log(row, index)
    }
  }
}
</script>
