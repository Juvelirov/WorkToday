import type { InternshipInfoResponse, UserDTO } from "@/api/apiTypes";
import { clsx, type ClassValue } from "clsx";
import { twMerge } from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export const fakeAdminD: UserDTO = {
  role: "ROLE_ADMIN",
  fio: "Шарифов Максим Максимович",
  email: "max@yandex.ru",
  password: "admin123",
};

export const fakeInternD: UserDTO = {
  role: "ROLE_STUDENT",
  fio: "Макаров Алексей Алексеевич",
  email: "alek@yandex.ru",
  password: "UHU123",
};

export const fakeHrD: UserDTO = {
  role: "ROLE_HR",
  fio: "Сергеев Сергей Сергеевич",
  email: "serGEY@yandex.ru",
  password: "hr123",
};

export const fakeInternshipD: InternshipInfoResponse[] = [
  {
    id: 123,
    title: "Стажировка в отделе разработки веб-приложений",
    description:
      "Приглашаем студентов на летнюю стажировку в нашу компанию. Вы будете работать над реальными проектами под руководством опытных разработчиков.",
    fields: "Веб-разработка, Frontend, Backend",
    tags: ["JavaScript", "React", "Node.js", "HTML", "CSS"],
    creator: {
      fio: "Иванов Иван Иванович",
      email: "ivanov@company.com",
      company: "ТехноВижн",
    },
    city: "Москва",
  },
  {
    id: 456,
    title: "Стажировка в отделе Data Science",
    description:
      "Ищете возможности применить свои знания в области машинного обучения? Присоединяйтесь к нашей команде и работайте над интересными проектами по анализу данных.",
    fields: "Data Science, Machine Learning, Python",
    tags: ["Python", "Pandas", "NumPy", "TensorFlow"],
    creator: {
      fio: "Петрова Анна Сергеевна",
      email: "petrova@company.com",
      company: "Инноватек",
    },
    city: "Екатеринбург",
  },
  {
    id: 789,
    title: "Стажировка в отделе мобильной разработки",
    description:
      "Хотите создавать приложения для мобильных устройств? У нас вы получите возможность разрабатывать приложения для iOS и Android.",
    fields: "Мобильная разработка, iOS, Android",
    tags: ["Swift", "Kotlin", "React Native", "Flutter"],
    creator: {
      fio: "Сидоров Михаил Владимирович",
      email: "sidorov@company.com",
      company: "Цифроград",
    },
    city: "Новосибирск",
  },
];
