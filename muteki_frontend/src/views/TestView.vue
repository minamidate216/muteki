<!-- <template>
    <div>
        <el-form>
            <el-form-item label="选择技能">
                <el-checkbox-group v-model="selectedSkills" @change="updateSession">
                    <el-checkbox v-for="skill in skills" :label="skill" :key="skill">{{ skill }}</el-checkbox>
                </el-checkbox-group>

            </el-form-item>
            <el-form-item label="業界">
                <el-checkbox-group v-model="selectedSkills" @change="updateSession">
                    <el-checkbox v-for="skill in skills" :label="skill" :key="skill">{{ skill }}</el-checkbox>
                </el-checkbox-group>
            </el-form-item>
        </el-form>
    </div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from 'vue'

// 技能列表
const skills = ['Java', 'Vue', 'JavaScript', 'HTML', 'CSS']

// 选中的技能列表
const selectedSkills = ref<string[]>(JSON.parse(sessionStorage.getItem('selectedSkills') || '[]'))

// 更新 sessionStorage
const updateSession = () => {
    sessionStorage.setItem('selectedSkills', JSON.stringify(selectedSkills.value))
    console.log(JSON.parse(sessionStorage.getItem('selectedSkills')));

}

// 监听 selectedSkills 变化并更新 sessionStorage
watchEffect(updateSession)
</script>

<style scoped>
.el-form {
    max-width: 500px;
    margin: auto;
}

.el-checkbox-group {
    display: flex;
    flex-direction: column;
}
</style> -->