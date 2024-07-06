<script setup lang="ts">
import { ref, watchEffect, onMounted, onBeforeUnmount, type Ref } from 'vue'
import { ElDialog } from 'element-plus'
import type { SkillModel } from '@/api';
import { itemApi } from '@/api/job-api-accessor';

const emit = defineEmits(['checkedJob', 'searchedJob'])

const skills: Ref<SkillModel[]> = ref([])
const fetchingSkills = ref(false)

onMounted(async () => {
    fetchingSkills.value = true
    fetchSkills()
})

async function fetchSkills() {
    try {
        skills.value = [];
        const response = await itemApi.itemSkillListGet()
        skills.value = response.data.skills.map(skill => { return { ...skill } });
        console.log(skills.value)
    } catch (error) {
        console.error('Failed to fetch skills lists', error)
    } finally {
        fetchingSkills.value = false
    }
};

// 職種リスト
const jobNames = ['フロントエンドエンジニア', 'バックエンドエンジニア', 'フルスタックエンジニア',
    'インフラエンジニア', 'システムエンジニア', 'セキュリティエンジニア', 'プロジェクトマネージャー',
    'プロジェクトリーダー', 'プログラマー', 'ITコンサルタント',
    'Webマーケター', 'UI/UXデザインナー']
// スキル
// const skills = ['Python', 'PHP', 'Java', 'SQL', 'JavaScript', 'NoSQL', 'C++', 'UI/UXデザイン', 'C#',
//     'HTML/CSS', 'Ruby']
// 勤務地
const locations = ['北海道・東北', '関東', '北陸・甲信越', '東海', '関西', '中国・四国', '九州・沖縄']


// サイドバーの選択された検索条件
const selectedJobNames = ref<string[]>(JSON.parse(sessionStorage.getItem('selectedJobNames') || '[]'))
const selectedSkills = ref<string[]>(JSON.parse(sessionStorage.getItem('selectedSkills') || '[]'))
const selectedJapaneseLevel = ref<number>(JSON.parse(sessionStorage.getItem('selectedJapaneseLevel') || '[]'))
const selectedLocations = ref<string[]>(JSON.parse(sessionStorage.getItem('selectedLocations') || '[]'))
const selectedSalary = ref<number>(JSON.parse(sessionStorage.getItem('selectedSalary') || '[]'))

// モーダルの選択データのref
const modalSelectedJobNames = ref<string[]>(JSON.parse(sessionStorage.getItem('modalSelectedJobNames') || '[]'))
const modalSelectedSkills = ref<string[]>(JSON.parse(sessionStorage.getItem('modalSelectedSkills') || '[]'))
const modalSelectedJapaneseLevel = ref<number>(JSON.parse(sessionStorage.getItem('modalSelectedJapaneseLevel') || '[]'))
const modalSelectedLocations = ref<string[]>(JSON.parse(sessionStorage.getItem('modalSelectedLocations') || '[]'))
const modalSelectedSalary = ref<number>(JSON.parse(sessionStorage.getItem('selectedSalary') || '[]'))
const selectedIndustry = ref<string[]>(JSON.parse(sessionStorage.getItem('selectedIndustry') || '[]'))
const selectedWorkExperience = ref<string>(JSON.parse(sessionStorage.getItem('selectedWorkExperience') || '[]'))
const selectedCompanySize = ref<string>(JSON.parse(sessionStorage.getItem('selectedCompanySize') || '[]'))
const selectedForeignRatio = ref<string>(JSON.parse(sessionStorage.getItem('selectedForeignRatio') || '[]'))
const selectedBenefits = ref<string[]>(JSON.parse(sessionStorage.getItem('selectedBenefits') || '[]'))
const selectedForeignNationals = ref<string[]>(JSON.parse(sessionStorage.getItem('selectedForeignNationals') || '[]'))

// 業界リスト
const industry = [
    'コミュニケーション系', '電機・家電（含むIoT）', '日用品', 'メディア系（動画・ニュース・EC）',
    '自動車', 'アパレル', 'ゲーム（VR・AR・eSports含む）', '電子部品', '小売り', '広告・マーケティング',
    '重工系', '外食', 'クラウド系（含むSaaS）', '運輸', '旅行・ホテル', '通信', '鉄道',
    '教育', '放送', '食品・飲料', '人材紹介', '銀行・証券', 'クレジット・決済・リース',
    '保険', '投資事業・投資ファンド', '不動産', '建設', '製薬', '医療（含む遠隔医療）', '介護',
    'コンサル', 'ITサービス系', 'その他'
];

// 仕事経験
const work_requirements = ['仕事経験なし', '1-3年の仕事経験', 'インターンシップ経験のみ', '3-5年の仕事経験', '1年未満の仕事経験', '5年以上の仕事経験'];

