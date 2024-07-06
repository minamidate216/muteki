<template>
  <div class="login-container">
    <img src="@/assets/mainlogo.png" class="main-logo" @click="toHome">
    <div class="left-panel">
      <div class="form-container">
        <h2>ログイン</h2>
        <el-form @submit.prevent="onSubmit">
          <el-form-item>
            <p v-if="submitAttempted && dbResponse" class="error-message">
              <el-icon>
                <Warning />
              </el-icon>
              「メールアドレス」または「パスワード」が間違っています
            </p>
            <el-input v-model="email" name="email" type="text" placeholder="sample@example.com"
              :class="{ 'error-input': submitAttempted && dbResponse }">
              <template #prefix>
                <el-icon>
                  <Message />
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="password" name="password" type="password" placeholder="パスワード" show-password
              :class="{ 'error-input': submitAttempted && dbResponse }">
              <template #prefix>
                <el-icon>
                  <Lock />
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-checkbox v-model="rememberMe">ログイン状態を保存する</el-checkbox>
          </el-form-item>
          <el-link href="#" class="forgot-password">パスワードを忘れましたか?</el-link>
          <el-form-item>
            <el-button type="primary" native-type="submit" class="login-button"
              style="background-color: #3563E9;">ログイン</el-button>
          </el-form-item>
        </el-form>
        <div class="register-link">
          初めてご利用ですか? <el-link href="/signup">新規登録</el-link>
        </div>
      </div>
    </div>
    <div class="right-panel">
      <img src="@/assets/login_img.png" alt="Background" class="background-image" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router';

const email = ref('');
const password = ref('');
const rememberMe = ref(false);
const submitAttempted = ref(false);
const dbResponse = ref(false);

async function login() {
  axios.postForm('http://localhost:8080/login', {
    email: email.value,
    password: password.value
  }, { withCredentials: true }).then(() => {
    console.log('ログイン成功')
    alert("ログイン成功");
    router.push('/');
  }).catch(error => {
    console.error('ログイン失敗!', error);
    dbResponse.value = true;
  });

}
const onSubmit = () => {
  submitAttempted.value = true;
  console.log('Email:', email.value);
  console.log('Password:', password.value);
  // サーバーからのレスポンスをシミュレート
  setTimeout(() => {
    login();
  });
};


const toHome = () => {
  router.push('/')
}
</script>

<style scoped>
.main-logo {
  position: absolute;
  height: 60px;
  width: 180px;
}

.login-container {
  display: flex;
  height: 100vh;
}

.left-panel {
  flex: 3;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 2rem;
}

.form-container {
  width: 100%;
  max-width: 400px;
  position: absolute;
  /* 絶対配置 */
  top: 200px;
}

h2 {
  text-align: center;
  margin-bottom: 1rem;
}

.el-form {
  display: flex;
  flex-direction: column;
}

.el-form-item {
  margin-bottom: 1rem;
}

.forgot-password {
  text-align: right;
  display: block;
  margin-bottom: 1rem;
}

.login-button {
  width: 100%;
}

.register-link {
  text-align: center;
  margin-top: 1rem;
}

.right-panel {
  flex: 2;
  background: url('@/assets/login_img.png') no-repeat center center;
  background-size: cover;
}

.background-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.error-message {
  color: red;
  font-size: 14px;
}

.password-conditions {
  font-size: 12px;
  color: #666;
  margin-top: 0.5rem;
}

.error-input {
  --el-fill-color-blank: #FFDEDE;
}
</style>
