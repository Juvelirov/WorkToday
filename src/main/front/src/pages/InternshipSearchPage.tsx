import type { InternshipInfoResponse } from "@/api/apiTypes";
import { fetchInternships, searchInternships } from "@/api/internAPI";
import Bbls from "@/components/Bbls";
import Header from "@/components/Header";
import I from "@/components/I";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import type { b, P, s, v } from "@/types";
import { MapPinIcon, SearchIcon } from "lucide-react";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export function InternshipSearchPage() {
  const [internships, setInternships] = useState<InternshipInfoResponse[]>();
  const [loading, setLoading] = useState<b>(false);
  const [error, setError] = useState<s | null>(null);

  useEffect(() => {
    loadAllInternships();
  }, []);

  const loadAllInternships = async () => {
    setLoading(true);
    setError(null);
    try {
      const internshipsD = await fetchInternships();
      console.log(internshipsD);
      setInternships(internshipsD);
    } catch (err) {
      setError(
        err instanceof Error ? err.message : "Failed to fetch internships."
      );
    } finally {
      setLoading(false);
    }
  };

  const searchInternshipData = async (query: s, city: s) => {
    setLoading(true);
    setError(null);
    try {
      const internshipsD = await searchInternships(`${query} ${city}`);
      setInternships(internshipsD);
    } catch (err) {
      setError(
        err instanceof Error ? err.message : "Failed to search internships."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="px-32 my-8">
      <Header />
      <Filter onSearch={searchInternshipData} />
      <div className="flex flex-col gap-7">
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

function InternshipCard({ data }: InternshipCard) {
  return (
    <Link to={`/internship/${data.id}`}>
      <div className="relative bg-[#F3DFFF] rounded-3xl p-6 transform transition hover:scale-105">
        <h3 className="font-bold mb-3">{data.title}</h3>
        <p className="w-2/3">{data.requirements}</p>
        <Bbls city={data.town} company={data.company} />
      </div>
    </Link>
  );
}

interface Filter {
  onSearch: (query: s, city: s) => P<v>;
}

function Filter(p: Filter) {
  const [query, setQuery] = useState<s>("");
  const [city, setCity] = useState<s>("");

  const handleSearch = () => {
    p.onSearch(query.trim(), city.trim());
  };

  return (
    <div className="flex items-center gap-5 mb-10 p-4 bg-[#F3DFFF] rounded-2xl">
      <div className="flex items-center gap-2 flex-grow">
        <I icon={SearchIcon} />
        <Input
          placeholder="Должность или ключевые слова"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
      </div>
      <div className="flex items-center gap-2 flex-grow">
        <I icon={MapPinIcon} />
        <Input
          placeholder="Город"
          value={city}
          onChange={(e) => setCity(e.target.value)}
        />
      </div>
      <Button
        className="bg-[#8300E7] hover:bg-[#8300E7]"
        onClick={handleSearch}
      >
        Найти
      </Button>
    </div>
  );
}
