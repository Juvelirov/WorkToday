import type { s } from "@/types";
import { LucideIcon } from "lucide-react";

interface I {
  c?: s;
  icon: LucideIcon;
}

export default function I(p: I) {
  return <p.icon className={`p-1 w-6 h-6 rounded-full ${p.c}`} />;
}
