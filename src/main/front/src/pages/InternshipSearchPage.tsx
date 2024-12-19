/* eslint-disable @typescript-eslint/no-unused-vars */
import type { InternshipInfoResponse } from "@/api/apiTypes";
import { fetchInternships } from "@/api/internAPI";
import Bbls from "@/components/Bbls";
import Header from "@/components/Header";
import I from "@/components/I";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { fakeInternshipD } from "@/lib/utils";
import type { b, P, s, v } from "@/types";
import { MapPinIcon, SearchIcon } from "lucide-react";
import { useState } from "react";
import { Link } from "react-router-dom";

export function InternshipSearchPage() {
  const [internships, setInternships] = useState<InternshipInfoResponse[]>();
  const [loading, setLoading] = useState<b>(false);
  const [error, setError] = useState<s | null>(null);

  const loadInternships = async () => {
    setLoading(true);
    setError(null);
    try {
      const internshipsD = await fetchInternships();
      setInternships(internshipsD);
    } catch (err) {
      setError(
        err instanceof Error ? err.message : "Failed to fetch internships."
      );
    } finally {
      setLoading(false);
    }
  };
  return (
    <div className="px-32 my-8">
      <Header />
      <Filter loadInternships={loadInternships} />
      <div className="flex flex-col gap-7">
        {/* {fakeInternshipD.map((i) => (
          <InternshipCard key={i.id} data={{ ...i }} />
        ))} */}
        {internships?.map((i) => (
          <InternshipCard key={i.id} data={{ ...i }} />
        ))}
      </div>
      {loading && <div>Loading...</div>}
      {error && <div className="text-red-500">Error: {error}</div>}
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

interface Filter {
  loadInternships: () => P<v>;
}

function Filter(p: Filter) {
  return (
    <div className="flex items-center gap-5 mb-10 p-4 bg-[#F3DFFF] rounded-2xl">
      <div className="flex items-center gap-2 flex-grow">
        <I icon={SearchIcon} />
        <Input placeholder="Должность или ключевые слова" />
      </div>
      <div className="flex items-center gap-2 flex-grow">
        <I icon={MapPinIcon} />
        <Input placeholder="Город" />
      </div>
      <Button className="bg-[#8300E7]" onClick={p.loadInternships}>
        Найти
      </Button>
    </div>
  );
}
