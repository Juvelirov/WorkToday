import FileBlock from "@/components/FileBlock";
import Header from "@/components/Header";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { cn } from "@/lib/utils";
import { s } from "@/types";
import { useState } from "react";

export function InternProfilePage() {
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
            <div>
              {["Резюме", "Отчёт", "Портфолио"].map((t) => (
                <File key={t} title={t} />
              ))}
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

interface FileProps {
  title: s;
}

function File(p: FileProps) {
  const [file, setFile] = useState<File | null>(null);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0];
    if (selectedFile) setFile(selectedFile);
  };

  return (
    <div className={cn("flex flex-col mb-3", file && "mb-5")}>
      <div className={cn("flex justify-between items-center", file && "mb-3")}>
        <h3 className="font-bold text-md">{p.title}</h3>
        {!file && (
          <label
            htmlFor={`file-upload-${p.title}`}
            className="flex justify-center items-center w-9 cursor-pointer bg-[#F3DFFF] rounded-lg "
          >
            <span className="text-2xl pb-1">+</span>
          </label>
        )}
      </div>
      {file ? (
        <FileBlock name={file.name} setFile={setFile} />
      ) : (
        <input
          type="file"
          id={`file-upload-${p.title}`}
          className="hidden"
          onChange={handleFileChange}
          accept=".docx,.pdf,.jpg,.png,.txt"
        />
      )}
    </div>
  );
}
