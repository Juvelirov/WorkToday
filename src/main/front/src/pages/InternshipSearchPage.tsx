import type { InternshipInfoResponse } from "@/api/apiTypes";
import Bbls from "@/components/Bbls";
import Header from "@/components/Header";
import I from "@/components/I";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { fakeInternshipD } from "@/lib/utils";
import { Link } from "react-router-dom";

export function InternshipSearchPage() {
  return (
    <div className="px-32 mt-8 mb-8">
      <Header />
      <Filter />
      <div className="flex flex-col gap-7">
        {fakeInternshipD.map((i) => (
          <InternshipCard key={i.id} data={{ ...i }} />
        ))}
      </div>
    </div>
  );
}

interface InternshipCard {
  data: InternshipInfoResponse;
}

function InternshipCard(p: InternshipCard) {
  return (
    <Link to="/internship" replace>
      <div className="relative bg-[#F3DFFF] rounded-3xl p-6 transform transition hover:scale-105">
        {/* <div className="flex justify-between items-center mb-3"> */}
        <h3 className="font-bold mb-3">{p.data.title}</h3>
        {/* <p className="text-sm">{p.data.city}</p> */}
        {/* </div> */}
        {/* <div className="flex justify-between items-end"> */}
        <p className="w-2/3">{p.data.description}</p>
        {/* <p className="text-sm">{p.data.creator.company}</p> */}
        {/* </div> */}
        <Bbls city={p.data.city} company={p.data.creator.company} />
      </div>
    </Link>
  );
}

function Filter() {
  return (
    <div className="flex items-center gap-5 mb-10 p-4 bg-[#F3DFFF] rounded-2xl">
      <div className="flex items-center gap-2 flex-grow">
        <I name="search" />
        <Input placeholder="Должность или ключевые слова" />
      </div>
      <div className="flex items-center gap-2 flex-grow">
        <I name="location_on" />
        <Input placeholder="Город" />
      </div>
      <Button className="bg-[#8300E7]">Найти</Button>
    </div>
  );
}
