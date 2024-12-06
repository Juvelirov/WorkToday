import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";

export function SignInForm() {
  return (
    <div className="flex justify-center items-center h-screen">
      <form className="flex flex-col w-[446px] bg-gray-500 p-8 rounded-3xl">
        <div className="flex flex-col gap-6 mb-8">
          <Input type="email" placeholder="Логин" />
          <Input type="password" placeholder="Пароль" />
        </div>
        <Button type="submit" className="bg-purple-500 w-full rounded-xl mb-8">
          Войти
        </Button>
        <p className="text-sm text-center">
          Нет аккаунта?{" "}
          <a href="f" className="text-blue-500">
            Зарегистрируйтесь
          </a>
        </p>
      </form>
    </div>
  );
}
