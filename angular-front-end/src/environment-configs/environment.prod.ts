export const environment = {
  production: true,
  //
  apiVersion: '/v1',
  apiUrl: 'https://api.demo.pjlindustries.com:9081/api',
  appUrl: 'https://demo.pjlindustries.com',
  environment: 'prod',
  backEndControllerPaths: {
    AuthController: {
      createGoogleUser: '/auth/sign-in/google',
    },
    UserController: {
      createUser: '/users/create',
      deleteMe: '/users/delete',
      deleteUser: '/users/delete',
      getEnabledUsers: '/users/',
      getUserInformation: '/users/info',
      toggleAdminStatus: '/users/admin-toggle',
    },
  },
};
