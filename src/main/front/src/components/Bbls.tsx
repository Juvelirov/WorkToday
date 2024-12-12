import type { s } from "@/types";

interface Bbls {
  city: s;
  company: s;
}

export default function Bbls(p: Bbls) {
  return (
    <div className="flex gap-4 absolute -top-4 right-4">
      <Bbl label={p.city} />
      <Bbl label={p.company} />
    </div>
  );
}

interface Bbl {
  label: s;
  c?: s;
}

function Bbl(p: Bbl) {
  return (
    <div className={`inline-block bg-[#E4C1FF] px-4 py-2 rounded-full ${p.c}`}>
      <p className="text-sm text-gray-800">{p.label}</p>
    </div>
  );
}
