export const environment = {
  production: true,
  //
  apiVersion: '/v1',
  apiUrl: 'https://api.demo.pjlindustries.com',
  appUrl: 'https://demo.pjlindustries.com',
  environment: 'prod',
  backEndControllerPaths: {
    AuthController: {
      createGoogleUser: '/auth/sign-in/google',
    },
    UserController: {
      createUser: '/users/create',
      deleteUser: '/users/delete',
      getEnabledUsers: '/users/',
      getUserInformation: '/users/info',
    },
  },
};
