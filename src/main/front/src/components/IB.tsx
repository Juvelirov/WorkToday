import type { s, v } from "@/types";
import { useState } from "react";
import I from "./I";

interface IB {
  nameOutlined: s;
  nameFilled?: s;
  iC?: s;
  bC?: s;
  onClick?: () => v;
}

export default function IB(p: IB) {
  const [isFilled, setIsFilled] = useState(false);

  return (
    <button
      type="button"
      className={`inline-flex justify-center items-center rounded-full hover:bg-[#F3DFFF] ${p.bC}`}
      onClick={() => {
        setIsFilled(!isFilled);
        if (p.onClick) p.onClick();
      }}
    >
      <I
        c={p.iC}
        name={isFilled && !!p.nameFilled ? p.nameFilled : p.nameOutlined}
      />
    </button>
  );
}
