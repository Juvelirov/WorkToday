import { apiClient } from "./apiClient";
import { endpoints } from "./endpoints";
import type {
  EnrollResponse,
  InternshipInfoResponse,
  UpdateProfile,
  UsersInfoResponse,
} from "./apiTypes";
import { n, P, s, v } from "@/types";

// Profile
export async function fetchMyProfile() {
  return apiClient<UsersInfoResponse>(endpoints.private.intern.profiles.my, {
    method: "GET",
  });
}

export async function updateProfile(data: UpdateProfile): P<v> {
  const formData = new FormData();

  formData.append("fio", data.fio);
  formData.append("phoneNumber", data.phoneNumber);
  formData.append("town", data.town);
  if (data.avatar) formData.append("avatar", data.avatar);

  return apiClient(endpoints.private.intern.profiles.save, {
    method: "POST",
    body: formData,
  });
}

export async function deleteProfile(): P<v> {
  return apiClient(endpoints.private.intern.profiles.delete, {
    method: "DELETE",
  });
}

export async function fetchAnotherProfile(email: s) {
  return apiClient<UsersInfoResponse>(
    endpoints.private.intern.profiles.get(email),
    { method: "GET" }
  );
}

// Portfolio
export async function createPortfolio(
  portfolio: File | undefined,
  url: s
): P<v> {
  const formData = new FormData();
  if (portfolio) formData.append("filePath", portfolio);
  formData.append("url", url);

  return apiClient(endpoints.private.intern.portfolio.create, {
    method: "POST",
    body: formData,
  });
}

export async function deletePortfolio(id: n): P<v> {
  return apiClient(endpoints.private.intern.portfolio.delete(id), {
    method: "DELETE",
  });
}

// Resume
export async function createResume(resume: File | undefined, url: s): P<v> {
  const formData = new FormData();
  if (resume) formData.append("filePath", resume);
  formData.append("url", url);

  return apiClient(endpoints.private.intern.resume.create, {
    method: "POST",
    body: formData,
  });
}

export async function deleteResume(id: n): P<v> {
  return apiClient(endpoints.private.intern.resume.delete(id), {
    method: "DELETE",
  });
}

// Report
export async function createReport(internshipId: s, file: File): P<v> {
  const formData = new FormData();
  formData.append("filePath", file);

  return apiClient(endpoints.private.intern.report.create(internshipId), {
    method: "POST",
    body: formData,
  });
}

export async function deleteReport(internshipId: s): P<v> {
  return apiClient(endpoints.private.intern.report.delete(internshipId), {
    method: "DELETE",
  });
}

// Internships
export async function fetchInternships(): P<InternshipInfoResponse[]> {
  return apiClient<InternshipInfoResponse[]>(
    endpoints.private.intern.internships.all,
    { method: "GET" }
  );
}

export async function searchInternships(query: s): P<InternshipInfoResponse[]> {
  return apiClient<InternshipInfoResponse[]>(
    endpoints.private.intern.internships.filtered,
    {
      method: "GET",
      queryParams: { query },
    }
  );
}

export async function fetchInternship(id: n): P<InternshipInfoResponse> {
  return apiClient<InternshipInfoResponse>(
    endpoints.private.intern.internships.byId(id),
    { method: "GET" }
  );
}

// Enroll
export async function enroll(id: n) {
  return apiClient<v>(endpoints.public.enroll(id), {
    method: "POST",
  });
}

export async function cancelEnroll(id: n) {
  return apiClient<v>(endpoints.public.cancel(id), {
    method: "DELETE",
  });
}

export async function fetchMyEnrolls() {
  return apiClient<EnrollResponse[]>(endpoints.public.myEnrolls, {
    method: "GET",
  });
}
