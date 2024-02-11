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
