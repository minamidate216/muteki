import { createRouter, createWebHistory } from 'vue-router';
import LayoutWithHeader from '@/layouts/LayoutWithHeader.vue';
import LayoutWithoutHeader from '@/layouts/LayoutWithoutHeader.vue';
import JobListView from '../views/JobListView.vue';
import JobDetailView from '../views/JobDetailView.vue';
import LoginView from '../views/LoginView.vue';
import SignupView from '../views/SignupView.vue';

const routes = [
  {
    path: '/',
    component: LayoutWithHeader,
    children: [
      {
        path: '',
        name: 'jobList',
        component: JobListView,
      },
      {
        path: 'job/:jobId',
        name: 'jobDetail',
        component: JobDetailView,
      }
    ],
  },
  {
    path: '/login',
    component: LayoutWithoutHeader,
    children: [
      {
        path: '',
        name: 'login',
        component: LoginView,
      },
    ],
  },
  {
    path: '/signup',
    component: LayoutWithoutHeader,
    children: [
      {
        path: '',
        name: 'signup',
        component: SignupView,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
