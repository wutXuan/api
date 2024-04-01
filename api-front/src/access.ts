/**
 * @see https://umijs.org/zh-CN/plugins/plugin-access
 * */
export default function access(initialState: InitialState) {
  const loginUser = initialState.loginUser;
  return {
    // canAdmin: currentUser && currentUser.access === 'admin',
    canUser: loginUser,
    canAdmin: loginUser && loginUser.userRole === 'admin'
  };
}
