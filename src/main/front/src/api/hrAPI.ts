import { n, s, v } from "@/types";
import { apiClient } from "./apiClient";
import {
  AnalyticsResponse,
  EnrollResponse,
  InternshipInfoDTO,
  ResultDTO,
} from "./apiTypes";
import { endpoints } from "./endpoints";

// Internships
export async function createInternship(internship: InternshipInfoDTO) {
  return apiClient<{ ID: n; status: s }>(
    endpoints.private.hr.internships.create,
    {
      method: "POST",
      body: JSON.stringify(internship),
      headers: { "Content-Type": "application/json" },
    }
  );
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

// Analytics
export async function fetchAnalytics() {
  return apiClient<AnalyticsResponse[]>(endpoints.private.hr.analytics.get, {
    method: "GET",
  });
}

export async function setAnalyticsResult(internshipId: n, result: ResultDTO) {
  return apiClient<ResultDTO>(
    endpoints.private.hr.analytics.setResult(internshipId),
    {
      method: "POST",
      body: JSON.stringify({ result }),
      headers: { "Content-Type": "application/json" },
    }
  );
}

// Enrolls
export async function fetchEnrolls() {
  return apiClient<EnrollResponse[]>(endpoints.private.hr.enrolls.get, {
    method: "GET",
  });
}

export async function acceptEnroll(internEmail: s, enrollId: n) {
  return apiClient<v>(
    endpoints.private.hr.enrolls.accept(internEmail, enrollId),
    { method: "POST" }
  );
}

export async function rejectEnroll(internEmail: s, enrollId: n) {
  return apiClient<v>(
    endpoints.private.hr.enrolls.reject(internEmail, enrollId),
    { method: "POST" }
  );
}
