import type { s } from "@/types";

interface I {
  name: s;
  c?: s;
}

export default function I(p: I) {
  return (
    <span className={`material-icons p-1 rounded-full ${p.c}`}>{p.name}</span>
  );
}
