// redux/store.js

import { createStore, applyMiddleware } from "redux";
import thunk from "redux-thunk"; // Redux Thunk 미들웨어
import rootReducer from "./reducers"; // 리듀서 파일

// Redux 스토어 생성
const store = createStore(rootReducer, applyMiddleware(thunk));

export default store;
