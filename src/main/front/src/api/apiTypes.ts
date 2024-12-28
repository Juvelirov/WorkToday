/* eslint-disable @typescript-eslint/no-unused-vars */
import type { b, n, s } from "@/types";

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
interface IntershipInfoDTO {
  title: s;
  description: s;
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

interface MyPortfolioResponse {
  id: n;
  userId: n;
  title: s;
  description: s;
  url: s;
  filePath: s;
  uploadDate: s;
}

type MyPortfolioResponseList = MyPortfolioResponse[];

// student/resume
interface ResumeDTO {
  url: s;
  filePath: s;
}

// hr/result
interface ResultDTO {
  mark: n; // double
  recommendation: b;
  report: ReportDTO;
}

interface ReportDTO {
  title: s;
  description: s;
}

// ResumeResponse
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

interface UsersInfoResponse {
  id: n;
  username: s;
  name: s;
  surname: s;
  patronymic: s;
  recommendations: s;
  phoneNumber: s;
  town: s;
  portfolios: PortfolioResponse;
  resumes: ResumeResponse;
  tasks: TaskResponse[];
  result: ReportResponse[];
}

type PortfolioResponse = {
  id: n;
  userId: n;
  title: s;
  description: s;
  filePath: s;
  url: s;
  uploadDate: s; // ISO 8601 date-time format
};

type ResumeResponse = { id: n; userId: n; url: s; filePath: s; uploadDate: s };
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
      tasks: {
        all: s;
        byId: (id: s) => s;
        start: (id: s) => s;
        complete: (id: s) => s;
      };
      portfolio: {
        my: s;
        create: s;
        get: (email: s, id: s) => s;
        getAll: (email: s) => s;
        delete: (id: s) => s;
      };

      resume: {
        my: s;
        create: s;
        get: (email: s, id: s) => s;
        getAll: (email: s) => s;
        delete: (id: s) => s;
      };
      profiles: {
        my: s;
        all: s;
        save: s;
        get: (email: s) => s;
      };
    };

    hr: {
      internships: {
        create: s;
        update: (id: s) => s;
        delete: (id: s) => s;
      };
      task: {
        assign: (internshipId: s, internId: s) => s;
        huh: (email: s, id: s) => s;
        bruh: (email: s) => s;
        aah: (email: s, id: s) => s;
        checkhuh: (email: s, id: s) => s;
      };

      result: {
        getResult: (internshipId: s, email: s) => s;
      };
    };
  };
}
