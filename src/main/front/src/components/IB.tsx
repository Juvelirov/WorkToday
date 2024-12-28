import type { Fv, s } from "@/types";
import { LucideIcon } from "lucide-react";
import I from "./I";

interface IB {
  icon: LucideIcon;
  iC?: s;
  bC?: s;
  onClick?: Fv;
}

export default function IB(p: IB) {
  return (
    <button
      type="button"
      className={`inline-flex justify-center items-center rounded-full w-8 h-8 hover:bg-[#F3DFFF] ${p.bC}`}
      onClick={() => {
        if (p.onClick) p.onClick();
      }}
    >
      <I icon={p.icon} c={p.iC} />
    </button>
  );
}
