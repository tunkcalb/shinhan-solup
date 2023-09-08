// redux/reducers.js

// 초기 상태
const initialState = {
    isLoggedIn: false,
  };
  
  // 리듀서
  const rootReducer = (state = initialState, action) => {
    switch (action.type) {
      case "SET_IS_LOGGED_IN":
        return { ...state, isLoggedIn: action.value };
      default:
        return state;
    }
  };
  
  export default rootReducer;
  