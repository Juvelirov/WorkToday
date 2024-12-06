import { type UserDTO, apiClient } from "@/api/apiClient";
import { cn } from "@/lib/utils";
import type { s } from "@/types";
import { useState } from "react";
import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";

export function SignUpForm() {
  const [formData, setFormData] = useState<UserDTO>({
    fio: "Иванов Иван Иванович",
    login: "ivanov@yandex.ru",
    password: "qwerty1",
    email: "ivanov@yandex.ru",
    role: "student",
  });
  const [error, setError] = useState<s | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleRoleChange = (role: UserDTO["role"]) =>
    setFormData((prevData) => ({ ...prevData, role }));

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    const result = await apiClient<UserDTO>("/public/registration", {
      method: "POST",
      body: JSON.stringify(formData),
    });

    console.log("Registration successful:", result);
  };

  return (
    <div className="flex flex-col justify-center items-center h-screen">
      <form
        onSubmit={handleSubmit}
        className="flex flex-col w-[446px] bg-gray-500 p-8 rounded-3xl"
      >
        <RoleSelection
          selectedRole={formData.role}
          onRoleChange={handleRoleChange}
        />
        <div className="flex flex-col gap-6 mb-8">
          <Input
            placeholder="ФИО"
            name="fio"
            value={formData.fio}
            onChange={handleChange}
          />
          <Input
            type="email"
            placeholder="Логин"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
          <Input
            type="password"
            placeholder="Пароль"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
        </div>
        <div className="flex mb-8">
          <input type="checkbox" className="mr-2" defaultChecked required />
          <p className="text-sm">
            Я принимаю условия использования и политику конфиденциальности
          </p>
        </div>
        {error && <p className="text-red-500 text-sm">{error}</p>}
        <Button type="submit" className="bg-purple-500 w-full rounded-xl">
          Зарегистрироваться
        </Button>
      </form>
    </div>
  );
}

interface RoleSelectionProps {
  selectedRole: UserDTO["role"];
  onRoleChange: (role: UserDTO["role"]) => void;
}

function RoleSelection({ selectedRole, onRoleChange }: RoleSelectionProps) {
  return (
    <div className="flex justify-center mb-8">
      <div className="flex gap-1 p-[6px] bg-white rounded-md">
        <p
          className={cn(
            "p-1 rounded-md cursor-pointer",
            selectedRole === "student" && "bg-gray-500"
          )}
          onClick={() => onRoleChange("student")}
          onKeyUp={(e) => {
            if (e.key === "Enter" || e.key === " ") onRoleChange("student");
          }}
        >
          Практикант
        </p>
        <p
          className={cn(
            "p-1 rounded-md cursor-pointer",
            selectedRole === "hr" && "bg-gray-500"
          )}
          onClick={() => onRoleChange("hr")}
          onKeyUp={(e) => {
            if (e.key === "Enter" || e.key === " ") onRoleChange("hr");
          }}
        >
          Работодатель
        </p>
      </div>
    </div>
  );
}