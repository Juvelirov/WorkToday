import { Separator } from "@radix-ui/react-separator";
import { Button } from "./ui/button";
import { Input } from "./ui/input";

export function SignInForm() {
  return (
    <div className="flex justify-center items-center h-screen">
      <div className="flex flex-col items-center w-[446px] h-[526px] bg-gray-500 rounded-[50px] p-[60px]">
        <div className="flex flex-col gap-7 w-full">
          <Input type="email" placeholder="Логин" />
          <Input type="password" placeholder="Пароль" />
          <Button className="bg-purple-500 w-full rounded-xl">Войти</Button>
        </div>
        <Sep />
        <OneClickSign />
      </div>
    </div>
  );
}

export function OneClickSign() {
  return (
    <div className="flex items-center w-full gap-2 bg-white rounded-full p-2">
      <div className="rounded-full bg-gray-500 w-6 h-6" />
      <p>Через ВКонтакте</p>
    </div>
  );
}

export function Sep() {
  return (
    <div className="relative w-full my-10">
      <Separator
        orientation="horizontal"
        className="w-full h-1 bg-gray-300 rounded-full"
      />
      <div className="absolute left-1/2 top-1/2 transform -translate-x-1/2 -translate-y-1/2">
        <div className="flex items-center justify-center bg-gray-500 w-10 h-6 rounded-md">
          <p>Или</p>
        </div>
      </div>
    </div>
  );
}
