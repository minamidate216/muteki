.<script setup lang="ts">
import { type JobDetailModel } from "@/api";
import { ref } from "vue";
import type { User } from '@/domain/user'
import { useRouter } from 'vue-router'

const router = useRouter();

const props = defineProps<{
  jobDetail: JobDetailModel;
  user: User;
}>();

const alertVisible = ref(false);
const alertDescription = ref('');


//エントリーボタンがクリックされたときに実行されるメソッド
const buttonClickHandler = () => {
  if (props.user.is_authenticated) {
    showAlert();
  } else {
    toLogin();
  }
}

//ログインしていない場合
const toLogin = () => {
    router.push('/login')
}

//ログインしていた場合
const showAlert = () => {
    console.log("job_id:" + props.jobDetail.job_id, "user_email:" + props.user.user_email);
    alertDescription.value = `エントリーしたユーザー：${props.user.user_email} エントリーする企業：${props.jobDetail.job_id}`;
    alertVisible.value = true;
    setTimeout(() => {
        alertVisible.value = false;
      }, 5000); // 5秒後にアラートを自動で閉じる
}
</script>

<template>
    <div>
        <el-button 
          style="background-color: #6358DC;" 
          @click="buttonClickHandler"
          >エントリー</el-button>
    </div>
    <el-alert
      class="top-alert"
      v-if="alertVisible"
      title="エントリー成功"
      type="success"
      show-icon
      center
      :description="alertDescription"
      :closable="false"
    />
</template>

<style scoped>
.el-button {
  border: none;
  background-color: #3563E9;
  color: #FFFFFF;
}
.el-alert {
  margin: 20px 0 0;
}
.el-alert:first-child {
  margin-top: 0;
}
.top-alert {
  position: fixed;
  top: 20px;
  left: 50%;
  transform: translateX(-50%);
  width: 90%;
  max-width: 300px;
  z-index: 1000;
}
</style>
