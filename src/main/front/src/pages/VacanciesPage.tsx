import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export function VacanciesPage() {
  return (
    <div className="px-20 mt-6">
      <Header />
      <Filter />
      {/* <div className="flex flex-col gap-4">
        <VacancyCard />
        <VacancyCard />
        <VacancyCard />
      </div> */}
    </div>
  );
}

// eslint-disable-next-line @typescript-eslint/no-unused-vars
function VacancyCard() {
  return <div className="h-40 bg-black rounded-3xl" />;
}

function Header() {
  return (
    <div className="flex justify-between items-center mb-6">
      <h1 className="text-xl">
        Work<span className="text-purple-900">Today</span>
      </h1>
      <div className="flex gap-3">
        <p>Вакансии</p>
        <p>Компании</p>
      </div>
      <div className="rounded-full w-12 h-12 bg-black" />
    </div>
  );
}

function Filter() {
  return (
    <div className="flex items-center mb-6">
      <div className="flex items-center gap-2 bg-black p-3 rounded-tl-md rounded-bl-md flex-grow">
        <div className="w-6 h-6 bg-white rounded-md" />
        <Input />
      </div>
      <div className="flex items-center gap-2 bg-black p-3 flex-grow">
        <div className="w-6 h-6 bg-white rounded-md" />
        <Input />
      </div>
      <div className="flex items-center gap-2 bg-black p-3 rounded-tr-md rounded-br-md flex-grow">
        <div className="w-6 h-6 bg-white rounded-md" />
        <Input />
      </div>
      <Button>Найти</Button>
    </div>
  );
}
