<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useUser } from '@/composable/auth-user'
import { onMounted } from 'vue'
import axios from 'axios'

const router = useRouter();

const { user, getUser } = useUser()

onMounted(async () => {
  await getUser()
})

const toLogout = async () => {
    await axios.get('http://localhost:8080/logout',{withCredentials: true}).then(() => {
      router.push('/login');
      console.log('Logout successful');
      console.log(user.value.user_email);
    }).catch(error => {console.error('Logout failed', error)}) 
};

const toLogin = () => {
    router.push('/login')
}
const toSignup = () => {
    router.push('/signup')
}
const toHome = () => {
    router.push('/')
}

</script>

<template>
    <el-header class="header-bar">
        <div class="head-logo">
            <img src="@/assets/mainlogo.png" class="logo-img" @click="toHome">
        </div>
        <div class="head-center">
            <div class="header-text">
                <el-text><el-link :underline="false">ホームページ</el-link></el-text>
                <el-text><el-link :underline="false">条件から検索</el-link></el-text>
                <el-text><el-link :underline="false">マイページ</el-link></el-text>
                <el-text><el-link :underline="false">問い合わせ</el-link></el-text>
            </div>
            <el-button v-if="!user.is_authenticated" @click="toLogin"
                style="background-color: #3961DF; color: #FFFFFF;">
                <el-icon>
                    <UserFilled />
                </el-icon>
                ログイン
            </el-button>
            <el-button v-if="user.is_authenticated" @click="toLogout"
                style="background-color: #3961DF; color: #FFFFFF;">
                <el-icon>
                    <UserFilled />
                </el-icon>
                <p v-if="user.is_authenticated">
                    {{ user.user_email }}
                </p>
                をログアウト
            </el-button>
            <el-button v-if="!user.is_authenticated" @click="toSignup" type="info" plain>
                <el-icon>
                    <Document />
                </el-icon>
                新規登録
            </el-button>
        </div>
    </el-header>
</template>

<style scoped>
.head-logo {
    display: flex;
    flex: 2;
}

.el-link {
    font-size: 16px;
    color: #000000;
    background-color: transparent; 
    border-radius: 8px;
    padding: 5px; 
    margin-right: 30px;
    transition: background-color 0.3s ease, color 0.3s ease; 
}


.el-link:hover {
    background-color: #D9D9D9; 
}

.header-text {
    /* margin-right: 40%; */
    margin: auto;
    justify-content: center;
}

.head-center {
    margin: auto;
    flex: 10;
    display: flex;
    position: relative;
}

.head-right {
    flex: 1;
    display: flex;
    justify-content: flex-end;
    align-items: center;
}

.header-bar {
    display: flex;
    background-color: #F6F6F6;
}

.el-button {
    border-radius: 10px;
    padding: 10px;
}

.el-icon {
    padding-right: 2px;
}
</style>