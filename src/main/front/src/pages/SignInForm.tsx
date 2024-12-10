import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";
import { apiClient } from "@/api/apiClient";
import type { s } from "@/types";
import { fakeInternD } from "./SignUpForm";

export function SignInForm() {
  const [formData, setFormData] = useState({
    email: fakeInternD.email,
    password: fakeInternD.password,
  });
  const [error, setError] = useState<s | null>(null);
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    const response = await apiClient<{ token: s }>("/public/login", {
      method: "POST",
      body: JSON.stringify(formData),
    });

    localStorage.setItem("token", response.token);

    navigate("/");
  };

  return (
    <div className="flex justify-center items-center h-screen">
      <form
        onSubmit={handleSubmit}
        className="flex flex-col w-[400px] bg-[#E4C1FF] p-8 rounded-3xl"
      >
        <div className="flex flex-col gap-6 mb-8">
          <Input
            type="email"
            placeholder="Почта"
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

        {error && <p className="text-red-500 text-sm">{error}</p>}

        <Button type="submit" className="bg-[#8300E7] w-full rounded-xl mb-8">
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
