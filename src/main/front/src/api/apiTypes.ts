/* eslint-disable @typescript-eslint/no-unused-vars */
import type { b, n, s, ss } from "@/types";

export interface InternshipInfoDTO {
  title: s;
  duties: s;
  requirements: s;
  task: s;
  company: s;
  town: s;
  tags: s[];
}

export interface UsersInfoResponse {
  id: n;
  email: s;
  fio: s;
  avatarPath: s;
  phoneNumber: s;
  town: s;
  recommendation: b;
  internships: InternshipInfoResponse[];
  internshipStatus: InternshipStatusResponse[];
  portfolio: FileResponse | undefined;
  resume: FileResponse;
  reports: ReportResponse[];
}

export interface UpdateProfile {
  fio: s;
  phoneNumber: s;
  town: s;
  avatar: File | undefined;
}

type ReportResponse = { title: s; description: s };

export interface FileResponse {
  id: n;
  userId: n;
  filePath: s;
  uploadDate: s;
}

export interface InternshipInfoResponse {
  id: n;
  title: s;
  company: s;
  duties: s;
  requirements: s;
  task: s;
  town: s;
  tags: s[];
  creator: UserResponse;
}
type UserResponse = { id: n; fio: s; email: s; avatarPath: s };

export interface InternshipStatusResponse {
  id: n;
  status: "IN_PROGRESS" | "COMPLETED" | "REVIEW" | "NOT_STARTED" | "REWORK";
  mark: n;
  recommendation: b;
}

export interface AnalyticsResponse {
  id: n;
  fio: s;
  status: "IN_PROGRESS" | "COMPLETED" | "REVIEW" | "NOT_STARTED" | "REWORK";
  mark: n;
  recommendation: b;
  userInfo: UsersInfoResponse;
}

export interface EnrollResponse {
  enrollId: n;
  internshipId: n;
  userInfo: UsersInfoResponse;
  enrollStatus: "PENDING" | "ACCEPTED" | "REJECTED";
}

export interface ResultDTO {
  status: "IN_PROGRESS" | "COMPLETED" | "REVIEW" | "NOT_STARTED" | "REWORK";
  mark: n;
  recommendation: b;
}

export interface UserDTO {
  fio: s;
  password: s;
  email: s;
  role: s;
}

interface Users {
  name: s;
  surname: s;
  patronymic: s;
  phoneNumber: s;
  town: s;
}

interface UserInfoDTO {
  name: s;
  surname: s;
  patronymic: s;
  phoneNumber: s;
  town: s;
}

interface PortfolioDTO {
  title: s;
  description: s;
  filePath: s;
}

interface ResumeDTO {
  filePath: s;
}

interface ReportDTO {
  title: s;
  description: s;
}

// export type PortfolioResponse = {
//   id: n;
//   userId: n;
//   filePath: s;
//   uploadDate: s;
// };

// export interface ResumeResponse {
//   id: n;
//   userId: n;
//   filePath: s;
//   uploadDate: s;
// }

// type TaskResponse = {
//   id: n;
//   userInfoId: n;
//   intershipId: n;
//   title: s;
//   info: s;
//   deadline: s;
//   url: s;
//   filePath: s;
//   status: "PENDING" | "IN_PROGRESS" | "COMPLETED" | "FAILED";
//   grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
//   result: s;
// };

// student/task
// TaskResponse
// interface TaskResponse2 {
//   id: n;
//   userInfoId: n;
//   intershipId: n;
//   title: s;
//   info: s;
//   deadline: s; // ISO 8601 date-time string
//   url: s;
//   filePath: s;
//   status: "PENDING" | "IN_PROGRESS" | "COMPLETED" | "FAILED";
//   grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
//   result: s;
// }
// interface TaskCompleteDTO {
//   result: s; // Example: "Ссылка на гитхаб или подобное, валидации нет"
// }

// // hr/task
// interface TaskDTO3 {
//   title: s; // Example: "Задание по программированию"
//   info: s; // Example: "Создать приложение на Java."
//   deadline: s; // Example: "2024-12-10T10:00:00Z"
//   url: s; // Example: "http://example.com/task"
//   filePath: s; // Example: "/files/task.pdf"
//   result: s; // Example: "Задание выполнено успешно."
// }

// interface TaskResponse5 {
//   id: n;
//   userInfoId: n;
//   intershipId: n;
//   title: s;
//   info: s;
//   deadline: s;
//   url: s;
//   filePath: s;
//   status: "PENDING" | "IN_PROGRESS" | "COMPLETED" | "FAILED";
//   grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
//   result: s;
// }

// interface TaskResponse6 {
//   id: n;
//   userInfoId: n;
//   intershipId: n;
//   title: s;
//   info: s;
//   deadline: s;
//   url: s;
//   filePath: s;
//   status: "PENDING" | "IN_PROGRESS" | "COMPLETED" | "FAILED";
//   grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
//   result: s;
// }

// interface CheckTaskDTO {
//   grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
// }

// student/portfolio

// interface MyPortfolioResponse {
//   id: n;
//   userId: n;
//   title: s;
//   description: s;
//   filePath: s;
//   uploadDate: s;
// }

// export type MyPortfolioResponseList = MyPortfolioResponse[];

// interface ResumeResponseHUH {
//   id: n;
//   userId: n;
//   url: s;
//   filePath: s;
//   uploadDate: s;
// }

// type ResumeResponseList = ResumeResponseHUH[];

// student/profiles
// UsersInfoDTO

export interface Endpoints {
  public: {
    cancel: (internshipId: n) => s;
    myEnrolls: s;
    registration: s;
    login: s;
    enroll: (internshipId: n) => s;
  };

  private: {
    admin: {
      allUsers: s;
      updateUser: s;
      deleteUser: s;
      getUser: (login: s) => s;
    };

    intern: {
      internships: {
        all: s;
        filtered: s;
        byId: (id: n) => s;
      };

      report: {
        create: (internshipId: s) => s;
        delete: (internshipId: s) => s;
      };

      portfolio: {
        // my: s;
        create: s;
        get: (email: s, id: s) => s;
        getAll: (email: s) => s;
        delete: (id: n) => s;
      };

      resume: {
        // my: s;
        create: s;
        get: (email: s, id: s) => s;
        getAll: (email: s) => s;
        delete: (id: n) => s;
      };

      profiles: {
        my: s;
        all: s;
        save: s;
        delete: s;
        get: (email: s) => s;
      };
    };

    hr: {
      internships: {
        create: s;
        update: (id: n) => s;
        delete: (id: n) => s;
      };

      analytics: {
        get: s;
        setResult: (internshipResultId: n) => s;
      };

      enrolls: {
        get: s;
        accept: (username: s, enrollId: n) => s;
        reject: (username: s, enrollId: n) => s;
      };

      result: {
        getResult: (internshipId: s, email: s) => s;
      };
    };
  };
}
