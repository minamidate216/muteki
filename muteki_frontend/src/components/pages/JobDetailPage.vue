<script setup lang="ts">
import { defineProps } from 'vue';
import type { JobDetailModel } from '@/api';
import EntryButton from '@/components/organisms/EntryButton.vue'
import type { User } from '@/domain/user'

const props = defineProps<{
  jobDetail: JobDetailModel;
  user: User;
  fetching: boolean;
}>();

const getImageUrl = (base64Data: string) => {
  console.log(base64Data)
  return 'data:image/jpeg;base64,' + base64Data;
}
</script>


<template>
  <div class="common-layout">
    <el-container>
      <el-main>
        <div class="container">
          <div v-if="fetching">Loading...</div>
          <div v-else>
            <div class="company">
              <span>
                <!-- バックエンドで修正し、company-logo取得する予定 -->
                <!-- <img justify="center" :src=getImageUrl(jobDetail.) width="400px" height="auto"> -->
                会社名：{{ jobDetail?.job_in_card.company_name }}
              </span>
            </div>
            <h1>{{ jobDetail?.job_in_card.job_name }}</h1>
            <div class="tags">
              <el-tag type="info">{{ jobDetail?.job_in_card.tag1 }}</el-tag>
              <el-tag type="info">{{ jobDetail?.job_in_card.tag2 }}</el-tag>
              <el-tag type="info">{{ jobDetail?.job_in_card.tag3 }}</el-tag>
            </div>
            <div class="location">
              <el-icon>
                <MapLocation />
              </el-icon>
              <el-text size="large">{{ jobDetail?.job_in_card.location }}</el-text>
            </div>
            <div class="salary">
              <el-icon>
                <Money />
              </el-icon>
              <el-text>{{ jobDetail?.job_in_card.min_salary.toLocaleString() }}円 ~ {{
                jobDetail?.job_in_card.max_salary.toLocaleString() }}円</el-text>
            </div>
            <div class="company_img">
              <el-carousel :interval="4000" type="card" autoplay>
                <el-carousel-item v-for="item in jobDetail?.imgs" :key="item">
                  <img justify="center" :src=getImageUrl(item.img_url) width="400px" height="auto">
                </el-carousel-item>
              </el-carousel>
            </div>
            <div class="company_mission">
              <h4>グロースエクスパートナーズグループは<br>
                「ITを駆使して顧客企業の価値を創造すること」をミッションに掲げています。<br>
                社会を支える大手企業が環境変化に適応して組織/ITを変革し、新しい事業価値を創出していく取り組みを「エンタープライズDX」と位置づけ、顧客の
                エンタープライズDXにパートナーとして伴走する「エンタープライズDX事業」に取り組んでいます。<br>
                GxPは、顧客が蓄積してきたレガシー資産（顧客、ブランド、設備・拠点、組織体制、サプライチェーン、ビッグデータ、既存IT基盤など）から新しい価値を引き出して世界中に届けていくことで、顧客とともに変化と成長を続けていきます。<br>
                顧客企業の価値創造を通して、日本・世界の多様な人々が、より生き生きと会話し、働き、生活する、サステナブルな社会作りに取り組んでいます。</h4>
            </div>
            <div class="job_detail">
              <h1 class="detail_title">
                <el-icon>
                  <List />
                </el-icon>
                求人概要
              </h1>
              <div class="job_name">
                <el-row>
                  <el-col :span="6">
                    <el-text size="default">職種</el-text>
                  </el-col>
                  <el-col :span="18">
                    <el-text size="small">{{ jobDetail?.job_in_card.job_name }}</el-text>
                  </el-col>
                </el-row>
              </div>
              <div class="employment_Status">
                <!-- 「正社員」みたいに雇用形態を表示するかも -->
              </div>
              <div class="work_description">
                <el-row>
                  <el-col :span="6">
                    <el-text size="default">仕事内容</el-text>
                  </el-col>
                  <el-col :span="18">
                    <el-text size="small">{{ jobDetail?.work_description }}</el-text>
                  </el-col>
                </el-row>
              </div>
              <div class="work_requirements">
                <el-row>
                  <el-col :span="6">
                    <el-text size=default>必須スキル/経験</el-text>
                  </el-col>
                  <el-col :span="18">
                    <el-text size="small">{{ jobDetail?.work_requirements }}</el-text>
                  </el-col>
                </el-row>
              </div>
              <div class="work_year_salary">
                <el-row>
                  <el-col :span="6">
                    <el-text size="default">給料</el-text>
                  </el-col>
                  <el-col :span="18">
                    <el-text size="small">{{ jobDetail?.work_year_salary }}</el-text>
                  </el-col>
                </el-row>
              </div>
              <div class="work_hours">
                <el-row>
                  <el-col :span="6">
                    <el-text size="default">勤務時間</el-text>
                  </el-col>
                  <el-col :span="18">
                    <el-text size="small">{{ jobDetail?.work_hours }}</el-text>
                  </el-col>
                </el-row>
              </div>
              <div class="schedule">
                <!-- 選考の流れかもしれません -->
              </div>
              <div class="japanese_level">
                <el-row>
                  <el-col :span="6">
                    <el-text size="default">応募資格</el-text>
                  </el-col>
                  <el-col :span="18">
                    <el-text size="small">N{{ jobDetail?.job_in_card.japanese_level }}以上</el-text>
                  </el-col>
                </el-row>
              </div>
              <div class="benefits">
                <el-row>
                  <el-col :span="6">
                    <el-text size="default">福利厚生</el-text>
                  </el-col>
                  <el-col :span="18">
                    <el-text size="small">{{ jobDetail?.benefits }}</el-text>
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>
        </div>
      </el-main>
      <el-footer>
        <div>
          <EntryButton :jobDetail="jobDetail" :user="user"></EntryButton>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>


<style scoped>
/* 全体のレイアウト */
.common-layout {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
  max-width: 960px;
  /* よりコンパクトなデザイン */
  background: #FFFFFF;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

/* ヘッダー部分 */
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

.head-right {
  margin-top: 10px;
  flex: 1;
  display: flex;
  justify-content: flex-end;
}

.header-bar {
  display: flex;
}

/* メインコンテンツエリアの調整 */
.el-main {
  padding: 20px;
  width: 100%;
}

.el-row {
  margin-bottom: 74px;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* 求人概要 */
.detail_title {
  text-align: center;
  font-size: 36px;
  font-family: "Roboto", sa;
  font-weight: bold;
  background: linear-gradient(180deg, #58c6ff 0%, #076ad9 40%, #12063f 80%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.tags {
  display: flex;
  gap: 5px;
  /* タグ間隔を狭くする */
  margin-bottom: 10px;
  /* 余白の調整 */
}

.location,
.salary {
  display: flex;
  align-items: center;
  gap: 5px;
  margin-bottom: 10px;
}

/* フッターエリアのスタイル調整 */
.el-footer {
  width: 100%;
  padding: 10px 15px;
  background-color: #f2f2f2;
  text-align: center;
}

.el-button {
  border: none;
  background-color: #4270B5;
  color: #FFFFFF;
}

.el-text--default {
  font-size: 16px;
}

.el-text--small {
  font-size: 16px;
  color: #000;
}


@media (max-width: 768px) {

  .header-bar,
  .el-main,
  .el-footer {
    padding: 10px;
  }

  .head-right {
    flex-direction: column;
    gap: 5px;
  }

}

.el-else {
  display: flex;
}

.company {
  flex: 1;
  justify-content: flex-end;
}
</style>
