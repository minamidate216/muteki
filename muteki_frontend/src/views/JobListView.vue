<script setup lang="ts">
import type { JobModel } from '@/api';
import { jobApi } from '@/api/job-api-accessor'
import JobListPage from '@/components/pages/JobListPage.vue'
import { useUser } from '@/composable/auth-user'
import { onMounted, ref, type Ref } from 'vue'

const jobs: Ref<JobModel[]> = ref([])
const fetching = ref(false)

const { user, getUser } = useUser()

onMounted(async () => {
  fetching.value = true
  fetchList()
  await getUser()
})

async function fetchList() {
  try {
    jobs.value = [];
    const response = await jobApi.jobJobListGet()
    console.log(response)

    jobs.value = response.data.jobs.map(job => { return { ...job } });
    console.log(jobs.value)
  } catch (error) {
    console.error('Failed to fetch jobs lists', error)
  } finally {
    fetching.value = false
  }
};

//検索ボタンが押された時のテスト関数
const detail_search = async (options: any) => {

  const selectedJobNamesJson = sessionStorage.getItem('modalSelectedJobNames')
  const selectedSkillsJson = sessionStorage.getItem('modalSelectedSkills')
  const selectedJapaneseLevelJson = sessionStorage.getItem('modalSelectedJapaneseLevel')
  const selectedLocationsJson = sessionStorage.getItem('modalSelectedLocations')
  const selectedSalaryJson = sessionStorage.getItem('modalSelectedSalary')

  const selectedIndustryJson = sessionStorage.getItem('selectedIndustry')
  const selectedWorkExperienceJson = sessionStorage.getItem('selectedWorkExperience')
  const selectedCompanySizeJson = sessionStorage.getItem('selectedCompanySize')
  const selectedForeignRatioJson = sessionStorage.getItem('selectedForeignRatio')
  const selectedBenefitsJson = sessionStorage.getItem('selectedBenefits')
  const selectedForeignNationalsJson = sessionStorage.getItem('selectedForeignNationals')

  // API使用のパラメータを準備
  // JSONを配列に転換
  const selectedJobNames = selectedJobNamesJson ? JSON.parse(selectedJobNamesJson) : [];
  const selectedSkills = selectedSkillsJson ? JSON.parse(selectedSkillsJson) : [];
  const selectedJapaneseLevel = selectedJapaneseLevelJson ? JSON.parse(selectedJapaneseLevelJson) : [];
  const selectedLocationsTemp = selectedLocationsJson ? JSON.parse(selectedLocationsJson) : [];
  const selectedSalary = selectedSalaryJson ? JSON.parse(selectedSalaryJson) : [];
  const selectedIndustry = selectedIndustryJson ? JSON.parse(selectedIndustryJson) : [];
  const selectedWorkExperience = selectedWorkExperienceJson ? JSON.parse(selectedWorkExperienceJson) : [];
  const selectedCompanySize = selectedCompanySizeJson ? JSON.parse(selectedCompanySizeJson) : [];
  const selectedForeignRatio = selectedForeignRatioJson ? JSON.parse(selectedForeignRatioJson) : [];
  const selectedBenefits = selectedBenefitsJson ? JSON.parse(selectedBenefitsJson) : [];
  const selectedForeignNationals = selectedForeignNationalsJson ? JSON.parse(selectedForeignNationalsJson) : [];


  //locationのパラメータの前処理
  const selectedLocations: string[] = selectedLocationsTemp.map((selectedLocation: string) => selectedLocation.replace('・', '_'));

  try {
    // jobのリストをクリア
    jobs.value = [];
    // jobListをサイドバーのデータで絞り込み
    // Apiを叩く
    const response = await jobApi.jobSearchMoreGet(selectedJobNames, selectedSkills,
      selectedJapaneseLevel, selectedLocations,
      selectedSalary, selectedIndustry, selectedWorkExperience,
      selectedCompanySize, selectedForeignNationals, selectedForeignRatio, selectedBenefits
    );
    jobs.value = response.data.jobs.map(job => { return { ...job } });
  }
  catch (error) {
    console.error('API request error:', error);
  } finally {
    fetching.value = false;
  }

};

// jobListをサイドバーのデータで絞り込み
const updateSession = async (options: any) => {

  const selectedJobNamesJson = sessionStorage.getItem('selectedJobNames')
  const selectedSkillsJson = sessionStorage.getItem('selectedSkills')
  const selectedJapaneseLevelJson = sessionStorage.getItem('selectedJapaneseLevel')
  const selectedLocationsJson = sessionStorage.getItem('selectedLocations')
  const selectedSalaryJson = sessionStorage.getItem('selectedSalary')


  // API使用のパラメータを準備
  // JSONを配列に転換
  const selectedJobNames = selectedJobNamesJson ? JSON.parse(selectedJobNamesJson) : [];
  const selectedSkills = selectedSkillsJson ? JSON.parse(selectedSkillsJson) : [];
  const selectedJapaneseLevel = selectedJapaneseLevelJson ? JSON.parse(selectedJapaneseLevelJson) : [];
  const selectedLocationsTemp = selectedLocationsJson ? JSON.parse(selectedLocationsJson) : [];
  const selectedSalary = selectedSalaryJson ? JSON.parse(selectedSalaryJson) : [];

  //locationのパラメータの前処理
  const selectedLocations: string[] = selectedLocationsTemp.map((selectedLocation: string) => selectedLocation.replace('・', '_'));

  try {
    // jobのリストをクリア
    jobs.value = [];
    // jobListをサイドバーのデータで絞り込み
    // Apiを叩く
    const response = await jobApi.jobSearchGet(selectedJobNames, selectedSkills,
      selectedJapaneseLevel, selectedLocations,
      selectedSalary);
    jobs.value = response.data.jobs.map(job => { return { ...job } });
  }
  catch (error) {
    console.error('API request error:', error);
  } finally {
    fetching.value = false;
  }

};
</script>

<template>
  <JobListPage :jobs="jobs" :fetching="fetching" @checked-job="updateSession" @searched-job="detail_search" :user="user" />
</template>
