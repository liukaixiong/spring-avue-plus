<template>
  <div id="login-div-id">
    <h2>欢迎您的登录</h2>
    <avue-login v-model="loginData"
                :option="option"
                @submit="submit"
                @keyup.enter="enterSubmit">
    </avue-login>
  </div>
</template>
<script setup>
import {onMounted, ref, toValue} from 'vue';
import {ElMessage} from 'element-plus';
import {useRouter} from 'vue-router';
import {login} from '@/api/crud/crud.js'


const router = useRouter();

const option = ref({
  width: 400,
  time: 60,
  codeType: 'phone',
  column: {
    username: {
      label: '用户名',
      autocomplete: 'off',
      rules: {
        required: true, message: '请输入用户名', trigger: 'blur'
      }
    },
    password: {
      label: '密码',
      autocomplete: 'off',
      rules: {required: true, message: '请输入密码', trigger: 'blur'}
    },
    code: {
      hide: true
    }
  }
});

const loginUrl = import.meta.env.VITE_API_URL + '/avue/login';
const loginData = ref({});

const enterSubmit = (form) => {
  if (!loginData.value.username) {
    ElMessage.error("用户名不能为空!")
    return;
  }

  if (!loginData.value.password) {
    ElMessage.error("密码不能为空!")
    return;
  }
  const formObject = toValue(loginData);
  submit(formObject);
}

const submit = (form) => {
  // ElMessage.success(JSON.stringify(form));
  login(loginUrl, form).then((res) => {
    if (res.success) {
      Object.keys(res.data).forEach(key => {
        setCookie(key, res.data[key], 1);
      });
      // 希望跳转到对应的路由地址,todo 待优化统一管理配置
      router.push('/avue-component?group=avueUrlList');
    } else {
      ElMessage.error(res.message);
    }
  });
  // console.log(form);
};

function setCookie(name, value, days) {
  let expires = '';
  if (days) {
    const date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    expires = '; expires=' + date.toUTCString();
  }
  document.cookie = name + '=' + (value || '') + expires + '; path=/';
}

onMounted(() => {
});
</script>
<style scoped>
#login-div-id {
  display: grid;
  place-items: center;
  height: 70vh; /* 使用视口高度单位 */
  margin: 0; /* 移除默认边距 */
}
</style>
