<script setup lang="ts">
import { defineProps, ref, computed, watch, onMounted } from 'vue'
import type { JobModel } from '@/api'
import { type Ref } from 'vue'
import JobCard from '@/components/organisms/JobCard.vue';
import SelectCheckBoxes from '@/components/organisms/SelectCheckBoxes.vue';
import { useRouter } from 'vue-router'
import AppFooter from '@/components/organisms/AppFooter.vue'
import type { User } from '@/domain/user'
import { jobApi, myPageApi } from '@/api/job-api-accessor'

interface Props {
  user: User
}

const props = defineProps<{
  jobs: JobModel[];
  user: User;
  fetching: boolean;
}>()

const emit = defineEmits(['checkedJob', 'searchedJob'])
//今のユーザー気に入った求人リスト
const isLikedList: Ref<number[]> = ref([])

const gxpIconUrl = '/gxp_logo.png'
const router = useRouter()
const onClickHandler = (jobId: number) => {
  router.push(`/job/${jobId}`)
}

const checked = () => {
  emit('checkedJob')
}
const searched = () => {
  emit('searchedJob')
}

async function isLikedFetchAll() {
  try {
    const response = await jobApi.jobIsLoginLikeJobListGet();
    console.log(response)
    if (response.status == 200 && response.data.jobIds != null) {
      isLikedList.value = response.data.jobIds;
    }
    else {
      console.error('Unexpected response format:', response);
    }
  } catch (error) {
    console.error('Failed to fetch isLikedJobIds lists', error)
  }
}


async function userProfileGet() {
  try {
    const response = await myPageApi.mypageProfileGet();
    console.log("ユーザープロファイル")
    console.log(response)
    // if (response.status == 200) {
    //   isLikedList.value = response.data.jobIds;
    // }
    // else {
    //   console.error('Unexpected response format:', response);
    // }
  } catch (error) {
    console.error('Failed to fetch isLikedJobIds lists', error)
  }
}


// Pagination logic
const currentPage = ref(1)
const pageSize = 6

const paginatedJobs = computed(() => {
  console.log('Computed paginatedJobs triggered')
  if (!props.jobs?.values) {
    console.log('No jobs found')
    return []
  }
  const start = (currentPage.value - 1) * pageSize
  const end = start + pageSize
  console.log('Paginated jobs:', props.jobs.slice(start, end))
  return props.jobs.slice(start, end)
})

watch(currentPage, () => {
  console.log('Current page changed:', currentPage.value)
  // Any additional logic when page changes
})

onMounted(() => {
  userProfileGet()
  isLikedFetchAll()
  console.log('Component mounted')
  console.log('Initial jobs:', props.jobs)
})

watch(props.jobs, (newVal) => {
  console.log('Jobs prop changed:', newVal)
}, { immediate: true })

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-container class="all">
        <el-aside class="aside no-scrollbar">
          <SelectCheckBoxes @checkedJob="checked" @searchedJob="searched"></SelectCheckBoxes>
        </el-aside>
        <el-container>
          <el-main class="no-scrollbar main-content">
            <div class="content-wrapper">
              <div class="header">
                <div class="title">
                  <h2>求人一覧</h2>
                </div>
                <div class="job-summary">
                  検索結果：{{ props.jobs?.length }}件
                  <span v-if="props.jobs?.length >= 6">（{{ pageSize * (currentPage - 1) + 1 }}~{{ pageSize *
                    (currentPage - 1) + paginatedJobs.length }}件表示）</span>
                  <span v-else>（{{ props.jobs?.length }}件表示）</span>
                </div>
              </div>
              <div class="container">
                <div class="card-list">
                  <el-row :gutter="24" v-for="job in paginatedJobs" :key="job.job_id">
                    <JobCard :job="job" :onClickHandler="onClickHandler" :user="user" :isLikedList="isLikedList">
                    </JobCard>
                  </el-row>
                </div>
              </div>
              <div class="page-slide">
                <el-pagination background style="--el-color-primary: #D9D9D9" layout="prev, pager, next"
                  :total="props.jobs?.length || 0" :page-size="pageSize" v-model:current-page="currentPage" />
              </div>
            </div>
          </el-main>
        </el-container>
      </el-container>
      <el-footer>
        <AppFooter></AppFooter>
      </el-footer>
    </el-container>
  </div>
</template>

<style scoped>
.header {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  height: 190px;
}

.title {
  margin: auto;
  font-size: 28px;
  font-family: 'HGPGothicB';
  color: #454545;
}

.job-summary {
  font-size: 16px;
  color: #666;
  margin-left: 10%;
  align-self: flex-start;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;

}


.card-list {
  width: 1280px;
}

.job-card {
  display: fixed;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
  flex: 1;
}

.logo-img {
  width: 30px;
  height: 30px;
}

.head-logo {
  display: flex;
}

.head-text {
  font-family: 'Georgia', serif;
  font-size: 24px;
  font-weight: bold;
  color: #333;
  text-align: center;
  background-color: #f8f8f8;
}

.no-scrollbar {
  scrollbar-width: none;
  /* Firefox */
}

.no-scrollbar::-webkit-scrollbar {
  display: none;
  /* scrollbarを隠す（Chrome、Safari等） */
}

.head-right {
  margin-top: 10px;
  flex: 1;
  display: flex;
  justify-content: flex-end;
}

.header-bar {
  display: flex;
}

.aside {
  width: 22%;
  padding: 20px;
  background-color: #f9f9f9;
  border-right: 1px solid #ddd;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  margin-top: 15px;
}

.filter-form {
  display: flex;
  flex-direction: column;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-select .el-input {
  width: 100%;
}

.search-button {
  width: 100%;
}

.main-content {
  position: relative;
  width: 100%;
  margin: 0;
}

.main-content::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0) 50%, white 100%), url('/list-page-background.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  opacity: 0.3;
  z-index: -1;

}

.content-wrapper {
  position: absolute;
  width: 85%;
  margin: 0 auto;
  transform: translateX(60px);
  z-index: 1;
}

.page-slide {
  margin-top: 10px;
  margin-bottom: 10px;
  position: relative;
  display: flex;
  justify-content: center;
  /* left: 37%; */
}

.page-slide>el-pagination {
  margin: auto;
}

.all>el-footer {
  width: 100%;
}
</style>