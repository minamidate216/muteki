<template>
    <div>
      <AppHeader></AppHeader>
      <router-view />
    </div>
</template>
  
<script setup>
import AppHeader from '@/components/organisms/AppHeader.vue';
import { RouterLink, RouterView } from 'vue-router';
</script>
  