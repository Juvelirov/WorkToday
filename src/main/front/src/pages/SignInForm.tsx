/* eslint-disable @typescript-eslint/no-unused-vars */
import { apiClient } from "@/api/apiClient";
import { saveBasicInfo, setToken } from "@/auth";
import { fakeAdminD, fakeHrD, fakeInternD } from "@/lib/utils";
import type { s } from "@/types";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";
import { EyeClosedIcon, EyeIcon } from "lucide-react";
import IB from "@/components/IB";
import { endpoints } from "@/api/endpoints";

export function SignInForm() {
  const [formData, setFormData] = useState({
    email: fakeHrD.email,
    password: fakeHrD.password,
  });
  const [error, setError] = useState<s | null>(null);
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    const res = await apiClient<{ token: s; role: s[] }>(
      endpoints.public.login,
      {
        method: "POST",
        body: JSON.stringify(formData),
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    saveBasicInfo(res.token, res.role[0]);

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
    }
  };

  return (
    <div className="flex justify-center items-center h-screen">
      <form
        onSubmit={handleSubmit}
        className="flex flex-col w-[400px] bg-[#E4C1FF] p-8 rounded-3xl"
      >
        <h2 className="font-bold text-xl text-center mb-6">Вход в аккаунт</h2>
        <div className="flex flex-col gap-4 mb-8">
          <div>
            <p className="font-bold text-sm mb-2">Почта</p>
            <Input
              type="email"
              placeholder="Введите вашу почту"
              name="email"
              value={formData.email}
              onChange={handleChange}
            />
          </div>
          <div className="relative">
            <p className="font-bold text-sm mb-2">Пароль</p>
            <Input
              type={showPassword ? "text" : "password"}
              placeholder="Введите ваш пароль"
              name="password"
              value={formData.password}
              onChange={handleChange}
              className="pr-10"
            />
            <IB
              bC="absolute right-2 top-8"
              icon={showPassword ? EyeIcon : EyeClosedIcon}
              onClick={() => setShowPassword((prev) => !prev)}
            />
          </div>
        </div>

        {error && <p className="text-red-500 text-sm">{error}</p>}

        <Button
          type="submit"
          className="bg-[#8300E7] hover:bg-[#8300E7] w-full rounded-xl mb-4"
        >
          Войти
        </Button>

        <p className="text-sm text-center">
          Нет аккаунта?{" "}
          <Link to="/signup">
            <span className="text-blue-700">Зарегистрируйтесь</span>
          </Link>
        </p>
      </form>
    </div>
  );
}