// 企業規模
const company_size = [
    '11人から50人',
    '51人から200人の従業員',
    '201人から500人の従業員',
    '501人から1000人の従業員',
    '1001人以上の従業員',
];

// 日本で働いている外国人の国籍
const foreign_nationals = [
    '中国', 'フィリピン', 'タイ', '越南（ベトナム）', 'インドネシア', 'ミャンマー', '韓国',
    'ネパール', 'スリランカ', 'インド', 'イギリス', 'フランス', 'ドイツ', 'アメリカ', 'その他'
];

// 外国人比率
const foreign_ratio = [
    '10%以上', '20%以上', '30%以上', '40%以上', '50%以上',
    '60%以上', '70%以上', '80%以上', '90%以上'
];

// こだわり条件
const benefits = [
    'ビザサポート', '語学サポート', '社宅・寮の提供あり', 'リモートワーク可', 'スキルアップ研修あり',
    '健康保険・社会保険完備', '交通費支給', '有給休暇制度あり', '家族手当あり'
];

// 更新 sessionStorage
const updateSession = () => {
    sessionStorage.setItem('selectedJobNames', JSON.stringify(selectedJobNames.value))
    sessionStorage.setItem('selectedSkills', JSON.stringify(selectedSkills.value))
    sessionStorage.setItem('selectedJapaneseLevel', JSON.stringify(selectedJapaneseLevel.value))
    sessionStorage.setItem('selectedLocations', JSON.stringify(selectedLocations.value))
    sessionStorage.setItem('selectedSalary', JSON.stringify(selectedSalary.value))
    emit('checkedJob')
}

// 更新モーダルの sessionStorage
const updateModalSession = () => {
    sessionStorage.setItem('modalSelectedJobNames', JSON.stringify(modalSelectedJobNames.value))
    sessionStorage.setItem('modalSelectedSkills', JSON.stringify(modalSelectedSkills.value))
    sessionStorage.setItem('modalSelectedJapaneseLevel', JSON.stringify(modalSelectedJapaneseLevel.value))
    sessionStorage.setItem('modalSelectedLocations', JSON.stringify(modalSelectedLocations.value))
    sessionStorage.setItem('modalSelectedSalary', JSON.stringify(modalSelectedSalary.value))
    sessionStorage.setItem('selectedIndustry', JSON.stringify(selectedIndustry.value))
    sessionStorage.setItem('selectedWorkExperience', JSON.stringify(selectedWorkExperience.value))
    sessionStorage.setItem('selectedCompanySize', JSON.stringify(selectedCompanySize.value))
    sessionStorage.setItem('selectedForeignRatio', JSON.stringify(selectedForeignRatio.value))
    sessionStorage.setItem('selectedBenefits', JSON.stringify(selectedBenefits.value))
    sessionStorage.setItem('selectedForeignNationals', JSON.stringify(selectedForeignNationals.value))
}

// refresh the session storage when the page is refreshed or closed
// リフレッシュする時session storageをクリア
const clearSideBarSessionStorage = () => {
    sessionStorage.removeItem('selectedJobNames')
    sessionStorage.removeItem('selectedSkills')
    sessionStorage.removeItem('selectedJapaneseLevel')
    sessionStorage.removeItem('selectedLocations')
    sessionStorage.removeItem('selectedSalary')
}
const clearModalSessionStorage = () => {
    sessionStorage.removeItem('modalSelectedJobNames')
    sessionStorage.removeItem('modalSelectedSkills')
    sessionStorage.removeItem('modalSelectedJapaneseLevel')
    sessionStorage.removeItem('modalSelectedLocations')
    sessionStorage.removeItem('modalSelectedSalary')
    sessionStorage.removeItem('selectedIndustry')
    sessionStorage.removeItem('selectedWorkExperience')
    sessionStorage.removeItem('selectedCompanySize')
    sessionStorage.removeItem('selectedForeignRatio')
    sessionStorage.removeItem('selectedBenefits')
    sessionStorage.removeItem('selectedForeignNationals')
}

// beforeunload イベントをlisteningする機能を追加
onMounted(() => {
    window.addEventListener('beforeunload', clearSideBarSessionStorage)
    window.addEventListener('beforeunload1', clearModalSessionStorage)
})

// beforeunload イベントをlisteningする機能を削除
onBeforeUnmount(() => {
    window.removeEventListener('beforeunload', clearSideBarSessionStorage)
    window.removeEventListener('beforeunload1', clearModalSessionStorage)
})

