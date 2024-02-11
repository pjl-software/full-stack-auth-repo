export interface FrontEndUserDto {
  firstName: string;
  lastName: string;
  username: string;
  roleNames: string[];
  isAuthenticated: boolean;
  isAdmin: boolean;
}
