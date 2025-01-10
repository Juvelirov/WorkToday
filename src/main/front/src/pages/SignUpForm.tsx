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
import { endpoints } from "@/api/endpoints";
import { Link, useNavigate } from "react-router-dom";
import { saveBasicInfo, setToken } from "@/auth";

export default function SignUpForm() {
  const [formData, setFormData] = useState<UserDTO>(fakeInternD);
  const [error, setError] = useState<s | null>(null);
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleRoleChange = (role: UserDTO["role"]) =>
    setFormData((prevData) => ({ ...prevData, role }));

  // const handleSubmit = async (e: React.FormEvent) => {
  //   e.preventDefault();
  //   setError(null);

  //   const result = await apiClient<UserDTO>(endpoints.public.registration, {
  //     method: "POST",
  //     body: JSON.stringify(formData),
  //     headers: {
  //       "Content-Type": "application/json",
  //     },
  //   });
  //   console.log("Registration successful:", result);
  // };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    try {
      await apiClient<UserDTO>(endpoints.public.registration, {
        method: "POST",
        body: JSON.stringify(formData),
        headers: {
          "Content-Type": "application/json",
        },
      });

      console.log("Registration successful");

      const loginRes = await apiClient<{ token: s; role: s[] }>(
        endpoints.public.login,
        {
          method: "POST",
          body: JSON.stringify({
            email: formData.email,
            password: formData.password,
          }),
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      console.log("Login res", loginRes);

      saveBasicInfo(loginRes.token, loginRes.role[0]);

      switch (localStorage.getItem("role")) {
        case "ROLE_STUDENT":
          navigate("/internProfile");
          break;
        case "ROLE_HR":
          navigate("/hrProfile");
          break;
        case "ROLE_ADMIN":
          navigate("/admin");
          break;
        default:
          navigate("/");
          break;
      }
    } catch (err) {
      setError("Регистрация или авторизация не удалась. Попробуйте снова.");
      console.error("Error during registration or login:", err);
    }
  };

  return (
    <div className="flex flex-col justify-center items-center h-screen">
      <form
        onSubmit={handleSubmit}
        className="flex flex-col w-[420px] bg-[#E4C1FF] p-8 rounded-3xl"
      >
        <RoleSelection
          selectedRole={formData.role}
          onRoleChange={handleRoleChange}
        />
        <div className="flex flex-col gap-3 mb-6">
          <div>
            <p className="font-bold text-sm mb-2">ФИО</p>
            <Input
              name="fio"
              placeholder="Введите ваше ФИО"
              value={formData.fio}
              onChange={handleChange}
            />
          </div>
          <div>
            <p className="font-bold text-sm mb-2">Почта</p>
            <Input
              type="email"
              name="email"
              placeholder="Введите вашу почту"
              value={formData.email}
              onChange={handleChange}
            />
          </div>

          <div className="relative">
            <p className="font-bold text-sm mb-2">Пароль</p>
            <Input
              type={showPassword ? "text" : "password"}
              name="password"
              className="pr-10"
              placeholder="Придумайте пароль"
              value={formData.password}
              onChange={handleChange}
            />
            <IB
              bC="absolute right-2 top-8"
              icon={showPassword ? EyeIcon : EyeClosedIcon}
              onClick={() => setShowPassword((prev) => !prev)}
            />
          </div>
        </div>
        <div className="flex mb-6">
          <input
            type="checkbox"
            className="accent-[#8300E7] mr-2"
            defaultChecked
            required
          />
          <p className="text-sm">
            Я принимаю условия использования и политику конфиденциальности
          </p>
        </div>
        {error && <p className="text-red-500 text-sm mb-3">{error}</p>}
        <Button
          type="submit"
          className="bg-[#8300E7] hover:bg-[#8300E7] w-full rounded-xl mb-4"
        >
          Зарегистрироваться
        </Button>
        <p className="text-sm text-center">
          Есть аккаунт?{" "}
          <Link to="/signin">
            <span className="text-blue-700">Войдите</span>
          </Link>
        </p>
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
    <div className="flex justify-center mb-4">
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
