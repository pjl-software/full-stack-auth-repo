import { RoleDto } from './role-dto.model';

export interface BackEndUserDto {
  firstName: string;
  lastName: string;
  username: string;
  roles: RoleDto[];
}
