export type User = {
  user_email?: string
    is_authenticated: boolean
  }
  
  // Fixture:修正
  export const LOGIN_TEST_USER = {
    user_email: 'dummy_user',
    is_authenticated: true
  }
  
  export const ANONYMOUS = {
    user_email: undefined,
    is_authenticated: false
  }
  