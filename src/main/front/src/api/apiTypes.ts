/* eslint-disable @typescript-eslint/no-unused-vars */
import type { b, n, s, ss } from "@/types";

// For admin
export interface UserInfoDTO {
  name: s;
  surname: s;
  patronymic: s;
  phoneNumber: s;
  town: s;
}

interface Users {
  name: s;
  surname: s;
  patronymic: s;
  phoneNumber: s;
  town: s;
}

export interface UserDTO {
  fio: s;
  password: s;
  email: s;
  role: s;
}

// student/internships!
export interface InternshipInfoResponse {
  id: n;
  title: s;
  description: s;
  fields: s;
  tags: s[];
  creator: UserResponse;
  city: s;
}
type UserResponse = { fio: s; email: s; company: s };

// hr/internships
export interface InternshipInfoDTO {
  title: s;
  company: s;
  duties: s;
  requirements: s;
  task: s;
  town: s;
  fields: s;
  tags: s[];
}

// student/task
// TaskResponse
interface TaskResponse2 {
  id: n;
  userInfoId: n;
  intershipId: n;
  title: s;
  info: s;
  deadline: s; // ISO 8601 date-time string
  url: s;
  filePath: s;
  status: "PENDING" | "IN_PROGRESS" | "COMPLETED" | "FAILED";
  grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
  result: s;
}
interface TaskCompleteDTO {
  result: s; // Example: "Ссылка на гитхаб или подобное, валидации нет"
}

// hr/task
interface TaskDTO3 {
  title: s; // Example: "Задание по программированию"
  info: s; // Example: "Создать приложение на Java."
  deadline: s; // Example: "2024-12-10T10:00:00Z"
  url: s; // Example: "http://example.com/task"
  filePath: s; // Example: "/files/task.pdf"
  result: s; // Example: "Задание выполнено успешно."
}

interface TaskResponse5 {
  id: n;
  userInfoId: n;
  intershipId: n;
  title: s;
  info: s;
  deadline: s;
  url: s;
  filePath: s;
  status: "PENDING" | "IN_PROGRESS" | "COMPLETED" | "FAILED";
  grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
  result: s;
}

interface TaskResponse6 {
  id: n;
  userInfoId: n;
  intershipId: n;
  title: s;
  info: s;
  deadline: s;
  url: s;
  filePath: s;
  status: "PENDING" | "IN_PROGRESS" | "COMPLETED" | "FAILED";
  grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
  result: s;
}

interface CheckTaskDTO {
  grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
}

// student/portfolio
interface PortfolioDTO {
  title: s;
  description: s;
  filePath: s;
  url: s;
}

// interface MyPortfolioResponse {
//   id: n;
//   userId: n;
//   title: s;
//   description: s;
//   url: s;
//   filePath: s;
//   uploadDate: s;
// }

// export type MyPortfolioResponseList = MyPortfolioResponse[];

interface ResumeDTO {
  url: s;
  filePath: s;
}

interface ResultDTO {
  mark: n;
  recommendation: b;
  report: ReportDTO;
}

interface ReportDTO {
  title: s;
  description: s;
}

interface ResumeResponseHUH {
  id: n;
  userId: n;
  url: s;
  filePath: s;
  uploadDate: s;
}

type ResumeResponseList = ResumeResponseHUH[];

// student/profiles
// UsersInfoDTO

export interface UsersInfoResponse {
  id: n;
  email: s;
  fio: s;
  recommendation: b;
  phoneNumber: s;
  town: s;
  avatarPath: s;
  portfolio: PortfolioResponse | undefined;
  resume: ResumeResponse;
  reports: ReportResponse[];
}

export type PortfolioResponse = {
  id: n;
  userId: n;
  filePath: s;
  url: s;
  uploadDate: s;
};

export interface UpdateProfile {
  fio: s;
  phoneNumber: s;
  town: s;
  avatar: File | undefined;
}

export interface ResumeResponse {
  id: n;
  userId: n;
  url: s;
  filePath: s;
  uploadDate: s;
}

export interface FileResponse {
  id: n;
  userId: n;
  filePath: s;
  uploadDate: s;
}

type TaskResponse = {
  id: n;
  userInfoId: n;
  intershipId: n;
  title: s;
  info: s;
  deadline: s;
  url: s;
  filePath: s;
  status: "PENDING" | "IN_PROGRESS" | "COMPLETED" | "FAILED";
  grade: "A" | "B" | "C" | "D" | "F" | "NOT_GRADED";
  result: s;
};
type ReportResponse = { title: s; description: s };

export interface Endpoints {
  public: {
    cancel: (internshipId: s) => s;
    myEnrolls: s;
    registration: s;
    login: s;
    enroll: (id: s) => s;
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
        byId: (id: s) => s;
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
        setResult: (internshipResultId: s) => s;
      };

      enrolls: {
        get: s;
        accept: (username: s, enrollId: s) => s;
        reject: (username: s, enrollId: s) => s;
      };

      // task: {
      //   assign: (internshipId: s, internId: s) => s;
      //   huh: (email: s, id: s) => s;
      //   bruh: (email: s) => s;
      //   aah: (email: s, id: s) => s;
      //   checkhuh: (email: s, id: s) => s;
      // };

      result: {
        getResult: (internshipId: s, email: s) => s;
      };
    };
  };
}
