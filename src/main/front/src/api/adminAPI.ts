import { apiClient } from "./apiClient";
import type { UsersInfoDTO } from "./apiTypes";
import { endpoints } from "./endpoints";

export async function fetchAllUsers() {
  return apiClient<UsersInfoDTO[]>(endpoints.private.admin.allUsers, {
    method: "GET",
  });
}

// export async function deleteUser(login: s) {
//   return apiClient<v>(endpoints.private.admin.deleteUser, {
//     method: "DELETE",
//     body: JSON.stringify({ login }),
//   });
// }

// export async function updateUser(user: User) {
//   return apiClient<v>(endpoints.private.admin.updateUser, {
//     method: "PUT",
//     body: JSON.stringify(user),
//   });
// }
