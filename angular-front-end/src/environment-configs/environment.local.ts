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
      deleteUser: '/users/delete',
      getEnabledUsers: '/users/',
    },
  },
};
