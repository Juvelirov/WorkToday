import File from "@/components/File";
import Header from "@/components/Header";
import IB from "@/components/IB";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export function StudentPrivatePage() {
  return (
    <div className="px-32 my-8">
      <Header />
      <div className="grid grid-cols-2 gap-12">
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
            <Button className="bg-[#F3DFFF] text-red-500 rounded-full">
              Удалить профиль
            </Button>
          </div>
        </div>
        <div className="flex flex-col">
          <div className="flex flex-col">
            <h2 className="font-bold text-xl mb-4">Файлы</h2>
            <div className="flex flex-col gap-8">
              <Resume />
              <Report />
              <Portfolio />
            </div>
          </div>
          {/* <div>
          <h2 className="font-bold text-xl mb-10">Ссылки</h2>
          <div className="flex justify-evenly gap-5">
            <div className="flex items-center gap-4 rounded-2xl p-4 bg-[#F3DFFF]">
              <I name="work" />
              <p>Стажировки</p>
            </div>
            <div className="flex items-center gap-4 rounded-2xl p-4 bg-[#F3DFFF]">
              <I name="info_outlined" />
              <p>Материалы</p>
            </div>
            <div className="flex items-center gap-4 rounded-2xl p-4 bg-[#F3DFFF]">
              <I name="task_alt" />
              <p>Задания</p>
            </div>
          </div>
        </div> */}
        </div>
      </div>
    </div>
  );
}

function Resume() {
  return (
    <div className="flex flex-col">
      <div className="flex justify-between items-center mb-3">
        <h3 className="font-bold text-md">Резюме</h3>
        <IB nameOutlined="add" iC="bg-[#F3DFFF]" />
      </div>
      <File name="resume.docx" />
    </div>
  );
}

function Report() {
  return (
    <div className="flex flex-col">
      <div className="flex justify-between items-center mb-3">
        <h3 className="font-bold text-md">Отчёт</h3>
        <IB nameOutlined="add" iC="bg-[#F3DFFF]" />
      </div>
      <File name="report.docx" />
    </div>
  );
}

function Portfolio() {
  return (
    <div className="flex flex-col">
      <div className="flex justify-between items-center mb-3">
        <h3 className="font-bold text-md">Портфолио</h3>
        <IB nameOutlined="add" iC="bg-[#F3DFFF]" />
      </div>
      <File name="report.docx" />
    </div>
  );
}
