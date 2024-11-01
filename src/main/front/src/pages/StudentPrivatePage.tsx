import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export function StudentPrivatePage() {
  return (
    <div className="flex gap-10 items-center justify-center h-screen">
      <div className="flex flex-col items-center justify-center gap-12">
        <div className="flex gap-5">
          <div className="w-60 h-60 rounded-3xl bg-gray-500" />
          <div className="flex flex-col justify-between">
            <Input
              className="placeholder:text-white bg-gray-500"
              placeholder="Введите ваше ФИО"
            />
            <Input
              className="placeholder:text-white bg-gray-500"
              color="pink"
              placeholder="Введите вашу почту"
            />
            <Input
              className="placeholder:text-white bg-gray-500"
              color="pink"
              placeholder="Введите ваш номер"
            />
            <Input
              className="placeholder:text-white bg-gray-500"
              color="pink"
              placeholder="Введите ваш город"
            />
          </div>
        </div>
        <div className="flex gap-6">
          <Button className="bg-gray-500 rounded-full">
            Редактировать профиль
          </Button>
          <Button className="bg-gray-500 rounded-full">Удалить профиль</Button>
        </div>
      </div>
      <div className="grid grid-cols-2 gap-5">
        <div className="w-60 h-40 p-4 rounded-2xl bg-gray-500">
          <p className="text-white">Стажировки</p>
        </div>
        <div className="w-60 h-40 p-4 rounded-2xl bg-gray-500">
          <p className="text-white">Чат с работодателем</p>
        </div>
        <div className="w-60 h-40 p-4 rounded-2xl bg-gray-500">
          <p className="text-white">Материалы</p>
        </div>
        <div className="w-60 h-40 p-4 rounded-2xl bg-gray-500">
          <p className="text-white">Задания</p>
        </div>
      </div>
    </div>
  );
}
