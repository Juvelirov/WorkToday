import { apiClient, type UsersInfoDTO } from "./apiClient";
import { endpoints } from "./endpoints";

export async function fetchAllUsers() {
  return apiClient<UsersInfoDTO[]>(endpoints.private.admin.allUsers, {
    method: "GET",
  });
}

// export async function deleteUser(login: s) {
//   return apiClient<void>(endpoints.private.admin.deleteUser, {
//     method: "DELETE",
//     body: JSON.stringify({ login }),
//   });
// }

// export async function getUser(login: s) {
//   return apiClient<User>(endpoints.private.admin.getUser(login));
// }

// export async function updateUser(user: User) {
//   return apiClient<void>(endpoints.private.admin.updateUser, {
//     method: "PUT",
//     body: JSON.stringify(user),
//   });
// }