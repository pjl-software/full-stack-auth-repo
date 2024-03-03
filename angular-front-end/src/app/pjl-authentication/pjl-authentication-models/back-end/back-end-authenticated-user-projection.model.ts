export interface BackEndAuthenticatedUserProjection {
  firstName: string;
  lastName: string;
  username: string;
  photoUrl: string;
  lastLogin: Date;
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
    lastLogin: new Date(),
    roleNames: [],
    isAuthenticated: false,
    isNotAuthenticated: true,
    isAdmin: false,
    isNotAdmin: true,
  };
