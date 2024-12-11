import type { InternshipInfoResponse } from "@/api/apiTypes";
import Header from "@/components/Header";
import I from "@/components/I";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { fakeInternshipD } from "@/lib/utils";

export function InternshipPage() {
  return (
    <div className="px-32 mt-8">
      <Header />
      <Filter />
      <div className="flex flex-col gap-4">
        {fakeInternshipD.map((i) => (
          <InternshipCard
            key={i.id}
            id={i.id}
            title={i.title}
            description={i.description}
            fields={i.fields}
            tags={i.tags}
            creator={i.creator}
            city={i.city}
          />
        ))}
      </div>
    </div>
  );
}

interface InternshipCard {
  data: InternshipInfoResponse;
}

function InternshipCard(p: InternshipInfoResponse) {
  return (
    <div className="bg-[#F3DFFF] rounded-3xl p-6 transform transition hover:scale-105">
      <div className="flex justify-between items-center mb-3">
        <h3 className="font-bold">{p.title}</h3>
        <p className="text-sm">{p.city}</p>
      </div>
      <p className="w-2/3">{p.description}</p>
    </div>
  );
}

function Filter() {
  return (
    <div className="flex items-center gap-5 mb-6">
      <div className="flex flex-grow">
        <div className="flex items-center gap-2 bg-[#F3DFFF] p-4 rounded-tl-2xl rounded-bl-2xl flex-grow">
          <I name="search" />
          <Input placeholder="Должность или ключевые слова" />
        </div>
        <div className="flex items-center gap-2 bg-[#F3DFFF] p-4 rounded-tr-2xl rounded-br-2xl flex-grow">
          <I name="location_on" />
          <Input placeholder="Город" />
        </div>
      </div>
      <Button className="bg-[#8300E7]">Найти</Button>
    </div>
  );
}
