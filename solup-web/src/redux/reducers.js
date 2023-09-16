// redux/reducers.js

// 초기 상태
const initialState = {
  isLoggedIn: false,
  isAccountRegistered: false,
  userId: null,
  userName: null,
  isCategorized: false,
};

// 리듀서
const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case "SET_IS_LOGGED_IN":
      return { ...state, isLoggedIn: action.value };
    case "SET_IS_ACCOUNT_REGISTERED":
      return { ...state, isAccountRegistered: action.payload };
    case "SET_USER_ID":
      return { ...state, userId: action.payload };
    case "SET_USER_NAME":
      return { ...state, userName: action.payload };
    case "SET_IS_CATEGORIZED":
      return { ...state, isCategorized: action.payload };
    default:
      return state;
  }
};

export default rootReducer;
