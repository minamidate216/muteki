import { JobApi, UserApi, ItemApi, MypageApi } from '@/api/api'
import type { AxiosInstance } from 'axios'
import axios from 'axios'

export let jobApi: JobApi
export let userApi: UserApi
export let itemApi: ItemApi
export let myPageApi: MypageApi

export const initializeApi = () => {
  const basePath = (import.meta.env.PROD ? '' : 'http://localhost:8080') + '/api'
  jobApi = new JobApi(undefined, basePath, createAxiosInstance())
  userApi = new UserApi(undefined, basePath, createAxiosInstance())
  itemApi = new ItemApi(undefined, basePath, createAxiosInstance())
  myPageApi = new MypageApi(undefined, basePath, createAxiosInstance())
}

const createAxiosInstance = (): AxiosInstance => {
  const instance = axios.create({ timeout: 60000, withCredentials: true })

  instance.interceptors.response.use(
    (response) => response,
    (error) => {
      console.log(error)
    }
  )

  return instance
}