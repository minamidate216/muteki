<template>
  <div class="login-container">
    <img src="@/assets/mainlogo2.png" class="main-logo2"  @click="toHome">
    <div class="left-panel">
      <img src="@/assets/signup_img.png" alt="Background" class="background-image"/>
    </div>
    <div class="right-panel">
      <div class="form-container">
        <h2>新規登録</h2>
        <el-form @submit.prevent="onSubmit">
          <el-form-item>
            <p v-if="submitAttempted && (isInValidEmail || isInValidPassword)" class="error-message">
              <el-icon>
                <Warning />
              </el-icon>
              「メールアドレス」「パスワード」を正しく入力してください
            </p>
            <p v-if="submitAttempted && responseStatus!==200" class="error-message">
              <el-icon>
                <Warning />
              </el-icon>
              そのメールアドレスは既に使用されています
            </p>
            <el-input v-model="email" type="text" placeholder="sample@example.com"
              :class="{ 'error-input': submitAttempted && (isInValidEmail || isInValidPassword) }">
              <template #prefix>
                <el-icon>
                  <Message />
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="password" type="password" placeholder="パスワード" show-password
              :class="{ 'error-input': submitAttempted && (isInValidEmail || isInValidPassword) }">
              <template #prefix>
                <el-icon>
                  <Lock />
                </el-icon>
              </template>
            </el-input>
            <p class="password-conditions">
              <el-icon>
                <Warning />
              </el-icon>
              半角英数を含む10文字以上
            </p>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" native-type="submit" class="login-button"
              style="background-color: #3563E9;">新規登録</el-button>
          </el-form-item>
        </el-form>
        <div class="login-link">
          <el-link href="/login">ログインはこちら</el-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed,type Ref } from 'vue';
import { ElForm, ElFormItem, ElInput, ElButton, ElCheckbox, ElLink, ElIcon } from 'element-plus';
import { Message, Lock } from '@element-plus/icons-vue';
import router from '@/router';
import { userApi } from '@/api/job-api-accessor';
import type { UserRequest } from '@/api';

const email = ref('');
const password = ref('');
const submitAttempted = ref(false);
const responseStatus = ref(200);

const validateEmail = (email: string) => {
  const reg = new RegExp(/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/);
  return reg.test(email);
};
const validatePassword = (password: string) => {
  const reg = new RegExp(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{10,20}$/);
  return reg.test(password);
}

const isInValidEmail = computed(() => !validateEmail(email.value));
const isInValidPassword = computed(() => !validatePassword(password.value));

const onSubmit = () => {
  submitAttempted.value = true;
  if (!isInValidEmail.value && !isInValidPassword.value) {
    console.log('Email:', email.value);
    console.log('Password:', password.value);
    signUp()
    // フォームの送信処理をここに追加
  } else {
    email.value = "";
    password.value = "";
  }
};
async function signUp(){
  try{
    const userRequest: UserRequest = { email: email.value, password: password.value };
    const response = await userApi.signUpPost(userRequest)
    console.log(response.status);
    responseStatus.value = response.status;
    if(response.status==200){
      alert("新規登録成功")
      router.push("/login")
    }else{
      alert("新規登録失敗")
    }
  }catch{

  }
}
const toHome =()=>{
    router.push('/')
}
</script>

<style scoped>
  .main-logo2 {
    position: absolute;
    height: 60px;
    width: 180px;
    padding-top: 20px;
    padding-left: 20px;
  }

.login-container {
  display: flex;
  height: 100vh;
}

.right-panel {
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

.left-panel {
  flex: 2;
  background: url('@/assets/signup_img.png') no-repeat center center;
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

.login-link {
  text-align: center;
  margin-top: 1rem;
}
</style>