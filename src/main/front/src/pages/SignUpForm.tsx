/* eslint-disable @typescript-eslint/no-unused-vars */
import { apiClient } from "@/api/apiClient";
import { cn, fakeAdminD, fakeHrD, fakeInternD } from "@/lib/utils";
import type { s, v } from "@/types";
import { useState } from "react";
import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";
import type { UserDTO } from "@/api/apiTypes";
import { EyeIcon, EyeClosedIcon } from "lucide-react";
import IB from "@/components/IB";

export function SignUpForm() {
  const [formData, setFormData] = useState<UserDTO>(fakeAdminD);
  const [error, setError] = useState<s | null>(null);
  const [showPassword, setShowPassword] = useState(false);

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
        className="flex flex-col w-[400px] bg-[#E4C1FF] p-8 rounded-3xl"
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
            placeholder="Почта"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
          <div className="relative">
            <Input
              type={showPassword ? "text" : "password"}
              placeholder="Пароль"
              name="password"
              value={formData.password}
              onChange={handleChange}
              className="pr-10"
            />
            <IB
              bC="absolute right-2 top-1"
              icon={showPassword ? EyeIcon : EyeClosedIcon}
              onClick={() => setShowPassword((prev) => !prev)}
            />
          </div>
        </div>
        <div className="flex mb-8">
          <input type="checkbox" className="mr-2" defaultChecked required />
          <p className="text-sm">
            Я принимаю условия использования и политику конфиденциальности
          </p>
        </div>
        {error && <p className="text-red-500 text-sm">{error}</p>}
        <Button type="submit" className="bg-[#8300E7] w-full rounded-xl">
          Зарегистрироваться
        </Button>
      </form>
    </div>
  );
}

interface RoleSelection {
  selectedRole: UserDTO["role"];
  onRoleChange: (role: UserDTO["role"]) => v;
}

function RoleSelection(p: RoleSelection) {
  const roles = [
    { id: "ROLE_STUDENT", label: "Практикант" },
    { id: "ROLE_HR", label: "Работодатель" },
  ];

  const handleKeyUp = (e: React.KeyboardEvent, role: UserDTO["role"]) => {
    if (e.key === "Enter" || e.key === " ") p.onRoleChange(role);
  };

  return (
    <div className="flex justify-center mb-8">
      <div className="flex gap-1 p-[6px] bg-white rounded-md">
        {roles.map((role) => (
          <button
            key={role.id}
            className={cn(
              "p-1 rounded-md",
              p.selectedRole === role.id && "bg-[#E4C1FF]"
            )}
            onClick={() => p.onRoleChange(role.id)}
            onKeyUp={(e) => handleKeyUp(e, role.id)}
            tabIndex={0}
          >
            {role.label}
          </button>
        ))}
      </div>
    </div>
  );
}
