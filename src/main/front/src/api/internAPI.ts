import { apiClient } from "./apiClient";
import type { InternshipInfoResponse } from "./apiTypes";
import { endpoints } from "./endpoints";

export async function fetchInternships() {
  return apiClient<InternshipInfoResponse[]>(
    endpoints.private.intern.internships.all,
    { method: "GET" }
  );
}

// export async function fetchInternshipDetails(id: s) {
//   return apiClient<InternshipDetails>(
//     endpoints.private.student.internshipById(id)
//   );
// }

// export async function fetchMyTasks() {
//   return apiClient<Task[]>(endpoints.private.student.myTasks);
// }

// export async function startTask(taskId: s) {
//   return apiClient<v>(endpoints.private.student.startTask(taskId), {
//     method: "POST",
//   });
// }
