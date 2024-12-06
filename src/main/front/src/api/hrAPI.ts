// import type { s } from "@/types";
// import { apiClient } from "./apiClient";
// import { endpoints } from "./endpoints";

// export async function createInternship(internship: InternshipCreateDto) {
//   return apiClient<Internship>(endpoints.private.hr.createInternship, {
//     method: "POST",
//     body: JSON.stringify(internship),
//   });
// }

// export async function updateInternship(
//   id: s,
//   internship: InternshipUpdateDto
// ) {
//   return apiClient<Internship>(endpoints.private.hr.updateInternship(id), {
//     method: "PUT",
//     body: JSON.stringify(internship),
//   });
// }

// export async function deleteInternship(id: s) {
//   return apiClient<void>(endpoints.private.hr.deleteInternship(id), {
//     method: "DELETE",
//   });
// }
