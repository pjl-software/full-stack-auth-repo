export interface BackEndAuthenticatedUserProjection {
  firstName: string;
  lastName: string;
  username: string;
  roleNames: string[];
  isAuthenticated: boolean;
  isNotAuthenticated: boolean;
  isAdmin: boolean;
  isNotAdmin: boolean;
}

export const UnauthenticatedBackEndAuthenticatedUserProjection: BackEndAuthenticatedUserProjection =
  {
    firstName: '',
    lastName: '',
    username: '',
    roleNames: [],
    isAuthenticated: false,
    isNotAuthenticated: true,
    isAdmin: false,
    isNotAdmin: true,
  };