const removeSideBarCheckbox = () => {
    clearSideBarSessionStorage();

    selectedJobNames.value = selectedJobNames.value.map(item => '');
    selectedSkills.value = selectedSkills.value.map(item => '');
    selectedJapaneseLevel.value = 0;
    selectedSalary.value = 0;
    selectedLocations.value = selectedLocations.value.map(item => '');
    updateSession
}
const removeModalCheckbox = () => {
    clearModalSessionStorage();

    modalSelectedJobNames.value = modalSelectedJobNames.value.map(item => '');
    modalSelectedSkills.value = modalSelectedSkills.value.map(item => '');
    modalSelectedJapaneseLevel.value = 0;
    modalSelectedSalary.value = 0;
    modalSelectedLocations.value = modalSelectedLocations.value.map(item => '');
    selectedIndustry.value = selectedIndustry.value.map(item => '');
    selectedWorkExperience.value = '';
    selectedCompanySize.value = '';
    selectedForeignRatio.value = '';
    selectedBenefits.value = selectedBenefits.value.map(item => '');
    selectedForeignNationals.value = selectedForeignNationals.value.map(item => '');
}
// listen for selectedJobNames and update sessionStorage
watchEffect(updateSession)
watchEffect(updateModalSession)

// モーダルの状態を管理するためのref
const isModalVisible = ref(false)

// 検索ボタンが押された時の処理
const search = () => {
    const requestData = {
        selectedJobNames: modalSelectedJobNames.value,
        selectedSkills: modalSelectedSkills.value,
        selectedJapaneseLevel: modalSelectedJapaneseLevel.value,
        selectedLocations: modalSelectedLocations.value,
        selectedSalary: modalSelectedSalary.value,
        selectedIndustry: selectedIndustry.value,
        selectedWorkExperience: selectedWorkExperience.value,
        selectedCompanySize: selectedCompanySize.value,
        selectedForeignRatio: selectedForeignRatio.value,
        selectedBenefits: selectedBenefits.value,
        selectedForeignNationals: selectedForeignNationals.value
    };
    console.log('検索リクエストデータ:', requestData);
    // ここにリクエストを送信するコードを追加します
    emit('searchedJob')
    isModalVisible.value = false;
}

</script>

