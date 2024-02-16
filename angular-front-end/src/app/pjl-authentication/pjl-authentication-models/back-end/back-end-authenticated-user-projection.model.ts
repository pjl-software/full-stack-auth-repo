export interface BackEndAuthenticatedUserProjection {
  firstName: string;
  lastName: string;
  username: string;
  photoUrl: string;
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
    photoUrl: '',
    roleNames: [],
    isAuthenticated: false,
    isNotAuthenticated: true,
    isAdmin: false,
    isNotAdmin: true,
  };
