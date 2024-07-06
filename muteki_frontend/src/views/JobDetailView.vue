<script setup lang="ts">
import type { JobDetailModel } from '@/api';
import { jobApi } from '@/api/job-api-accessor';
import JobDetailPage from '@/components/pages/JobDetailPage.vue';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { useUser } from '@/composable/auth-user'

const jobDetail = ref<JobDetailModel | null>(null);
const fetching = ref(false);
const route = useRoute();
const jobId = route.params.jobId as string;

const { user, getUser } = useUser()

onMounted(async () => {
  fetching.value = true;
  await getUser()
  await fetchDetail();
});

async function fetchDetail() {
  try {
    const response = await jobApi.jobJobIdGet(jobId);
    console.log(response);
    jobDetail.value = {
      ...response.data,
      job_id: response.data.job_id
    };
  } catch (error) {
    console.error('Failed to fetch job details', error);
  } finally {
    fetching.value = false;
  }
}
</script>

<template>
  <JobDetailPage :jobDetail="jobDetail" :fetching="fetching" :user="user" />
</template>

<style scoped></style>
