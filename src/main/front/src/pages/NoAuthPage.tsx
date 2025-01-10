import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";

export default function NoAuthPage() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen">
      <div className="bg-[#E4C1FF] p-8 rounded-3xl shadow-lg text-center max-w-md">
        <h1 className="text-3xl font-bold mb-4">Вы не авторизованы</h1>
        <p className="text-md mb-8">
          Чтобы получить доступ к системе, войдите в свою учетную запись или
          зарегистрируйтесь.
        </p>
        <div className="flex justify-center gap-4">
          <Link to="/signin">
            <Button className="bg-[#8300E7] hover:bg-[#6a00b9] text-white rounded-lg shadow-md">
              Войти
            </Button>
          </Link>
          <Link to="/signup">
            <Button className="bg-[#8300E7] hover:bg-[#6a00b9] text-white rounded-lg shadow-md">
              Зарегистрироваться
            </Button>
          </Link>
        </div>
      </div>
    </div>
  );
}
