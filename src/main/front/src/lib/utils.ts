import type { UserDTO } from "@/api/apiTypes";
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
