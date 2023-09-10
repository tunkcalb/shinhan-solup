
export const SET_IS_LOGGED_IN = "SET_IS_LOGGED_IN";

export const setIsLoggedIn = (value) => ({
  type: SET_IS_LOGGED_IN,
  value,
});

export const setIsAccountRegistered = (isAccountRegistered) => ({
  type: 'SET_IS_ACCOUNT_REGISTERED',
  payload: isAccountRegistered,
});

export const setUserId = (userId) => ({
  type: 'SET_USER_ID',
  payload: userId,
});
export const setUserName = (userName) => ({
  type: 'SET_USER_NAME',
  payload: userName,
});
