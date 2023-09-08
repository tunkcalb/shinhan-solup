// redux/actions.js

// 액션 타입
export const SET_IS_LOGGED_IN = "SET_IS_LOGGED_IN";

// 액션 생성자
export const setIsLoggedIn = (value) => ({
  type: SET_IS_LOGGED_IN,
  value,
});
