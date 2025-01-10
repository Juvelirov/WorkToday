import { useState } from "react";
import Header from "@/components/Header";
import { Button } from "@/components/ui/button";
import { InternshipInfoDTO } from "@/api/apiTypes";
import { TrashIcon, SaveIcon } from "lucide-react";
import {
  createInternship,
  deleteInternship,
  updateInternship,
} from "@/api/hrAPI";
import { s } from "@/types";
import { Input } from "@/components/ui/input";

export default function InternshipCreationPage() {
  const initialState: InternshipInfoDTO = {
    title: "huh",
    company: "huh",
    duties: "",
    requirements: "",
    task: "",
    town: "huh",
    tags: [],
  };

  const state: InternshipInfoDTO = {
    title: "Стажировка в отделе разработки веб-приложений",
    company: "ТехноВижн",
    duties:
      "Анализ требований, проектирование решений, разработку кода, тестирование и исправление ошибок.",
    requirements: "Умение работать в команде и эффективно решать проблемы.",
    task: "Создать приложение на Java.",
    tags: ["JavaScript", "React", "CSS"],
    town: "Москва",
  };

  const data = localStorage.getItem("internshipData");
  const parsed = data ? JSON.parse(data) : null;
  const [saved, setSaved] = useState<InternshipInfoDTO | null>(parsed);
  const [internship, setInternship] = useState<InternshipInfoDTO>(
    state ?? parsed ?? initialState
  );
  const [tags, setTags] = useState<s>(internship.tags.join(", "));

  const handleInputChange = (
    field: keyof InternshipInfoDTO,
    value: s | s[]
  ) => {
    console.log(value);
    setInternship((prev) => ({ ...prev, [field]: value }));
  };

  const handleCreate = async () => {
    try {
      const res = await createInternship(internship);
      localStorage.setItem("internshipId", String(res.ID));
      localStorage.setItem("internshipData", JSON.stringify(internship));
      setSaved(internship);
    } catch (error) {
      console.error("Failed to create internship:", error);
    }
  };

  const handleUpdate = async () => {
    const id = localStorage.getItem("internshipId");
    if (!id) throw new Error("No internship's id found");
    try {
      await updateInternship(+id, internship);
      console.log("Updated internship:", internship);
      localStorage.setItem("internshipData", JSON.stringify(internship));
      setSaved(internship);
    } catch (e) {
      console.error("Failed to update internship:", e);
    }
  };

  const handleDelete = async () => {
    try {
      const id = localStorage.getItem("internshipId");
      if (!id) throw new Error("No internship's id found");
      await deleteInternship(+id);
      localStorage.removeItem("internshipId");
      localStorage.removeItem("internshipData");
      setSaved(null);
      setTags("");
      setInternship(initialState);
    } catch (error) {
      console.error("Failed to delete internship:", error);
    }
  };

  const isFilled =
    internship.duties.trim() !== "" &&
    internship.requirements.trim() !== "" &&
    internship.task.trim() !== "" &&
    tags.trim() !== "";

  const isChanged =
    isFilled && JSON.stringify(internship) !== JSON.stringify(saved);

  return (
    <div className="px-32 my-8">
      <Header />
      <div className="mb-6">
        <h2 className="font-bold text-2xl mb-2">Обязанности</h2>
        <Input
          type="text"
          className="border rounded w-full p-2"
          value={internship.duties}
          onChange={(e) => handleInputChange("duties", e.target.value)}
          placeholder="Введите обязанности"
        />
      </div>

      <div className="mb-6">
        <h2 className="font-bold text-2xl mb-2">Требования</h2>
        <Input
          type="text"
          className="border rounded w-full p-2"
          value={internship.requirements}
          onChange={(e) => handleInputChange("requirements", e.target.value)}
          placeholder="Введите требования"
        />
      </div>

      <div className="mb-6">
        <h2 className="font-bold text-2xl mb-2">Задание</h2>
        <Input
          type="text"
          className="border rounded w-full p-2"
          value={internship.task}
          onChange={(e) => handleInputChange("task", e.target.value)}
          placeholder="Введите задание"
        />
      </div>
      <div className="mb-6">
        <h2 className="font-bold text-2xl mb-2">Теги</h2>
        <Input
          type="text"
          className="border rounded w-full p-2"
          value={tags}
          onChange={(e) => {
            setTags(e.target.value);
            handleInputChange("tags", e.target.value.split(","));
          }}
          placeholder="Введите теги через запятую"
        />
      </div>

      <div className="flex gap-4 mt-4">
        {isChanged &&
          (localStorage.getItem("internshipId") ? (
            <Button
              onClick={handleUpdate}
              className="flex items-center gap-2 bg-green-500 text-white px-4 py-2 rounded"
            >
              Обновить
            </Button>
          ) : (
            <Button
              onClick={handleCreate}
              className="flex items-center gap-2 bg-green-500 text-white px-4 py-2 rounded"
            >
              <SaveIcon className="w-4 h-4" />
              Сохранить
            </Button>
          ))}

        {isFilled && (
          <Button
            onClick={handleDelete}
            className="flex items-center gap-2 bg-red-500 text-white px-4 py-2 rounded"
          >
            <TrashIcon className="w-4 h-4" />
            Удалить
          </Button>
        )}
      </div>
    </div>
  );
}

//   setInternship({
//     title: "Стажировка в отделе разработки веб-приложений",
//     company: "ТехноВижн",
//     duties:
//       "Анализ требований, проектирование решений, разработку кода, тестирование и исправление ошибок.",
//     requirements: "Умение работать в команде и эффективно решать проблемы.",
//     task: "Создать приложение на Java.",
//     tags: ["JavaScript", "React", "Node.js", "HTML", "CSS"],
//     town: "Москва",
//   });
