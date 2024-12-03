import { OneClickSign, Sep } from "./SignInForm";
import { Button } from "./ui/button";
import { Input } from "./ui/input";

export function SignUpForm() {
  return (
    <div className="flex flex-col gap-10 w-[446px]">
      <div className="flex flex-col justify-between items-center w-full h-[610px] bg-gray-500 rounded-[50px] p-[40px]">
        <div className="flex flex-col gap-7 w-full">
          <p className="text-xl text-center">
            Зарегистрироваться как практикант
          </p>
          <Input placeholder="ФИО" />
          <Input type="email" placeholder="Логин" />
          <Input type="password" placeholder="Пароль" />
          <div className="flex items-center">
            <input type="checkbox" className="mr-2" />
            <p className="text-sm">
              Я принимаю условия использования и политику конфиденциальности
            </p>
          </div>
          <Button className="bg-purple-500 w-full rounded-xl mt-4">
            Зарегистрироваться
          </Button>
        </div>
        <Sep />
        <OneClickSign />
        <div className="mt-4">
          <p>
            <a href="f">Зарегистрироваться как работодатель</a>
          </p>
        </div>
      </div>
    </div>
  );
}
