import { OneClickSign, Sep } from "./SignInForm";
import { Button } from "./ui/button";
import { Input } from "./ui/input";

export function SignUpForm() {
  return (
    <div className="flex flex-col justify-center items-center h-screen">
      <div className="flex flex-col gap-10 w-[446px]">
        <div className="flex flex-col justify-between items-center w-full h-[526px] bg-gray-500 rounded-[50px] p-[40px]">
          <div className="flex flex-col gap-7 w-full">
            <p className="text-xl text-center">
              Зарегистрироваться как практикант
            </p>
            <Input placeholder="ФИО" />
            <Input type="email" placeholder="Логин" />
            <Input type="password" placeholder="Пароль" />
            <Button className="bg-purple-500 w-full rounded-xl mt-4">
              Зарегистрироваться
            </Button>
          </div>
          <Sep />
          <OneClickSign />
        </div>
        <div className="flex justify-center items-center rounded-full bg-gray-500 w-full h-12">
          <p>Зарегистрироваться как работодатель</p>
        </div>
      </div>
    </div>
  );
}
