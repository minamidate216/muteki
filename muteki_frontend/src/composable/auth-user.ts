import { userApi } from '@/api/job-api-accessor'
import { ANONYMOUS, type User } from '@/domain/user'
import { ref, type Ref } from 'vue'

/**
 * ユーザ情報を取得する
 *
 * @returns ユーザ情報
 */
export const useUser = () => {
  const user: Ref<User> = ref(ANONYMOUS)

  const getUser = async () => {
    const response = await userApi.usersMeGet()
    user.value = { ...response.data }
  }

  return { user, getUser }
}
