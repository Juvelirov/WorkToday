import type { s } from "@/types";

export const API_BASE_URL = "http://localhost:8080/api/v1";

const BASE_PATHS = {
  public: "/public",
  private: {
    admin: "/private/admin",
    intern: "/private/student",
    hr: "/private/hr",
  },
};

export const endpoints = {
  public: {
    registration: `${BASE_PATHS.public}/registration`,
    login: `${BASE_PATHS.public}/login`,
    enroll: (id: s) => `${BASE_PATHS.public}/${id}/enroll`,
  },

  private: {
    admin: {
      allUsers: `${BASE_PATHS.private.admin}/all-users`,
      updateUser: `${BASE_PATHS.private.admin}/update-user`,
      deleteUser: `${BASE_PATHS.private.admin}/delete-user`,
      getUser: (login: s) => `${BASE_PATHS.private.admin}/user/${login}`,
    },

    intern: {
      internships: {
        all: `${BASE_PATHS.private.intern}/interships`,
        filtered: `${BASE_PATHS.private.intern}/interships/search`,
        byId: (id: s) => `${BASE_PATHS.private.intern}/interships/${id}`,
      },
      tasks: {
        all: `${BASE_PATHS.private.intern}/task/my-tasks`,
        byId: (id: s) => `${BASE_PATHS.private.intern}/task/my-tasks/${id}`,
        start: (id: s) => `${BASE_PATHS.private.intern}/task/start-task/${id}`,
        complete: (id: s) =>
          `${BASE_PATHS.private.intern}/task/complete-task/${id}`,
      },
      portfolio: {
        my: `${BASE_PATHS.private.intern}/portfolio/my-portfolio`,
        create: `${BASE_PATHS.private.intern}/portfolio/create`,
        get: (email: s, id: s) =>
          `${BASE_PATHS.private.intern}/portfolio/${email}/${id}`,
        getAll: (email: s) => `${BASE_PATHS.private.intern}/portfolio/${email}`,
        delete: (id: s) =>
          `${BASE_PATHS.private.intern}/portfolio/delete/${id}`,
      },

      resume: {
        my: `${BASE_PATHS.private.intern}/resume/my-resume`,
        create: `${BASE_PATHS.private.intern}/resume/create`,
        get: (email: s, id: s) =>
          `${BASE_PATHS.private.intern}/resume/${email}/${id}`,
        getAll: (email: s) => `${BASE_PATHS.private.intern}/resume/${email}`,
        delete: (id: s) => `${BASE_PATHS.private.intern}/resume/delete/${id}`,
      },
      profiles: {
        my: `${BASE_PATHS.private.intern}/profiles/my-profile`,
        all: `${BASE_PATHS.private.intern}/profiles`,
        save: `${BASE_PATHS.private.intern}/profiles/my-profile/set-data`,
        get: (email: s) => `${BASE_PATHS.private.intern}/profiles/${email}`,
      },
    },

    hr: {
      internships: {
        create: `${BASE_PATHS.private.hr}/interships/create`,
        update: (id: s) => `${BASE_PATHS.private.hr}/interships/update/${id}`,
        delete: (id: s) => `${BASE_PATHS.private.hr}/interships/delete/${id}`,
      },
      task: {
        assign: (internshipId: s, internId: s) =>
          `${BASE_PATHS.private.hr}/task/create/${internshipId}/${internId}`,
        huh: (email: s, id: s) =>
          `${BASE_PATHS.private.hr}/task/${email}/${id}`,
        bruh: (email: s) => `${BASE_PATHS.private.hr}/task/${email}`,
        aah: (email: s, id: s) =>
          `${BASE_PATHS.private.hr}/task/delete/${email}/${id}`,
        checkhuh: (email: s, id: s) =>
          `${BASE_PATHS.private.hr}/task/check/${email}/${id}`,
      },

      result: {
        getResult: (internshipId: s, email: s) =>
          `${BASE_PATHS.private.hr}/result/create/${internshipId}/${email}`,
      },
    },
  },
};
