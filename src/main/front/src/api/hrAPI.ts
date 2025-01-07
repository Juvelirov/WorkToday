import { n, v } from "@/types";
import { apiClient } from "./apiClient";
import { InternshipInfoDTO } from "./apiTypes";
import { endpoints } from "./endpoints";

export async function createInternship(internship: InternshipInfoDTO) {
  return apiClient<v>(endpoints.private.hr.internships.create, {
    method: "POST",
    body: JSON.stringify(internship),
    headers: { "Content-Type": "application/json" },
  });
}

export async function updateInternship(id: n, internship: InternshipInfoDTO) {
  return apiClient<v>(endpoints.private.hr.internships.update(id), {
    method: "PUT",
    body: JSON.stringify(internship),
    headers: { "Content-Type": "application/json" },
  });
}

export async function deleteInternship(id: n) {
  return apiClient<v>(endpoints.private.hr.internships.delete(id), {
    method: "DELETE",
  });
}
