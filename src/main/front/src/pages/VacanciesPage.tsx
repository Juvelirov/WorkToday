import Header from "@/components/Header";
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
  return <div className="h-40 bg-[#F3DFFF] rounded-3xl" />;
}

function Filter() {
  return (
    <div className="flex items-center mb-6">
      <div className="flex items-center gap-2 bg-[#F3DFFF] p-3 rounded-tl-md rounded-bl-md flex-grow">
        <div className="w-6 h-6 bg-white rounded-md" />
        <Input />
      </div>
      <div className="flex items-center gap-2 bg-[#F3DFFF] p-3 flex-grow">
        <div className="w-6 h-6 bg-white rounded-md" />
        <Input />
      </div>
      <div className="flex items-center gap-2 bg-[#F3DFFF] p-3 rounded-tr-md rounded-br-md flex-grow">
        <div className="w-6 h-6 bg-white rounded-md" />
        <Input />
      </div>
      <Button className="bg-[#8300E7]">Найти</Button>
    </div>
  );
}
