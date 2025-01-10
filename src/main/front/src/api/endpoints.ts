import type { Endpoints } from "./apiTypes";

export const API_BASE_URL = "http://localhost:8080/api/v1";

const BASE_PATHS = {
  public: "/public",
  private: {
    admin: "/private/admin",
    intern: "/private/student",
    hr: "/private/hr",
  },
};

export const endpoints: Endpoints = {
  public: {
    cancel: (internshipId) => `${BASE_PATHS.public}/${internshipId}/cancel`,
    myEnrolls: `${BASE_PATHS.public}/my-enrolls`,
    registration: `${BASE_PATHS.public}/registration`,
    login: `${BASE_PATHS.public}/login`,
    enroll: (internshipId) => `${BASE_PATHS.public}/${internshipId}/enroll`,
  },

  private: {
    admin: {
      allUsers: `${BASE_PATHS.private.admin}/all-users`,
      updateUser: `${BASE_PATHS.private.admin}/update-user`,
      deleteUser: `${BASE_PATHS.private.admin}/delete-user`,
      getUser: (login) => `${BASE_PATHS.private.admin}/user/${login}`,
    },

    intern: {
      internships: {
        all: `${BASE_PATHS.private.intern}/interships`,
        filtered: `${BASE_PATHS.private.intern}/interships/search`,
        byId: (id) => `${BASE_PATHS.private.intern}/interships/${id}`,
      },

      report: {
        create: (internshipId) =>
          `${BASE_PATHS.private.intern}/report/create/${internshipId}`,
        delete: (internshipId) =>
          `${BASE_PATHS.private.intern}/report/delete-report/${internshipId}`,
      },

      portfolio: {
        // my: `${BASE_PATHS.private.intern}/portfolio/my-portfolio`,
        create: `${BASE_PATHS.private.intern}/portfolio/create`,
        get: (email, id) =>
          `${BASE_PATHS.private.intern}/portfolio/${email}/${id}`,
        getAll: (email) => `${BASE_PATHS.private.intern}/portfolio/${email}`,
        delete: (id) => `${BASE_PATHS.private.intern}/portfolio/delete/${id}`,
      },

      resume: {
        // my: `${BASE_PATHS.private.intern}/resume/my-resume`,
        create: `${BASE_PATHS.private.intern}/resume/create`,
        get: (email, id) =>
          `${BASE_PATHS.private.intern}/resume/${email}/${id}`,
        getAll: (email) => `${BASE_PATHS.private.intern}/resume/${email}`,
        delete: (id) => `${BASE_PATHS.private.intern}/resume/delete/${id}`,
      },
      profiles: {
        my: `${BASE_PATHS.private.intern}/profiles/my-profile`,
        all: `${BASE_PATHS.private.intern}/profiles`,
        save: `${BASE_PATHS.private.intern}/profiles/my-profile/set-data`,
        delete: `${BASE_PATHS.private.intern}/profiles/delete-account`,
        get: (email) => `${BASE_PATHS.private.intern}/profiles/${email}`,
      },
    },

    hr: {
      internships: {
        create: `${BASE_PATHS.private.hr}/interships/create`,
        update: (id) => `${BASE_PATHS.private.hr}/interships/update/${id}`,
        delete: (id) => `${BASE_PATHS.private.hr}/interships/delete/${id}`,
      },

      analytics: {
        get: `${BASE_PATHS.private.hr}/analytics`,
        setResult: (internshipResultId) =>
          `${BASE_PATHS.private.hr}/analytics/set-result/${internshipResultId}`,
      },

      enrolls: {
        get: `${BASE_PATHS.private.hr}/enrolls`,
        accept: (username, enrollId) =>
          `${BASE_PATHS.private.hr}/enrolls/accept/${username}/${enrollId}`,
        reject: (username, enrollId) =>
          `${BASE_PATHS.private.hr}/enrolls/reject/${username}/${enrollId}`,
      },

      // task: {
      //   assign: (internshipId, internId) =>
      //     `${BASE_PATHS.private.hr}/task/create/${internshipId}/${internId}`,
      //   huh: (email, id) => `${BASE_PATHS.private.hr}/task/${email}/${id}`,
      //   bruh: (email) => `${BASE_PATHS.private.hr}/task/${email}`,
      //   aah: (email, id) =>
      //     `${BASE_PATHS.private.hr}/task/delete/${email}/${id}`,
      //   checkhuh: (email, id) =>
      //     `${BASE_PATHS.private.hr}/task/check/${email}/${id}`,
      // },

      result: {
        getResult: (internshipId, email) =>
          `${BASE_PATHS.private.hr}/result/create/${internshipId}/${email}`,
      },
    },
  },
};
