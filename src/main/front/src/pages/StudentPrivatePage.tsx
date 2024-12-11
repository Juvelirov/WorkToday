import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Link } from "react-router-dom";

export function StudentPrivatePage() {
  return (
    <div className="flex gap-10 items-center justify-center h-screen">
      <div className="flex flex-col items-center justify-center gap-12">
        <div className="flex gap-5">
          <div className="w-60 h-60 rounded-3xl bg-[#F3DFFF]" />
          <div className="flex flex-col justify-between">
            <Input
              className="placeholder:text-black bg-[#F3DFFF]"
              placeholder="Введите ваше ФИО"
            />
            <Input
              className="placeholder:text-black bg-[#F3DFFF]"
              color="pink"
              placeholder="Введите вашу почту"
            />
            <Input
              className="placeholder:text-black bg-[#F3DFFF]"
              color="pink"
              placeholder="Введите ваш номер"
            />
            <Input
              className="placeholder:text-black bg-[#F3DFFF]"
              color="pink"
              placeholder="Введите ваш город"
            />
          </div>
        </div>
        <div className="flex gap-6">
          <Button className="bg-[#F3DFFF] text-black rounded-full">
            Редактировать профиль
          </Button>
          <Button className="bg-[#F3DFFF] text-black rounded-full">
            Удалить профиль
          </Button>
        </div>
      </div>
      <div className="grid grid-cols-2 gap-5">
        <Link to="/internships" replace>
          <div className="w-60 h-40 p-4 rounded-2xl bg-[#F3DFFF] transform transition hover:scale-105">
            <span>Стажировки</span>
          </div>
        </Link>
        <div className="w-60 h-40 p-4 rounded-2xl bg-[#F3DFFF] transform transition hover:scale-105">
          <p>Чат с работодателем</p>
        </div>
        <Link to="/base" replace>
          <div className="w-60 h-40 p-4 rounded-2xl bg-[#F3DFFF] transform transition hover:scale-105">
            <p>Материалы</p>
          </div>
        </Link>
        <div className="w-60 h-40 p-4 rounded-2xl bg-[#F3DFFF] transform transition hover:scale-105">
          <p>Задания</p>
        </div>
      </div>
    </div>
  );
}
