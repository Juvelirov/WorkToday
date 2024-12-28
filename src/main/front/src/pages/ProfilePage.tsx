import Header from "@/components/Header";
import I from "@/components/I";
import ProfileImage from "@/components/ProfileImage";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import WFile from "@/components/WFile";
import { s } from "@/types";
import { ScrollTextIcon } from "lucide-react";
import { useState } from "react";

export function InternProfilePage() {
  return <ProfileBase role="intern" />;
}

export function HrProfilePage() {
  return <ProfileBase role="hr" />;
}

interface ProfileBase {
  role: "intern" | "hr";
}

function ProfileBase(p: ProfileBase) {
  const [image, setImage] = useState<File | null>(null);

  const handleImageUpload = (file: File | null) => {
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64Image = reader.result as s;
        localStorage.setItem("imgUrl", base64Image);
        setImage(file);
      };
      reader.readAsDataURL(file);
    } else {
      setImage(null);
      localStorage.removeItem("imgUrl");
    }
  };

  const imageUrl = localStorage.getItem("imgUrl") ?? "";

  return (
    <div className="px-32 my-8">
      <Header />
      <div className="flex gap-16">
        <div className="flex flex-col items-center justify-center gap-12">
          <div className="flex gap-8">
            <ProfileImage
              image={image}
              setImage={handleImageUpload}
              imageUrl={imageUrl}
            />
            <div className="flex flex-col justify-between">
              <Input
                className="placeholder:text-gray-600 bg-[#F3DFFF] w-[230px]"
                defaultValue="Макаров Алексей Алексеевич"
                placeholder="Введите ваше ФИО"
              />
              <Input
                className="placeholder:text-gray-600 bg-[#F3DFFF]"
                defaultValue="alek@yandex.ru"
                placeholder="Введите вашу почту"
              />
              <Input
                className="placeholder:text-gray-600 bg-[#F3DFFF]"
                placeholder="Введите ваш номер"
              />
              <Input
                className="placeholder:text-gray-600 bg-[#F3DFFF]"
                defaultValue="Москва"
                placeholder="Введите ваш город"
              />
            </div>
          </div>
          <div className="flex gap-6">
            <Button className="bg-[#F3DFFF] text-black rounded-full">
              Редактировать профиль
            </Button>
            <Button className="bg-[#F3DFFF] text-red-500 rounded-full">
              Удалить профиль
            </Button>
          </div>
        </div>
        <div className="flex flex-col flex-grow">
          <div className="flex flex-col">
            <h2 className="font-bold text-xl mb-4">Файлы</h2>
            <div>
              {["Резюме", "Отчёт", "Портфолио"].map((t) => (
                <WFile key={t} title={t} />
              ))}
            </div>
          </div>
          {p.role === "hr" && (
            <div>
              <h2 className="font-bold text-xl mb-10">Ссылки</h2>
              <div className="flex justify-evenly gap-5">
                <div className="flex items-center gap-4 rounded-2xl p-4 bg-[#F3DFFF]">
                  <I icon={ScrollTextIcon} />
                  <p>Аналитика</p>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
