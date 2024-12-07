import type { s } from "@/types";

export const API_BASE_URL = "http://localhost:8080/api/v1";

export const endpoints = {
  public: {
    registration: "/public/registration",
    login: "/public/login",
    enroll: (id: s) => `/public/${id}/enroll`,
  },
  private: {
    admin: {
      deleteUser: "/private/admin/delete-user",
      allUsers: "/private/admin/all-users",
      getUser: (login: s) => `/private/admin/user/${login}`,
    },
    intern: {
      internships: "/private/student/internships",
      internshipById: (id: s) => `/private/student/internships/${id}`,
      tasks: "/private/student/task/my-tasks",
    },
    hr: {
      createInternship: "/private/hr/internships/create",
      updateInternship: (id: s) => `/private/hr/internships/update/${id}`,
    },
  },
};
