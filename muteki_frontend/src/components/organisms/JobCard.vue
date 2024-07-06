<script setup lang="ts">
import { JobApi, type JobModel, type LikeRequest } from "@/api";
import { onMounted, ref, type Ref } from "vue";
import { jobApi } from '@/api/job-api-accessor'
import type { User } from '@/domain/user'

const props = defineProps<{
  job: JobModel;
  user: User;
  onClickHandler: (jobId: number) => void;
  isLikedList: number[]
}>();

const buttonClicked = ref(false); // ボタンがクリックされたかどうかを示す状態

const cardClickHandler = () => {
  props.onClickHandler(props.job.job_id);
};

// job_id が isLikedList に含まれているかをチェックし、含まれていたら buttonClicked を true に設定
onMounted(() => {
  if (props.isLikedList.includes(props.job.job_id)) {
    buttonClicked.value = true;
  }
});

async function jobLikePost() {
  try {
    const email = ref("");
    if (props.user.user_email != null) {
      email.value = props.user.user_email
    } else {
      return
    }
    const likeRequest: LikeRequest = { jobId: props.job.job_id, email: email.value };
    const response = await jobApi.jobIsLoginLikePost(likeRequest)
    console.log(response)
    return response.status
  } catch (error) {
    console.error('Failed to fetch jobs lists', error)
  }
};

async function jobLikeDelete() {
  try {
    const email = ref("");
    if (props.user.user_email != null) {
      email.value = props.user.user_email
    } else {
      return
    }
    const response = await jobApi.jobIsLoginJobIdDelete(props.job.job_id);
    console.log(response)
    return response.status
  } catch (error) {
    console.error('Failed to fetch jobs lists', error)
  }
};

const toggleButtonClickHandler = () => {
  buttonClicked.value = !buttonClicked.value; // トグル機能を実装
  console.log("job_id:", props.job.job_id, "button_status:", buttonClicked.value); // job_id とボタンの状態を渡す

  if (props.user.is_authenticated && buttonClicked.value == true) {
    console.log('user_email:', props.user.user_email); // props.user.username を参照
    jobLikePost().then(() => {
      console.log('like登録成功')
    }).catch(error => {
      console.error('like登録失敗!', error);
    });
  } else if (props.user.is_authenticated && buttonClicked.value == false) {
    console.log('user_email:', props.user.user_email); // props.user.username を参照
    jobLikeDelete().then(() => {
      console.log('like削除成功')
    }).catch(error => {
      console.error('like削除失敗!', error);
    })
  } else {
    console.log('User information is not available');
  };
}

const tagFilter = (tags: (string | null | undefined)[]) => {
  // 空いてるtagを削除（null、undefined）
  const validTags = tags.filter(tag => tag !== null && tag !== undefined && tag !== '');
  return validTags;
};
</script>

<template>
  <el-card class="job-card" @click="cardClickHandler">
    <el-row :gutter="24">
      <el-col :span="10">
        <p class="title">{{ job.job_name }}</p>
      </el-col>
      <el-col :span="6"></el-col>
      <el-col :span="8">
        <div class="company-info">
          <div class="company-logo">
            <img src="/gxp_logo.png" alt="GxP株式会社" />
          </div>
          <div class="company-details">
            <div class="company-name">{{ job.company_name }}</div>
            <div class="company-location"><el-icon style="margin-top:3px">
                <Location />
              </el-icon>{{ job.location }}</div>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="24">
      <el-col :span="10">
        <div class="tags">
          <el-tag class="tagItem" v-for="(tag, index) in tagFilter([job.tag1, job.tag2, job.tag3])" :key="index">{{ tag
            }}</el-tag>
        </div>
      </el-col>
      <el-col :span="14"></el-col>
    </el-row>
    <el-row :gutter="24">
      <el-col :span="10">
        <div class="salary">
          {{ job.min_salary / 10000 }}万円〜{{ job.max_salary / 10000 }}万円
        </div>
        <el-col :span="14"></el-col>
      </el-col>
    </el-row>
    <el-row :gutter="24">
      <el-col :span="10">
        <div class="stat1">
          <p style="margin-right:6px; color:#6B6A6A;">外国人比率</p>
          <el-progress :percentage="job.foreign_ratio.toFixed(2) * 100" :text-inside="true" stroke-width="20"
            style="width:70%" color="#658bc3" />
        </div>
      </el-col>
      <el-col :span="7">
        <div class="stat2">日本語レベル：N{{ job.japanese_level }}以上</div>
      </el-col>
      <el-col :span="5">
        <el-tooltip v-if="!props.user.is_authenticated" content="ログインしてください" placement="top">
          <el-button :class="{ 'clicked-button': buttonClicked }" @click.stop="toggleButtonClickHandler"
            :disabled="!props.user.is_authenticated">
            <el-icon style="margin-right:2px;margin-bottom:1px" :style="{ color: buttonClicked ? '#3563E9' : '' }">
              <StarFilled />
            </el-icon>
            {{ buttonClicked ? 'いいかも済み' : 'いいかも!' }}
          </el-button>
        </el-tooltip>
        <el-button v-else :class="{ 'clicked-button': buttonClicked }" @click.stop="toggleButtonClickHandler">
          <el-icon style="margin-right:2px;margin-bottom:1px" :style="{ color: buttonClicked ? '#3563E9' : '' }">
            <StarFilled />
          </el-icon>
          {{ buttonClicked ? 'いいかも済み' : 'いいかも!' }}
        </el-button>
      </el-col>
    </el-row>
  </el-card>
</template>

<style scoped>
.job-card {
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: box-shadow 0.3s;
}

.title {
  font-weight: bold;
  font-size: 20px;
  margin-left: 10px;
}

.job-card:hover {
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.2);
}

.tags {
  gap: 10px;
}

.tagItem {
  margin-right: 6px;
  background-color: #D9D9D9;
  color: #999999;
  border-radius: 15px;
}

.salary {
  font-size: 17px;
  color: #658bc3;
  margin-top: 13px;
  font-weight: bold;
  margin-left: 5px;
}

.stat1 {
  display: flex;
  font-weight: bold;
  margin-left: 5px;
}

.stat2 {
  margin-top: 15px;
  color: #6B6A6A;
  font-weight: bold;
}

.company-info {
  margin-right: 5px;
  display: flex;
}

.company-logo img {
  max-width: 50px;
  margin-top: 9px;
}

.company-name {
  font-weight: bold;
  margin-left: 5px;
  margin-top: 10px;
}

.company-location {
  margin-top: 6px;
  display: flex;
  color: #666;
}


.el-button {
  width: 100px;
  height: 40px;
  border-radius: 10px;
  background-color: #658bc3;
  /* 初期状態の色 */
  color: #ffffff;
  border: 2px solid #658bc3;
  /* 枠線の色 */
}

/* クリックされたときのボタンのスタイル */
.clicked-button {
  background-color: #ffffff;
  /* クリックされたときの背景色 */
  color: #658bc3;
  /* クリックされたときの文字色 */
  border: 2px solid #3563E9;
  /* クリックされたときの枠線の色 */
  font-size: 13px;
  /* クリックされたときのフォントサイズを少し小さく */
}
</style>
