export const environment = {
  production: false,
  //
  apiVersion: '/v1',
  apiUrl: 'https://localhost:8443/api',
  environment: 'local',
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
    },
  },
};