<template>
    <div>
        <el-form class="checkList">
            <div class="detail">
                <el-button plain class="detail-button" @click="removeSideBarCheckbox">クリア</el-button>
            </div>
            <el-form-item label="職種">
                <el-checkbox-group v-model="selectedJobNames" @change="updateSession">
                    <el-checkbox v-for="jobName in jobNames" :label="jobName" :key="jobName">{{ jobName }}</el-checkbox>
                </el-checkbox-group>
            </el-form-item>
            <el-form-item label="スキル">
                <el-checkbox-group v-model="selectedSkills" @change="updateSession">
                    <el-checkbox v-for="skill in skills" :label="skill.skill_name" :key="skill.skill_id">{{
                        skill.skill_name }}</el-checkbox>
                </el-checkbox-group>
            </el-form-item>
            <el-form-item label="日本語レベル">
                <el-select v-model="selectedJapaneseLevel" @change="updateSession">
                    <el-option v-if="selectedJapaneseLevel === 0" label="選択項目なし" :value=selectedJapaneseLevel> 選択項目なし
                    </el-option>
                    <el-option label="N1 ネイティブ" value=1>N1 ネイティブ</el-option>
                    <el-option label="N2 ビジネスレベル相当" value=2>N2 ビジネスレベル相当</el-option>
                    <el-option label="N3 日常会話レベル" value=3>N3 日常会話レベル</el-option>
                    <el-option label="N4 初心者" value=4>N4 初心者</el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="勤務地">
                <el-checkbox-group v-model="selectedLocations" @change="updateSession">
                    <el-checkbox v-for="location in locations" :label="location" :key="location">{{ location
                        }}</el-checkbox>
                </el-checkbox-group>
            </el-form-item>
            <el-form-item label="希望年収">
                <el-select v-model="selectedSalary" @change="updateSession">
                    <el-option v-if="selectedSalary === 0" label="選択項目なし" :value=selectedSalary>
                        選択項目なし
                    </el-option>
                    <el-option label="300万円以上" value=3000000>300万円以上</el-option>
                    <el-option label="400万円以上" value=4000000>400万円以上</el-option>
                    <el-option label="500万円以上" value=5000000>500万円以上</el-option>
                    <el-option label="1000万円以上" value=10000000>1000万円以上</el-option>
                </el-select>
            </el-form-item>
            <div class="detail">
                <el-button plain class="detail-button" @click="isModalVisible = true">詳しく条件を追加</el-button>
            </div>
        </el-form>

        <!-- モーダルの定義 -->
        <el-dialog customClass="dialog-check" v-model="isModalVisible" title="検索条件の追加・変更" width="600px">
            <el-form>
                <el-form-item label="職種">
                    <el-checkbox-group v-model="modalSelectedJobNames" @change="updateModalSession">
                        <el-checkbox v-for="jobName in jobNames" :label="jobName" :key="jobName">{{ jobName
                            }}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="スキル">
                    <el-checkbox-group v-model="modalSelectedSkills" @change="updateModalSession">
                        <el-checkbox v-for="skill in skills" :label="skill.skill_name" :key="skill.skill_id">{{
                            skill.skill_name
                            }}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="日本語レベル">
                    <el-select v-model="modalSelectedJapaneseLevel" @change="updateModalSession">
                        <el-option v-if="selectedJapaneseLevel === 0" label="選択項目なし" :value=selectedJapaneseLevel>
                            選択項目なし
                        </el-option>
                        <el-option label="N1 ネイティブ" value=1>N1 ネイティブ</el-option>
                        <el-option label="N2 ビジネスレベル相当" value=2>N2 ビジネスレベル相当</el-option>
                        <el-option label="N3 日常会話レベル" value=3>N3 日常会話レベル</el-option>
                        <el-option label="N4 初心者" value=4>N4 初心者</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="勤務地">
                    <el-checkbox-group v-model="modalSelectedLocations" @change="updateModalSession">
                        <el-checkbox v-for="location in locations" :label="location" :key="location">{{ location
                            }}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="希望年収">
                    <el-select v-model="modalSelectedSalary" @change="updateModalSession">
                        <el-option v-if="modalSelectedSalary === 0" label="選択項目なし" :value=modalSelectedSalary> 選択項目なし
                        </el-option>
                        <el-option label="300万円以上" value=3000000>300万円以上</el-option>
                        <el-option label="400万円以上" value=4000000>400万円以上</el-option>
                        <el-option label="500万円以上" value=5000000>500万円以上</el-option>
                        <el-option label="1000万円以上" value=10000000>1000万円以上</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="業界リスト">
                    <el-checkbox-group v-model="selectedIndustry" @change="updateModalSession">
                        <el-checkbox v-for="industryItem in industry" :label="industryItem" :key="industryItem">{{
                            industryItem }}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="仕事経験">
                    <el-radio-group v-model="selectedWorkExperience" @change="updateModalSession">
                        <el-radio v-for="workItem in work_requirements" :label="workItem" :key="workItem">{{ workItem
                            }}</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="企業規模">
                    <el-radio-group v-model="selectedCompanySize" @change="updateModalSession">
                        <el-radio v-for="companySizeItem in company_size" :label="companySizeItem"
                            :key="companySizeItem">{{ companySizeItem }}</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="国籍">
                    <el-checkbox-group v-model="selectedForeignNationals" @change="updateModalSession">
                        <el-checkbox v-for="nationalItem in foreign_nationals" :label="nationalItem"
                            :key="nationalItem">{{ nationalItem }}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="外国人比率">
                    <el-radio-group v-model="selectedForeignRatio" @change="updateModalSession">
                        <el-radio v-for="ratioItem in foreign_ratio" :label="ratioItem" :key="ratioItem">{{ ratioItem
                            }}</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="こだわり条件">
                    <el-checkbox-group v-model="selectedBenefits" @change="updateModalSession">
                        <el-checkbox v-for="benefitItem in benefits" :label="benefitItem" :key="benefitItem">{{
                            benefitItem }}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item class="centered-button">
                    <el-button type="primary" @click="search" style="background-color: #6358DC;">検索</el-button>
                    <el-button type="warning" @click="removeModalCheckbox"
                        style="background-color: #6358DC;">クリア</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<style scoped>
.el-form .checkList {
    max-width: 500px;
    margin: auto;
    background-color: #F6F6F6;
    box-shadow: -10px 0 10px -10px rgba(0, 0, 0, 0.5), 10px 0 10px -10px rgba(0, 0, 0, 0.5);
}

.checkbox-group {
    display: flex;
    flex-wrap: wrap;
}

.checkbox-item {
    flex: 1 1 200px;
    margin-bottom: 10px;
}

.el-checkbox {
    margin-bottom: 2px;
    width: 100%;
}

.centered-button {
    display: flex;
    justify-content: center;
    width: 100%;
}

.dialog-check {
    width: 600px !important;
    min-width: 600px !important;
}

.el-button {
    margin: 0 auto;
}

.detail {
    width: 100%;
    display: flex;
    height: 50px;
    margin-bottom: 3%;
}

.detail-button {
    background-color: #658bc3;
    color: #ffffff;
    font: bolder;
    height: 100%;
    width: 100%;
}
</style>
