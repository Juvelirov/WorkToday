import type { s, v } from "@/types";
import { apiClient } from "./apiClient";
import type { UserDTO, UserInfoDTO } from "./apiTypes";
import { endpoints } from "./endpoints";

export async function fetchAllUsers() {
  return apiClient<UserInfoDTO[]>(endpoints.private.admin.allUsers, {
    method: "GET",
  });
}

export async function fetchUser(login: s) {
  return apiClient<UserInfoDTO>(`/api/v1/private/admin/user/${login}`, {
    method: "GET",
  });
}

export async function updateUser(user: UserDTO) {
  return apiClient<v>(endpoints.private.admin.updateUser, {
    method: "PUT",
    body: JSON.stringify(user),
  });
}

export async function deleteUser(login: s) {
  return apiClient<v>(endpoints.private.admin.deleteUser, {
    method: "DELETE",
    body: JSON.stringify({ login }),
  });
}

// updateUser: `${BASE_PATHS.private.admin}/update-user`,
// deleteUser: `${BASE_PATHS.private.admin}/delete-user`,
// getUser: (login) => `${BASE_PATHS.private.admin}/user/${login}`,
