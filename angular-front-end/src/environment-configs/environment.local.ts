export const environment = {
  production: false,
  //
  apiVersion: '/v1',
  apiUrl: 'https://localhost:8443/api',
  environment: 'local',
  backEndControllerPaths: {
    UserController: {
      createGoogleUser: '/users/create/google',
      createUser: '/users/create',
      deleteUser: '/users/delete',
      getEnabledUsers: '/users/',
    },
  },
};
